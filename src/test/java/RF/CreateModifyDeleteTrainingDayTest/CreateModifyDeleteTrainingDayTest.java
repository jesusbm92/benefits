package RF.CreateModifyDeleteTrainingDayTest;

import java.util.ArrayList;
import java.util.Collection;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.Assert;

import services.AdministratorService;
import services.DietService;
import services.ExerciseGroupService;
import services.PlanService;
import services.SponsorService;
import services.TrainingDayService;
import utilities.PopulateDatabase;
import domain.Days;
import domain.ExerciseGroup;
import domain.TrainingDay;
import funcionalRequirement.GlobalTest;

@ContextConfiguration(locations = { "classpath:spring/datasource.xml",
		"classpath:spring/config/packages.xml" })
@RunWith(SpringJUnit4ClassRunner.class)
public class CreateModifyDeleteTrainingDayTest extends GlobalTest {

	@Autowired
	AdministratorService administratorService;

	@Autowired
	PlanService planService;

	@Autowired
	DietService dietService;

	@Autowired
	ExerciseGroupService exerciseGroupService;

	@Autowired
	SponsorService sponsorService;

	@Autowired
	TrainingDayService trainingDaysService;

	@Before
	public void setUp() {
		PopulateDatabase.main(null);
	}

	@After
	public void tearDown() {
		logout();
	}

	@Test
	public void createTrainingDayTest() {

		authenticate("admin");

		Collection<TrainingDay> trainingsDaysbefore = trainingDaysService
				.findAll();
		Collection<ExerciseGroup> egs = new ArrayList<ExerciseGroup>();
		egs.add(exerciseGroupService.findOne(86));

		TrainingDay trainingDay = trainingDaysService.create();
		trainingDay.setExerciseGroups(egs);
		trainingDay.setName(Days.SUNDAY);
		trainingDaysService.save(trainingDay);

		Collection<TrainingDay> trainingsDaysafter = trainingDaysService
				.findAll();
		trainingsDaysafter.removeAll(trainingsDaysbefore);

		Assert.notEmpty(trainingsDaysafter);

	}

	@Test(expected = IllegalArgumentException.class)
	public void userCantCreateTrainingDayTest() {

		authenticate("customer1");

		Collection<TrainingDay> trainingsDaysbefore = trainingDaysService
				.findAll();
		Collection<ExerciseGroup> egs = new ArrayList<ExerciseGroup>();
		egs.add(exerciseGroupService.findOne(86));

		TrainingDay trainingDay = trainingDaysService.create();
		trainingDay.setExerciseGroups(egs);
		trainingDay.setName(Days.SUNDAY);
		trainingDaysService.save(trainingDay);

		Collection<TrainingDay> trainingsDaysafter = trainingDaysService
				.findAll();
		trainingsDaysafter.removeAll(trainingsDaysbefore);

		Assert.notEmpty(trainingsDaysafter);
	}

	@Test(expected = IllegalArgumentException.class)
	public void nonRegisteredCantCreateTrainingDayTest() {

		Collection<TrainingDay> trainingsDaysbefore = trainingDaysService
				.findAll();
		Collection<ExerciseGroup> egs = new ArrayList<ExerciseGroup>();
		egs.add(exerciseGroupService.findOne(86));

		TrainingDay trainingDay = trainingDaysService.create();
		trainingDay.setExerciseGroups(egs);
		trainingDay.setName(Days.SUNDAY);
		trainingDaysService.save(trainingDay);

		Collection<TrainingDay> trainingsDaysafter = trainingDaysService
				.findAll();
		trainingsDaysafter.removeAll(trainingsDaysbefore);

		Assert.notEmpty(trainingsDaysafter);
	}

	@Test
	public void adminModifyTrainingDayTest() {

		authenticate("admin");

		TrainingDay trainingDayToModify = trainingDaysService.findOne(93);
		trainingDayToModify.setName(Days.FRIDAY);
		trainingDaysService.save(trainingDayToModify);
		TrainingDay trainingDayAfter = trainingDaysService.findOne(93);
		Assert.isTrue(trainingDayAfter.getName() == (Days.FRIDAY));

	}

