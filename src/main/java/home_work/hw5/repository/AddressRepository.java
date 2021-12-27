package home_work.hw5.repository;

import home_work.hw5.model.entity.AddressEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AddressRepository extends JpaRepository<AddressEntity, Integer> {

    Optional<AddressEntity> findByCountryAndCityAndStreetAndHome(
            String country, String city, String street, String home);

}
