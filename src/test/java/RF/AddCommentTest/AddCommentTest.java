package RF.AddCommentTest;

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
import domain.Comment;
import domain.Plan;
import funcionalRequirement.GlobalTest;

@ContextConfiguration(locations = { "classpath:spring/datasource.xml",
		"classpath:spring/config/packages.xml" })
@RunWith(SpringJUnit4ClassRunner.class)
public class AddCommentTest extends GlobalTest {

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
	public void testAddCommentAdmin() {

		authenticate("admin");

		int sizeBefore = commentService.findAll().size();

		Plan plan = planService.findOne(67);
		Comment comment = commentService.create(plan.getId());
		comment.setContent("New Comment");

		commentService.save(comment);

		int sizeAfter = commentService.findAll().size();

		Assert.isTrue(sizeAfter > sizeBefore);
	}

	@Test
	public void testAddCommentCustomer() {

		authenticate("customer1");

		int sizeBefore = commentService.findAll().size();

		Plan plan = planService.findOne(67);
		Comment comment = commentService.create(plan.getId());
		comment.setContent("New Comment");

		commentService.save(comment);

		int sizeAfter = commentService.findAll().size();

		Assert.isTrue(sizeAfter > sizeBefore);
	}

	@Test(expected = AssertionError.class)
	public void testAddCommentNotLogin() {

		Plan plan = planService.findOne(67);
		Comment comment = commentService.create(plan.getId());
		comment.setContent("New Comment");

		commentService.save(comment);
	}

	@Test(expected = AssertionError.class)
	public void testCreateCommentException() {

		authenticate("admin");

		int sizeBefore = commentService.findAll().size();

		Plan plan = planService.findOne(67);
		Comment comment = commentService.create(plan.getId());
		comment.setContent("New Comment");

		commentService.save(comment);

		Assert.isTrue(sizeBefore < (sizeBefore + 1));
	}

}
