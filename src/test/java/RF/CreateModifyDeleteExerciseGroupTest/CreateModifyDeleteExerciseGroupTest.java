package RF.CreateModifyDeleteExerciseGroupTest;

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
import services.ExerciseGroupService;
import services.ExerciseService;
import services.SponsorService;
import services.TrainingDayService;
import utilities.PopulateDatabase;
import domain.Exercise;
import domain.ExerciseGroup;
import funcionalRequirement.GlobalTest;

@ContextConfiguration(locations = { "classpath:spring/datasource.xml",
		"classpath:spring/config/packages.xml" })
@RunWith(SpringJUnit4ClassRunner.class)
public class CreateModifyDeleteExerciseGroupTest extends GlobalTest {

	@Autowired
	AdministratorService administratorService;

	@Autowired
	ExerciseService exerciseService;

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
	public void createExerciseGroupTest() {

		authenticate("admin");

		Collection<ExerciseGroup> exerciseGroupsbefore = exerciseGroupService
				.findAll();
		Collection<Exercise> exercises = new ArrayList<Exercise>();
		exercises.add(exerciseService.findOne(74));

		ExerciseGroup exerciseGroup = exerciseGroupService.create();
		exerciseGroup.setExercises(exercises);
		exerciseGroup.setName("TestExGroup");
		exerciseGroupService.save(exerciseGroup);

		Collection<ExerciseGroup> exerciseGroupsafter = exerciseGroupService
				.findAll();
		exerciseGroupsafter.removeAll(exerciseGroupsbefore);

		Assert.notEmpty(exerciseGroupsafter);

	}

	@Test(expected = IllegalArgumentException.class)
	public void userCantCreateExerciseGroupTest() {

		authenticate("customer1");

		Collection<ExerciseGroup> exerciseGroupsbefore = exerciseGroupService
				.findAll();
		Collection<Exercise> exercises = new ArrayList<Exercise>();
		exercises.add(exerciseService.findOne(74));

		ExerciseGroup exerciseGroup = exerciseGroupService.create();
		exerciseGroup.setExercises(exercises);
		exerciseGroup.setName("TestExGroup");
		exerciseGroupService.save(exerciseGroup);

		Collection<ExerciseGroup> exerciseGroupsafter = exerciseGroupService
				.findAll();
		exerciseGroupsafter.removeAll(exerciseGroupsbefore);

		Assert.notEmpty(exerciseGroupsafter);
	}

	@Test(expected = IllegalArgumentException.class)
	public void nonRegisteredCantCreateExerciseGroupTest() {

		Collection<ExerciseGroup> exerciseGroupsbefore = exerciseGroupService
				.findAll();
		Collection<Exercise> exercises = new ArrayList<Exercise>();
		exercises.add(exerciseService.findOne(74));

		ExerciseGroup exerciseGroup = exerciseGroupService.create();
		exerciseGroup.setExercises(exercises);
		exerciseGroup.setName("TestExGroup");
		exerciseGroupService.save(exerciseGroup);

		Collection<ExerciseGroup> exerciseGroupsafter = exerciseGroupService
				.findAll();
		exerciseGroupsafter.removeAll(exerciseGroupsbefore);

		Assert.notEmpty(exerciseGroupsafter);
	}

	@Test
	public void adminModifyExerciseGroupTest() {

		authenticate("admin");

		ExerciseGroup exerciseGroupToModify = exerciseGroupService.findOne(85);
		exerciseGroupToModify.setName("name modified");
		exerciseGroupService.save(exerciseGroupToModify);
		ExerciseGroup exerciseGroupAfter = exerciseGroupService.findOne(85);

		Assert.isTrue(exerciseGroupAfter.getName().equals("name modified"));

	}

	@Test(expected = IllegalArgumentException.class)
	public void userCantModifyExerciseGroupTest() {

		authenticate("customer1");

		ExerciseGroup exerciseGroupToModify = exerciseGroupService.findOne(85);
		exerciseGroupToModify.setName("name modified");
		exerciseGroupService.save(exerciseGroupToModify);
		ExerciseGroup exerciseGroupAfter = exerciseGroupService.findOne(95);

		Assert.isTrue(exerciseGroupAfter.getName().equals("name modified"));
	}

