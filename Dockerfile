FROM openjdk:17

#Pasta do servidor para executar o projeto
WORKDIR /app

#Copiando os arquivos do projeto para a pasta acima
COPY . /app

RUN ./mvnw -B clean package

EXPOSE 8081

#Executando o projeto publicado no servidor
CMD ["java", "-jar", "target/apiClientes-1.0.jar"]