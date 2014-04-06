package RF.RegisterCustomer;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.Assert;

import services.CustomerService;
import utilities.PopulateDatabase;
import domain.Customer;
import funcionalRequirement.GlobalTest;

@ContextConfiguration(locations = { "classpath:spring/datasource.xml",
		"classpath:spring/config/packages.xml" })
@RunWith(SpringJUnit4ClassRunner.class)
public class RegisterCustomerTest extends GlobalTest {

	@Autowired
	CustomerService customerService;

	@Before
	public void setUp() {
		PopulateDatabase.main(null);
	}

	@After
	public void tearDown() {
		logout();
	}

	@Test
	public void testRegisterAsNonLogged() {

		int numberOfCustomersBefore = customerService.findAll().size();

		Customer customer = customerService.create();

		customer.setEmail("test@email.dp");
		customer.setName("testName");
		customer.setSurname("testSurname");
		customer.setNationality("nationalityTest");
		customer.setCity("cityTest");
		customer.getUserAccount().setPassword("testPassword");
		customer.getUserAccount().setUsername("testUsername");

		customerService.save(customer);

		int numberOfCustomersAfter = customerService.findAll().size();

		Assert.isTrue(numberOfCustomersBefore + 1 == numberOfCustomersAfter);

	}

	@Test
	public void testRegisterCustomerAsAdministrator() {

		authenticate("admin");

		int numberOfCustomersBefore = customerService.findAll().size();

		Customer customer = customerService.create();

		customer.setEmail("test@email.dp");
		customer.setName("testName");
		customer.setSurname("testSurname");
		customer.setNationality("nationalityTest");
		customer.setCity("cityTest");
		customer.getUserAccount().setPassword("testPassword");
		customer.getUserAccount().setUsername("testUsername");

		customerService.save(customer);

		int numberOfCustomersAfter = customerService.findAll().size();

		Assert.isTrue(numberOfCustomersBefore + 1 == numberOfCustomersAfter);

	}

	@Test
	public void testRegisterAsAdministratorLoginCustomer() {

		authenticate("customer1");

		int numberOfCustomersBefore = customerService.findAll().size();

		Customer customer = customerService.create();

		customer.setEmail("test@email.dp");
		customer.setName("testName");
		customer.setSurname("testSurname");
		customer.setNationality("nationalityTest");
		customer.setCity("cityTest");
		customer.getUserAccount().setPassword("testPassword");
		customer.getUserAccount().setUsername("testUsername");

		customerService.save(customer);

		int numberOfCustomersAfter = customerService.findAll().size();

		Assert.isTrue(numberOfCustomersBefore + 1 == numberOfCustomersAfter);

	}
}