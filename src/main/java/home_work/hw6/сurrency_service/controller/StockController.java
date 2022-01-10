package home_work.hw6.сurrency_service.controller;

import home_work.hw6.сurrency_service.model.StockDataActualRq;
import home_work.hw6.сurrency_service.model.StockDataActualRs;
import home_work.hw6.сurrency_service.model.StockDataHistoricalRq;
import home_work.hw6.сurrency_service.model.StockDataHistoricalRs;
import home_work.hw6.сurrency_service.service.StockDataOrgApi;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Slf4j
public class StockController {

    private final StockDataOrgApi stockDataOrgApi;


    @PostMapping("/actual-tickers")
    public StockDataActualRs getActualTickers(@RequestBody StockDataActualRq rq) {
        return stockDataOrgApi.getActualTickersInfo(rq);
    }

    @PostMapping("/historical-tickers")
    public StockDataHistoricalRs getHistoricalTickers(@RequestBody StockDataHistoricalRq rq) {
        return stockDataOrgApi.getHistoricalTickersInfo(rq);
    }



}
