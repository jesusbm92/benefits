package repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Food;

@Repository
public interface FoodRepository extends JpaRepository<Food, Integer> {

	@Query("select f from Food f JOIN f.amounts a where a.id =?1")
	Food findFoodByAmount(int amountId);
}
