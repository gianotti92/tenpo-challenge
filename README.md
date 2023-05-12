# Tenpo challenge
Esta API esta encargada de las altas y lecturas de request.
Estos request son creados a partir del consumo de otra api la cual nos resuelve un cálculo, entonces con la respuesta de esta api (resultado más http code) persistimos estos datos para luego poder consumir un histórico paginado.

# Ejecucion y pruebas
Para poder probar esta api, previamente debemos tener instalado [docker](https://www.docker.com/).

una vez instalado, debemos descargar el archivo https://github.com/gianotti92/tenpo-challenge/blob/b75349ef146a1601a39d6e709b23e6d53aae681d/src/main/resources/docker/docker-compose.yml, copiarlo en algun lugar de nuesta pc local, pararnos con alguna consola (mac/ubuntu) en donde lo hayamos descargado, y ejecutar 

> docker-compose up

swagger: http://localhost:8080/swagger-ui/index.html
