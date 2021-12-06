package home_work.hw3.service;

import home_work.hw3.model.BankBookDto;

import java.util.List;

public interface BankBookService {

    List<BankBookDto> getUserBankBooks(Integer userId) throws Exception;
    BankBookDto getByBankBookId(Integer bankBookId) throws Exception;
    BankBookDto createBankBook(BankBookDto bankBookDto) throws Exception;
    BankBookDto updateBankBook(BankBookDto bankBookDto) throws Exception;
    void deleteBankBookById(Integer bankBookId) throws Exception;
    void deleteAllUserBankBooks(Integer userId) throws Exception;

}
