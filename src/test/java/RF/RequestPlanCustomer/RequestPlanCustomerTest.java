package RF.RequestPlanCustomer;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.Assert;

import repositories.AdministratorRepository;
import services.CustomerService;
import services.PlanService;
import utilities.PopulateDatabase;
import domain.Customer;
import domain.Plan;
import funcionalRequirement.GlobalTest;

@ContextConfiguration(locations = { "classpath:spring/datasource.xml",
		"classpath:spring/config/packages.xml" })
@RunWith(SpringJUnit4ClassRunner.class)
public class RequestPlanCustomerTest extends GlobalTest {

	@Autowired
	AdministratorRepository administratorRepository;

	@Autowired
	PlanService planService;

	@Autowired
	CustomerService customerService;

	@Before
	public void setUp() {
		PopulateDatabase.main(null);
	}

	@Test
	public void testRequestPlanCustomer() {

		authenticate("felix");

		Plan plan = planService.findOne(68);
		Customer customer = customerService.findByPrincipal();
		planService.request(plan.getGoal(), customer);

		Assert.isTrue(!(customer.getPlan() == null));

	}

	@Test(expected = IllegalArgumentException.class)
	public void testAddPlanException() {

		authenticate("felix");

		Plan plan = planService.findOne(68);

		Customer customer = customerService.findByPrincipal();

		planService.request(plan.getGoal(), customer);

		Assert.isTrue((customer.getPlan() == null));

	}

	@Test(expected = IllegalArgumentException.class)
	public void testAddPlanNotLogin() {
		// Error por no estar logueado entonces falla servicio

		Plan plan = planService.findOne(68);

		Customer customer = customerService.findByPrincipal();

		planService.request(plan.getGoal(), customer);

		Assert.isTrue((customer.getPlan() == null));

	}

}
