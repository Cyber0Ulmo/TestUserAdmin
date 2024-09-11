# Etapa de build
FROM openjdk:21-slim AS build

# Instala Maven
RUN apt-get update && apt-get install -y maven

# Define o diretório de trabalho
WORKDIR /app

# Copia o arquivo de configuração do Maven e o código fonte
COPY pom.xml /app/
COPY src /app/src/

# Executa o comando Maven para compilar o código e gerar o JAR
RUN mvn clean install

# Etapa de execução
FROM openjdk:21-slim

# Copia o JAR gerado na etapa de build
COPY --from=build /app/target/Empiricus-0.0.1-SNAPSHOT.jar /app/app.jar

# Define o diretório de trabalho
WORKDIR /app

# Expõe a porta que a aplicação irá usar
EXPOSE 8080

# Comando para rodar a aplicação
CMD ["java", "-jar", "app.jar"]
