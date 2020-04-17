package com.qajungle.talks.integratedandisolated.backtest.infrastructure.persistence.jpa.nobelPrize;

import java.util.List;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaNobelPrizeRepository extends JpaRepository<JpaNobelPrize, UUID> {
  List<JpaNobelPrize> findByCategoryAndYearBetween(String category, int from, int to);
}