	@Test(expected = IllegalArgumentException.class)
	public void userCantModifyTrainingDayTest() {

		authenticate("customer1");

		TrainingDay trainingDayToModify = trainingDaysService.findOne(93);
		trainingDayToModify.setName(Days.FRIDAY);
		trainingDaysService.save(trainingDayToModify);
		TrainingDay trainingDayAfter = trainingDaysService.findOne(93);
		Assert.isTrue(trainingDayAfter.getName() == (Days.FRIDAY));
	}

	@Test(expected = IllegalArgumentException.class)
	public void nonRegisteredCantModifyTrainingDayTest() {

		TrainingDay trainingDayToModify = trainingDaysService.findOne(93);
		trainingDayToModify.setName(Days.FRIDAY);
		trainingDaysService.save(trainingDayToModify);
		TrainingDay trainingDayAfter = trainingDaysService.findOne(93);
		Assert.isTrue(trainingDayAfter.getName() == (Days.FRIDAY));
	}

	@Test
	public void adminDeleteTrainingDayTest() {

		authenticate("admin");

		Collection<TrainingDay> trainingDaysBefore = trainingDaysService
				.findAll();

		// El training elegido tiene que ser uno sin planes, ya que si esta
		// asignado a un customer no se puede borrar
		TrainingDay trainingDayToDelete = trainingDaysService.findOne(97);
		trainingDaysService.delete(trainingDayToDelete);

		Collection<TrainingDay> trainingDaysAfter = trainingDaysService
				.findAll();

		Assert.isTrue(!trainingDaysAfter.contains(trainingDayToDelete));

		trainingDaysBefore.removeAll(trainingDaysAfter);

		Assert.notEmpty(trainingDaysBefore);

	}

	@Test(expected = DataIntegrityViolationException.class)
	public void adminCantDeleteTrainingDayWithTrainingTest() {

		authenticate("admin");

		Collection<TrainingDay> trainingDaysBefore = trainingDaysService
				.findAll();

		TrainingDay trainingToDelete = trainingDaysService.findOne(93);
		trainingDaysService.delete(trainingToDelete);

		Collection<TrainingDay> trainingDaysAfter = trainingDaysService
				.findAll();

		Assert.isTrue(!trainingDaysAfter.contains(trainingToDelete));

		trainingDaysBefore.removeAll(trainingDaysAfter);

		Assert.notEmpty(trainingDaysBefore);

	}

	@Test(expected = IllegalArgumentException.class)
	public void userCantDeleteTrainingDayTest() {

		authenticate("customer1");

		Collection<TrainingDay> trainingDaysBefore = trainingDaysService
				.findAll();

		// El training elegido tiene que ser uno sin planes, ya que si esta
		// asignado a un customer no se puede borrar
		TrainingDay trainingDayToDelete = trainingDaysService.findOne(97);
		trainingDaysService.delete(trainingDayToDelete);

		Collection<TrainingDay> trainingDaysAfter = trainingDaysService
				.findAll();

		Assert.isTrue(!trainingDaysAfter.contains(trainingDayToDelete));

		trainingDaysBefore.removeAll(trainingDaysAfter);

		Assert.notEmpty(trainingDaysBefore);

	}

	@Test(expected = IllegalArgumentException.class)
	public void nonRegisteredCantDeleteTrainingDayTest() {

		Collection<TrainingDay> trainingDaysBefore = trainingDaysService
				.findAll();

		// El training elegido tiene que ser uno sin planes, ya que si esta
		// asignado a un customer no se puede borrar
		TrainingDay trainingDayToDelete = trainingDaysService.findOne(97);
		trainingDaysService.delete(trainingDayToDelete);

		Collection<TrainingDay> trainingDaysAfter = trainingDaysService
				.findAll();

		Assert.isTrue(!trainingDaysAfter.contains(trainingDayToDelete));

		trainingDaysBefore.removeAll(trainingDaysAfter);

		Assert.notEmpty(trainingDaysBefore);
	}

}
