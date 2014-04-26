package DietsRF.ListDietAssignedAndNotAssignedTest;

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
public class ListDietAssignedAndNotAssignedTest extends GlobalTest {

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
	public void testListDietAssignedAdmin() {

		authenticate("admin");

		int sizeDiets = dietService.findAssigned().size();

		Assert.isTrue(sizeDiets == 2);
	}

	@Test
	public void testListDietNotAssignedAdmin() {

		authenticate("admin");

		int sizeDiets = dietService.findFree().size();

		Assert.isTrue(sizeDiets == 1);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testListDietAssignedCustomer() {

		authenticate("customer1");

		int sizeEdits = dietService.findAssigned().size();

	}

	@Test(expected = IllegalArgumentException.class)
	public void testListDietNotAssignedCustomer() {

		authenticate("customer1");

		int sizeEdits = dietService.findFree().size();

	}

	@Test(expected = IllegalArgumentException.class)
	public void testListDietAssignedAdminException() {

		authenticate("admin");

		int sizeDiets = dietService.findAssigned().size();

		Assert.isTrue(sizeDiets != 2);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testListDietNotAssignedAdminException() {

		authenticate("admin");

		int sizeDiets = dietService.findFree().size();

		Assert.isTrue(sizeDiets != 1);
	}

}
