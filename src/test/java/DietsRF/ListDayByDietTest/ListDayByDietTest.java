package DietsRF.ListDayByDietTest;

import java.util.Collection;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.Assert;

import repositories.AdministratorRepository;
import services.DayService;
import services.DietService;
import utilities.PopulateDatabase;
import domain.Day;
import domain.Diet;
import funcionalRequirement.GlobalTest;

@ContextConfiguration(locations = { "classpath:spring/datasource.xml",
		"classpath:spring/config/packages.xml" })
@RunWith(SpringJUnit4ClassRunner.class)
public class ListDayByDietTest extends GlobalTest {

	@Autowired
	AdministratorRepository administratorRepository;

	@Autowired
	DietService dietService;

	@Autowired
	DayService dayService;

	@Before
	public void setUp() {
		PopulateDatabase.main(null);
	}

	@Test
	public void testListDaysByDietAdmin() {

		authenticate("admin");

		Diet diet = dietService.findOne(57);

		Collection<Day> days = dayService.findDaysByDiet(diet.getId());

		Assert.isTrue(days.size() == 4);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testListDietCustomer() {

		authenticate("customer1");

		Diet diet = dietService.findOne(57);

		Collection<Day> days = dayService.findDaysByDiet(diet.getId());

	}

	@Test(expected = IllegalArgumentException.class)
	public void testListDayException() {

		authenticate("admin");

		Diet diet = dietService.findOne(57);

		Collection<Day> days = dayService.findDaysByDiet(diet.getId());

		Assert.isTrue(days.size() != 4);
	}

}
