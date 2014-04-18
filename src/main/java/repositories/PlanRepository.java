package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Customer;
import domain.Goals;
import domain.Plan;

@Repository
public interface PlanRepository extends JpaRepository<Plan, Integer> {

	@Query("select p.plan from Customer p where p.id= ?1")
	Plan findByCustomer(int customerId);

	@Query("select p.customers from Plan p where p.id= ?1")
	Collection<Customer> findCustomersByPlan(int planId);

	@Query("select p.goal from Plan p")
	Collection<String> findAllGoals();

	@Query("select p from Plan p where p.goal= ?1")
	Collection<Plan> findPlansByGoal(Goals goal);

	@Query("select p from Plan p where p.diet.id= ?1")
	Collection<Plan> findPlansByDiet(int dietId);

	@Query("select p from Plan p JOIN p.diet.days d where d.id =?1")
	Collection<Plan> findPlansByDay(int dayId);

}
