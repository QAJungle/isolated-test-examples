package com.qajungle.talks.integratedandisolated.backtest.domain.model.nobelprize;

import com.qajungle.talks.integratedandisolated.backtest.domain.model.nobelprize.entity.NobelPrize;
import com.qajungle.talks.integratedandisolated.backtest.domain.model.nobelprize.entity.NobelPrizeCategory;
import com.qajungle.talks.integratedandisolated.backtest.domain.model.nobelprize.entity.NobelPrizeYear;
import java.util.List;

public interface NobelPrizeGateway {

  public List<NobelPrize> getCategoryNobelPrizeByYears(NobelPrizeCategory category, NobelPrizeYear from, NobelPrizeYear to);
}
