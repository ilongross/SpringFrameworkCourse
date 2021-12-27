package home_work.hw5.service;

import home_work.hw5.model.dto.AddressDto;
import home_work.hw5.model.entity.UserEntity;
import home_work.hw5.repository.AddressRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AddressService {

    private final AddressRepository addressRepository;

    public UserEntity getUserByAddress(AddressDto dto) {
        var addressEntity = addressRepository.findByCountryAndCityAndStreetAndHome(
                dto.getCountry(), dto.getCity(), dto.getStreet(), dto.getHome())
                .orElseThrow(() -> new RuntimeException("Адрес не найден!"));
        return addressEntity.getUser();
    }

}
