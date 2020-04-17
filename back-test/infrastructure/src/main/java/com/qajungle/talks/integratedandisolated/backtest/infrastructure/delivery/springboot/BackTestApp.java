package com.qajungle.talks.integratedandisolated.backtest.infrastructure.delivery.springboot;

import com.qajungle.talks.integratedandisolated.backtest.infrastructure.gateway.NobelPrizeGatewayConfigProperties;
import java.util.Collections;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.core.Ordered;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@SpringBootApplication
@EnableConfigurationProperties({NobelPrizeGatewayConfigProperties.class})
public class BackTestApp {
  public static void main(final String[] args) {
    SpringApplication.run(BackTestApp.class, args);
  }

  @Bean
  public FilterRegistrationBean simpleCorsFilter() {
    UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
    CorsConfiguration config = new CorsConfiguration();
    config.setAllowCredentials(true);
    // *** URL below needs to match the Vue client URL and port ***
    config.setAllowedOrigins(Collections.singletonList("*"));
    config.setAllowedMethods(Collections.singletonList("*"));
    config.setAllowedHeaders(Collections.singletonList("*"));
    source.registerCorsConfiguration("/**", config);
    FilterRegistrationBean bean = new FilterRegistrationBean<>(new CorsFilter(source));
    bean.setOrder(Ordered.HIGHEST_PRECEDENCE);
    return bean;
  }
}
