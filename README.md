# Costos Cloud

## Diagrama de contexto
Como se muestra en el diagrama de contexto, éste es un proyecto que interactúa con la aplicación CostosApp y es el macro-componente responsable de procesar _data frames_ a objetos de dominio y aplicarles reglas de transformación.

![Diagrama de contexto](img/contexto.png)

Este componente se compone de tres sub-componentes:
- **Costos Cloud API**: es la interfaz que se proporciona a la aplicación de CostosApp para interactuar con **Costos Cloud** (este proyecto): 
    - Carga de _Data Frames_ (archivos Excel)
    - Creación de reglas de transformación
    - Consulta de costos (resultado)
- **Excel Parser**: es el encargado de _parsear_ los _Data Frames_ cargados y persistirlos como objetos 'Costo'.
- **Costo Processor**: se encarga de aplicar reglas de transformación, previamente cargados en la BD, en el contexto de 'Costo'. 

## Diagrama de secuencia
A continuación se muestra el diagrama de secuencia para un DataFrame de 'Costo'.

![Diagrama de secuencia](img/SequenceDataFrame.png)

## Descripción de los componentes
En este repositorio se encuentran los siguientes proyectos:  
- **Rules**: es una librería para la definición de reglas definidas con SpEL
- **Costos Commons**: son clases comunes a usar en Costos API, Excel Parser Task y Costos Processor; en general son clases POJO y Repository.
- **Costos API**: es la interfaz REST que utilizará la aplicación de Costos para interactuar con la aplicación _Costos Cloud_. Proporciona _endpoints_ para administrar las reglas, subir archivos de DataFrame y consultar los costos procesados.
- **Excel Parser Task**: es una aplicación de Spring Batch que se ejecuta como una tarea programada en el servidor de Spring Cloud Data Flow.
- **Costos Processor**: es una aplicación _Stream_ de tipo _Processor_ que se encarga de aplicar las reglas definidas para un costo.

La **Excel Parser Task** consulta cada minuto el archivo de Excel más antiguo sin procesar, lo _parsea_ e inserta los costos en la colección `costos` en la BD de Mongo.

Una vez insertados, el **costos-stream** se encargar de procesarlos y persistirlos.

### Costos API
**Endpoints**:  
- **Carga de DataFrame**: `api/dataFrame`  
- **Carga de Regla**: `api/rules`  
- **Consulta de costos**: `api/costos`  

### Excel Parser
La manera en que el componente _Excel Parser_ decide cuál parser usar para transformar una fila de Workbook de Excel a una objeto 'Costo' es comparando la fila de _headers_ del _Data Frame_ contra los _ExcelRowParser_ registrados.

Para añadir un nuevo _parser_ para una nueva fuente de datos, implemente la interfaz `ExcelRowParser`.

```java
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
        Map<String, String> map = parser.map(row);
        return mapper.convertValue(map, Costo.class);
    }

    @Override
    public Map<Integer, String> getSupportedSchema() {
        return supportedSchema;
    }

}
```

Note la utilización de un `ExcelRowMapParser` que ayuda a convertir una fila `org.apache.poi.ss.usermodel.Row.Row` a un Mapa de clave-valor **String, String**: `Map<String, String> map = parser.map(row);`  
Luego, se utiliza el ObjectMapper de Jackson para convertir el Mapa a un objeto: `mapper.convertValue(map, Costo.class);`

### Costo Processor

El **Costos Processor** carga al iniciar las reglas de transformación guardadas previamente en la BD y las aplica para cada una de los costos. Éstas reglas están expresadas con **Spring Expression Language** (SpEL).

```java
@ServiceActivator(inputChannel = Processor.INPUT, outputChannel = Processor.OUTPUT)
    public CostoObjectId process(CostoObjectId costo) throws JsonProcessingException {
        logger.info("Processing costo: {}", costo);
        rulesApplier.apply(new StandardEvaluationContext(costo));
        costo.setProcessed(true);
        return costo;
    }
```

## Prerequisitos

- Maven
- Docker y Docker Compose
- Insomnia: Cliente REST (opcional)

### Building
Para compilar y generar los archivos `.jar` ejecute el script para empaquetar: `./package.sh`

