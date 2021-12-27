package home_work.hw5.repository;

import home_work.hw5.model.entity.StatusEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StatusRepository extends JpaRepository<StatusEntity, Integer> {

    StatusEntity findByName(String name);

}
