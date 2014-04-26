package DietsRF.ListDayByMealTest;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.Assert;

import repositories.AdministratorRepository;
import services.DayService;
import services.MealService;
import utilities.PopulateDatabase;
import domain.Meal;
import funcionalRequirement.GlobalTest;

@ContextConfiguration(locations = { "classpath:spring/datasource.xml",
		"classpath:spring/config/packages.xml" })
@RunWith(SpringJUnit4ClassRunner.class)
public class ListDayByMealTest extends GlobalTest {

	@Autowired
	AdministratorRepository administratorRepository;

	@Autowired
	DayService dayService;

	@Autowired
	MealService mealService;

	@Before
	public void setUp() {
		PopulateDatabase.main(null);
	}

	@Test
	public void testListDietAdmin() {

		authenticate("admin");

		Meal meal = mealService.findOne(7);
		int sizeDays = dayService.findDaysByMeal(meal.getId()).size();

		Assert.isTrue(sizeDays == 1);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testListDietCustomer() {

		authenticate("customer1");

		int sizeDays = dayService.findDaysByMeal(29).size();

	}

	@Test(expected = IllegalArgumentException.class)
	public void testListException() {

		authenticate("admin");

		Meal meal = mealService.findOne(7);
		int sizeDays = dayService.findDaysByMeal(meal.getId()).size();

		Assert.isTrue(sizeDays != 1);
	}

}
