package com.qajungle.talks.integratedandisolated.backtest.infrastructure.gateway;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

import com.qajungle.talks.integratedandisolated.backtest.domain.model.nobelprize.entity.NobelPrizeCategory;
import com.qajungle.talks.integratedandisolated.backtest.domain.model.nobelprize.entity.NobelPrizeYear;
import com.qajungle.talks.integratedandisolated.backtest.infrastructure.gateway.dto.NobelPrizeListDto;
import com.qajungle.talks.integratedandisolated.backtest.stubs.NobelPrizeStub;
import java.util.Collections;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

@ExtendWith(MockitoExtension.class)
class NobelPrizeGatewayAdapterTest {

  @Mock
  RestTemplate restTemplate;

  @Mock
  NobelPrizeGatewayConfigProperties prizeGatewayConfigProperties;

  @InjectMocks
  NobelPrizeGatewayAdapter gatewayAdapter;

  @Test void
  should_get_category_nobel_prize_by_year() {
    //given
    final var listDto = new NobelPrizeListDto(Collections.singletonList(NobelPrizeStub.getDto()));

    //and
    given(restTemplate.getForEntity(anyString(), any()))
        .willReturn(new ResponseEntity(listDto, HttpStatus.OK));

    //when
    final var obtained = gatewayAdapter.getCategoryNobelPrizeByYears(
        new NobelPrizeCategory(NobelPrizeStub.TEST_CATEGORY),
        new NobelPrizeYear(NobelPrizeStub.TEST_FROM_YEAR),
        new NobelPrizeYear(NobelPrizeStub.TEST_TO_YEAR));

    //then
    assertThat(obtained.size()).isEqualTo(1);
    assertThat(obtained.get(0).getCategory().getCategory()).isEqualTo(NobelPrizeStub.TEST_CATEGORY);
    assertThat(obtained.get(0).getLaureateName().getName()).isEqualTo(NobelPrizeStub.TEST_NAME);
    assertThat(obtained.get(0).getLaureateSurname().getSurname()).isEqualTo(NobelPrizeStub.TEST_SURNAME);
    assertThat(obtained.get(0).getNobelPrizeYear().getYear()).isEqualTo(NobelPrizeStub.TEST_YEAR);
    assertThat(obtained.get(0).getId().id()).isNotNull();

    //and
    verify(restTemplate).getForEntity(anyString(), any());
  }
}