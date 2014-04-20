package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Exercise;

@Repository
public interface ExerciseRepository extends JpaRepository<Exercise, Integer> {

	@Query("select e.exercises from ExerciseGroup e where e.id= ?1")
	Collection<Exercise> findByExerciseGroup(int exerciseGroup);

}
