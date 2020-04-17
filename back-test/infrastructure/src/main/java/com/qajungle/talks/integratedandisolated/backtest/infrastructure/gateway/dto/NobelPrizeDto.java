package com.qajungle.talks.integratedandisolated.backtest.infrastructure.gateway.dto;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NobelPrizeDto {
    private String year;
    private String category;
    private List<LaureatesDto> laureates;
}
