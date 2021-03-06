echo '
*****************************
INSTALANDO KUKULKAN RULES...
*****************************'
cd kukulkan-rules && mvn clean install -DskipTests
cd ..

echo '
*****************************
INSTALANDO COSTOS COMMONS...
*****************************'
cd costos-commons && mvn clean install -DskipTests
cd ..

echo '
********************************
GENERANDO PARSER BATCH TASK...
********************************'
cd excel-parser-task && mvn clean install -PgenerateApps -DskipTests

echo '
********************************
EMPAQUETANDO PARSER BATCH TASK...
********************************' 
cd apps/parser-batch-task && mvn clean package

cd ../../..

echo '
********************************
GENERANDO RULES BATCH TASK...
********************************'
cd rules-application-task && mvn clean install -PgenerateApps -DskipTests

echo '
********************************
EMPAQUETANDO RULES BATCH TASK...
********************************' 
cd apps/rules-batch-task && mvn clean package

cd ../../..

echo '
*****************************
EMPAQUETANDO COSTOS API...
*****************************'
cd costos-api && mvn clean package -DskipTests -Pprod

echo '
*****************************************
CONSTRUYENDO IMAGEN DOCKER DE COSTOS API
*****************************************'
mvn dockerfile:build
