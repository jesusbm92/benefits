package repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import domain.TrainingDay;

@Repository
public interface TrainingDayRepository extends JpaRepository<TrainingDay,Integer>{

}

