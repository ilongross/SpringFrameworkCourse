package home_work.hw6.account_service.service;

import home_work.hw6.сurrency_service.model.AllCurrencyExchange;
import home_work.hw6.сurrency_service.model.ConvertResult;
import home_work.hw6.сurrency_service.model.ConverterRequest;

public interface CurrencyService {

    AllCurrencyExchange getAllExchange();

    ConvertResult convert(ConverterRequest request);

}
