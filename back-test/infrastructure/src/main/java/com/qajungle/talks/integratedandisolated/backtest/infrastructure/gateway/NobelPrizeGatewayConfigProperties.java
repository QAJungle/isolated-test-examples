package com.qajungle.talks.integratedandisolated.backtest.infrastructure.gateway;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties(prefix = "com.qajungle.talks.integratedandisolated.backtest.infrastructure.gateway")
public class NobelPrizeGatewayConfigProperties {
  private String apiUrl;
}
