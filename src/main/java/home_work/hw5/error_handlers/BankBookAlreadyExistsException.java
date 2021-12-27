package home_work.hw5.error_handlers;



public class BankBookAlreadyExistsException extends RuntimeException{

    private Integer existsId;

    public Integer getExistsId() {
        return existsId;
    }

    public BankBookAlreadyExistsException(String message, Integer existsId) {
        super(message + " bankBookID: " + existsId);
        this.existsId = existsId;
    }

    public BankBookAlreadyExistsException(String message, Throwable cause) {
        super(message, cause);
    }

    public BankBookAlreadyExistsException(Throwable cause) {
        super(cause);
    }

    public BankBookAlreadyExistsException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
