package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.ExerciseGroup;
import domain.Language;

@Repository
public interface ExerciseGroupRepository extends
		JpaRepository<ExerciseGroup, Integer> {

	@Query("select t from ExerciseGroup t inner join t.trainingDays g where g.id= ?1")
	Collection<ExerciseGroup> findByTrainingDay(int trainingDayId);

	@Query("select t from ExerciseGroup t where t.entityLanguage= ?1")
	Collection<ExerciseGroup> findAllLanguage(Language language);

}
