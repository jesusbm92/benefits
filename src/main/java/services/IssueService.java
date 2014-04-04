package services;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.IssueRepository;
import domain.Issue;

@Transactional
@Service
public class IssueService {

	// Managed repository-----------------------

	@Autowired
	private IssueRepository issueRepository;

	// Supporting services -----------------
	@Autowired
	private CustomerService customerService;
	@Autowired
	private AdministratorService administratorService;
	@Autowired
	private PlanService planService;

	// Constructors --------------------------
	public IssueService() {
		super();
	}

	// Simple CRUD methods -----------------
	/**
	 * Constructor por defecto de IssueService
	 * 
	 * @return Issue issue
	 */
	public Issue create(int planId) {
		Issue issue = new Issue();
		issue.setCustomer(customerService.findByPrincipal());
		issue.setPlan(planService.findOne(planId));

		return issue;
	}

	/**
	 * Devuelve una colecci�n con todos los objetos de tipo Issue
	 * 
	 * @return Collection<Issue> issues
	 */
	public Collection<Issue> findAll() {
		return issueRepository.findAll();
	}

	/**
	 * Devuelve una instancia de un objetos de tipo Issue En caso de no
	 * encontrarse, devuelve null
	 * 
	 * @return Issue issue
	 */
	public Issue findOne(int issueId) {
		return issueRepository.findOne(issueId);
	}

	/**
	 * Persiste (guarda o crea) el objeto de tipo Issue en la base de datos a
	 * trav�s del repositorio IssueRepository
	 * 
	 * @return void
	 */
	public void save(Issue issue) {
		// TODO Restricciones de Save
		Assert.notNull(issue);
		Assert.isTrue((customerService.IAmACustomer()));
		// si es administrador tmb a�adir esto:
		// || (administratorService.IAmAnAdmin()))
		issueRepository.save(issue);
	}

	public void delete(Issue issue) {
		Assert.notNull(issue);
		// TODO Restricciones de Borrado
		Assert.isTrue((customerService.IAmACustomer()));

		issueRepository.delete(issue);
	}

	public Collection<Issue> findByPlan(int planId) {
		// TODO Auto-generated method stub
		return issueRepository.findByPlan(planId);
	}

	// Other business methods ----------------

	// Assertions

}
