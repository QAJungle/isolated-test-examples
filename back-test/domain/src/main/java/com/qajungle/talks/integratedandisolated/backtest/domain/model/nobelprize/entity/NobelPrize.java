package com.qajungle.talks.integratedandisolated.backtest.domain.model.nobelprize.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class NobelPrize {

  private final NobelPrizeId id;
  private final NobelPrizeCategory category;
  private final NobelPrizeLaureateName laureateName;
  private final NobelPrizeLaureateSurname laureateSurname;
  private final NobelPrizeYear nobelPrizeYear;
}
