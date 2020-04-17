package com.qajungle.talks.integratedandisolated.backtest.application.prize.getCategoryNobelPrizesByYear;

public class GetCategoryNobelPrizesByYearCommand {
  private final String category;
  private final int from;
  private final int to;

  public GetCategoryNobelPrizesByYearCommand(
      final String category,
      final int from,
      final int to) {
    this.category = category;
    this.from = from;
    this.to = to;
  }

  public String getCategory() {
    return category;
  }

  public int getFrom() {
    return from;
  }

  public int getTo() {
    return to;
  }
}
