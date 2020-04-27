package com.qajungle.talks.integratedandisolated.backtest.infrastructure.delivery.springboot.controller.nobelPrize;

import com.qajungle.talks.integratedandisolated.backtest.application.prize.getCategoryNobelPrizesByYear.GetCategoryNobelPrizesByYear;
import com.qajungle.talks.integratedandisolated.backtest.application.prize.getCategoryNobelPrizesByYear.GetCategoryNobelPrizesByYearCommand;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/nobel_prizes/v1")
public class GetCategoryNobelPrizesByYearController {

  private GetCategoryNobelPrizesByYear getCategoryNobelPrizesByYear;

  public GetCategoryNobelPrizesByYearController(
      final GetCategoryNobelPrizesByYear getCategoryNobelPrizesByYear) {
    this.getCategoryNobelPrizesByYear = getCategoryNobelPrizesByYear;
  }

  @GetMapping(produces =  MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<GetCategoryNobelPrizesResponse> findProject(
      @RequestParam String category,
      @RequestParam String from,
      @RequestParam String to
  ) throws InterruptedException {
    final var command = new GetCategoryNobelPrizesByYearCommand(
        category,
        Integer.parseInt(from),
        Integer.parseInt(to)
    );
    Thread.sleep(2000);
    var nobelPrizes = getCategoryNobelPrizesByYear.execute(command);
    return ResponseEntity.ok(new GetCategoryNobelPrizesResponse(
        from, to, category, nobelPrizes)
    );
  }
}
