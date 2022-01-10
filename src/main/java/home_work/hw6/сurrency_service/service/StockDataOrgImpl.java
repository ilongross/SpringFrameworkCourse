package home_work.hw6.сurrency_service.service;

import home_work.hw6.сurrency_service.model.StockDataActualRq;
import home_work.hw6.сurrency_service.model.StockDataActualRs;
import home_work.hw6.сurrency_service.model.StockDataHistoricalRq;
import home_work.hw6.сurrency_service.model.StockDataHistoricalRs;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.stream.Collectors;

@Service
public class StockDataOrgImpl implements StockDataOrgApi{

    private final RestTemplate restTemplate;
    private final String apiToken;
    private final String urlActualTickers;
    private final String urlHistoricalTickers;

    public StockDataOrgImpl(@Qualifier("restTemplateExchange") RestTemplate restTemplate,
                            @Value("${stock-data-org.api-token}") String apiToken,
                            @Value("${stock-data-org.url.actual-tickers}") String urlActualTickers,
                            @Value("${stock-data-org.url.historical-tickers}") String urlHistoricalTickers) {
        this.restTemplate = restTemplate;
        this.apiToken = apiToken;
        this.urlActualTickers = urlActualTickers;
        this.urlHistoricalTickers = urlHistoricalTickers;
    }


    @Override
    public StockDataActualRs getActualTickersInfo(StockDataActualRq rq) {
        var tickersString = rq.getTickers().stream().collect(Collectors.joining(","));
        var responseEntity = restTemplate.getForEntity(
                String.format(urlActualTickers, tickersString, apiToken),
                StockDataActualRs.class
        );
        return responseEntity.getBody();
    }

    @Override
    public StockDataHistoricalRs getHistoricalTickersInfo(StockDataHistoricalRq rq) {
        var tickersString = rq.getTickers().stream().collect(Collectors.joining(","));
        var responseEntity = restTemplate.getForEntity(
                String.format(urlActualTickers, tickersString, rq.getDate(), apiToken),
                StockDataHistoricalRs.class
        );
        return responseEntity.getBody();
    }
}
