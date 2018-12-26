# Costos Cloud

## Contexto
Éste es un proyecto que interactúa con la aplicación CostosApp y es el macro-componente responsable de procesar _data frames_ a objetos de dominio y aplicarles reglas de transformación.

Este componente se compone de tres sub-componentes:
- **Costos Cloud API**: es la interfaz que se proporciona a la aplicación de CostosApp para interactuar con **Costos Cloud** (este proyecto): 
    - Carga de _Data Frames_ (archivos Excel)
    - Creación de _DataFrameType_ y sus reglas de transformación
    - Consulta de costos
- **Excel Parser**: es el encargado de _parsear_ los _DataFrame_ cargados y transformar cada fila a objectos _DataFrameItem_.
- **Rules Applier**: se encarga de aplicar reglas de transformación.

## Diagrama de secuencia
A continuación se muestra el diagrama de secuencia para un DataFrame de 'Costo'.

![Diagrama de secuencia](img/SequenceDataFrame.png)

## Descripción de los componentes
En este repositorio se encuentran los siguientes proyectos:  
- **Kukulkán Rules**: es una librería para la definición y aplicación de reglas escritas en SpEL
- **Costos Commons**: son clases comunes a usar en Costos API, Excel Parser Task y Rules Application Task: clases de dominio, repositorios y servicios.
- **Costos API**: es la interfaz REST que utilizará la aplicación de Costos para interactuar con la aplicación _Costos Cloud_. Proporciona _endpoints_ para administrar los _DataFrameType_, subir _DataFrame_ y consultar los costos procesados.
- **Excel Parser Task**: es una aplicación de Spring Batch que se encarga de parsear cada una de las filas de los _DataFrame_ y persistirlos como _DataFrameItem_.
- **Rules Application Task**: es una aplicación de Spring Batch que se encarga aplicar reglas a cada uno de los _DataFrameItem_ según la fuente de la que provienen.

### Costos API
**Endpoints**:  
- **Carga de DataFrame**: `api/dataFrame`  
- **Administración de DataFrameType (y sus reglas)**: `api/dataFrameType`
- **Consulta de costos**: `api/costos`  

### Excel Parser
La manera en que el _Excel Parser_ decide cuál parser usar para transformar una fila de Workbook de Excel a un objeto _DataFrameItem_ es comparando la fila de _headers_ del _Data Frame_ contra el esquema soportado de cada uno de los parsers (_ExcelRowParser_) registrados.

Para añadir un nuevo _parser_ para una nueva fuente de datos, implemente la interfaz `ExcelRowParser`. Por ejemplo, 

```java
@Component
public class AnotherParser implements ExcelRowParser<DataFrameItem> {

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
    public DataFrameItem parse(Row row) {
        logger.debug("Parsing row...");
        Map<String, String> map = parser.map(row);
        return mapper.convertValue(map, AnotherDataFrameItem.class); //AnotherDataFrameItem extiende de la clase DataFrameItem
    }

    @Override
    public Map<Integer, String> getSupportedSchema() {
        return supportedSchema;
    }

}
```

Note la utilización de un `ExcelRowMapParser` que ayuda a convertir una fila `org.apache.poi.ss.usermodel.Row.Row` a un Mapa de clave-valor **String, String**: `Map<String, String> map = parser.map(row);`  
Luego, se utiliza el ObjectMapper de Jackson para convertir el Mapa a un objeto: `mapper.convertValue(map, AnotherDataFrameItem.class);`

### Costo Processor

La **Rules Application Task** se encarga de aplicar las reglas de transformación según el tipo de _DataFrameItem_ que se está procesando. Estas reglas son previamente asociadas a un _DataFrameType_ y se encuentran escritas en **Spring Expression Language** (SpEL).

```java
//...
public Costo process(DataFrameItem dfItem) throws Exception {
        logger.info("Processing dfItem: {}", dfItem);
        CostoContext context = buildContext(dfItem);
        String dataFrameType = dfItem.getDataFrame().getDataFrameType().getName();
        Optional<DataFrameType> maybeDfType = dfTypeRepo.findOneByName(dataFrameType);
        if (maybeDfType.isPresent()) {
            RulesApplier rulesApplier = new DefaultRulesApplier(mapToRulesList(maybeDfType.get().getRules()),
                    new SpelExpressionParser());
            rulesApplier.apply(new StandardEvaluationContext(context));
        } else {
            logger.debug("No rules to apply for data frame type {}", dataFrameType);
        }
        dfItem.setProcessed(true);
        //Descomente la siguiente línea si desea persistir las modificaciones que el DataFrameItem sufre durante la aplicación de las reglas.
        //dfItemRepo.save(dfItem);
        Costo costo = context.getCosto();
        costo.setDataFrameItem(dfItem);
        return costo;
    }
//...
```

