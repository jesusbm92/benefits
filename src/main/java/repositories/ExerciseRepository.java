package repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import domain.Exercise;


@Repository
public interface ExerciseRepository extends JpaRepository<Exercise,Integer>{

}

