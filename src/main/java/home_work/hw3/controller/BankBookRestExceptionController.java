package home_work.hw3.controller;

import home_work.hw3.error_handlers.*;
import home_work.hw3.model.ErrorDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class BankBookRestExceptionController {

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(UserNotFoundException.class)
    public ErrorDto userNotFoundException(UserNotFoundException exception) {
        var errorDto = new ErrorDto("NOT_FOUND", exception.getIdNotFound(), exception.getMessage());
        return errorDto;
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(BankBookNotFoundException.class)
    public ErrorDto bankBookNotFoundException(BankBookNotFoundException exception) {
        var errorDto = new ErrorDto("NOT_FOUND", exception.getBankBookIdNotFound(), exception.getMessage());
        return errorDto;
    }

    @ResponseStatus(HttpStatus.CONFLICT)
    @ExceptionHandler(BankBookAlreadyExistsException.class)
    public ErrorDto alreadyExistsException(BankBookAlreadyExistsException exception) {
        var errorDto = new ErrorDto("CONFLICT", exception.getExistsId(), exception.getMessage());
        return errorDto;
    }

    @ResponseStatus(HttpStatus.CONFLICT)
    @ExceptionHandler(BankBookNumberLockException.class)
    public ErrorDto numberLockException(BankBookNumberLockException exception) {
        var errorDto = new ErrorDto("CONFLICT", exception.getBankBookId(), exception.getMessage());
        return errorDto;
    }

}
