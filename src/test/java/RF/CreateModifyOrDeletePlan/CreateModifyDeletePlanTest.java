package RF.CreateModifyOrDeletePlan;

import java.util.Collection;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.Assert;

import services.AdministratorService;
import services.DietService;
import services.PlanService;
import services.TrainingService;
import utilities.PopulateDatabase;
import domain.Goals;
import domain.Plan;
import funcionalRequirement.GlobalTest;

@ContextConfiguration(locations = { "classpath:spring/datasource.xml",
		"classpath:spring/config/packages.xml" })
@RunWith(SpringJUnit4ClassRunner.class)
public class CreateModifyDeletePlanTest extends GlobalTest {

	@Autowired
	AdministratorService administratorService;

	@Autowired
	PlanService planService;

	@Autowired
	DietService dietService;

	@Autowired
	TrainingService trainingService;

	@Before
	public void setUp() {
		PopulateDatabase.main(null);
	}

	@After
	public void tearDown() {
		logout();
	}

	@Test
	public void createPlanTest() {

		authenticate("admin");

		Collection<Plan> plansbefore = planService.findAll();

		Plan plan = planService.create();
		plan.setDiet(dietService.findOne(19));
		plan.setTraining(trainingService.findOne(48));
		plan.setGoal(Goals.KEEP_FIT);
		planService.save(plan);

		Collection<Plan> plansafter = planService.findAll();
		plansafter.removeAll(plansbefore);

		Assert.notEmpty(plansafter);

	}

	@Test(expected = IllegalArgumentException.class)
	public void userCantCreatePlanTest() {

		authenticate("customer1");

		Collection<Plan> plansbefore = planService.findAll();

		Plan plan = planService.create();
		plan.setDiet(dietService.findOne(19));
		plan.setTraining(trainingService.findOne(48));
		plan.setGoal(Goals.KEEP_FIT);
		planService.save(plan);

		Collection<Plan> plansafter = planService.findAll();
		plansafter.removeAll(plansbefore);

		Assert.notEmpty(plansafter);
	}

	@Test(expected = IllegalArgumentException.class)
	public void nonRegisteredCantCreatePlanTest() {

		Collection<Plan> plansbefore = planService.findAll();

		Plan plan = planService.create();
		plan.setDiet(dietService.findOne(19));
		plan.setTraining(trainingService.findOne(48));
		plan.setGoal(Goals.KEEP_FIT);
		planService.save(plan);

		Collection<Plan> plansafter = planService.findAll();
		plansafter.removeAll(plansbefore);

		Assert.notEmpty(plansafter);
	}

	@Test
	public void adminModifyPlanTest() {

		authenticate("admin");

		Plan planToModify = planService.findOne(68);
		planToModify.setGoal(Goals.GAIN_MUSCLE_MASS);
		planToModify.setDiet(dietService.findOne(6));
		planToModify.setTraining(trainingService.findOne(29));
		planService.save(planToModify);
		Plan planAfter = planService.findOne(68);
		Assert.isTrue(planAfter.getGoal().equals(Goals.GAIN_MUSCLE_MASS));
		Assert.isTrue(planAfter.getDiet().equals(dietService.findOne(6)));
		Assert.isTrue(planAfter.getTraining().equals(
				trainingService.findOne(29)));

	}

	@Test(expected = IllegalArgumentException.class)
	public void userCantModifyPlanTest() {

		authenticate("customer1");

		Plan planToModify = planService.findOne(68);
		planToModify.setGoal(Goals.GAIN_MUSCLE_MASS);
		planToModify.setDiet(dietService.findOne(6));
		planToModify.setTraining(trainingService.findOne(29));
		planService.save(planToModify);
		Plan planAfter = planService.findOne(68);
		Assert.isTrue(planAfter.getGoal().equals(Goals.GAIN_MUSCLE_MASS));
		Assert.isTrue(planAfter.getDiet().equals(dietService.findOne(6)));
		Assert.isTrue(planAfter.getTraining().equals(
				trainingService.findOne(29)));
	}

	@Test(expected = IllegalArgumentException.class)
	public void nonRegisteredCantModifyPlanTest() {

		Plan planToModify = planService.findOne(68);
		planToModify.setGoal(Goals.GAIN_MUSCLE_MASS);
		planToModify.setDiet(dietService.findOne(6));
		planToModify.setTraining(trainingService.findOne(29));
		planService.save(planToModify);
		Plan planAfter = planService.findOne(68);
		Assert.isTrue(planAfter.getGoal().equals(Goals.GAIN_MUSCLE_MASS));
		Assert.isTrue(planAfter.getDiet().equals(dietService.findOne(6)));
		Assert.isTrue(planAfter.getTraining().equals(
				trainingService.findOne(29)));
	}

	@Test
	public void adminDeletePlanTest() {

		authenticate("admin");

		Collection<Plan> plansBefore = planService.findAll();

		Plan planToDelete = planService.findOne(69);
		planService.delete(planToDelete);

		Collection<Plan> plansAfter = planService.findAll();

		Assert.isTrue(!plansAfter.contains(planToDelete));

		plansBefore.removeAll(plansAfter);

		Assert.notEmpty(plansBefore);

	}

	@Test(expected = IllegalArgumentException.class)
	public void userCantDeletePlanTest() {

		authenticate("customer1");

		Collection<Plan> plansBefore = planService.findAll();

		Plan planToDelete = planService.findOne(69);
		planService.delete(planToDelete);

		Collection<Plan> plansAfter = planService.findAll();

		Assert.isTrue(!plansAfter.contains(planToDelete));

		plansBefore.removeAll(plansAfter);

		Assert.notEmpty(plansBefore);

	}

	@Test(expected = IllegalArgumentException.class)
	public void nonRegisteredCantDeletePlanTest() {

		Collection<Plan> plansBefore = planService.findAll();

		Plan planToDelete = planService.findOne(69);
		planService.delete(planToDelete);

		Collection<Plan> plansAfter = planService.findAll();

		Assert.isTrue(!plansAfter.contains(planToDelete));

		plansBefore.removeAll(plansAfter);

		Assert.notEmpty(plansBefore);

	}

}