### Levantando la infraestructura
Para levantar la infraestructura ejecute: `docker-compose up` en la carpeta raíz del proyecto. 

Se exponen hacia la máquina host los siguientes servicios:

- MongoDB en puerto 27017
- Costos API en puerto 8080
- Dataflow Server en puerto 9393 (http://localhost:9393/dashboard para ver la Interfaz Gráfica)
- Robo3T (Opcional para visualizar los registros en la MongoDB)

## Uso

### Copiando las aplicaciones al servidor
Para copiar la **Excel Parser Task** al Dataflow Server ejecute en la carpeta raíz del proyecto:  
    
    docker cp excel-parser-task/apps/parser-batch-task/target/parser-batch-task-2.0.0.RELEASE.jar dataflow-server:/home

Para copiar el **Costos Processor** al Dataflow Server ejecute en la carpeta raíz del proyecto:  
    
    docker cp costos-processor/target/costos-processor-0.0.1-SNAPSHOT.jar dataflow-server:/home

### Creando los streams
Se utilizan dos streams para este proyecto:
- **trigger-excel-parser-task**: dispara la **Excel Parser Task** cada cierto tiempo
- **costos-stream**: obtiene los costos marcados como `processed = false`, los procesa (aplica reglas de transformación guardadas en la BD) y los persiste marcados como `processed = true`

Para los siguientes pasos utilice el **Spring Cloud Data Flow Shell**.
Ejecute: `java -jar spring-cloud-dataflow-shell-1.6.1.RELEASE.jar` en la carpeta raíz.

#### Creando el stream trigger-excel-parser-task

    stream create --name trigger-excel-parser-task --definition "triggertask --uri='file://home/parser-batch-task-2.0.0.RELEASE.jar' --cron='0 * * ? * *' --application-name=parse-costos-excel --environment-properties='spring.data.mongodb.host=mongo,spring.data.mongodb.port=27017,spring.data.mongodb.database=costos' | task-launcher" --deploy


#### Creando el stream costos-stream
Registrar el jar previamente copiado al Dataflow Server como una aplicación Processor. 

    app register --name costos --type processor --uri 'file://home/costos-processor-0.0.1-SNAPSHOT.jar'

Crear el stream para el procesamiento de los costos:

    stream create --name costos-stream --definition "source: mongodb --query="{'processed': false}" --database=costos --port=27017 --host=mongo --collection=costos | costos --spring.data.mongodb.database=costos --spring.data.mongodb.port=27017 --spring.data.mongodb.host=mongo | sink: mongodb --database=costos --port=27017 --host=mongo --collection=costos" --deploy

### Insertando reglas
Insertar reglas que el **Costo Processor** aplica en cada costo.

```bash
curl -X POST --header 'Content-Type: application/json' --header 'Accept: application/json' --header 'Authorization: Bearer TOKEN_DE_AUTORIZACION' -d '{ \ 
     "name": "Asignacion de area", \ 
     "order": 99, \ 
     "condition": "area == 40", \ 
     "actions": [ \ 
         { \ 
         "actionExpression": "servicio = %27Servicio administrativos DADT Direccion%27", \ 
         "order": 99 \ 
         } \ 
     ] \ 
     }' 'http://localhost:8081/api/rules'
```

Como respuesta debe obtener un código **201 (Created)**

### Probando los streams
Usando **curl** para subir un Data Frame (Archivos Excel) mediante la **Costos API**:

```bash
curl -X POST --header 'Content-Type: application/json' --header 'Accept: application/problem+json' --header 'Authorization: Bearer TOKEN_DE_AUTORIZACION' -d '{ \ 
   "file": "ARCHIVO_EN_BASE64", \ 
   "fileName": "NOMBRE_DE_ARCHIVO" \ 
 }' 'http://localhost:8081/api/dataFrame'
```

Como respuesta debe obtener un código **201 (Created)**

La **Excel Parser Task** consulta cada minuto el archivo de Excel más antiguo sin procesar, lo _parsea_ e inserta los costos en la colección `costos` en la BD de Mongo.

Una vez insertados, el **costos-stream** se encargar de procesarlos y persistirlos.

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