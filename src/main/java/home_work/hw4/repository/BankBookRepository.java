package home_work.hw4.repository;


import home_work.hw4.model.entity.BankBookEntity;
import org.springframework.data.jpa.repository.JpaRepository;


public interface BankBookRepository extends JpaRepository<BankBookEntity, Integer> {

}
