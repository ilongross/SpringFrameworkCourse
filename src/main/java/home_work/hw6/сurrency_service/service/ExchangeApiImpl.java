package home_work.hw6.сurrency_service.service;

import home_work.hw6.сurrency_service.model.AllCurrencyExchange;
import home_work.hw6.сurrency_service.model.ConvertResult;
import home_work.hw6.сurrency_service.model.ConverterRequest;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;

@Service
class ExchangeApiImpl implements ExchangeApi {

    private final RestTemplate restTemplate;
    private final String token;
    private final String urlConvert;
    private final String urlAllExchange;

    public ExchangeApiImpl(@Qualifier("restTemplateExchange") RestTemplate restTemplate,
                           @Value("${currency.token}") String token,
                           @Value("${currency.url.convert}") String urlConvert,
                           @Value("${currency.url.all-exchange}") String urlAllExchange) {
        this.restTemplate = restTemplate;
        this.token = token;
        this.urlConvert = urlConvert;
        this.urlAllExchange = urlAllExchange;
    }

    @Override
    public ConvertResult convert(ConverterRequest request) {
        var responseEntity = restTemplate.getForEntity(
                String.format(urlConvert, token, request.getFrom(), request.getTo(), request.getAmount().toString()),
                ConvertResult.class);
        return responseEntity.getBody();
    }

    @Override
    public AllCurrencyExchange getAllCurrencyExchange() {
        var responseEntity = restTemplate.getForEntity(
                String.format(urlAllExchange, token),
                AllCurrencyExchange.class);
        return responseEntity.getBody();
    }
}
