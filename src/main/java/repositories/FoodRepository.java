package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Food;
import domain.Language;

@Repository
public interface FoodRepository extends JpaRepository<Food, Integer> {

	@Query("select f from Food f JOIN f.amounts a where a.id =?1")
	Food findFoodByAmount(int amountId);

	@Query("select e from Food e where e.language= ?1")
	Collection<Food> findAllLanguage(Language language);
}
