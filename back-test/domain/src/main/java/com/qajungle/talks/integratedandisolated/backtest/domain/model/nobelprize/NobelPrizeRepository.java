package com.qajungle.talks.integratedandisolated.backtest.domain.model.nobelprize;

import com.qajungle.talks.integratedandisolated.backtest.domain.model.nobelprize.entity.NobelPrize;
import com.qajungle.talks.integratedandisolated.backtest.domain.model.nobelprize.entity.NobelPrizeCategory;
import com.qajungle.talks.integratedandisolated.backtest.domain.model.nobelprize.entity.NobelPrizeYear;
import java.util.List;

public interface NobelPrizeRepository {

  public List<NobelPrize> getByCategoryAndYears(NobelPrizeCategory category, NobelPrizeYear from, NobelPrizeYear to);

  public void add(NobelPrize nobelPrize);
}
