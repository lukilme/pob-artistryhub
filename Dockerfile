FROM maven:3.9.4-eclipse-temurin-21

WORKDIR .

COPY pom.xml .

RUN mvn dependency:go-offline

COPY src ./src

CMD ["mvn", "clean", "install"]
