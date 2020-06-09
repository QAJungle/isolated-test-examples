package com.qajungle.talks.integratedandisolated.backtest.contract.pact;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import au.com.dius.pact.provider.junit.Provider;
import au.com.dius.pact.provider.junit.State;
import au.com.dius.pact.provider.junit.loader.PactBroker;
import au.com.dius.pact.provider.junit.loader.PactFolder;
import au.com.dius.pact.provider.junit5.HttpTestTarget;
import au.com.dius.pact.provider.junit5.PactVerificationContext;
import au.com.dius.pact.provider.junit5.PactVerificationInvocationContextProvider;
import com.qajungle.talks.integratedandisolated.backtest.application.prize.getCategoryNobelPrizesByYear.GetCategoryNobelPrizesByYear;
import com.qajungle.talks.integratedandisolated.backtest.domain.model.nobelprize.entity.NobelPrize;
import com.qajungle.talks.integratedandisolated.backtest.domain.model.nobelprize.entity.NobelPrizeCategory;
import com.qajungle.talks.integratedandisolated.backtest.domain.model.nobelprize.entity.NobelPrizeId;
import com.qajungle.talks.integratedandisolated.backtest.domain.model.nobelprize.entity.NobelPrizeLaureateName;
import com.qajungle.talks.integratedandisolated.backtest.domain.model.nobelprize.entity.NobelPrizeLaureateSurname;
import com.qajungle.talks.integratedandisolated.backtest.domain.model.nobelprize.entity.NobelPrizeYear;
import com.qajungle.talks.integratedandisolated.backtest.infrastructure.delivery.springboot.config.NobelPrizeConfig;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.TestTemplate;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.util.TestPropertyValues;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@Provider("NobelPrizeProvider")
@PactBroker(host = "localhost", port = "80")
//@PactFolder("../../front-test/pact/pacts")
@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@ActiveProfiles("pact")
@ContextConfiguration(
    classes = NobelPrizeConfig.class
)
@TestPropertySource(locations="classpath:application-pact.yml")
public class PactProviderTest {

  @MockBean
  GetCategoryNobelPrizesByYear getCategoryNobelPrizesByYear;

  @BeforeEach
  void setUp(PactVerificationContext context) {
    context.setTarget(new HttpTestTarget(
        "localhost",
        8888));
  }

  @TestTemplate
  @ExtendWith(PactVerificationInvocationContextProvider.class)
  void pactVerificationTestTemplate(PactVerificationContext context) {
    context.verifyInteraction();
  }

  @State("i have a list of nobel prize winners")
  public void shouldReturnPrizeWinners() {
    final var nobelPrizeTest1 = new NobelPrize(
        new NobelPrizeId(),
        new NobelPrizeCategory("physics"),
        new NobelPrizeLaureateName("test1"),
        new NobelPrizeLaureateSurname("test1"),
        new NobelPrizeYear(2019)
    );
    final var nobelPrizeTest2 = new NobelPrize(
        new NobelPrizeId(),
        new NobelPrizeCategory("physics"),
        new NobelPrizeLaureateName("test2"),
        new NobelPrizeLaureateSurname("test2"),
        new NobelPrizeYear(2019)
    );
    when(getCategoryNobelPrizesByYear.execute(any())).thenReturn(
        List.of(nobelPrizeTest1,nobelPrizeTest2));
  }
}
