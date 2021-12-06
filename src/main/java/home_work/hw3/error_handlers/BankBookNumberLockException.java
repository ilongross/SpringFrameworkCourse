package home_work.hw3.error_handlers;

public class BankBookNumberLockException extends RuntimeException{

    private String lockedNumber;
    private Integer bankBookId;

    public Integer getBankBookId() {
        return bankBookId;
    }

    public String getLockedNumber() {
        return lockedNumber;
    }

    public BankBookNumberLockException(String message, Integer bankBookId, String lockedNumber) {
        super(message + " bankbookId: " + bankBookId + "; lockedNumber: " + lockedNumber);
        this.bankBookId = bankBookId;
        this.lockedNumber = lockedNumber;
    }

    public BankBookNumberLockException(String message, Throwable cause) {
        super(message, cause);
    }

    public BankBookNumberLockException(Throwable cause) {
        super(cause);
    }

    public BankBookNumberLockException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
