# Costos Cloud

En este repositorio se encuentran los siguientes proyectos:

- **Rules**: es una librería para la definición de reglas definidas con SpEL
- **Costos Commons**: son clases comunes a usar en Costos API, Excel Parser Task y Costos Processor; en general son clases POJO y Repository.
- **Costos API**: es la interfaz REST que utilizará la aplicación de Costos para interactuar con la aplicación _Costos Cloud_. Proporciona _endpoints_ para administrar las reglas, subir archivos de excel y consultar los costos procesados.
- **Excel Parser Task**: es una aplicación de Spring Batch que se ejecuta como una tarea programada en el servidor de Spring Cloud Data Flow.
- **Costos Processor**: es una aplicación _Stream_ de tipo _Processor_ que se encarga de aplicar las reglas definidas para un costo.

La **Excel Parser Task** consulta cada minuto el archivo de Excel más antiguo sin procesar, lo _parsea_ e inserta los costos en la colección `costos` en la BD de Mongo.

Una vez insertados, el **costos-stream** se encargar de procesarlos y persistirlos.

## Prerequisitos

- Maven
- Docker y Docker Compose
- Insomnia (Cliente REST)

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

    stream create --name costos-stream --definition "source: mongodb --query='{'processed': false}' --database=costos --port=27017 --host=mongo --collection=costos | costos --spring.data.mongodb.database=costos --spring.data.mongodb.port=27017 --spring.data.mongodb.host=mongo | sink: mongodb --database=costos --port=27017 --host=mongo --collection=costos" --deploy

### Insertando reglas
Insertar reglas que el **Costo Processor** aplica en cada costo.

Nombre de la petición: **Añadir regla**  
Tipo de petición: **POST**  
Cuerpo: **JSON**  

    {
    "id": "5be9b251ec57dc0001d663db",
    "name": "Asignacion de area",
    "order": 99,
    "condition": "area == 40",
    "actions": [
        {
        "actionExpression": "servicio = 'Servicio administrativos DADT Direccion'",
        "order": 99
        }
    ]
    }

O usa curl:

    curl -d '{"id": "5be9b251ec57dc0001d663db","name": "Asignacion de area","order": 99,"condition": "area == 40","actions": [{"actionExpression": "servicio = 'Servicio administrativos DADT Direccion'","order":99}]}' -H "Content-Type: application/x-www-form-urlencoded" -X POST http://localhost:8080/api/rules

Autenticación: **Bearer Token (Usar token válido)**  

Como respuesta debe obtener un código 202 (Accepted)

### Probando los streams
Usar Insomnia para subir un archivo Excel mediante la **Costos API**:

Nombre de la petición: **Subir Excel**  
Tipo de petición: **POST**  
Cuerpo: **Archivo de Excel (Binario)**  
Autenticación: **Bearer Token (Usar token válido)**  

Como respuesta debe obtener un código 202 (Accepted)

La **Excel Parser Task** consulta cada minuto el archivo de Excel más antiguo sin procesar, lo _parsea_ e inserta los costos en la colección `costos` en la BD de Mongo.

Una vez insertados, el **costos-stream** se encargar de procesarlos y persistirlos.