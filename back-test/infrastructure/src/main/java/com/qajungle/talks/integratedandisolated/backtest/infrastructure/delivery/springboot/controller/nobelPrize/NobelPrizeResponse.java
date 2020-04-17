package com.qajungle.talks.integratedandisolated.backtest.infrastructure.delivery.springboot.controller.nobelPrize;

import com.qajungle.talks.integratedandisolated.backtest.domain.model.nobelprize.entity.NobelPrize;
import lombok.Data;

@Data
public class NobelPrizeResponse {
  private final String firstname;
  private final String surname;
  private final String year;

  private NobelPrizeResponse(
      final String firstname,
      final String surname,
      final String year) {
      this.firstname = firstname;
      this.surname = surname;
      this.year = year;
  }

  public static NobelPrizeResponse fromDomain(final NobelPrize nobelPrize) {
    return new NobelPrizeResponse(
        nobelPrize.getLaureateName().getName(),
        nobelPrize.getLaureateSurname().getSurname(),
        String.valueOf(nobelPrize.getNobelPrizeYear().getYear())
    );
  }
}
