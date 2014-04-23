package DietsRF.AddEditDeleteDietTest;

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
import services.DietService;
import services.SponsorService;
import utilities.PopulateDatabase;
import domain.Day;
import domain.Diet;
import funcionalRequirement.GlobalTest;

@ContextConfiguration(locations = { "classpath:spring/datasource.xml",
		"classpath:spring/config/packages.xml" })
@RunWith(SpringJUnit4ClassRunner.class)
public class AddEditDeleteDietTest extends GlobalTest {

	@Autowired
	DietService dietService;

	@Autowired
	DayService dayService;

	@Autowired
	SponsorService sponsorService;

	@Before
	public void setUp() {
		PopulateDatabase.main(null);
	}

	@Test
	public void testAddDietAdmin() {

		authenticate("admin");

		int sizeBefore = dietService.findAll().size();

		Diet diet = dietService.create();
		diet.setDescription("prueba");
		diet.setName("prueba");
		Day day = dayService.findOne(51);
		Collection<Day> days = new ArrayList<Day>();
		diet.setSponsor(sponsorService.findOne(5));
		days.add(day);
		diet.setDays(days);
		dietService.save(diet);

		int sizeAfter = dietService.findAll().size();

		Assert.isTrue(sizeAfter > sizeBefore);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testAddDietCustomer() {

		authenticate("customer1");

		int sizeBefore = dietService.findAll().size();

		Diet diet = dietService.create();
		diet.setDescription("prueba");
		diet.setName("prueba");
		Day day = dayService.findOne(51);
		Collection<Day> days = new ArrayList<Day>();
		diet.setSponsor(sponsorService.findOne(5));
		days.add(day);
		diet.setDays(days);
		dietService.save(diet);

		int sizeAfter = dietService.findAll().size();

		Assert.isTrue(sizeAfter > sizeBefore);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testAddEditDietNotLogin() {

		int sizeBefore = dietService.findAll().size();

		Diet diet = dietService.create();
		diet.setDescription("prueba");
		diet.setName("prueba");
		Day day = dayService.findOne(51);
		Collection<Day> days = new ArrayList<Day>();
		diet.setSponsor(sponsorService.findOne(5));
		days.add(day);
		diet.setDays(days);
		dietService.save(diet);

		int sizeAfter = dietService.findAll().size();

		Assert.isTrue(sizeAfter > sizeBefore);
	}

	@Test(expected = AssertionError.class)
	public void testAddDietException() {

		authenticate("admin");

		Diet diet = dietService.create();
		diet.setDescription("prueba");
		diet.setName("prueba");
		Day day = dayService.findOne(51);
		Collection<Day> days = new ArrayList<Day>();
		diet.setSponsor(sponsorService.findOne(5));
		days.add(day);
		dietService.save(diet);
	}

	@Test
	public void testEditDiet() {

		authenticate("admin");
		Diet diet = dietService.findOne(59);
		diet.setDescription("prueba");
		dietService.save(diet);

	}

	// @Test(expected = AssertionError.class)
	// public void testRemoveDietException() {
	//
	// authenticate("admin");
	//
	// // Falla asignado a un plan.
	// Diet diet = dietService.findOne(57);
	// dietService.delete(diet);
	//
	// }

	@Test(expected = AssertionError.class)
	public void testEditDietException() {

		authenticate("admin");

		// Falla asignado a un plan.
		Diet diet = dietService.findOne(57);
		diet.setDescription("prueba");
		dietService.save(diet);

	}

	@Test
	public void testRemoveDiet() {

		authenticate("admin");

		int sizeBefore = dietService.findAll().size();

		Diet diet = dietService.findOne(59);
		dietService.delete(diet);

		int sizeAfter = dietService.findAll().size();

		Assert.isTrue(sizeAfter < sizeBefore);

	}
}
