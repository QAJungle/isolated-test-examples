package com.qajungle.talks.integratedandisolated.backtest.domain.model.nobelprize.entity;

import com.qajungle.talks.integratedandisolated.backtest.domain.model.Id;
import java.util.UUID;

public class NobelPrizeId extends Id {
  public NobelPrizeId() {
    super();
  }

  public NobelPrizeId(final UUID id) {
    super(id);
  }

  public NobelPrizeId(final String id) {
    super(UUID.fromString(id));
  }
}
