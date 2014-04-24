package DietsRF.CreateDayTest;

import java.util.ArrayList;
import java.util.Collection;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.Assert;

import services.DayService;
import services.MealService;
import services.SponsorService;
import utilities.PopulateDatabase;
import domain.Day;
import domain.Days;
import domain.Meal;
import funcionalRequirement.GlobalTest;

@ContextConfiguration(locations = { "classpath:spring/datasource.xml",
		"classpath:spring/config/packages.xml" })
@RunWith(SpringJUnit4ClassRunner.class)
public class CreateDayTest extends GlobalTest {

	@Autowired
	DayService dayService;

	@Autowired
	MealService mealService;

	@Autowired
	SponsorService sponsorService;

	@Before
	public void setUp() {
		PopulateDatabase.main(null);
	}

	@Test
	public void testAddDayAdmin() {

		authenticate("admin");

		int sizeBefore = dayService.findAll().size();

		Day day = dayService.create();
		day.setName(Days.SATURDAY);
		Collection<Meal> meals = new ArrayList<Meal>();
		meals.add(mealService.findOne(7));
		day.setMeals(meals);
		dayService.save(day);

		int sizeAfter = dayService.findAll().size();
		Assert.isTrue(sizeAfter > sizeBefore);
	}

	@Test(expected = java.lang.AssertionError.class)
	public void testAddDayCustomer() {

		authenticate("customer1");

		int sizeBefore = dayService.findAll().size();

		Day day = dayService.create();
		day.setName(Days.SATURDAY);
		Collection<Meal> meals = new ArrayList<Meal>();
		meals.add(mealService.findOne(7));
		day.setMeals(meals);
		dayService.save(day);

		int sizeAfter = dayService.findAll().size();

		Assert.isTrue(sizeAfter > sizeBefore);
	}

	@Test(expected = AssertionError.class)
	public void testAddEditDayNotLogin() {

		int sizeBefore = dayService.findAll().size();

		Day day = dayService.create();
		day.setName(Days.SATURDAY);
		Collection<Meal> meals = new ArrayList<Meal>();
		meals.add(mealService.findOne(7));
		day.setMeals(meals);
		dayService.save(day);

		int sizeAfter = dayService.findAll().size();

		Assert.isTrue(sizeAfter > sizeBefore);
	}
}
