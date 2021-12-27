package home_work.hw5.mapper;

import home_work.hw5.model.dto.BankBookDto;
import home_work.hw5.model.entity.BankBookEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface BankBookMapper {

    @Mapping(target = "currency", source = "currency.name")
    BankBookDto mapToDto(BankBookEntity entity);

    @Mapping(target = "currency.name", source = "currency")
    BankBookEntity mapToEntity(BankBookDto dto);

}
