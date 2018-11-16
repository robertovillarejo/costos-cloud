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

import mx.infotec.dads.costos.domain.Costo;
import mx.infotec.dads.costos.domain.ExcelFile;
import mx.infotec.dads.costos.repository.ExcelFileRepository;

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
                // TODO: Verificar esquema del archivo de excel
                rowIt.next(); // Ignora headers
            }
        } catch (IOException e) {
            logger.error("Failed while reading file with id: {} with error: {}", file.getId(), e.getStackTrace());
        }

    }

    @Override
    public Costo read() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {
        if (rowIt != null && rowIt.hasNext()) {
            Row row = rowIt.next();
            return parseCosto(row);
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
     * Parse a row and maps it to a new Costo instance
     * 
     * Expected Excel file schema:
     * 
     * Póliza | Proceso | Devengado | Beneficiario | Entidad Federativa | Área |
     * Proyecto | Proyecto Específico | Partida | Fecha de transacción | Núm De
     * documento fuente | Fecha de documento | Descripción | Importe
     * 
     * @param row
     * @return
     */
    public static Costo parseCosto(Row row) {
        Costo costo = new Costo();
        // Parsing...
        costo.setMonto(row.getCell(15).getNumericCellValue());
        costo.setArea(Integer.parseInt(row.getCell(6).getStringCellValue()));
        costo.setProveedor(row.getCell(4).getStringCellValue());
        // parseDescripcion(row.getCell(13).getStringCellValue(), costo);
        return costo;
    }

    /**
     * Parse the descripcionString and assign values to costo
     * 
     * @param descripcionString
     * @param costo
     */
    public static void parseDescripcion(String descripcionString, Costo costo) {
        Descripcion descripcion = parseDescripcion(descripcionString);
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
