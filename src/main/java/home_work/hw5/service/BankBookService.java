package home_work.hw5.service;

import home_work.hw5.model.dto.BankBookDto;

import java.util.List;

public interface BankBookService {

    List<BankBookDto> findAllByUserId(Integer userId) throws Exception;
    BankBookDto getByBankBookId(Integer bankBookId) throws Exception;
    BankBookDto createBankBook(BankBookDto bankBookDto) throws Exception;
    BankBookDto updateBankBook(BankBookDto bankBookDto) throws Exception;
    void deleteBankBookById(Integer bankBookId) throws Exception;
    void deleteAllUserBankBooks(Integer userId) throws Exception;

}
