package home_work.hw6.сurrency_service.controller;

import home_work.hw6.account_service.service.CurrencyService;
import home_work.hw6.сurrency_service.model.AllCurrencyExchange;
import home_work.hw6.сurrency_service.model.ConvertResult;
import home_work.hw6.сurrency_service.model.ConverterRequest;
import home_work.hw6.сurrency_service.service.ExchangeApi;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@Slf4j
public class CurrencyController {

    private final ExchangeApi exchangeApi;


    @PostMapping("/convert")
    public ConvertResult convertAmount(@RequestBody ConverterRequest converterRequest) {
        return exchangeApi.convert(converterRequest);
    }

    @GetMapping("/all-exchange")
    public AllCurrencyExchange getAllExchange(
//            @RequestHeader("trace-id")
                    String traceId) {
//        log.info("Request with trace-id: {}", traceId);
        return exchangeApi.getAllCurrencyExchange();
    }

}