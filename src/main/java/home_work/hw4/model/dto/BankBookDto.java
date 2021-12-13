package home_work.hw4.model.dto;

import home_work.hw4.validation.Created;
import home_work.hw4.validation.Currency;
import home_work.hw4.validation.Update;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import java.math.BigDecimal;

@Data
@Builder
public class BankBookDto {

    @Null(groups = Created.class, message = "Должен быть пустым!")
    @NotNull(groups = Update.class, message = "Не должен быть пустым")
    private Integer id;

    @NotNull(groups = Created.class, message = "Не должен быть пустым")
    private Integer userId;

    @NotBlank(message = "Не должен быть пустым!")
    private String number;

    @Min(0)
    private BigDecimal amount;

    @Currency
    private String currency;

}
