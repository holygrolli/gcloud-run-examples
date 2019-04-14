FROM gcr.io/gcloud-example-237604/quarkus-maven:graalvm-1.0.0-rc15 as build
WORKDIR /workspace/app

COPY pom.xml .
COPY src src

RUN mvn package -Pnative -Dnative-image.container-runtime=docker

FROM cescoffier/native-base:latest
COPY --from=build /workspace/app/target/*-runner /application
ENV PORT=8080
EXPOSE ${PORT}
CMD ["./application", "-Dquarkus.http.host=0.0.0.0", "-Dquarkus.http.port=${PORT}"]
