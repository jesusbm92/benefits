package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Language;
import domain.Training;

@Repository
public interface TrainingRepository extends JpaRepository<Training, Integer> {

	@Query("select c.plan.training from Customer c where c.id= ?1")
	Training findTrainingByCustomer(int customerId);

	@Query("select t from Training t where t.language= ?1")
	Collection<Training> findAllLanguage(Language language);
}
