package RF.ListPlanCustomerTest;

import java.util.Collection;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.Assert;

import repositories.AdministratorRepository;
import services.PlanService;
import utilities.PopulateDatabase;
import domain.Plan;
import funcionalRequirement.GlobalTest;

@ContextConfiguration(locations = { "classpath:spring/datasource.xml",
		"classpath:spring/config/packages.xml" })
@RunWith(SpringJUnit4ClassRunner.class)
public class ListPlanCustomerTest extends GlobalTest {

	@Autowired
	AdministratorRepository administratorRepository;

	@Autowired
	PlanService planService;

	@Before
	public void setUp() {
		PopulateDatabase.main(null);
	}

	@Test
	public void testListPlanCustomer() {

		authenticate("customer1");

		Collection<Plan> planCustomer = planService.findPlanByCustomer(70);
		int sizePlan = planCustomer.size();

		Assert.isTrue(sizePlan == 1);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testAddPlanNotLogin() {

		authenticate("customer1");

		Collection<Plan> planCustomer = planService.findPlanByCustomer(70);
		int sizePlan = planCustomer.size();

		Assert.isTrue(sizePlan != 1);
	}

}
