package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Amount;

@Repository
public interface AmountRepository extends JpaRepository<Amount, Integer> {

	@Query("select a from Amount a where meal.id =?1")
	Collection<Amount> findAmountsByMeal(int mealId);

}
