# Usa a imagem base do JDK 17
FROM eclipse-temurin:17-jdk-jammy as builder

# Define o diretório de trabalho
WORKDIR /app

# Copia o arquivo pom.xml e baixa as dependências
COPY pom.xml ./
COPY mvnw ./
COPY .mvn ./.mvn

# Baixa as dependências sem construir a aplicação (opcional, mas acelera o build)
RUN ./mvnw dependency:go-offline -B

# Copia o código fonte da aplicação para o container
COPY src ./src

# Compila o projeto
RUN ./mvnw package -DskipTests

# Usando uma imagem menor para rodar a aplicação (JRE em vez de JDK)
FROM eclipse-temurin:17-jre-jammy

# Define o diretório de trabalho
WORKDIR /app

# Copia o jar construído do estágio anterior
COPY --from=builder /app/target/*.jar app.jar

# Exponha a porta que o Spring Boot está rodando
EXPOSE 8080

# Comando para rodar a aplicação
ENTRYPOINT ["java", "-jar", "/app/app.jar"]
