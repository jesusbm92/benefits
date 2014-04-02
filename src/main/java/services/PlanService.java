package services;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.PlanRepository;
import domain.Plan;

@Transactional
@Service
public class PlanService {

	// Managed repository-----------------------

	@Autowired
	private PlanRepository planRepository;

	// Supporting services -----------------

	@Autowired
	private UserService userService;

	// Constructors --------------------------
	public PlanService() {
		super();
	}

	// Simple CRUD methods -----------------
	/**
	 * Constructor por defecto de PlanService
	 * 
	 * @return Plan plan
	 */
	public Plan create() {
		Plan plan = new Plan();

		return plan;
	}

	/**
	 * Devuelve una colecci�n con todos los objetos de tipo Plan
	 * 
	 * @return Collection<Plan> plans
	 */
	public Collection<Plan> findAll() {
		return planRepository.findAll();
	}

	/**
	 * Devuelve una instancia de un objetos de tipo Plan En caso de no
	 * encontrarse, devuelve null
	 * 
	 * @return Plan plan
	 */
	public Plan findOne(int planId) {
		return planRepository.findOne(planId);
	}

	/**
	 * Persiste (guarda o crea) el objeto de tipo Plan en la base de datos a
	 * trav�s del repositorio PlanRepository
	 * 
	 * @return void
	 */
	public void save(Plan plan) {
		Assert.notNull(plan);
		// TODO Restricciones de Save

		planRepository.save(plan);
	}

	/**
	 * Elimina el objeto de tipo Plan de la base de datos a trav�s del
	 * repositorio PlanRepository
	 * 
	 * @return void
	 */
	public void delete(Plan plan) {
		Assert.notNull(plan);
		// TODO Restricciones de Borrado

		planRepository.delete(plan);
	}

	// Other business methods ----------------
	public Collection<Plan> findPlanByCustomer(int customerId) {
		Assert.isTrue(userService.IAmACustomer());
		Plan plan = planRepository.findByCustomer(customerId);
		Collection<Plan> res = new ArrayList<Plan>();
		res.add(plan);
		return res;
	}

	// Assertions

}
