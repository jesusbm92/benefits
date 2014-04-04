package RF.ListCommentTest;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.Assert;

import repositories.AdministratorRepository;
import services.CommentService;
import services.PlanService;
import utilities.PopulateDatabase;
import funcionalRequirement.GlobalTest;

@ContextConfiguration(locations = { "classpath:spring/datasource.xml",
		"classpath:spring/config/packages.xml" })
@RunWith(SpringJUnit4ClassRunner.class)
public class ListCommentTest extends GlobalTest {

	@Autowired
	AdministratorRepository administratorRepository;

	@Autowired
	CommentService commentService;

	@Autowired
	PlanService planService;

	@Before
	public void setUp() {
		PopulateDatabase.main(null);
	}

	@Test
	public void testListCommentAdmin() {

		authenticate("admin");

		int sizeComments = commentService.findAll().size();

		Assert.isTrue(sizeComments == 1);
	}

	@Test
	public void testListCommentCustomer() {

		authenticate("customer1");

		int sizeComments = commentService.findAll().size();

		Assert.isTrue(sizeComments == 1);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testAddCommentNotLogin() {

		authenticate("admin");

		int sizeComments = commentService.findAll().size();

		Assert.isTrue(sizeComments != 1);
	}

}
