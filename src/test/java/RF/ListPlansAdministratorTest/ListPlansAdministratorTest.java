package RF.ListPlansAdministratorTest;

import java.util.Collection;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.Assert;

import services.AdministratorService;
import services.PlanService;
import utilities.PopulateDatabase;
import domain.Plan;
import funcionalRequirement.GlobalTest;

@ContextConfiguration(locations = { "classpath:spring/datasource.xml",
		"classpath:spring/config/packages.xml" })
@RunWith(SpringJUnit4ClassRunner.class)
public class ListPlansAdministratorTest extends GlobalTest {

	@Autowired
	AdministratorService administratorService;

	@Autowired
	PlanService planService;

	@Before
	public void setUp() {
		PopulateDatabase.main(null);
	}

	@Test
	public void listPlanAdministratorTest() {

		authenticate("admin");

		Collection<Plan> plans = planService.findAll();
		Assert.isTrue(plans.size() == 2);

	}

	@Test(expected = IllegalArgumentException.class)
	public void listPlanAdministratorCustomerLoginTest() {

		authenticate("customer1");

		Collection<Plan> plans = planService.findAll();
		Assert.isTrue(plans.size() == 0);
	}

	@Test(expected = IllegalArgumentException.class)
	public void listPlanAdministratorCustomerNoLogTest() {

		Collection<Plan> plans = planService.findAll();
		Assert.isTrue(plans.size() == 0);
	}
}