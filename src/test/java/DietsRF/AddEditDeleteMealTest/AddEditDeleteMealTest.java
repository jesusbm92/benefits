package DietsRF.AddEditDeleteMealTest;

import java.util.ArrayList;
import java.util.Collection;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.Assert;

import services.AmountService;
import services.DayService;
import services.MealService;
import services.SponsorService;
import utilities.PopulateDatabase;
import domain.Amount;
import domain.Day;
import domain.Meal;
import domain.Meals;
import funcionalRequirement.GlobalTest;

@ContextConfiguration(locations = { "classpath:spring/datasource.xml",
		"classpath:spring/config/packages.xml" })
@RunWith(SpringJUnit4ClassRunner.class)
public class AddEditDeleteMealTest extends GlobalTest {

	@Autowired
	MealService mealService;

	@Autowired
	DayService dayService;

	@Autowired
	AmountService amountService;

	@Autowired
	SponsorService sponsorService;

	@Before
	public void setUp() {
		PopulateDatabase.main(null);
	}

	@Test
	public void testAddMealAdmin() {

		authenticate("admin");

		int sizeBefore = mealService.findAll().size();

		Meal meal = mealService.create();
		meal.setName(Meals.BREAKFAST);
		Collection<Day> days = new ArrayList<Day>();
		Collection<Amount> amounts = new ArrayList<Amount>();
		days.add(dayService.findOne(50));
		amounts.add(amountService.findOne(29));
		meal.setDays(days);
		meal.setAmounts(amounts);
		mealService.save(meal);

		int sizeAfter = mealService.findAll().size();

		Assert.isTrue(sizeAfter > sizeBefore);
	}

	@Test(expected = java.lang.AssertionError.class)
	public void testAddMealCustomer() {

		authenticate("customer1");

		mealService.save(mealService.create());

	}

	@Test(expected = java.lang.Exception.class)
	public void testAddEditMealNotLogin() {

		mealService.save(mealService.create());

	}

	@Test(expected = java.lang.AssertionError.class)
	public void testAddMealException() {
		authenticate("admin");
		Meal meal = mealService.create();
		mealService.save(meal);

	}

	@Test
	public void testEditMeal() {

		authenticate("admin");
		Meal meal = mealService.findOne(7);
		meal.setName(Meals.BREAKFAST);
		mealService.save(meal);

	}

	// @Test
	// public void testRemoveMeal() {
	//
	// authenticate("admin");
	//
	// int sizeBefore = mealService.findAll().size();
	//
	// Meal meal = mealService.findOne(7);
	// mealService.delete(meal);
	//
	// int sizeAfter = mealService.findAll().size();
	//
	// Assert.isTrue(sizeAfter < sizeBefore);
	//
	// }
}
