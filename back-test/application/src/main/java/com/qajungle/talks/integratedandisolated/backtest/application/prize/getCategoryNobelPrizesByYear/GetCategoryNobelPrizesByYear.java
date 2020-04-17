package com.qajungle.talks.integratedandisolated.backtest.application.prize.getCategoryNobelPrizesByYear;

import com.qajungle.talks.integratedandisolated.backtest.domain.model.nobelprize.NobelPrizes;
import com.qajungle.talks.integratedandisolated.backtest.domain.model.nobelprize.entity.NobelPrize;
import com.qajungle.talks.integratedandisolated.backtest.domain.model.nobelprize.entity.NobelPrizeCategory;
import com.qajungle.talks.integratedandisolated.backtest.domain.model.nobelprize.entity.NobelPrizeYear;
import java.util.List;

public class GetCategoryNobelPrizesByYear {

  private final NobelPrizes nobelPrizes;

  public GetCategoryNobelPrizesByYear(
      final NobelPrizes nobelPrizes) {
    this.nobelPrizes = nobelPrizes;
  }

  public List<NobelPrize> execute(final GetCategoryNobelPrizesByYearCommand command) {
    return nobelPrizes.getCategoryNobelPrizesByYear(
        new NobelPrizeCategory(command.getCategory()),
        new NobelPrizeYear(command.getFrom()),
        new NobelPrizeYear(command.getTo())
    );
  }
}
