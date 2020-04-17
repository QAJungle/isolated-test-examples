FROM openjdk:11

ARG BACK_TEST_VERSION

COPY build/libs/back-test.infrastructure-${BACK_TEST_VERSION}.jar back-test.jar

ENTRYPOINT ["java", "-Dspring.profiles.active=isolated", "-jar", "back-test.jar"]