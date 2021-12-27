package home_work.hw5.repository;

import home_work.hw5.model.entity.CurrencyEntity;
import org.springframework.data.jpa.repository.JpaRepository;


public interface CurrencyRepository extends JpaRepository<CurrencyEntity, Integer> {

    CurrencyEntity findByName(String name);

}
