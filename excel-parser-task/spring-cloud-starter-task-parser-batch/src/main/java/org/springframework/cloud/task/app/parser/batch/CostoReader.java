/*
 *  
 * The MIT License (MIT)
 * Copyright (c) 2018 Roberto Villarejo Martínez
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package org.springframework.cloud.task.app.parser.batch;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;

import mx.infotec.dads.costos.domain.Costo;
import mx.infotec.dads.costos.domain.DataFrame;
import mx.infotec.dads.costos.domain.Error;
import mx.infotec.dads.costos.domain.Origin;
import mx.infotec.dads.costos.repository.DataFrameRepository;

/**
 * 
 * @author Roberto Villarejo Martínez
 *
 */
public class CostoReader implements ItemReader<Costo>, StepExecutionListener {

    private final Logger logger = LoggerFactory.getLogger(CostoReader.class);

    @Autowired
    private DataFrameRepository repository;

    private XSSFWorkbook workbook;

    private Iterator<Row> rowIt;

    private DataFrame dataFrame;

    private ExcelRowParser<Costo> parser;

    @Autowired
    private CostoParserRegistry registry;

    @Override
    public void beforeStep(StepExecution stepExecution) {
        // El registro más antiguo que no ha sido marcado como procesado
        logger.info("Querying for not processed DataFrame...");
        List<DataFrame> list = repository.findByProcessedFalse(PageRequest.of(0, 1)).getContent();
        try {
            if (!list.isEmpty()) {
                dataFrame = list.get(0);

                logger.info("Reading dataFrame with Id: {} ", dataFrame.getId());

                workbook = new XSSFWorkbook(new ByteArrayInputStream(dataFrame.getFile()));
                rowIt = workbook.getSheetAt(0).iterator();

                Row headersRow = rowIt.next();

                String suggestedParser = registry.detect(headersRow);
                Optional<ExcelRowParser<Costo>> maybeParser = registry.lookup(suggestedParser);

                if (maybeParser.isPresent()) {
                    parser = maybeParser.get();
                } else {
                    dataFrame.addError(new Error("No parser found for this source",
                            "No adequate parser was found for this data frame"));
                }
            }
        } catch (IOException e) {
            logger.error("Failed while reading file with id: {} with error: {}", dataFrame.getId(), e.getStackTrace());
        }

    }

    @Override
    public Costo read() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {
        if (parser == null)
            return null;
        if (rowIt != null && rowIt.hasNext()) {
            Row row = rowIt.next();
            Costo costo;
            try {
                costo = parser.parse(row);
                costo.setOrigin(new Origin(dataFrame.getId(), dataFrame.getFileName(), row.getRowNum()));
            } catch (IllegalArgumentException ex) {
                dataFrame.addError(new Error("parseFailure", "Fail to parse row number: " + row.getRowNum()));
                return read();
            }
            return costo;
        }
        return null;
    }

    @Override
    public ExitStatus afterStep(StepExecution stepExecution) {
        try {
            if (workbook != null) {
                workbook.close();
            }
            if (dataFrame != null) {
                dataFrame.setProcessed(true);
                repository.save(dataFrame);
            }
        } catch (IOException e) {
            logger.error("Failed to close workbook for excel file: {}", dataFrame.getId());
        }
        return ExitStatus.COMPLETED;
    }

}
