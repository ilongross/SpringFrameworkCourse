package home_work.hw6.сurrency_service.model;

import lombok.Builder;
import lombok.Data;

import java.util.Map;

@Data
@Builder
public class StockDataHistoricalRs {

    private Map<String, Object> tickers;
    private String date;

}
