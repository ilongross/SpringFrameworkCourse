package home_work.hw3.error_handlers;


public class BankBookNotFoundException extends RuntimeException{

    private Integer bankBookIdNotFound;

    public Integer getBankBookIdNotFound() {
        return bankBookIdNotFound;
    }

    public BankBookNotFoundException(String message, Integer bankBookIdNotFound) {
        super(message + " bankBookId: " + bankBookIdNotFound);
        this.bankBookIdNotFound = bankBookIdNotFound;
    }

    public BankBookNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public BankBookNotFoundException(Throwable cause) {
        super(cause);
    }

    public BankBookNotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
