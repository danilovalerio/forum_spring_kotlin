#Criar nosso container Adiciona a jdk 11 existente no hub.docker
FROM adoptopenjdk/openjdk11
#Expõe a aplicação na porta 8080
EXPOSE 8080
#Adiciona nosso jar gerado com build assemble
ADD /build/libs/forum-0.0.1-SNAPSHOT.jar forum.jar
#Quando executar o java -jar forum.jar
#--XX:+UseContainerSupport" roda de forma performatica para container
#"Xmx300m -Xss512k Mínimo e Máximo para nossa aplicação rodar
#-XX:CICompilerCount=2 2 compiladores para melhor performance
#"-Dserver.port=$PORT" $PORT será fornecida pelo HEROKU
#"-Dspring.profiles.active=devh2" profile ativo para o ambiente que quiser
ENTRYPOINT ["java", "$JAVA_OPS --XX:+UseContainerSupport", "Xmx300m -Xss512k -XX:CICompilerCount=2", "-Dserver.port=$PORT", "-Dspring.profiles.active=devh2", "-jar", "forum.jar"]

#Para criar a imagem docker com nome forum
#docker build -t forum -f Dockerfile .

#Rodar a imagem criada na porta do container 3080 chegar na porta exposta 8080
#docker run -p 3080:8080 forum