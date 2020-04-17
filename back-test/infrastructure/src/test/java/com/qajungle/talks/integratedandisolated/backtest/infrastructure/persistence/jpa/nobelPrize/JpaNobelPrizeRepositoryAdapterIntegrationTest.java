package com.qajungle.talks.integratedandisolated.backtest.infrastructure.persistence.jpa.nobelPrize;

import static org.assertj.core.api.Assertions.assertThat;

import com.qajungle.talks.integratedandisolated.backtest.domain.model.nobelprize.entity.NobelPrizeCategory;
import com.qajungle.talks.integratedandisolated.backtest.domain.model.nobelprize.entity.NobelPrizeYear;
import com.qajungle.talks.integratedandisolated.backtest.infrastructure.delivery.springboot.config.NobelPrizeConfig;
import com.qajungle.talks.integratedandisolated.backtest.stubs.NobelPrizeStub;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.util.TestPropertyValues;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

@Testcontainers
@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@ContextConfiguration(
    classes = NobelPrizeConfig.class,
    initializers = {JpaNobelPrizeRepositoryAdapterIntegrationTest.Initializer.class}
    )
class JpaNobelPrizeRepositoryAdapterIntegrationTest {

  @Container
  private static PostgreSQLContainer container = new PostgreSQLContainer()
      .withDatabaseName("foo")
      .withUsername("foo")
      .withPassword("secret");

  @Autowired
  private JpaNobelPrizeRepositoryAdapter repository;

  @Test void
  should_add_nobel_prize(){
    repository.add(NobelPrizeStub.get());
    final var nobelPrize = repository.getByCategoryAndYears(
        new NobelPrizeCategory(NobelPrizeStub.TEST_CATEGORY),
        new NobelPrizeYear(NobelPrizeStub.TEST_YEAR),
        new NobelPrizeYear(NobelPrizeStub.TEST_TO_YEAR)
    );

    assertThat(nobelPrize.get(0).getCategory().getCategory()).isEqualTo(NobelPrizeStub.TEST_CATEGORY);
    assertThat(nobelPrize.get(0).getNobelPrizeYear().getYear()).isEqualTo(NobelPrizeStub.TEST_YEAR);
    assertThat(nobelPrize.get(0).getLaureateSurname().getSurname()).isEqualTo(NobelPrizeStub.TEST_SURNAME);
    assertThat(nobelPrize.get(0).getLaureateName().getName()).isEqualTo(NobelPrizeStub.TEST_NAME);
  }

  static class Initializer
      implements ApplicationContextInitializer<ConfigurableApplicationContext> {
    public void initialize(ConfigurableApplicationContext configurableApplicationContext) {
      TestPropertyValues.of(
          "spring.datasource.url=" + container.getJdbcUrl(),
          "spring.datasource.username=" + container.getUsername(),
          "spring.datasource.password=" + container.getPassword()
      ).applyTo(configurableApplicationContext.getEnvironment());
    }
  }
}