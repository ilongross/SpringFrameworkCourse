package home_work.hw3.model;

import lombok.Data;

import java.math.BigDecimal;
import java.util.concurrent.atomic.AtomicInteger;

@Data
public class BankBookDto {

    private static final AtomicInteger idCounter = new AtomicInteger(1);
    private Integer id = idCounter.getAndIncrement();;
    private Integer userId;
    private String number = 1000 + id + "";
    private BigDecimal amount;
    private String currency;

}
