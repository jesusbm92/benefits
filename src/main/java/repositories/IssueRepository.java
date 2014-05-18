package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Issue;
import domain.Language;

@Repository
public interface IssueRepository extends JpaRepository<Issue, Integer> {

	@Query("select i from Issue i where i.plan.id= ?1")
	Collection<Issue> findByPlan(int planId);

	@Query("select i from Issue i inner join i.plan p where p.entityLanguage = ?1")
	Collection<Issue> findAllLanguage(Language language);

}
