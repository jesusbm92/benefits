package RF.AddIssueTest;

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
import domain.Issue;
import domain.Plan;
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
	public void testAddIssueCustomer() {

		authenticate("customer1");

		int sizeBefore = issueService.findAll().size();

		Plan plan = planService.findOne(67);
		Issue issue = issueService.create(plan.getId());
		issue.setDescription("New Issue");

		issueService.save(issue);

		int sizeAfter = issueService.findAll().size();

		Assert.isTrue(sizeAfter > sizeBefore);
	}

	@Test(expected = AssertionError.class)
	public void testAddIssueNotLogin() {

		Plan plan = planService.findOne(67);
		Issue issue = issueService.create(plan.getId());
		issue.setDescription("New Issue");

		issueService.save(issue);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testCreateIssueException() {

		authenticate("customer1");

		int sizeBefore = issueService.findAll().size();

		Plan plan = planService.findOne(67);
		Issue issue = issueService.create(plan.getId());
		issue.setDescription("New Issue");

		issueService.save(issue);

		Assert.isTrue(sizeBefore < (sizeBefore + 1));
	}

}
