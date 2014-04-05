package RF.RegisterAdministratorTest;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.Assert;

import services.AdministratorService;
import utilities.PopulateDatabase;
import domain.Administrator;
import funcionalRequirement.GlobalTest;

@ContextConfiguration(locations = { "classpath:spring/datasource.xml",
		"classpath:spring/config/packages.xml" })
@RunWith(SpringJUnit4ClassRunner.class)
public class RegisterAsAdministratorTest extends GlobalTest {

	@Autowired
	AdministratorService administratorService;

	@Before
	public void setUp() {
		PopulateDatabase.main(null);
	}

	@Test
	public void testRegisterAsAdministrator() {

		authenticate("admin");

		int numberOfAdministratorsBefore = administratorService.findAll()
				.size();

		Administrator administrator = administratorService.create();

		administrator.setEmail("test@email.dp");
		administrator.setName("testName");
		administrator.setSurname("testSurname");
		administrator.setNationality("nationalityTest");
		administrator.setCity("cityTest");
		administrator.getUserAccount().setPassword("testPassword");
		administrator.getUserAccount().setUsername("testUsername");

		administratorService.save(administrator);

		int numberOfAdministratorsAfter = administratorService.findAll().size();

		Assert.isTrue(numberOfAdministratorsBefore + 1 == numberOfAdministratorsAfter);

	}

	@Test(expected = IllegalArgumentException.class)
	public void testRegisterAsAdministratorNoLogin() {

		int numberOfAdministratorsBefore = administratorService.findAll()
				.size();

		Administrator administrator = administratorService.create();

		administrator.setEmail("test@email.dp");
		administrator.setName("testName");
		administrator.setSurname("testSurname");
		administrator.setNationality("nationalityTest");
		administrator.setCity("cityTest");
		administrator.getUserAccount().setPassword("testPassword");
		administrator.getUserAccount().setUsername("testUsername");

		administratorService.save(administrator);

		int numberOfAdministratorsAfter = administratorService.findAll().size();

		Assert.isTrue(numberOfAdministratorsBefore + 1 == numberOfAdministratorsAfter);

	}

	@Test(expected = IllegalArgumentException.class)
	public void testRegisterAsAdministratorLoginCustomer() {

		authenticate("customer1");

		int numberOfAdministratorsBefore = administratorService.findAll()
				.size();

		Administrator administrator = administratorService.create();

		administrator.setEmail("test@email.dp");
		administrator.setName("testName");
		administrator.setSurname("testSurname");
		administrator.setNationality("nationalityTest");
		administrator.setCity("cityTest");
		administrator.getUserAccount().setPassword("testPassword");
		administrator.getUserAccount().setUsername("testUsername");

		administratorService.save(administrator);

		int numberOfAdministratorsAfter = administratorService.findAll().size();

		Assert.isTrue(numberOfAdministratorsBefore + 1 == numberOfAdministratorsAfter);

	}

}