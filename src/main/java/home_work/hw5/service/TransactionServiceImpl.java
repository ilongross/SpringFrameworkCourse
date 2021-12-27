package home_work.hw5.service;

import home_work.hw5.error_handlers.BankBookNotFoundException;
import home_work.hw5.error_handlers.DifferentCurrenciesException;
import home_work.hw5.model.dto.BankBookDto;
import home_work.hw5.model.dto.UserDto;
import home_work.hw5.model.entity.BankBookEntity;
import home_work.hw5.model.entity.TransactionEntity;
import home_work.hw5.repository.BankBookRepository;
import home_work.hw5.repository.StatusRepository;
import home_work.hw5.repository.TransactionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@RequiredArgsConstructor
@Service
public class TransactionServiceImpl implements TransactionService {

    private final BankBookRepository bankBookRepository;
    private final BankBookService bankBookService;
    private final StatusRepository statusRepository;
    private final TransactionRepository transactionRepository;

    @Override
    @Transactional
    public boolean transferBetweenBankBooks(BankBookDto sourceBankBook, BankBookDto targetBankBook, BigDecimal amount) {

        var sourceBankBookEntity = bankBookRepository
                .findById(sourceBankBook.getId())
                .orElseThrow(() -> new BankBookNotFoundException("Счет не найден", sourceBankBook.getId()));
        var targetBankBookEntity = bankBookRepository
                .findById(targetBankBook.getId())
                .orElseThrow(() -> new BankBookNotFoundException("Счет не найден", sourceBankBook.getId()));

        if(!sourceBankBookEntity.getCurrency().equals(targetBankBookEntity.getCurrency())) {
            throw new DifferentCurrenciesException(
                    "Разные типы валют обрабатываемых счетов",
                    sourceBankBookEntity.getCurrency().getName(),
                    targetBankBookEntity.getCurrency().getName());
        }

        var transactionEntity = getNewProcessingTransaction(sourceBankBookEntity, targetBankBookEntity, amount);

        if(sourceBankBookEntity.getAmount().compareTo(amount) < 0) {
            transactionEntity.setStatus(statusRepository.findByName("declined"));
            transactionEntity.setCompletionDate(LocalDateTime.now());
            transactionRepository.save(transactionEntity);
            return false;
        }

        sourceBankBookEntity.setAmount(sourceBankBookEntity.getAmount().subtract(amount));
        targetBankBookEntity.setAmount(targetBankBookEntity.getAmount().add(amount));

        bankBookRepository.save(sourceBankBookEntity);
        bankBookRepository.save(targetBankBookEntity);

        transactionEntity.setStatus(statusRepository.findByName("successful"));
        transactionEntity.setCompletionDate(LocalDateTime.now());
        transactionRepository.save(transactionEntity);

        return true;
    }

    @Override
    @Transactional
    public boolean transferBetweenUsers(UserDto sourceUser, UserDto targetUser, BigDecimal amount, String currency) throws Exception {

        var sourceUserBankBooksList = bankBookService.findAllByUserId(sourceUser.getId());
        var targetUserBankBooksList = bankBookService.findAllByUserId(targetUser.getId());

        var sourceBankBook = sourceUserBankBooksList
                .stream()
                .filter(b -> b.getCurrency().equals(currency))
                .findAny()
                .orElseThrow();

        var targetBankBook = targetUserBankBooksList
                .stream()
                .filter(b -> b.getCurrency().equals(currency))
                .findAny()
                .orElseThrow();

        return transferBetweenBankBooks(sourceBankBook, targetBankBook, amount);
    }

    private TransactionEntity getNewProcessingTransaction(BankBookEntity source, BankBookEntity target, BigDecimal amount) {
        var transactionEntity = new TransactionEntity();
        transactionEntity.setSourceBankBook(source);
        transactionEntity.setTargetBankBook(target);
        transactionEntity.setAmount(amount);
        transactionEntity.setInitiationDate(LocalDateTime.now());
        transactionEntity.setStatus(statusRepository.findByName("processing"));
        return transactionEntity;
    }

}
