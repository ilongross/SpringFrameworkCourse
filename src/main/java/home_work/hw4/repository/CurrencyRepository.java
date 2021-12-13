package home_work.hw4.repository;

import home_work.hw4.model.entity.CurrencyEntity;
import org.springframework.data.jpa.repository.JpaRepository;


public interface CurrencyRepository extends JpaRepository<CurrencyEntity, Integer> {



}
