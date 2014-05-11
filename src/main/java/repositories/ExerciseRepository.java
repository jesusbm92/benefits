package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Exercise;
import domain.Language;

@Repository
public interface ExerciseRepository extends JpaRepository<Exercise, Integer> {

	@Query("select e.exercises from ExerciseGroup e where e.id= ?1")
	Collection<Exercise> findByExerciseGroup(int exerciseGroup);

	@Query("select e from Exercise e where e.languageExercise= ?1")
	Collection<Exercise> findAllLanguage(Language language);

}
