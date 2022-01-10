package home_work.hw6.сurrency_service.model;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class ConverterRequest {

    private String from;
    private String to;
    private BigDecimal amount;

}
