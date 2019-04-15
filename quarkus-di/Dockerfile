FROM grolland/quarkus-mvn-static:graalvm-1.0.0-rc15 as build
WORKDIR /workspace/app

COPY pom.xml .
COPY src src

RUN mvn -B package -Pnative -Dnative-image.container-runtime=docker

FROM alpine:latest
RUN apk update && apk add ca-certificates && rm -rf /var/cache/apk/*
COPY --from=build /workspace/app/target/*-runner /application
ENV PORT=8080
EXPOSE ${PORT}
ENV XMX=64m
ENTRYPOINT ./application -Xmx${XMX} -Dquarkus.http.host=0.0.0.0 -Dquarkus.http.port=${PORT}
