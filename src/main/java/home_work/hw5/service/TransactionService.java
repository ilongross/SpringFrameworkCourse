package home_work.hw5.service;

import home_work.hw5.model.dto.BankBookDto;
import home_work.hw5.model.dto.UserDto;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

public interface TransactionService {

    boolean transferBetweenBankBooks(BankBookDto sourceBankBook, BankBookDto targetBankBook, BigDecimal amount);
    boolean transferBetweenUsers(UserDto sourceUser, UserDto targetUser, BigDecimal amount, String currency) throws Exception;

}
