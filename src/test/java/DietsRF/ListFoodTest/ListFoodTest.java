package DietsRF.ListFoodTest;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.Assert;

import repositories.AdministratorRepository;
import services.FoodService;
import services.PlanService;
import utilities.PopulateDatabase;
import funcionalRequirement.GlobalTest;

@ContextConfiguration(locations = { "classpath:spring/datasource.xml",
		"classpath:spring/config/packages.xml" })
@RunWith(SpringJUnit4ClassRunner.class)
public class ListFoodTest extends GlobalTest {

	@Autowired
	AdministratorRepository administratorRepository;

	@Autowired
	FoodService foodService;

	@Autowired
	PlanService planService;

	@Before
	public void setUp() {
		PopulateDatabase.main(null);
	}

	@Test
	public void testListFoodAdmin() {

		authenticate("admin");

		int sizeFoods = foodService.findAll().size();

		Assert.isTrue(sizeFoods == 8);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testListFoodCustomer() {

		authenticate("customer1");

		int sizeEdits = foodService.findAll().size();

	}

	@Test(expected = IllegalArgumentException.class)
	public void testListException() {

		authenticate("admin");

		int sizeFoods = foodService.findAll().size();

		Assert.isTrue(sizeFoods != 8);
	}

}
