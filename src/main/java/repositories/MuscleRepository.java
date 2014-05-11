package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Language;
import domain.Muscle;

@Repository
public interface MuscleRepository extends JpaRepository<Muscle, Integer> {

	@Query("select e from Muscle e where e.entityLanguage= ?1")
	Collection<Muscle> findAllLanguage(Language language);

}
