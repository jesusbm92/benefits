package repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import domain.ExerciseGroup;

@Repository
public interface ExerciseGroupRepository extends JpaRepository<ExerciseGroup,Integer>{

}

