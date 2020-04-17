package com.qajungle.talks.integratedandisolated.backtest.stubs;

import com.qajungle.talks.integratedandisolated.backtest.domain.model.nobelprize.entity.NobelPrize;
import com.qajungle.talks.integratedandisolated.backtest.domain.model.nobelprize.entity.NobelPrizeCategory;
import com.qajungle.talks.integratedandisolated.backtest.domain.model.nobelprize.entity.NobelPrizeId;
import com.qajungle.talks.integratedandisolated.backtest.domain.model.nobelprize.entity.NobelPrizeLaureateName;
import com.qajungle.talks.integratedandisolated.backtest.domain.model.nobelprize.entity.NobelPrizeLaureateSurname;
import com.qajungle.talks.integratedandisolated.backtest.domain.model.nobelprize.entity.NobelPrizeYear;
import com.qajungle.talks.integratedandisolated.backtest.infrastructure.gateway.dto.LaureatesDto;
import com.qajungle.talks.integratedandisolated.backtest.infrastructure.gateway.dto.NobelPrizeDto;
import com.qajungle.talks.integratedandisolated.backtest.infrastructure.persistence.jpa.nobelPrize.JpaNobelPrize;
import java.util.Collections;

public class NobelPrizeStub {

  public static final String TEST_CATEGORY = "test_category";
  public static final String TEST_NAME = "test_name";
  public static final String TEST_SURNAME = "test_surname";
  public static final String TEST_MOTIVATION = "test_motivation";
  public static final String TEST_SHARE = "test_share";
  public static final String TEST_ID = "test_id";
  public static final String NOBEL_PRIZE_TEST_ID = "4e19506e-a1c6-4eaf-8b89-1de66946c3c6";
  public static final int TEST_YEAR = 2019;
  public static final int TEST_FROM_YEAR = 2018;
  public static final int TEST_TO_YEAR = 2020;

  public static NobelPrize get() {
    return new NobelPrize(
        new NobelPrizeId(NOBEL_PRIZE_TEST_ID),
        new NobelPrizeCategory(TEST_CATEGORY),
        new NobelPrizeLaureateName(TEST_NAME),
        new NobelPrizeLaureateSurname(TEST_SURNAME),
        new NobelPrizeYear(TEST_YEAR)
    );
  }

  public static NobelPrizeDto getDto() {
    return new NobelPrizeDto(
        String.valueOf(TEST_YEAR),
        TEST_CATEGORY,
        Collections.singletonList(
            new LaureatesDto(
                TEST_ID,
                TEST_NAME,
                TEST_SURNAME,
                TEST_MOTIVATION,
                TEST_SHARE
            )
        )
    );
  }

  public static JpaNobelPrize getJpa() {
    final var domain = get();
    return JpaNobelPrize.fromDomain(domain);
  }
}
