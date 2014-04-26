package DietsRF.ListDietTest;

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
import funcionalRequirement.GlobalTest;

@ContextConfiguration(locations = { "classpath:spring/datasource.xml",
		"classpath:spring/config/packages.xml" })
@RunWith(SpringJUnit4ClassRunner.class)
public class ListDietTest extends GlobalTest {

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
	public void testListDietAdmin() {

		authenticate("admin");

		int sizeDiets = dietService.findAll().size();

		Assert.isTrue(sizeDiets == 3);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testListDietCustomer() {

		authenticate("customer1");

		int sizeEdits = dietService.findAll().size();

	}

	@Test(expected = IllegalArgumentException.class)
	public void testListException() {

		authenticate("admin");

		int sizeDiets = dietService.findAll().size();

		Assert.isTrue(sizeDiets != 3);
	}

}
