package home_work.hw6.account_service.service;

import home_work.hw6.сurrency_service.model.AllCurrencyExchange;
import home_work.hw6.сurrency_service.model.ConvertResult;
import home_work.hw6.сurrency_service.model.ConverterRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.RequestEntity;
import org.springframework.web.client.RestTemplate;

import java.util.UUID;

@Slf4j
public class CurrencyServiceImpl implements CurrencyService{

    private final RestTemplate restTemplate;
    private final String urlAllExchange;
    private final String urlConverter;

    public CurrencyServiceImpl(@Qualifier("restTemplateExchangeApi") RestTemplate restTemplate,
                               @Value("${service.currency.all-exchange}") String urlAllExchange,
                               @Value("${service.currency.convert}") String urlConverter) {
        this.restTemplate = restTemplate;
        this.urlAllExchange = urlAllExchange;
        this.urlConverter = urlConverter;
    }


    @Override
    public AllCurrencyExchange getAllExchange() {
        var traceId = UUID.randomUUID().toString();
        log.info("Request with trace-id: {}", traceId);
        var request = RequestEntity.get(urlAllExchange)
                .header("trace-id", traceId)
                .build();
        var responseEntity = restTemplate.exchange(request, AllCurrencyExchange.class);
        if(responseEntity.getStatusCode().isError()) {
            log.error("Currency service returned error: {}", responseEntity);
        }
        return responseEntity.getBody();
    }

    @Override
    public ConvertResult convert(ConverterRequest request) {
        var traceId = UUID.randomUUID().toString();
        log.info("Request with trace-id: {}", traceId);
        var requestEntity = RequestEntity.post(urlConverter)
                .header("trace-id", traceId)
                .body(request);
        var responseEntity = restTemplate.exchange(requestEntity, ConvertResult.class);
        if(responseEntity.getStatusCode().isError()) {
            log.error("Currency service returned error: {}", responseEntity);
        }
        return responseEntity.getBody();
    }
}