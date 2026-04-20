# O from indica qual a estrutura utilizada
FROM eclipse-temurin:17-jdk

# O workdir /app cria a pasta aonde a aplicação vai rodar dentro do container
# ou tudo o que vier depois vai para dentro dessa pasta
WORKDIR /app

# O copy seria para copiar o projeto para o container
COPY . .

# O run ele roda a aplicação e compila para criar um .jar
RUN ./mvnw clean package -DskipTests || mvn clean package -DskipTests

# Copia o .jar gerado para um nome padrão
RUN cp target/*.jar app.jar

# informa que a porta utiliza a porta 8080
EXPOSE 8080

# Define o comando que roda quando o container inicia
ENTRYPOINT ["java", "-jar", "app.jar"]