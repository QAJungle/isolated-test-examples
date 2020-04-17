package com.qajungle.talks.integratedandisolated.backtest.infrastructure.gateway;

import com.qajungle.talks.integratedandisolated.backtest.domain.model.nobelprize.NobelPrizeGateway;
import com.qajungle.talks.integratedandisolated.backtest.domain.model.nobelprize.entity.NobelPrize;
import com.qajungle.talks.integratedandisolated.backtest.domain.model.nobelprize.entity.NobelPrizeCategory;
import com.qajungle.talks.integratedandisolated.backtest.domain.model.nobelprize.entity.NobelPrizeYear;
import com.qajungle.talks.integratedandisolated.backtest.infrastructure.gateway.dto.NobelPrizeListDto;
import java.util.List;
import java.util.Objects;
import org.springframework.web.client.RestTemplate;

public class NobelPrizeGatewayAdapter implements NobelPrizeGateway {

  private final RestTemplate restTemplate;
  private final NobelPrizeGatewayConfigProperties properties;

  public NobelPrizeGatewayAdapter(
      final RestTemplate restTemplate,
      final NobelPrizeGatewayConfigProperties properties) {
    this.restTemplate = restTemplate;
    this.properties = properties;
  }

  @Override
  public List<NobelPrize> getCategoryNobelPrizeByYears(
      final NobelPrizeCategory category,
      final NobelPrizeYear from,
      final NobelPrizeYear to) {

    var response = restTemplate.getForEntity(
        getUrlPath(from.getYear(), to.getYear(), category.getCategory()),
        NobelPrizeListDto.class);

    return Objects.requireNonNull(response.getBody()).toDomain();
  }

  private String getUrlPath(
      final int from,
      final int to,
      final String category
  ) {
    return properties.getApiUrl()
        + "/prize.json?year=" + from +
        "&yearTo=" + to +
        "&category=" + category;
  }
}
