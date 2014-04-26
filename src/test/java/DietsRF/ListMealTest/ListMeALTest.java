package DietsRF.ListMealTest;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.Assert;

import repositories.AdministratorRepository;
import services.MealService;
import services.PlanService;
import utilities.PopulateDatabase;
import funcionalRequirement.GlobalTest;

@ContextConfiguration(locations = { "classpath:spring/datasource.xml",
		"classpath:spring/config/packages.xml" })
@RunWith(SpringJUnit4ClassRunner.class)
public class ListMeALTest extends GlobalTest {

	@Autowired
	AdministratorRepository administratorRepository;

	@Autowired
	MealService mealService;

	@Autowired
	PlanService planService;

	@Before
	public void setUp() {
		PopulateDatabase.main(null);
	}

	@Test
	public void testListMealAdmin() {

		authenticate("admin");

		int sizeMeals = mealService.findAll().size();

		Assert.isTrue(sizeMeals == 14);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testListMealCustomer() {

		authenticate("customer1");

		int sizeEdits = mealService.findAll().size();

	}

	@Test(expected = IllegalArgumentException.class)
	public void testListException() {

		authenticate("admin");

		int sizeMeals = mealService.findAll().size();

		Assert.isTrue(sizeMeals != 14);
	}

}
