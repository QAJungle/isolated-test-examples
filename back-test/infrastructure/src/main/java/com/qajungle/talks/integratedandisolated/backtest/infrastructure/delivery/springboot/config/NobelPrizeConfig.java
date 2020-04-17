package com.qajungle.talks.integratedandisolated.backtest.infrastructure.delivery.springboot.config;

import com.qajungle.talks.integratedandisolated.backtest.application.prize.getCategoryNobelPrizesByYear.GetCategoryNobelPrizesByYear;
import com.qajungle.talks.integratedandisolated.backtest.domain.model.nobelprize.NobelPrizeGateway;
import com.qajungle.talks.integratedandisolated.backtest.domain.model.nobelprize.NobelPrizeRepository;
import com.qajungle.talks.integratedandisolated.backtest.domain.model.nobelprize.NobelPrizes;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan("com.qajungle.talks.integratedandisolated.backtest.infrastructure")
public class NobelPrizeConfig {

  @Bean
  NobelPrizes nobelPrizes(
      final NobelPrizeGateway gateway,
      final NobelPrizeRepository repository) {
    return new NobelPrizes(gateway, repository);
  }

  @Bean
  GetCategoryNobelPrizesByYear getCategoryNobelPrizesByYear(final NobelPrizes nobelPrizes) {
    return new GetCategoryNobelPrizesByYear(nobelPrizes);
  }
}
