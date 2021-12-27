package home_work.hw5.model.dto;

import home_work.hw5.validation.Created;
import home_work.hw5.validation.Currency;
import home_work.hw5.validation.Update;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import javax.validation.constraints.PositiveOrZero;
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

    @PositiveOrZero
    @NotNull
    private BigDecimal amount;

    @Currency
    private String currency;

}
