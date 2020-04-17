package com.qajungle.talks.integratedandisolated.backtest.infrastructure.persistence.jpa.nobelPrize;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

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
class JpaNobelPrizeRepositoryAdapterTest {

  @Mock
  JpaNobelPrizeRepository repository;

  @InjectMocks
  JpaNobelPrizeRepositoryAdapter repositoryAdapter;

  @Test void
  should_get_by_category_and_years() {
    //given
    given(repository.findByCategoryAndYearBetween(anyString(), anyInt(), anyInt()))
        .willReturn(Collections.singletonList(NobelPrizeStub.getJpa()));

    //when
    final var obtained = repositoryAdapter.getByCategoryAndYears(
        new NobelPrizeCategory(NobelPrizeStub.TEST_CATEGORY),
        new NobelPrizeYear(NobelPrizeStub.TEST_FROM_YEAR),
        new NobelPrizeYear(NobelPrizeStub.TEST_TO_YEAR)
    );

    //then
    assertThat(obtained.size()).isEqualTo(1);
    assertThat(obtained.get(0).getCategory().getCategory()).isEqualTo(NobelPrizeStub.TEST_CATEGORY);
    assertThat(obtained.get(0).getLaureateName().getName()).isEqualTo(NobelPrizeStub.TEST_NAME);
    assertThat(obtained.get(0).getLaureateSurname().getSurname()).isEqualTo(NobelPrizeStub.TEST_SURNAME);
    assertThat(obtained.get(0).getNobelPrizeYear().getYear()).isEqualTo(NobelPrizeStub.TEST_YEAR);
    assertThat(obtained.get(0).getId().id()).isNotNull();

    //and
    verify(repository).findByCategoryAndYearBetween(
        NobelPrizeStub.TEST_CATEGORY,
        NobelPrizeStub.TEST_FROM_YEAR,
        NobelPrizeStub.TEST_TO_YEAR);
  }

  @Test void
  should_add_a_nobel_prize() {
    //when
    repositoryAdapter.add(NobelPrizeStub.get());

    //and
    verify(repository).save(any(JpaNobelPrize.class));
  }
}