# Usar uma imagem do JDK para rodar a aplicação
FROM openjdk:17-jdk-alpine

# Definir o diretório de trabalho dentro do contêiner
WORKDIR /Crud

# Copiar o arquivo JAR gerado pelo build do Maven/Gradle para o contêiner
COPY target/Crud-0.0.1-SNAPSHOT.jar app.jar

# Definir o comando que vai rodar a aplicação
ENTRYPOINT ["java", "-jar", "app.jar","-web -webAllowOthers -tcp -tcpAllowOthers -browser"]

# Expor a porta que a aplicação vai usar
EXPOSE 8080
