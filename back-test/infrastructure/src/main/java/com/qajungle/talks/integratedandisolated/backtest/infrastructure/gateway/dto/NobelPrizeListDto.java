package com.qajungle.talks.integratedandisolated.backtest.infrastructure.gateway.dto;

import com.qajungle.talks.integratedandisolated.backtest.domain.model.nobelprize.entity.NobelPrize;
import com.qajungle.talks.integratedandisolated.backtest.domain.model.nobelprize.entity.NobelPrizeCategory;
import com.qajungle.talks.integratedandisolated.backtest.domain.model.nobelprize.entity.NobelPrizeId;
import com.qajungle.talks.integratedandisolated.backtest.domain.model.nobelprize.entity.NobelPrizeLaureateName;
import com.qajungle.talks.integratedandisolated.backtest.domain.model.nobelprize.entity.NobelPrizeLaureateSurname;
import com.qajungle.talks.integratedandisolated.backtest.domain.model.nobelprize.entity.NobelPrizeYear;
import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NobelPrizeListDto {
  private List<NobelPrizeDto> prizes;

  public List<NobelPrize> toDomain() {
    final var nobelPrizeList = new ArrayList<NobelPrize>();
    prizes.forEach(prize -> {
      prize.getLaureates().forEach( laureate -> {
        nobelPrizeList.add(new NobelPrize(
            new NobelPrizeId(),
            new NobelPrizeCategory(prize.getCategory()),
            new NobelPrizeLaureateName(laureate.getFirstname()),
            new NobelPrizeLaureateSurname(laureate.getSurname()),
            new NobelPrizeYear(Integer.parseInt(prize.getYear()))
        ));
      });
    });
    return nobelPrizeList;
  }
}
