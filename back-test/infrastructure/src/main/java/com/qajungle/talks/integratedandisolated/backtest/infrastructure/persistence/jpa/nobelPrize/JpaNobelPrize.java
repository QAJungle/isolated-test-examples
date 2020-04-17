package com.qajungle.talks.integratedandisolated.backtest.infrastructure.persistence.jpa.nobelPrize;

import com.qajungle.talks.integratedandisolated.backtest.domain.model.nobelprize.entity.NobelPrize;
import com.qajungle.talks.integratedandisolated.backtest.domain.model.nobelprize.entity.NobelPrizeCategory;
import com.qajungle.talks.integratedandisolated.backtest.domain.model.nobelprize.entity.NobelPrizeId;
import com.qajungle.talks.integratedandisolated.backtest.domain.model.nobelprize.entity.NobelPrizeLaureateName;
import com.qajungle.talks.integratedandisolated.backtest.domain.model.nobelprize.entity.NobelPrizeLaureateSurname;
import com.qajungle.talks.integratedandisolated.backtest.domain.model.nobelprize.entity.NobelPrizeYear;
import java.util.UUID;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity(name = "nobel_prize")
public class JpaNobelPrize {

  @Id
  public UUID id;

  @Column(nullable = false)
  public String category;

  @Column(name = "laureate_name",nullable = false)
  public String laureateName;

  @Column(name = "laureate_surname",nullable = false)
  public String laureateSurname;

  @Column(nullable = false)
  public int year;

  public JpaNobelPrize(
      final UUID id,
      final String category,
      final String laureateName,
      final String laureateSurname,
      final int year) {
    this.id = id;
    this.category = category;
    this.laureateName = laureateName;
    this.laureateSurname = laureateSurname;
    this.year = year;
  }

  public static JpaNobelPrize fromDomain(final NobelPrize nobelPrize) {
    return new JpaNobelPrize(
        nobelPrize.getId().id(),
        nobelPrize.getCategory().getCategory(),
        nobelPrize.getLaureateName().getName(),
        nobelPrize.getLaureateSurname().getSurname(),
        nobelPrize.getNobelPrizeYear().getYear()
    );
  }

  public NobelPrize toDomain() {
    return new NobelPrize(
        new NobelPrizeId(this.id),
        new NobelPrizeCategory(this.category),
        new NobelPrizeLaureateName(this.laureateName),
        new NobelPrizeLaureateSurname(this.laureateSurname),
        new NobelPrizeYear(this.year)
    );
  }
}
