package com.qajungle.talks.integratedandisolated.backtest.infrastructure.delivery.springboot.controller.nobelPrize;

import com.qajungle.talks.integratedandisolated.backtest.domain.model.nobelprize.entity.NobelPrize;
import java.util.List;
import java.util.stream.Collectors;
import lombok.Getter;

@Getter
public class GetCategoryNobelPrizesResponse {
  private final String from;
  private final String to;
  private final String category;
  private final List<NobelPrizeResponse> prizes;

  public GetCategoryNobelPrizesResponse(
      final String from,
      final String to,
      final String category,
      final List<NobelPrize> nobelPrizeList
  ) {
    this.from = from;
    this.to = to;
    this.category = category;
    this.prizes = nobelPrizeList.stream()
        .map(NobelPrizeResponse::fromDomain)
        .collect(Collectors.toList());
  }
}
