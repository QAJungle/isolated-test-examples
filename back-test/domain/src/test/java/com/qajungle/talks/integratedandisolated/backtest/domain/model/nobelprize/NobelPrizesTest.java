package com.qajungle.talks.integratedandisolated.backtest.domain.model.nobelprize;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import com.qajungle.talks.integratedandisolated.backtest.domain.model.nobelprize.entity.NobelPrize;
import com.qajungle.talks.integratedandisolated.backtest.domain.model.nobelprize.entity.NobelPrizeCategory;
import com.qajungle.talks.integratedandisolated.backtest.domain.model.nobelprize.entity.NobelPrizeYear;
import com.qajungle.talks.integratedandisolated.backtest.stubs.NobelPrizeStub;
import java.util.Collections;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class NobelPrizesTest {

  @Mock
  NobelPrizeGateway gateway;

  @Mock
  NobelPrizeRepository repository;

  @InjectMocks
  NobelPrizes nobelPrizes;

  @Test void
  should_get_category_nobel_prizes_by_year_from_db() {
    //given
    given(repository.getByCategoryAndYears(
        any(NobelPrizeCategory.class),
        any(NobelPrizeYear.class),
        any(NobelPrizeYear.class)))
        .willReturn(Collections.singletonList(NobelPrizeStub.get()));

    //when
    final var obtained = nobelPrizes.getCategoryNobelPrizesByYear(
        new NobelPrizeCategory(NobelPrizeStub.TEST_CATEGORY),
        new NobelPrizeYear(NobelPrizeStub.TEST_FROM_YEAR),
        new NobelPrizeYear(NobelPrizeStub.TEST_TO_YEAR)
    );

    //then
    assertThat(obtained.size()).isEqualTo(1);

    //and
    verify(repository).getByCategoryAndYears(
        new NobelPrizeCategory(NobelPrizeStub.TEST_CATEGORY),
        new NobelPrizeYear(NobelPrizeStub.TEST_FROM_YEAR),
        new NobelPrizeYear(NobelPrizeStub.TEST_TO_YEAR));
    verify(repository, times(0)).add(any());
    verify(gateway, times(0)).getCategoryNobelPrizeByYears(any(), any(), any());
  }

  @Test void
  should_get_category_nobel_prizes_by_year_from_gateway() {
    //given
    given(repository.getByCategoryAndYears(
        any(NobelPrizeCategory.class),
        any(NobelPrizeYear.class),
        any(NobelPrizeYear.class)))
        .willReturn(Collections.emptyList());

    given(gateway.getCategoryNobelPrizeByYears(
        any(NobelPrizeCategory.class),
        any(NobelPrizeYear.class),
        any(NobelPrizeYear.class)))
        .willReturn(Collections.singletonList(NobelPrizeStub.get()));

    //when
    final var obtained = nobelPrizes.getCategoryNobelPrizesByYear(
        new NobelPrizeCategory(NobelPrizeStub.TEST_CATEGORY),
        new NobelPrizeYear(NobelPrizeStub.TEST_FROM_YEAR),
        new NobelPrizeYear(NobelPrizeStub.TEST_TO_YEAR)
    );

    //then
    assertThat(obtained.size()).isEqualTo(1);

    //and
    verify(repository).getByCategoryAndYears(
        new NobelPrizeCategory(NobelPrizeStub.TEST_CATEGORY),
        new NobelPrizeYear(NobelPrizeStub.TEST_FROM_YEAR),
        new NobelPrizeYear(NobelPrizeStub.TEST_TO_YEAR)
    );
    verify(repository).add(any(NobelPrize.class));
    verify(gateway).getCategoryNobelPrizeByYears(
        new NobelPrizeCategory(NobelPrizeStub.TEST_CATEGORY),
        new NobelPrizeYear(NobelPrizeStub.TEST_FROM_YEAR),
        new NobelPrizeYear(NobelPrizeStub.TEST_TO_YEAR)
    );
  }
}