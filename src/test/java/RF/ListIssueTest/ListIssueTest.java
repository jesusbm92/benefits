package RF.ListIssueTest;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.Assert;

import repositories.AdministratorRepository;
import services.IssueService;
import services.PlanService;
import utilities.PopulateDatabase;
import funcionalRequirement.GlobalTest;

@ContextConfiguration(locations = { "classpath:spring/datasource.xml",
		"classpath:spring/config/packages.xml" })
@RunWith(SpringJUnit4ClassRunner.class)
public class ListIssueTest extends GlobalTest {

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
	public void testListIssueAdmin() {

		authenticate("admin");

		int sizeIssues = issueService.findAll().size();

		Assert.isTrue(sizeIssues == 0);
	}

	@Test
	public void testListIssueCustomer() {

		authenticate("customer1");

		int sizeIssues = issueService.findAll().size();

		Assert.isTrue(sizeIssues == 0);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testAddIssueNotLogin() {

		authenticate("admin");

		int sizeIssues = issueService.findAll().size();

		Assert.isTrue(sizeIssues != 1);
	}

}
