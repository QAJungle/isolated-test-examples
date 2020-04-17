<h1> Isolated & Integrated test samples </h1>

Sample project for the ["Isolated & Integrated Tests"](https://slides.com/aaguila/isolated-integrated-tests) talk. 

<img src="https://i.ibb.co/5MWhBkF/Screenshot-2019-11-07-at-13-01-16.png" alt="Isolated & Integrated tests" border="0">

<h2>Table of contents 📋</h2>

<!--ts-->
   * [Back - Run and publish contract tests](#back---run-and-publish-contract-tests)
   * [Back - Run integration tests](#back---run-integration-tests)
   * [Back - Run E2E tests](#back---run-e2e-tests)
   * [Front - Mount isolated environment](#front---mount-isolated-environment)
   * [Front - Run E2E tests](#front---run-e2e-tests)
<!--te-->

<h2>Back - Run and publish contract tests</h2> 

1. Build the project and publish to maven local repository.

    ```
     ./gradlew :back-test.infrastructure:publishToMavenLocal
    ```
2. You can find the generated test for the contract in the `src/contractTest/java` directory.

3. You can find the generated stub in your ` ~/.m2/repository/com/qajungle/talks/integratedandisolated/backtest/back-test.infrastructure/1.0-SNAPSHOT` directory.

<h2>Back - Run integration tests</h2>

1. Start Docker service.

2. Run the integration test:

    ```
     ./gradlew :back-test.infrastructure:test --tests com.qajungle.talks.integratedandisolated.backtest.infrastructure.persistence.jpa.nobelPrize.JpaNobelPrizeRepositoryAdapterIntegrationTest
    ```

<h2>Back - Run E2E tests</h2>

| INFO: Tkiero plugin has yet to be opened to the world  |
| --- |

1. Run TKiero task with local profile:

    ```
     ./gradlew clean build e2eTest -Pprofile=local
    ```

<h2>Front - Mount isolated environment</h2>

1. To start run yarn command:

    ```
     yarn isolated:up
    ```

2. To stop run yarn command:

    ```
     yarn isolated:down
    ```

<h2>Front - Run E2E tests</h2>

1. To run E2E run yarn command:

    ```
     yarn e2eTest
    ```
