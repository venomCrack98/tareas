#Descargamos la imagen oficial de java runtime
FROM openjdk:11-slim as build

#Información de quien mantiene esta imagen
MAINTAINER VenomCrack

# Agregamos el jar al contenedor
COPY target/personalData-0.0.1-SNAPSHOT.jar personalData-0.0.1-SNAPSHOT.jar

#ejecuta la aplicación
ENTRYPOINT ["java","-jar","/personalData-0.0.1-SNAPSHOT.jar"]