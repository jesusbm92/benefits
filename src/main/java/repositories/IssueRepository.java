package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Issue;

@Repository
public interface IssueRepository extends JpaRepository<Issue, Integer> {

	@Query("select i from Issue i where i.plan.id= ?1")
	Collection<Issue> findByPlan(int planId);

}
