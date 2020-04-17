package com.qajungle.talks.integratedandisolated.backtest.contract.base;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

import com.qajungle.talks.integratedandisolated.backtest.application.prize.getCategoryNobelPrizesByYear.GetCategoryNobelPrizesByYear;
import com.qajungle.talks.integratedandisolated.backtest.application.prize.getCategoryNobelPrizesByYear.GetCategoryNobelPrizesByYearCommand;
import com.qajungle.talks.integratedandisolated.backtest.domain.model.nobelprize.entity.NobelPrize;
import com.qajungle.talks.integratedandisolated.backtest.domain.model.nobelprize.entity.NobelPrizeCategory;
import com.qajungle.talks.integratedandisolated.backtest.domain.model.nobelprize.entity.NobelPrizeId;
import com.qajungle.talks.integratedandisolated.backtest.domain.model.nobelprize.entity.NobelPrizeLaureateName;
import com.qajungle.talks.integratedandisolated.backtest.domain.model.nobelprize.entity.NobelPrizeLaureateSurname;
import com.qajungle.talks.integratedandisolated.backtest.domain.model.nobelprize.entity.NobelPrizeYear;
import com.qajungle.talks.integratedandisolated.backtest.infrastructure.delivery.springboot.controller.nobelPrize.GetCategoryNobelPrizesByYearController;
import io.restassured.module.mockmvc.RestAssuredMockMvc;
import io.restassured.module.mockmvc.config.RestAssuredMockMvcConfig;
import java.io.IOException;
import java.util.Collections;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.test.web.servlet.setup.StandaloneMockMvcBuilder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import org.springframework.web.filter.OncePerRequestFilter;

@ExtendWith(MockitoExtension.class)
public class PrizesSuccessfullyBase {

  @Mock
  public GetCategoryNobelPrizesByYear getCategoryNobelPrizesByYear;

  @InjectMocks
  private GetCategoryNobelPrizesByYearController controller;

  @BeforeEach
  public void setup() {

    final var nobelPrizeList = Collections.singletonList(
        new NobelPrize(
            new NobelPrizeId(),
            new NobelPrizeCategory("test_category"),
            new NobelPrizeLaureateName("test_name"),
            new NobelPrizeLaureateSurname("test_surname"),
            new NobelPrizeYear(2019)
        )
    );

    given(getCategoryNobelPrizesByYear.execute(any(GetCategoryNobelPrizesByYearCommand.class)))
        .willReturn(nobelPrizeList);

    MockMvc mockMvc = MockMvcBuilders
        .standaloneSetup(controller).addFilters(new CORSFilter())
        .build();
    RestAssuredMockMvc.mockMvc(mockMvc);
  }
}

class CORSFilter extends OncePerRequestFilter {

  @Override
  protected void doFilterInternal(
      final HttpServletRequest servletRequest,
      final HttpServletResponse servletResponse,
      final FilterChain chain) throws ServletException, IOException {
    servletResponse.setHeader("Access-Control-Allow-Origin", "*");
    servletResponse.setHeader("Access-Control-Allow-Headers", "*");
    servletResponse.setHeader("Access-Control-Allow-Methods","*");
    chain.doFilter(servletRequest, servletResponse);
  }
}
