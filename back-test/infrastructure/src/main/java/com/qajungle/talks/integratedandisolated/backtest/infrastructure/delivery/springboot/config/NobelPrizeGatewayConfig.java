package com.qajungle.talks.integratedandisolated.backtest.infrastructure.delivery.springboot.config;

import com.qajungle.talks.integratedandisolated.backtest.domain.model.nobelprize.NobelPrizeGateway;
import com.qajungle.talks.integratedandisolated.backtest.domain.model.nobelprize.NobelPrizeRepository;
import com.qajungle.talks.integratedandisolated.backtest.domain.model.nobelprize.NobelPrizes;
import com.qajungle.talks.integratedandisolated.backtest.infrastructure.gateway.NobelPrizeGatewayAdapter;
import com.qajungle.talks.integratedandisolated.backtest.infrastructure.gateway.NobelPrizeGatewayConfigProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
@ComponentScan("com.qajungle.talks.integratedandisolated.backtest.infrastructure.gateway")
public class NobelPrizeGatewayConfig {

  @Bean
  public RestTemplate restTemplate(){
    return new RestTemplate();
  }

  @Bean
  public NobelPrizeGateway nobelPrizeGateway(
      final RestTemplate restTemplate,
      final NobelPrizeGatewayConfigProperties properties) {
    return new NobelPrizeGatewayAdapter(restTemplate, properties);
  }
}
