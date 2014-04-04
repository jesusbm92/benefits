package RF.DeleteIssueTest;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import repositories.AdministratorRepository;
import services.IssueService;
import services.PlanService;
import utilities.PopulateDatabase;
import funcionalRequirement.GlobalTest;

@ContextConfiguration(locations = { "classpath:spring/datasource.xml",
		"classpath:spring/config/packages.xml" })
@RunWith(SpringJUnit4ClassRunner.class)
public class AddIssueTest extends GlobalTest {

	@Autowired
	AdministratorRepository administratorRepository;

	@Autowired
	IssueService issueService;

	@Autowired
	PlanService planService;

	@Before
	public void setUp() {
		PopulateDatabase.main(null);
	}

	@Test
	public void testDeleteIssueCustomer() {

		authenticate("customer1");

		// Crear issue en el populate.
		int sizeBefore = issueService.findAll().size();
		// Issue issue = issueService.findOne();
		//
		// issueService.delete(issue);
		//
		// int sizeAfter = issueService.findAll().size();
		//
		// Assert.isTrue(sizeAfter > sizeBefore);
	}

	@Test(expected = AssertionError.class)
	public void testAddIssueNotLogin() {

		// Issue issue = issueService.findOne();
		//
		// issueService.delete(issue);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testCreateIssueException() {

		authenticate("customer1");

		int sizeBefore = issueService.findAll().size();
		// Issue issue = issueService.findOne();
		//
		// issueService.delete(issue);
		//
		// int sizeAfter = issueService.findAll().size();
		//
		// Assert.isTrue(sizeBefore < (sizeBefore+1));
	}

}
