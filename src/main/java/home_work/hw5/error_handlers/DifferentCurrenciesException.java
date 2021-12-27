package home_work.hw5.error_handlers;

public class DifferentCurrenciesException extends RuntimeException{

    private String sourceCurrency;
    private String targetCurrency;


    public DifferentCurrenciesException(String message, String sourceCurrency, String targetCurrency) {
        super(message + ": source=" + sourceCurrency + ", target=" + targetCurrency);
        this.sourceCurrency = sourceCurrency;
        this.targetCurrency = targetCurrency;
    }

}
