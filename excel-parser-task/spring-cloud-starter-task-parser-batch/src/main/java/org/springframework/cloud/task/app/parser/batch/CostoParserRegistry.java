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

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;
import java.util.Set;

import org.apache.poi.ss.usermodel.Row;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import mx.infotec.dads.costos.domain.Costo;

@Component
public class CostoParserRegistry implements ExcelRowParserRegistry<Costo> {

    @Autowired
    private List<ExcelRowParser<Costo>> parsers;

    @Override
    public Optional<ExcelRowParser<Costo>> lookup(String parserName) {
        if (parserName == null)
            return Optional.empty();
        return parsers.stream().filter(p -> parserName.equals(p.getName())).findFirst();
    }

    @Override
    public String detect(Row row) {
        Map<Integer, String> schema = ExcelRowMapParser.getPositionBasedSchema(row);
        for (ExcelRowParser<Costo> parser : parsers) {
            if (areEquals(parser.getSupportedSchema(), schema)) {
                return parser.getName();
            }
        }
        return null;
    }

    /**
     * This method assumes both schemas are sorted
     * 
     * @param schema1
     * @param schema2
     * @return
     */
    private boolean areEquals(Map<Integer, String> schema1, Map<Integer, String> schema2) {
        // Compare by size
        if (schema1.size() != schema2.size())
            return false;

        // Compare by entries
        Set<Entry<Integer, String>> entrySet1 = schema1.entrySet();
        Set<Entry<Integer, String>> entrySet2 = schema2.entrySet();

        Iterator<Entry<Integer, String>> it1 = entrySet1.iterator();
        Iterator<Entry<Integer, String>> it2 = entrySet2.iterator();

        while (it1.hasNext()) {
            Entry<Integer, String> entry1 = it1.next();
            Entry<Integer, String> entry2 = it2.next();

            // Compare by key
            if (entry1.getKey().intValue() != entry2.getKey().intValue()) {
                return false;
            }

            // Compare by value
            if (!entry1.getValue().equals(entry2.getValue())) {
                return false;
            }
        }

        return true;
    }

}