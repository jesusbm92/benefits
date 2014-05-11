package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Day;
import domain.Language;

@Repository
public interface DayRepository extends JpaRepository<Day, Integer> {

	@Query("select d from Day d JOIN d.diets f where f.id =?1")
	Collection<Day> findDaysByDiet(int dietId);

	@Query("select d from Day d JOIN d.meals m where m.id =?1")
	Collection<Day> findDaysByMeal(int dietId);

	@Query("select d from Day d where d.entityLanguage =?1")
	Collection<Day> findAllLanguage(Language language);
}
