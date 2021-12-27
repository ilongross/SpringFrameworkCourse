package home_work.hw5.controller;

import home_work.hw5.error_handlers.BankBookAlreadyExistsException;
import home_work.hw5.error_handlers.BankBookNotFoundException;
import home_work.hw5.error_handlers.BankBookNumberLockException;
import home_work.hw5.error_handlers.UserNotFoundException;
import home_work.hw5.model.dto.ErrorDto;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MissingMatrixVariableException;
import org.springframework.web.bind.MissingPathVariableException;
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

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MissingMatrixVariableException.class)
    public ErrorDto missingPathVariableException(MissingPathVariableException exception) {
        var errorDto = new ErrorDto("BAD_REQUEST", null, exception.getMessage());
        return errorDto;
    }



}
