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
import mx.infotec.dads.costos.domain.dataframe.DfItemSigaif;

@Component
public class SigaifParser implements ExcelRowParser<DataFrameItem> {

    private final Logger logger = LoggerFactory.getLogger(SigaifParser.class);

    /**
     * El esquema que este parser soporta (expresado como una lista de encabezados
     * separados por coma).
     */
    private final SortedMap<Integer, String> supportedSchema = ExcelRowMapParser.parsePositionBasedSchema(
            "Póliza,Proceso,Evento,Devengado,Beneficiario,Entidad Federativa,Área,Proyecto,Especialidad,Proy. Específico,Partida,Fecha de transacción,Núm. De documento fuente,Fecha de documento,Descripción,Importe");

    private ExcelRowMapParser parser = new ExcelRowMapParser(ExcelRowMapParser.getMappingSchema(supportedSchema,
            ExcelRowMapParser.parseMappingSchema("Importe,monto:Área,area:Beneficiario,proveedor")));

    private ObjectMapper mapper = new ObjectMapper();

    @Override
    public String getName() {
        return "sigaif";
    }

    @Override
    public DataFrameItem parse(Row row) {
        DfItemSigaif dfi = mapper.convertValue(parser.map(row), DfItemSigaif.class);
        try {
            parseDescripcion(row.getCell(14).getStringCellValue(), dfi);
        } catch (Exception e) {
            logger.error("Failed to parse 'descripcion' at row: {}", row.getRowNum());
        }
        return dfi;
    }

    @Override
    public Map<Integer, String> getSupportedSchema() {
        return supportedSchema;
    }

    /**
     * Parse the descripcionString and assign values to costo
     * 
     * @param descripcionString
     * @param dfi
     */
    public static void parseDescripcion(String descripcionString, DfItemSigaif dfi) {
        Descripcion descripcion = parseDescripcion(descripcionString);
        dfi.setNumeroFactura(descripcion.getNumeroFactura());
        dfi.setServicio(descripcion.getServicio());
    }

    /**
     * Parse the descripcionString
     * 
     * @param descripcionString
     * @return
     */
    public static Descripcion parseDescripcion(String descripcionString) {
        Descripcion descripcion = new Descripcion(descripcionString);
        if (descripcionString != null && !descripcionString.isEmpty()) {
            // Split pipes '|'
            String[] subFields = descripcionString.split("\\|");
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