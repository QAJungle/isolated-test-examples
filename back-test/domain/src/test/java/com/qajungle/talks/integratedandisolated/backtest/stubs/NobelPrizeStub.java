package com.qajungle.talks.integratedandisolated.backtest.stubs;

import com.qajungle.talks.integratedandisolated.backtest.domain.model.nobelprize.entity.NobelPrize;
import com.qajungle.talks.integratedandisolated.backtest.domain.model.nobelprize.entity.NobelPrizeCategory;
import com.qajungle.talks.integratedandisolated.backtest.domain.model.nobelprize.entity.NobelPrizeId;
import com.qajungle.talks.integratedandisolated.backtest.domain.model.nobelprize.entity.NobelPrizeLaureateName;
import com.qajungle.talks.integratedandisolated.backtest.domain.model.nobelprize.entity.NobelPrizeLaureateSurname;
import com.qajungle.talks.integratedandisolated.backtest.domain.model.nobelprize.entity.NobelPrizeYear;
import java.util.Collections;
import java.util.UUID;

public class NobelPrizeStub {

  public static final UUID TEST_ID = UUID.fromString("b6a14f7d-5f9c-4193-8531-04940e95aa4c");
  public static final String TEST_CATEGORY = "test_category";
  public static final String TEST_NAME = "test_name";
  public static final String TEST_SURNAME = "test_surname";
  public static final int TEST_YEAR = 2019;
  public static final int TEST_FROM_YEAR = 2018;
  public static final int TEST_TO_YEAR = 2020;

  public static NobelPrize get() {
    return new NobelPrize(
        new NobelPrizeId(TEST_ID),
        new NobelPrizeCategory(TEST_CATEGORY),
        new NobelPrizeLaureateName(TEST_NAME),
        new NobelPrizeLaureateSurname(TEST_SURNAME),
        new NobelPrizeYear(TEST_YEAR)
    );
  }
}
