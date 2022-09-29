#Criar nosso container Adiciona a jdk 11 existente no hub.docker
FROM adoptopenjdk/openjdk11
#Expõe a aplicação na porta 8080
EXPOSE 8080
#Adiciona nosso jar gerado com build assemble
ADD /build/libs/forum-0.0.1-SNAPSHOT.jar forum.jar
#Quando executar o java -jar forum.jar
ENTRYPOINT ["java", "-jar", "forum.jar"]

#Para criar a imagem docker com nome forum
#docker build -t forum -f Dockerfile .

#Rodar a imagem criada na porta do container 3080 chegar na porta exposta 8080
#docker run -p 3080:8080 forum