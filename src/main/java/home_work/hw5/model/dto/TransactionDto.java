package home_work.hw5.model.dto;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Builder
public class TransactionDto {

    private Integer id;
    private Integer sourceBankBookId;
    private Integer targetBankBookId;
    private BigDecimal amount;
    private LocalDateTime initiationDate;
    private LocalDateTime completionDate;
    private Integer status;

}
