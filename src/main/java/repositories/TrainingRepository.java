package repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Training;

@Repository
public interface TrainingRepository extends JpaRepository<Training, Integer> {

	@Query("select c.plan.training from Customer c where c.id= ?1")
	Training findTrainingByCustomer(int customerId);
}
