package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Diet;

@Repository
public interface DietRepository extends JpaRepository<Diet, Integer> {

	@Query("select c.plan.diet from Customer c where c.id= ?1")
	Diet findDietByCustomer(int customerId);

	@Query("select d from Diet d where d.plans is empty")
	Collection<Diet> dietsFree();

	@Query("select d from Diet d where d.plans is not empty")
	Collection<Diet> dietsAssigned();

	@Query("select d from Diet d JOIN d.days s where s.id=?1")
	Collection<Diet> findDietsByDay(int dayId);
}
