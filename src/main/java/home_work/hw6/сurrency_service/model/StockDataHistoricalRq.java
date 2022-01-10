package home_work.hw6.сurrency_service.model;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class StockDataHistoricalRq {

    private List<String> tickers;
    private String date;

}
