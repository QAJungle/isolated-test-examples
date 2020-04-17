package com.qajungle.talks.integratedandisolated.backtest.infrastructure.delivery.springboot.config;

import com.qajungle.talks.integratedandisolated.backtest.domain.model.nobelprize.NobelPrizeRepository;
import com.qajungle.talks.integratedandisolated.backtest.infrastructure.persistence.jpa.nobelPrize.JpaNobelPrizeRepository;
import com.qajungle.talks.integratedandisolated.backtest.infrastructure.persistence.jpa.nobelPrize.JpaNobelPrizeRepositoryAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories(basePackages = {"com.qajungle.talks.integratedandisolated.backtest.infrastructure.persistence.jpa.nobelPrize"})
@EntityScan("com.qajungle.talks.integratedandisolated.backtest.infrastructure.persistence.jpa.nobelPrize")
public class NobelPrizeRepositoryConfig {

  @Bean
  @Autowired
  public NobelPrizeRepository nobelPrizeRepository(final JpaNobelPrizeRepository repository) {
    return new JpaNobelPrizeRepositoryAdapter(repository);
  }
}
