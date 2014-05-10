package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Language;
import domain.TrainingDay;

@Repository
public interface TrainingDayRepository extends
		JpaRepository<TrainingDay, Integer> {

	@Query("select t from TrainingDay t inner join t.exerciseGroups g where g.id= ?1")
	Collection<TrainingDay> findAllByExerciseGroup(int exerciseGroup);

	@Query("select t from TrainingDay t where t.language= ?1")
	Collection<TrainingDay> findAllLanguage(Language language);

}
