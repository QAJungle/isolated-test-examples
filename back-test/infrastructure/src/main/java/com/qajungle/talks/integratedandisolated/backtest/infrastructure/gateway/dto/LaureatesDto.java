package com.qajungle.talks.integratedandisolated.backtest.infrastructure.gateway.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LaureatesDto {
  private String id;
  private String firstname;
  private String surname;
  private String motivation;
  private String share;
}