	@Test(expected = IllegalArgumentException.class)
	public void nonRegisteredCantModifyExerciseGroupTest() {

		ExerciseGroup exerciseGroupToModify = exerciseGroupService.findOne(85);
		exerciseGroupToModify.setName("name modified");
		exerciseGroupService.save(exerciseGroupToModify);
		ExerciseGroup exerciseGroupAfter = exerciseGroupService.findOne(95);

		Assert.isTrue(exerciseGroupAfter.getName().equals("name modified"));
	}

	@Test
	public void adminDeleteExerciseGroupTest() {

		authenticate("admin");

		Collection<ExerciseGroup> exerciseGroupsBefore = exerciseGroupService
				.findAll();

		// El exerciseGroup elegido tiene que ser uno sin planes, ya que si esta
		// asignado a un customer no se puede borrar
		ExerciseGroup exerciseGroupToDelete = exerciseGroupService.findOne(90);
		exerciseGroupService.delete(exerciseGroupToDelete);

		Collection<ExerciseGroup> exerciseGroupsAfter = exerciseGroupService
				.findAll();

		Assert.isTrue(!exerciseGroupsAfter.contains(exerciseGroupToDelete));

		exerciseGroupsBefore.removeAll(exerciseGroupsAfter);

		Assert.notEmpty(exerciseGroupsBefore);

	}

	@Test(expected = DataIntegrityViolationException.class)
	public void adminCantDeleteExerciseGroupWithPlanTest() {

		authenticate("admin");

		Collection<ExerciseGroup> exerciseGroupsBefore = exerciseGroupService
				.findAll();

		ExerciseGroup exerciseGroupToDelete = exerciseGroupService.findOne(85);
		exerciseGroupService.delete(exerciseGroupToDelete);

		Collection<ExerciseGroup> exerciseGroupsAfter = exerciseGroupService
				.findAll();

		Assert.isTrue(!exerciseGroupsAfter.contains(exerciseGroupToDelete));

		exerciseGroupsBefore.removeAll(exerciseGroupsAfter);

		Assert.notEmpty(exerciseGroupsBefore);

	}

	@Test(expected = IllegalArgumentException.class)
	public void userCantDeleteExerciseGroupTest() {

		authenticate("customer1");

		Collection<ExerciseGroup> exerciseGroupsBefore = exerciseGroupService
				.findAll();

		// El exerciseGroup elegido tiene que ser uno sin planes, ya que si esta
		// asignado a un customer no se puede borrar
		ExerciseGroup exerciseGroupToDelete = exerciseGroupService.findOne(90);
		exerciseGroupService.delete(exerciseGroupToDelete);

		Collection<ExerciseGroup> exerciseGroupsAfter = exerciseGroupService
				.findAll();

		Assert.isTrue(!exerciseGroupsAfter.contains(exerciseGroupToDelete));

		exerciseGroupsBefore.removeAll(exerciseGroupsAfter);

		Assert.notEmpty(exerciseGroupsBefore);

	}

	@Test(expected = IllegalArgumentException.class)
	public void nonRegisteredCantDeleteExerciseGroupTest() {

		Collection<ExerciseGroup> exerciseGroupsBefore = exerciseGroupService
				.findAll();

		// El exerciseGroup elegido tiene que ser uno sin planes, ya que si esta
		// asignado a un customer no se puede borrar
		ExerciseGroup exerciseGroupToDelete = exerciseGroupService.findOne(90);
		exerciseGroupService.delete(exerciseGroupToDelete);

		Collection<ExerciseGroup> exerciseGroupsAfter = exerciseGroupService
				.findAll();

		Assert.isTrue(!exerciseGroupsAfter.contains(exerciseGroupToDelete));

		exerciseGroupsBefore.removeAll(exerciseGroupsAfter);

		Assert.notEmpty(exerciseGroupsBefore);
	}

}
