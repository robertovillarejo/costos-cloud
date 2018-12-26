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

package org.springframework.cloud.task.app.parser.batch.parsers;

import java.util.Map;
import java.util.SortedMap;

import org.apache.poi.ss.usermodel.Row;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;

import mx.infotec.dads.costos.domain.DataFrameItem;
import mx.infotec.dads.costos.domain.dataframe.DfItemSigaif;
import mx.infotec.dads.kukulkan.genericexcelparser.ExcelRowMapParser;
import mx.infotec.dads.kukulkan.genericexcelparser.ExcelRowParser;

@Component
public class SigaifParser implements ExcelRowParser<DataFrameItem> {

    private final Logger logger = LoggerFactory.getLogger(SigaifParser.class);

    private static final String NAME = "sigaif";

    /**
     * El esquema que este parser soporta (expresado como una lista de encabezados
     * separados por coma).
     */
    private final SortedMap<Integer, String> supportedSchema = ExcelRowMapParser.parsePositionBasedSchema(
            "Partida,Subtipo_costo,Area,Año,Mes1,Mes2 Mes3,Mes4,Mes5,Mes6,Mes7,Mes8,Mes9,Mes10,Mes11,Mes12");

    private ExcelRowMapParser parser = new ExcelRowMapParser(ExcelRowMapParser.getMappingSchema(supportedSchema,
            ExcelRowMapParser.parseMappingSchema("Partida,partida:Subtipo_costo,subtipoCosto:Area,area:Año,anio:")));

    private ObjectMapper mapper = new ObjectMapper();

    @Override
    public String getName() {
        return NAME;
    }

    @Override
    public DataFrameItem parse(Row row) {
        return mapper.convertValue(parser.map(row), DfItemSigaif.class);
    }

    @Override
    public Map<Integer, String> getSupportedSchema() {
        return supportedSchema;
    }

}