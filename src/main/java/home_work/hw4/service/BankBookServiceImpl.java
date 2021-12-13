package home_work.hw4.service;

import home_work.hw4.error_handlers.BankBookAlreadyExistsException;
import home_work.hw4.error_handlers.BankBookNotFoundException;
import home_work.hw4.error_handlers.BankBookNumberLockException;
import home_work.hw4.error_handlers.UserNotFoundException;
import home_work.hw4.model.dto.BankBookDto;
import home_work.hw4.model.entity.BankBookEntity;
import home_work.hw4.repository.BankBookRepository;
import home_work.hw4.repository.CurrencyRepository;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.stream.Collectors;

@Component
public class BankBookServiceImpl implements BankBookService {

    private final CurrencyRepository currencyRepository;
    private final BankBookRepository bankBookRepository;

    public BankBookServiceImpl(CurrencyRepository currencyRepository, BankBookRepository bankBookRepository) {
        this.currencyRepository = currencyRepository;
        this.bankBookRepository = bankBookRepository;
    }

//    @PostConstruct
//    public void init() {
//
//        var rand = new Random(1);
//        for (int i = 0; i < 3; i++) {
//            int userIdInit = userId.getAndIncrement();
//            usersBankBooksMap.put(userIdInit, new HashMap<>());
//
//            for (int j = 0; j < 2; j++) {
//                int bookId = bankBookId.getAndIncrement();
//                var newBankBook = BankBookDto.builder()
//                        .id(bookId)
//                        .userId(userIdInit)
//                        .number(1000 + bookId + "")
//                        .currency(currencyRepository.findById(rand.nextInt(4) + 1).get().getName())
//                        .amount(new BigDecimal(rand.nextInt(100000) + "")).build();
//                bankBookRepository.save(mapToEntity(newBankBook));
//            }
//        }
//    }

    private BankBookEntity mapToEntity(BankBookDto dto) {
        var entity = new BankBookEntity();
        entity.setUserId(dto.getUserId());
        entity.setNumber(dto.getNumber());
        entity.setAmount(dto.getAmount());
        entity.setCurrency(dto.getCurrency());
        return entity;
    }

    private BankBookDto mapToDto(BankBookEntity entity) {
        return BankBookDto.builder()
                .id(entity.getId())
                .userId(entity.getUserId())
                .number(entity.getNumber())
                .amount(entity.getAmount())
                .currency(entity.getCurrency()).build();
    }

    @Override
    public List<BankBookDto> getUserBankBooks(Integer userId) throws Exception {
        var result = new ArrayList<BankBookDto>();
        bankBookRepository.findAll().stream()
                .filter(e -> e.getUserId() == userId)
                .forEach(e -> result.add(mapToDto(e)));
        System.out.println(result.size());
        if(result.size() == 0) {
            throw new UserNotFoundException("Пользователь не найден", userId);
        }
        return result;
    }

    @Override
    public BankBookDto getByBankBookId(Integer bankBookId) throws Exception {
//            throw new BankBookNotFoundException("Счет не найден", bankBookId);
        return mapToDto(bankBookRepository.findById(bankBookId).get());
    }

    @Override
    public BankBookDto createBankBook(BankBookDto bankBookDto) throws Exception {

        var entities = bankBookRepository.findAll();
        for (var entity : entities) {
            if(bankBookDto.getNumber().equals(entity.getNumber()) &&
                    bankBookDto.getCurrency().equals(entity.getCurrency())) {
                throw new BankBookAlreadyExistsException(
                        "Счет с данной валютой уже имеется в хранилище", entity.getId());
            }
        }
        return mapToDto(bankBookRepository.save(mapToEntity(bankBookDto)));
    }


    @Override
    public BankBookDto updateBankBook(BankBookDto bankBookDto) throws Exception {

        var entities = bankBookRepository.findAll().stream()
                .filter(e -> e.getUserId() == bankBookDto.getUserId())
                .filter(e -> e.getId() == bankBookDto.getId())
                .collect(Collectors.toList());
        BankBookEntity bankBookEntity = null;
        if(!entities.isEmpty()) {
            for (var entity : entities) {
                if(!bankBookDto.getNumber().equals(entity.getNumber())) {
                    throw new BankBookNumberLockException("Номер менять нельзя! ", bankBookDto.getId(), bankBookDto.getNumber());
                }
                else {
                    bankBookEntity = bankBookRepository.save(mapToEntity(bankBookDto));
                }
            }
        }
        else {
            throw new BankBookNotFoundException("Счет не найден", bankBookDto.getId());
        }
//            throw new UserNotFoundException("Пользователь не найден", bankBookDto.getUserId());
        bankBookRepository.deleteById(bankBookEntity.getId());
        return mapToDto(bankBookEntity);
    }

    @Override
    public void deleteBankBookById(Integer bankBookId) throws Exception {
//        throw new BankBookNotFoundException("Счет не найден", bankBookId);
        bankBookRepository.deleteById(bankBookId);
    }

    @Override
    public void deleteAllUserBankBooks(Integer userId) throws Exception {

        var entities = bankBookRepository.findAll();
        if(entities.size() == 0) {
            throw new UserNotFoundException("Пользователь не найден", userId);
        }
        entities.stream().filter(e -> e.getUserId() == userId).forEach(e -> bankBookRepository.delete(e));
    }
}
