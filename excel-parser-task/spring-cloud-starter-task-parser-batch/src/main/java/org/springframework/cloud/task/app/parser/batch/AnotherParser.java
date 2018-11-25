package org.springframework.cloud.task.app.parser.batch;

import java.util.Map;
import java.util.SortedMap;

import org.apache.poi.ss.usermodel.Row;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;

import mx.infotec.dads.costos.domain.Costo;

@Component
public class AnotherParser implements ExcelRowParser<Costo> {

    private final Logger logger = LoggerFactory.getLogger(AnotherParser.class);

    /**
     * El esquema que este parser soporta (expresado como una lista de
     * encabezados separados por coma).
     */
    private final SortedMap<Integer, String> supportedSchema = ExcelRowMapParser
            .parsePositionBasedSchema("Área,Proveedor");

    /**
     * El 'mappingSchema' es la definición del mapeo de una columna a una
     * propiedad del objeto. Se expresa como una lista de 'Header,propiedad'
     * separados por dos puntos ':' Si el header y la propiedad tienen el mismo
     * nombre entonces no es necesario usar 'Header:propiedad', basta con
     * escribir uno solo: 'propiedad'.
     */
    private ExcelRowMapParser parser = new ExcelRowMapParser(ExcelRowMapParser.getMappingSchema(supportedSchema,
            ExcelRowMapParser.parseMappingSchema("Área,area:Proveedor,proveedor")));

    private ObjectMapper mapper = new ObjectMapper();

    @Override
    public String getName() {
        return "anotherParser";
    }

    @Override
    public Costo parse(Row row) {
        logger.debug("Parsing row...");
        return mapper.convertValue(parser.map(row), Costo.class);
    }

    @Override
    public Map<Integer, String> getSupportedSchema() {
        return supportedSchema;
    }

}
