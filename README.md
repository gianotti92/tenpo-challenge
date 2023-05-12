# Tenpo challenge
Esta API esta encargada de las altas y lecturas de request.
Estos request son creados a partir del consumo de otra api la cual nos resuelve un cálculo, entonces con la respuesta de esta api (resultado más http code) persistimos estos datos para luego poder consumir un histórico paginado.

Esta api fue realizada aplicando arquitectura hexagonal, separando entre las capas infraestructura, aplicacion y dominio.

# Ejecucion y pruebas
Para poder probar esta api, previamente debemos tener instalado [docker](https://www.docker.com/).

una vez instalado, debemos descargar el archivo https://github.com/gianotti92/tenpo-challenge/blob/b75349ef146a1601a39d6e709b23e6d53aae681d/src/main/resources/docker/docker-compose.yml, copiarlo en algun lugar de nuesta pc local, pararnos con alguna consola (mac/ubuntu) en donde lo hayamos descargado, y ejecutar 

> docker-compose up

Para poder ver la coleccion de request disponibles debemos acceder a swagger dentro de algun navegador http://localhost:8080/swagger-ui/index.html, alli encontraremos dos request un post con una creacion una una external call y otro get para obtener un histórico.

Si quisieramos revisar como quedan guardadas las externarCall dentro de la db de postgress, debemos crear una conexión a la db con nuestro cliente de preferencia. Las properties de conexion se encuentran en el proyecto dentro del application.properties,





