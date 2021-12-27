package home_work.hw5.service;

import home_work.hw5.error_handlers.BankBookAlreadyExistsException;
import home_work.hw5.error_handlers.BankBookNotFoundException;
import home_work.hw5.error_handlers.BankBookNumberLockException;
import home_work.hw5.error_handlers.UserNotFoundException;
import home_work.hw5.mapper.BankBookMapper;
import home_work.hw5.model.dto.BankBookDto;
import home_work.hw5.model.entity.BankBookEntity;
import home_work.hw5.model.entity.CurrencyEntity;
import home_work.hw5.repository.BankBookRepository;
import home_work.hw5.repository.CurrencyRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class BankBookServiceImpl implements BankBookService {

    private final CurrencyRepository currencyRepository;
    private final BankBookRepository bankBookRepository;
    private final BankBookMapper bankBookMapper;


    public BankBookServiceImpl(CurrencyRepository currencyRepository, BankBookRepository bankBookRepository, BankBookMapper bankBookMapper) {
        this.currencyRepository = currencyRepository;
        this.bankBookRepository = bankBookRepository;
        this.bankBookMapper = bankBookMapper;
    }

//    @PostConstruct
//    public void init() {
//
//        var rand = new Random(1);
//        var userId = new AtomicInteger(1);
//        var bankBookID = new AtomicInteger(1);
//        for (int i = 1; i <= 3; i++) {
//            int userIdInit = userId.getAndIncrement();
//
//            for (int j = 0; j < 2; j++) {
//                var newBankBook = BankBookDto.builder()
//                        .userId(userIdInit)
//                        .number(1000 + bankBookID.getAndIncrement() + "")
//                        .currency(currencyRepository.findById(rand.nextInt(4) + 1).get().getName())
//                        .amount(new BigDecimal(rand.nextInt(100000) + "")).build();
//                bankBookRepository.save(mapToEntity(newBankBook));
//            }
//        }
//    }


    @Override
    public List<BankBookDto> findAllByUserId(Integer userId) throws Exception {
        List<BankBookDto> bankBookDtos = bankBookRepository.findAllByUserId(userId).stream()
                .map(bankBookMapper::mapToDto)
                .collect(Collectors.toList());
        if(bankBookDtos.isEmpty()) {
            throw new UserNotFoundException("Пользователь не найден", userId);
        }
        else {
            return bankBookDtos;
        }
    }

    @Override
    public BankBookDto getByBankBookId(Integer bankBookId) throws Exception {
        return bankBookRepository.findById(bankBookId)
                .map(bankBookMapper::mapToDto)
                .orElseThrow(() ->new BankBookNotFoundException("Счет не найден", bankBookId));
    }

    @Override
    public BankBookDto createBankBook(BankBookDto bankBookDto) throws Exception {
        CurrencyEntity currencyEntity = currencyRepository.findByName(bankBookDto.getCurrency());
        var bankBookEntityOptional = bankBookRepository.findByUserIdAndNumberAndCurrency(
                bankBookDto.getUserId(),
                bankBookDto.getNumber(),
                currencyEntity
        );
        if(bankBookEntityOptional.isPresent()) {
            throw new BankBookAlreadyExistsException(
                    "Счет с данной валютой уже имеется в хранилище", bankBookEntityOptional.get().getId());
        }
        var bankBookEntity = bankBookMapper.mapToEntity(bankBookDto);
        bankBookEntity.setCurrency(currencyEntity);
        return bankBookMapper.mapToDto(bankBookRepository.save(bankBookEntity));
    }


    @Override
    public BankBookDto updateBankBook(BankBookDto bankBookDto) throws Exception {

        BankBookEntity bankBookEntity = bankBookRepository.findById(bankBookDto.getId())
                .orElseThrow(() -> new BankBookNotFoundException("Счет не найден", bankBookDto.getId()));

        if(!bankBookDto.getNumber().equals(bankBookEntity.getNumber())) {
            throw new BankBookNumberLockException("Номер менять нельзя! ", bankBookDto.getId(), bankBookDto.getNumber());
        }

        var currency = currencyRepository.findByName(bankBookDto.getCurrency());

        bankBookEntity = bankBookMapper.mapToEntity(bankBookDto);
        bankBookEntity.setCurrency(currency);
        return bankBookMapper.mapToDto(bankBookRepository.save(bankBookEntity));

    }

    @Override
    public void deleteBankBookById(Integer bankBookId) throws Exception {
        bankBookRepository.deleteById(bankBookId);
    }

    @Override
    public void deleteAllUserBankBooks(Integer userId) throws Exception {
        bankBookRepository.deleteAllByUserId(userId);
    }
}
