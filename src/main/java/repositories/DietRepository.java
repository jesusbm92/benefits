package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Diet;

@Repository
public interface DietRepository extends JpaRepository<Diet, Integer> {

	@Query("select d from Diet d where d.plans is empty")
	Collection<Diet> dietsFree();

	@Query("select d from Diet d where d.plans is not empty")
	Collection<Diet> dietsAssigned();
}
