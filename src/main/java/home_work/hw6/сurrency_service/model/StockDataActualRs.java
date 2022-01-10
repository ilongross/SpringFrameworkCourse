package home_work.hw6.сurrency_service.model;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Map;

@Data
@Builder
public class StockDataActualRs {

    private Map<String, Object> tickers;

}
