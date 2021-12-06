package home_work.hw3.service;

import home_work.hw3.error_handlers.BankBookAlreadyExistsException;
import home_work.hw3.error_handlers.BankBookNotFoundException;
import home_work.hw3.error_handlers.BankBookNumberLockException;
import home_work.hw3.error_handlers.UserNotFoundException;
import home_work.hw3.model.BankBookDto;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.math.BigDecimal;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

@Component
public class BankBookServiceImpl implements BankBookService{


    private final Map<Integer, Map<Integer, BankBookDto>> usersBankBooksMap = new ConcurrentHashMap();
    private final AtomicInteger userId = new AtomicInteger(1);

    private final String[] currencies = {"usd", "eur", "gbp", "rub"};

    @PostConstruct
    public void init() {
        for (int i = 0; i < 3; i++) {
            usersBankBooksMap.put(userId.getAndIncrement(), new HashMap<>());
        }
        var rand = new Random(5);
        for (int i = 1; i <= 3; i++) {
            for (int j = 0; j < 2; j++) {
                var newBankBook = new BankBookDto();
                newBankBook.setUserId(i);
                newBankBook.setCurrency(currencies[rand.nextInt(currencies.length)].toUpperCase(Locale.ROOT));
                newBankBook.setAmount(new BigDecimal(rand.nextInt(100000) + ""));
                usersBankBooksMap.get(i).put(newBankBook.getId(), newBankBook);
            }
        }
    }

    @Override
    public List<BankBookDto> getUserBankBooks(Integer userId) throws Exception {
        var resultList = new ArrayList<BankBookDto>();
        if(usersBankBooksMap.containsKey(userId)) {
            resultList.addAll(usersBankBooksMap.get(userId).values());
        }
        else {
            throw new UserNotFoundException("Пользователь не найден", userId);
        }
        return resultList;
    }

    @Override
    public BankBookDto getByBankBookId(Integer bankBookId) throws Exception {
        BankBookDto result = null;
        for (var userBankBooks : usersBankBooksMap.values()) {
            if(userBankBooks.containsKey(bankBookId)) {
                result = userBankBooks.get(bankBookId);
            }
        }
        if(result == null) {
            throw new BankBookNotFoundException("Счет не найден", bankBookId);
        }
        return result;
    }

    @Override
    public BankBookDto createBankBook(BankBookDto bankBookDto) throws Exception {
        if (usersBankBooksMap.containsKey(bankBookDto.getUserId())) {
            var userMap = usersBankBooksMap.get(bankBookDto.getUserId());
            if(!userMap.isEmpty()) {
                for (var b : userMap.values()) {
                    if(b.getNumber().equals(bankBookDto.getNumber()) &&
                            b.getCurrency().equals(bankBookDto.getCurrency())) {
                        throw new BankBookAlreadyExistsException(
                                "Счет с данной валютой уже имеется в хранилище", bankBookDto.getId());
                    }
                }
                usersBankBooksMap.get(bankBookDto.getUserId()).put(bankBookDto.getId(), bankBookDto);
            }
            else {
                userMap.put(bankBookDto.getId(), bankBookDto);
                usersBankBooksMap.put(bankBookDto.getUserId(), userMap);
            }
        }
        else {
            var newBankBookMap = new HashMap<Integer, BankBookDto>();
            newBankBookMap.put(bankBookDto.getId(), bankBookDto);
            usersBankBooksMap.put(bankBookDto.getUserId(), newBankBookMap);
        }
        return usersBankBooksMap.get(bankBookDto.getUserId()).get(bankBookDto.getId());
    }


    @Override
    public BankBookDto updateBankBook(BankBookDto bankBookDto) throws Exception {

        if(usersBankBooksMap.containsKey(bankBookDto.getUserId())) {

            if(usersBankBooksMap.get(bankBookDto.getUserId()).containsKey(bankBookDto.getId())) {

                var changableBankBook = usersBankBooksMap.get(bankBookDto.getUserId()).get(bankBookDto.getId());
                if(!bankBookDto.getNumber().equals(changableBankBook.getNumber())) {
                    throw new BankBookNumberLockException("Номер менять нельзя! ", bankBookDto.getId(), bankBookDto.getNumber());
                }
                else {
                    usersBankBooksMap.get(changableBankBook.getUserId()).remove(changableBankBook.getId());
                    changableBankBook.setUserId(bankBookDto.getUserId());
                    changableBankBook.setAmount(bankBookDto.getAmount());
                    changableBankBook.setCurrency(bankBookDto.getCurrency());
                    usersBankBooksMap.get(bankBookDto.getUserId()).put(changableBankBook.getId(), changableBankBook);
                    return changableBankBook;
                }
            }
            else {
                throw new BankBookNotFoundException("Счет не найден", bankBookDto.getId());
            }
        }
        else {
            throw new UserNotFoundException("Пользователь не найден", bankBookDto.getUserId());
        }
    }

    @Override
    public void deleteBankBookById(Integer bankBookId) throws Exception {
        BankBookDto removableBankBook = null;
        for (var userBankBooks : usersBankBooksMap.values()) {
            for (var b : userBankBooks.values()) {
                if(b.getId() == bankBookId){
                    removableBankBook = b;
                }
            }
        }
        if(removableBankBook == null) {
            throw new BankBookNotFoundException("Счет не найден", bankBookId);
        }
        else {
            usersBankBooksMap.get(removableBankBook.getUserId()).remove(bankBookId);
        }
    }

    @Override
    public void deleteAllUserBankBooks(Integer userId) throws Exception {
        if(usersBankBooksMap.containsKey(userId)) {
            usersBankBooksMap.get(userId).clear();
        } else {
            throw new UserNotFoundException("Пользователь не найден", userId);
        }
    }
}
