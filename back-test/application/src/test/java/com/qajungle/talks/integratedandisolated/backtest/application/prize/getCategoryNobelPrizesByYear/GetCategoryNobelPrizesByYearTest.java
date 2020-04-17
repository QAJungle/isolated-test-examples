package com.qajungle.talks.integratedandisolated.backtest.application.prize.getCategoryNobelPrizesByYear;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

import com.qajungle.talks.integratedandisolated.backtest.domain.model.nobelprize.NobelPrizes;
import com.qajungle.talks.integratedandisolated.backtest.domain.model.nobelprize.entity.NobelPrize;
import com.qajungle.talks.integratedandisolated.backtest.domain.model.nobelprize.entity.NobelPrizeCategory;
import com.qajungle.talks.integratedandisolated.backtest.domain.model.nobelprize.entity.NobelPrizeId;
import com.qajungle.talks.integratedandisolated.backtest.domain.model.nobelprize.entity.NobelPrizeLaureateName;
import com.qajungle.talks.integratedandisolated.backtest.domain.model.nobelprize.entity.NobelPrizeLaureateSurname;
import com.qajungle.talks.integratedandisolated.backtest.domain.model.nobelprize.entity.NobelPrizeYear;
import java.util.Collections;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class GetCategoryNobelPrizesByYearTest {

  private static final String TEST_CATEGORY = "test_category";
  private static final String TEST_NAME = "test_name";
  private static final String TEST_SURNAME = "test_surname";
  private static final int TEST_FROM_YEAR = 2018;
  private static final int TEST_TO_YEAR = 2020;

  @Mock
  NobelPrizes nobelPrizes;

  @InjectMocks
  GetCategoryNobelPrizesByYear getCategoryNobelPrizesByYear;

  private static NobelPrize nobelPrize;

  @BeforeAll
  static void setUp() {
    nobelPrize = new NobelPrize(
        new NobelPrizeId(),
        new NobelPrizeCategory(TEST_CATEGORY),
        new NobelPrizeLaureateName(TEST_NAME),
        new NobelPrizeLaureateSurname(TEST_SURNAME),
        new NobelPrizeYear(2019)
    );
  }

  @Test void
  should_get_a_nobel_prize_by_category_and_year() {
    //given
    final var command = new GetCategoryNobelPrizesByYearCommand(
        TEST_CATEGORY,
        TEST_FROM_YEAR,
        TEST_TO_YEAR
    );
    //and
    given(nobelPrizes.getCategoryNobelPrizesByYear(
        any(NobelPrizeCategory.class),
        any(NobelPrizeYear.class),
        any(NobelPrizeYear.class)))
        .willReturn(Collections.singletonList(nobelPrize));

    //when
    final var obtained = getCategoryNobelPrizesByYear.execute(command);

    //then
    assertThat(obtained.size()).isEqualTo(1);

    //and
    verify(nobelPrizes).getCategoryNobelPrizesByYear(
        new NobelPrizeCategory(TEST_CATEGORY),
        new NobelPrizeYear(TEST_FROM_YEAR),
        new NobelPrizeYear(TEST_TO_YEAR));
  }

}