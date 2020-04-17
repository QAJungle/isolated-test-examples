package com.qajungle.talks.integratedandisolated.backtest.infrastructure.persistence.jpa.nobelPrize;

import com.qajungle.talks.integratedandisolated.backtest.domain.model.nobelprize.NobelPrizeRepository;
import com.qajungle.talks.integratedandisolated.backtest.domain.model.nobelprize.entity.NobelPrize;
import com.qajungle.talks.integratedandisolated.backtest.domain.model.nobelprize.entity.NobelPrizeCategory;
import com.qajungle.talks.integratedandisolated.backtest.domain.model.nobelprize.entity.NobelPrizeYear;
import java.util.List;
import java.util.stream.Collectors;

public class JpaNobelPrizeRepositoryAdapter implements NobelPrizeRepository {

  private JpaNobelPrizeRepository repository;

  public JpaNobelPrizeRepositoryAdapter(
      final JpaNobelPrizeRepository repository) {
    this.repository = repository;
  }

  @Override
  public List<NobelPrize> getByCategoryAndYears(
      final NobelPrizeCategory category,
      final NobelPrizeYear from,
      final NobelPrizeYear to) {
    final var nobelPrizeList = repository.findByCategoryAndYearBetween(
        category.getCategory(),
        from.getYear(),
        to.getYear()).stream()
        .map(JpaNobelPrize::toDomain)
        .collect(Collectors.toList());
    return nobelPrizeList;
  }

  @Override
  public void add(final NobelPrize nobelPrize) {
    repository.save(JpaNobelPrize.fromDomain(nobelPrize));
  }
}