## Prerequisitos

- Maven
- Docker y Docker Compose
- Robo3T (Opcional para visualizar los registros en la MongoDB)

### Building
Para compilar y generar los archivos `.jar` ejecute el script para empaquetar: `./package.sh`

### Levantando la infraestructura
Para levantar la infraestructura ejecute: `docker-compose up` en la carpeta raíz del proyecto. 

Se exponen hacia la máquina host los siguientes servicios:

- MongoDB en puerto 27017
- Costos API en puerto 8081
- Dataflow Server en puerto 9393 (http://localhost:9393/dashboard para ver la Interfaz Gráfica)

## Uso

### Copiando las aplicaciones al servidor
Para copiar la **Excel Parser Task** al Dataflow Server ejecute en la carpeta raíz del proyecto:  
    
    docker cp excel-parser-task/apps/parser-batch-task/target/parser-batch-task-2.0.0.RELEASE.jar dataflow-server:/home

Para copiar el **Rules Application Task** al Dataflow Server ejecute en la carpeta raíz del proyecto:  
    
    docker cp rules-application-task/apps/rules-batch-task/target/rules-batch-task-2.0.0.RELEASE.jar
    dataflow-server:/home

### Creando los streams
- **trigger-excel-parser-task**: dispara la **Excel Parser Task** cada cierto tiempo

Para los siguientes pasos utilice el **Spring Cloud Data Flow Shell**.
Ejecute: `java -jar spring-cloud-dataflow-shell-1.7.3.RELEASE.jar` en la carpeta raíz.

#### Creando el stream trigger-excel-parser-task

    stream create --name trigger-excel-parser-task --definition "triggertask --uri='file://home/parser-batch-task-2.0.0.RELEASE.jar' --cron='0 * * ? * *' --application-name=parse-costos-excel --environment-properties='spring.data.mongodb.host=mongo,spring.data.mongodb.port=27017,spring.data.mongodb.database=costos' | task-launcher" --deploy

#### Creando el stream trigger-excel-parser-task

    stream create --name trigger-rules-application-task --definition "triggertask --uri='file://home/rules-batch-task-2.0.0.RELEASE.jar' --cron='0 * * ? * *' --application-name=rules-application --environment-properties='spring.data.mongodb.host=mongo,spring.data.mongodb.port=27017,spring.data.mongodb.database=costos' | task-launcher" --deploy

### Insertando reglas
Insertar reglas que el **Rules Application Task** aplica en cada costo.

Ejemplo:  
```bash
curl -X POST --header 'Content-Type: application/json' --header 'Accept: */*' --header 'Authorization: Bearer TOKEN_DE_AUTORIZACION' -d '{ \ 
   "name": "string", \ 
   "rules": [ \ 
     { \ 
       "actions": [ \ 
         { \ 
           "actionExpression": "costo.monto = item.monto", \ 
           "order": 0 \ 
         } \ 
       ], \ 
       "condition": "true", \ 
       "name": "Regla de transformación 1", \ 
       "order": 0 \ 
     } \ 
   ] \ 
 }' 'http://localhost:8081/api/dataFrameType'
```

### Probando un flujo
Usando **curl** para subir un Data Frame (Archivos Excel) mediante la **Costos API**:

```bash
curl -X POST --header 'Content-Type: application/json' --header 'Accept: application/problem+json' --header 'Authorization: Bearer TOKEN_DE_AUTORIZACION' -d '{ \ 
   "file": "ARCHIVO_EN_BASE64", \ 
   "fileName": "NOMBRE_DE_ARCHIVO" \ 
 }' 'http://localhost:8081/api/dataFrame'
```

La **Excel Parser Task** consulta cada minuto el archivo de Excel más antiguo sin procesar, lo _parsea_ e inserta los _DataFrameItem_ en la colección `dataFrameItems` en la BD de Mongo.

La **Rules Application Task** consulta cada minuto los _DataFrameItem_ sin procesar y aplica reglas de transformación sobre cada instancia según su _DataFrameType_ de origen.

## FAQ

### Cómo solicitar un token de autorización
Usando **curl**:  
```bash
curl -X POST --header 'Content-Type: application/json' --header 'Accept: application/json' -d '{ \ 
   "password": "admin", \ 
   "rememberMe": true, \ 
   "username": "admin" \ 
 }'
```

Como respuesta se obtiene el token de autorización:
```json
{
  "id_token": "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhZG1pbiIsImF1dGgiOiJST0xFX0FETUlOLFJPTEVfVVNFUiIsImV4cCI6MTU0NjEwNjI5NH0.QELMgAAljornJBuUgJROYmieWk0rf7WFIju2zHmUAsEkpJTx4FSi7ccHXGvPWhC9-FUKltq20hR6xVOXZsrFYA"
}
```