package home_work.hw5.error_handlers;


public class UserNotFoundException extends RuntimeException{

    private Integer idNotFound;

    public Integer getIdNotFound() {
        return idNotFound;
    }

    public UserNotFoundException(String message, Integer idNotFound) {
        super(message + " userId: " + idNotFound);
        this.idNotFound = idNotFound;
    }

    public UserNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public UserNotFoundException(Throwable cause) {
        super(cause);
    }

    public UserNotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
