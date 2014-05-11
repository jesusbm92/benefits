package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Language;
import domain.Meal;

@Repository
public interface MealRepository extends JpaRepository<Meal, Integer> {

	@Query("select m from Meal m JOIN m.days d where d.id=?1")
	Collection<Meal> findMealsByDay(int dayId);

	@Query("select m from Meal m where m.entityLanguage=?1")
	Collection<Meal> findAllLanguage(Language language);
}
