package home_work.hw5.error_handlers;

import home_work.hw5.model.dto.BankBookDto;

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
