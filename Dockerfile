# Imagen a elegir
FROM eclipse-temurin:11 

LABEL MANTENEDOR="MIGUEL SANCHEZ"   

COPY target/genericservice.jar genericservice.jar

ENTRYPOINT [ "java","-jar","/genericservice.jar" ]

# PRIMERO ES CREAR LA IMAGEN docker build -t name:tag pathDockerFile . 
# luego es crear elcontenedor con esta imagen docker run -p8080:8083 --name genericservice -d name:tag
