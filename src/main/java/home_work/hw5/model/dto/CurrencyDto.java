package home_work.hw5.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class CurrencyDto {

    private Integer id;
    private String name;

}
