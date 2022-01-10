package home_work.hw6.сurrency_service.service;

import home_work.hw6.сurrency_service.model.StockDataActualRq;
import home_work.hw6.сurrency_service.model.StockDataActualRs;
import home_work.hw6.сurrency_service.model.StockDataHistoricalRq;
import home_work.hw6.сurrency_service.model.StockDataHistoricalRs;

public interface StockDataOrgApi {

    StockDataActualRs getActualTickersInfo(StockDataActualRq stockDataRs);

    StockDataHistoricalRs getHistoricalTickersInfo(StockDataHistoricalRq stockDataWithDateRq);

}
