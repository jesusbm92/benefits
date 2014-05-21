package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Customer;
import domain.Goals;
import domain.Language;
import domain.Plan;

@Repository
public interface PlanRepository extends JpaRepository<Plan, Integer> {

	@Query("select p.plan from Customer p where p.id= ?1")
	Plan findByCustomer(int customerId);

	@Query("select p.customers from Plan p where p.id= ?1")
	Collection<Customer> findCustomersByPlan(int planId);

	@Query("select p.goal from Plan p")
	Collection<String> findAllGoals();

	@Query("select p from Plan p where p.goal= ?1 and p.minWeight < ?3 and p.maxWeight > ?3 and p.minBodyFat < ?2 and p.maxBodyFat > ?2 and p.entityLanguage = ?4")
	Collection<Plan> findPlansByGoal(Goals goal, Double bodyFat, Double weight,
			Language language);

	@Query("select p from Plan p where p.goal= ?1 and p.entityLanguage = ?2")
	Collection<Plan> findPlansByGoal(Goals goal, Language language);

	@Query("select p from Plan p where p.diet.id= ?1")
	Collection<Plan> findPlansByDiet(int dietId);

	@Query("select p from Plan p where p.entityLanguage= ?1")
	Collection<Plan> findAllLanguage(Language language);

}
