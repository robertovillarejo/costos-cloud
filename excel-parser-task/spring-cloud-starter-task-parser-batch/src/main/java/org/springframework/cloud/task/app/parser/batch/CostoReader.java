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

import com.fasterxml.jackson.databind.ObjectMapper;

import mx.infotec.dads.costos.domain.Costo;
import mx.infotec.dads.costos.domain.ExcelFile;
import mx.infotec.dads.costos.repository.ExcelFileRepository;

import static org.springframework.cloud.task.app.parser.batch.ExcelRowParser.getMappingSchema;
import static org.springframework.cloud.task.app.parser.batch.ExcelRowParser.parseMappingSchema;

/**
 * 
 * @author Roberto Villarejo Martínez
 *
 */
public class CostoReader implements ItemReader<Costo>, StepExecutionListener {

    private final Logger logger = LoggerFactory.getLogger(CostoReader.class);

    @Autowired
    private ExcelFileRepository repository;

    private XSSFWorkbook workbook;

    private Iterator<Row> rowIt;

    private ExcelFile file;

    private ObjectMapper mapper;

    private ExcelRowParser parser;

    public CostoReader() {
        mapper = new ObjectMapper();
    }

    @Override
    public void beforeStep(StepExecution stepExecution) {
        // El registro más antiguo que no ha sido marcado como procesado
        logger.info("Querying for not processed ExcelFile...");
        List<ExcelFile> list = repository.findByProcessedFalse(PageRequest.of(0, 1)).getContent();
        try {
            if (!list.isEmpty()) {
                file = list.get(0);
                logger.info("Reading file with Id: {} ", file.getId());
                workbook = new XSSFWorkbook(new ByteArrayInputStream(file.getFile()));
                rowIt = workbook.getSheetAt(0).iterator();
                parser = new ExcelRowParser(getMappingSchema(rowIt.next(),
                        parseMappingSchema("Importe,monto:Área,area:Beneficiario,proveedor")));
            }
        } catch (IOException e) {
            logger.error("Failed while reading file with id: {} with error: {}", file.getId(), e.getStackTrace());
        }

    }

    @Override
    public Costo read() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {
        if (rowIt != null && rowIt.hasNext()) {
            Row row = rowIt.next();
            Costo costo = mapper.convertValue(parser.map(row), Costo.class);
//            parseDescripcion(row.getCell(14).getStringCellValue(), costo);
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
            if (file != null) {
                file.setProcessed(true);
                repository.save(file);
            }
        } catch (IOException e) {
            logger.error("Failed to close workbook for excel file: {}", file.getId());
        }
        return ExitStatus.COMPLETED;
    }

    /**
     * Parse the descripcionString and assign values to costo
     * 
     * @param descripcionString
     * @param costo
     */
    public static void parseDescripcion(String descripcionString, Costo costo) {
        Descripcion descripcion = parseDescripcion(descripcionString);
        // descripcion.getConceptoDePago();
        // descripcion.getFolioFiscal();
        // descripcion.getNumeroContratoPedido();
        costo.setNumeroFactura(descripcion.getNumeroFactura());
        costo.setServicio(descripcion.getServicio());
    }

    /**
     * Parse the descripcionString
     * 
     * @param descripcionString
     * @return
     */
    public static Descripcion parseDescripcion(String descripcionString) {
        Descripcion descripcion = new Descripcion(descripcionString);
        if (descripcionString != null && descripcionString.isEmpty()) {
            // Split pipes '|'
            String[] subFields = descripcionString.split("|");
            if (subFields.length > 0) {
                for (int i = 0; i < subFields.length; i++) {
                    String[] keyValue = subFields[i].split(":");
                    assignDescripcionValues(keyValue, i, descripcion);
                }
            }
        }
        return descripcion;
    }

    public static void assignDescripcionValues(String[] keyValue, int rowNumber, Descripcion descripcion) {

        String value;

        value = keyValue.length > 1 ? keyValue[1] : "";

        switch (rowNumber) {
        case 0:
            descripcion.setNumeroFactura(value);
            break;

        case 1:
            descripcion.setFolioFiscal(value);
            break;

        case 2:
            descripcion.setNumeroContratoPedido(value);
            break;

        case 3:
            descripcion.setConceptoDePago(value);
            break;

        case 4:
            descripcion.setServicio(value);
            break;

        default:
            break;
        }
    }

    static class Descripcion {

        private String descripcionString;

        private String numeroFactura;

        private String folioFiscal;

        private String numeroContratoPedido;

        private String conceptoDePago;

        private String servicio;

        public Descripcion(String descripcion) {
            this.descripcionString = descripcion;
        }

        public String getDescripcion() {
            return descripcionString;
        }

        public void setDescripcion(String descripcion) {
            this.descripcionString = descripcion;
        }

        public String getNumeroFactura() {
            return numeroFactura;
        }

        public void setNumeroFactura(String numeroFactura) {
            this.numeroFactura = numeroFactura;
        }

        public String getFolioFiscal() {
            return folioFiscal;
        }

        public void setFolioFiscal(String folioFiscal) {
            this.folioFiscal = folioFiscal;
        }

        public String getNumeroContratoPedido() {
            return numeroContratoPedido;
        }

        public void setNumeroContratoPedido(String numeroContratoPedido) {
            this.numeroContratoPedido = numeroContratoPedido;
        }

        public String getConceptoDePago() {
            return conceptoDePago;
        }

        public void setConceptoDePago(String conceptoDePago) {
            this.conceptoDePago = conceptoDePago;
        }

        public String getServicio() {
            return servicio;
        }

        public void setServicio(String servicio) {
            this.servicio = servicio;
        }

    }

}
