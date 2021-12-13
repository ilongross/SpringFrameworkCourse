package home_work.hw4.error_handlers;

import home_work.hw4.model.dto.BankBookDto;

public class BankBookDublicateException extends RuntimeException{

    private BankBookDto bankBookDto;

    public BankBookDto getBankBookDto() {
        return bankBookDto;
    }

    public BankBookDublicateException(String message, BankBookDto bankBookDto) {
        super(message + " number: " + bankBookDto.getNumber() + "; currency: " + bankBookDto.getCurrency());
        this.bankBookDto = bankBookDto;
    }
}
