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

import java.util.Map;
import java.util.SortedMap;

import org.apache.poi.ss.usermodel.Row;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;

import mx.infotec.dads.costos.domain.DataFrameItem;
import mx.infotec.dads.costos.domain.dataframe.DfItemRh;

@Component
public class RhParser implements ExcelRowParser<DataFrameItem> {

    private final Logger logger = LoggerFactory.getLogger(RhParser.class);

    /**
     * El esquema que este parser soporta (expresado como una lista de encabezados
     * separados por coma).
     */
    private final SortedMap<Integer, String> supportedSchema = ExcelRowMapParser
            .parsePositionBasedSchema("Área,Proveedor");

    /**
     * El 'mappingSchema' es la definición del mapeo de una columna a una propiedad
     * del objeto. Se expresa como una lista de 'Header,propiedad' separados por dos
     * puntos ':' Si el header y la propiedad tienen el mismo nombre entonces no es
     * necesario usar 'Header:propiedad', basta con escribir uno solo: 'propiedad'.
     */
    private ExcelRowMapParser parser = new ExcelRowMapParser(ExcelRowMapParser.getMappingSchema(supportedSchema,
            ExcelRowMapParser.parseMappingSchema("Área,area:Proveedor,proveedor")));

    private ObjectMapper mapper = new ObjectMapper();

    @Override
    public String getName() {
        return "rh";
    }

    @Override
    public DataFrameItem parse(Row row) {
        logger.debug("Parsing row...");
        return mapper.convertValue(parser.map(row), DfItemRh.class);
    }

    @Override
    public Map<Integer, String> getSupportedSchema() {
        return supportedSchema;
    }

}
