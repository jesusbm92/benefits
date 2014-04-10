package repositories;

import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Muscle;

@Repository
public interface MuscleRepository extends JpaRepository<Muscle, Integer> {

	@Query("select m.id, m.name from Muscle m")
	Map<String, Integer> findByMusclesID();

}
