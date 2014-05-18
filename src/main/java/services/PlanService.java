package services;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.PlanRepository;
import domain.Customer;
import domain.Goals;
import domain.Language;
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

	@Autowired
	private AdministratorService adminService;

	@Autowired
	private CustomerService customerService;

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
		Assert.notNull(adminService.findByPrincipal());
		Plan plan = new Plan();

		return plan;
	}

	/**
	 * Devuelve una colección con todos los objetos de tipo Plan
	 * 
	 * @return Collection<Plan> plans
	 */
	public Collection<Plan> findAll() {
		Assert.isTrue(userService.IAmAnAdmin());
		return planRepository.findAll();
	}

	public Collection<Plan> findAllLanguage(String language) {
		Assert.isTrue(userService.IAmAnAdmin());
		Collection<Plan> result;
		if (language.equals(Language.english.toString())) {
			result = planRepository.findAllLanguage(Language.english);
		} else {
			result = planRepository.findAllLanguage(Language.spanish);
		}
		return result;
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
	 * través del repositorio PlanRepository
	 * 
	 * @return void
	 */
	public void save(Plan plan) {
		Assert.notNull(plan);

		// TODO Restricciones de Save
		Assert.notNull(adminService.findByPrincipal());
		// Assert.isTrue(findCustomersByPlan(plan.getId()).isEmpty());
		// ES NECESARIO QUE NO HAYA CUSTOMERS PARA MODIFICARLOS?

		planRepository.save(plan);
	}

	/**
	 * Elimina el objeto de tipo Plan de la base de datos a través del
	 * repositorio PlanRepository
	 * 
	 * @return void
	 */
	public void delete(Plan plan) {
		Assert.notNull(plan);
		// TODO Restricciones de Borrado
		Assert.notNull(adminService.findByPrincipal());
		Assert.isTrue(findCustomersByPlan(plan.getId()).isEmpty());

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

	public Collection<Customer> findCustomersByPlan(int planId) {
		Assert.isTrue(userService.IAmAnAdmin());
		Collection<Customer> customers = planRepository
				.findCustomersByPlan(planId);
		return customers;
	}

	public Collection<String> findAllGoals() {
		Assert.isTrue(userService.IAmACustomer());
		Collection<String> goals = planRepository.findAllGoals();
		return goals;
	}

	public Collection<Plan> findPlansByDiet(int dietId) {
		Assert.isTrue(userService.IAmAnAdmin());
		Collection<Plan> plans = planRepository.findPlansByDiet(dietId);
		return plans;
	}

	public boolean request(Goals goal, Customer customer, String language) {

		// Se comprueba que el goal que se pasa como parámetro está
		// dentro
		// de los valores del enumerado.
		Assert.isTrue(Goals.GAIN_MUSCLE_MASS.equals(goal)
				|| Goals.KEEP_FIT.equals(goal)
				|| Goals.LOSE_WEIGHT.equals(goal));

		Collection<Plan> plansForGoal;
		if (language.equals(Language.english.toString())) {
			plansForGoal = planRepository.findPlansByGoal(goal,
					customer.getBodyfat(), customer.getWeight(),
					Language.english);
		} else {
			plansForGoal = planRepository.findPlansByGoal(goal,
					customer.getBodyfat(), customer.getWeight(),
					Language.spanish);
		}

		ArrayList<Plan> planList = new ArrayList<Plan>(plansForGoal);
		if (planList.isEmpty()) {
			return false;
		} else {
			Plan selectedPlan = planList.get((int) Math.random()
					* planList.size());
			customerService.changeCustomerPlan(selectedPlan);
			return true;
		}
	}

	// Assertions

	public Collection<Plan> findByGoal(Goals goal) {
		// TODO Auto-generated method stub
		return planRepository.findPlansByGoal(goal);
	}

}
