package DietsRF.ListPlansByDietTest;

import java.util.Collection;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.Assert;

import repositories.AdministratorRepository;
import services.DietService;
import services.PlanService;
import utilities.PopulateDatabase;
import domain.Diet;
import domain.Plan;
import funcionalRequirement.GlobalTest;

@ContextConfiguration(locations = { "classpath:spring/datasource.xml",
		"classpath:spring/config/packages.xml" })
@RunWith(SpringJUnit4ClassRunner.class)
public class ListPlansByDietTest extends GlobalTest {

	@Autowired
	AdministratorRepository administratorRepository;

	@Autowired
	DietService dietService;

	@Autowired
	PlanService planService;

	@Before
	public void setUp() {
		PopulateDatabase.main(null);
	}

	@Test
	public void testListPlansByDietAdmin() {

		authenticate("admin");

		Diet diet = dietService.findOne(57);

		Collection<Plan> plans = planService.findPlansByDiet(diet.getId());

		Assert.isTrue(plans.size() == 1);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testListDietCustomer() {

		authenticate("customer1");

		Diet diet = dietService.findOne(57);

		Collection<Plan> plans = planService.findPlansByDiet(diet.getId());

	}

	@Test(expected = IllegalArgumentException.class)
	public void testListDayException() {

		authenticate("admin");

		Diet diet = dietService.findOne(57);

		Collection<Plan> plans = planService.findPlansByDiet(diet.getId());

		Assert.isTrue(plans.size() != 1);
	}

}
