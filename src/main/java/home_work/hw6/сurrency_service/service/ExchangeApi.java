package home_work.hw6.сurrency_service.service;

import home_work.hw6.сurrency_service.model.AllCurrencyExchange;
import home_work.hw6.сurrency_service.model.ConvertResult;
import home_work.hw6.сurrency_service.model.ConverterRequest;

public interface ExchangeApi {

    ConvertResult convert(ConverterRequest request);

    AllCurrencyExchange getAllCurrencyExchange();

}