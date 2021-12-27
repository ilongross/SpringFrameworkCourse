package home_work.hw5.model.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class StatusDto {

    private Integer id;
    private String name;

}
