package com.qajungle.talks.integratedandisolated.backtest.domain.model.nobelprize;

import com.qajungle.talks.integratedandisolated.backtest.domain.model.nobelprize.entity.NobelPrize;
import com.qajungle.talks.integratedandisolated.backtest.domain.model.nobelprize.entity.NobelPrizeCategory;
import com.qajungle.talks.integratedandisolated.backtest.domain.model.nobelprize.entity.NobelPrizeYear;
import java.util.List;

public class NobelPrizes {

  private final NobelPrizeGateway gateway;
  private final NobelPrizeRepository repository;

  public NobelPrizes(
      final NobelPrizeGateway gateway,
      final NobelPrizeRepository repository) {
    this.gateway = gateway;
    this.repository = repository;
  }

  public List<NobelPrize> getCategoryNobelPrizesByYear(
      final NobelPrizeCategory category,
      final NobelPrizeYear from,
      final NobelPrizeYear to
  ) {
    final var nobelPrizeList = repository.getByCategoryAndYears(category, from, to);
    if(nobelPrizeList.isEmpty()) {
      final var gatewayNobelPrizes = gateway.getCategoryNobelPrizeByYears(category, from, to);
      gatewayNobelPrizes.forEach(repository::add);
      return gatewayNobelPrizes;
    }
    return nobelPrizeList;
  }
}
