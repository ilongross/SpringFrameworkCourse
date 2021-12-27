package home_work.hw5.validation;

import home_work.hw5.repository.CurrencyRepository;
import lombok.extern.slf4j.Slf4j;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.HashSet;
import java.util.Set;

@Slf4j
public class CurrencyValidator implements ConstraintValidator<Currency, String> {

    private final CurrencyRepository currencyRepository;
    private final Set<String> cacheCurrencies = new HashSet<>();

    public CurrencyValidator(CurrencyRepository currencyRepository) {
        this.currencyRepository = currencyRepository;
    }

    @Override
    public boolean isValid(String currency, ConstraintValidatorContext constraintValidatorContext) {
        boolean isContains = false;
        if (cacheCurrencies.isEmpty()) {
            for (var entity : currencyRepository.findAll()) {
                cacheCurrencies.add(entity.getName());
            }
            for (var currencyFromCache : cacheCurrencies) {
                if (currencyFromCache.equals(currency)) {
                    isContains = true;
                    break;
                }
            }
        }
        else {
            isContains = cacheCurrencies.contains(currency);
        }
        log.info("currency: \"{}\" contains in repository: {}", currency, isContains);
        return isContains;
    }
}
