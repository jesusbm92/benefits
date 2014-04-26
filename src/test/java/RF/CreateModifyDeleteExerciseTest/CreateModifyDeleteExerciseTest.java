package RF.CreateModifyDeleteExerciseTest;

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
import services.MuscleService;
import utilities.PopulateDatabase;
import domain.Exercise;
import funcionalRequirement.GlobalTest;

@ContextConfiguration(locations = { "classpath:spring/datasource.xml",
		"classpath:spring/config/packages.xml" })
@RunWith(SpringJUnit4ClassRunner.class)
public class CreateModifyDeleteExerciseTest extends GlobalTest {

	@Autowired
	AdministratorService administratorService;

	@Autowired
	ExerciseService exerciseService;

	@Autowired
	MuscleService muscleService;

	@Autowired
	ExerciseGroupService exerciseGroupService;

	@Before
	public void setUp() {
		PopulateDatabase.main(null);
	}

	@After
	public void tearDown() {
		logout();
	}

	@Test
	public void createExerciseTest() {

		authenticate("admin");

		Collection<Exercise> exercisesbefore = exerciseService.findAll();

		Exercise exercise = exerciseService.create();
		exercise.setCycles(2);
		exercise.setName("Test");
		exercise.setRepetitions(2);
		exercise.setMuscle(muscleService.findOne(61));
		exerciseService.save(exercise);

		Collection<Exercise> exercisesafter = exerciseService.findAll();
		exercisesafter.removeAll(exercisesbefore);

		Assert.notEmpty(exercisesafter);

	}

	@Test(expected = IllegalArgumentException.class)
	public void userCantCreateExerciseTest() {

		authenticate("customer1");

		Collection<Exercise> exercisesbefore = exerciseService.findAll();

		Exercise exercise = exerciseService.create();
		exercise.setCycles(2);
		exercise.setName("Test");
		exercise.setRepetitions(2);
		exercise.setMuscle(muscleService.findOne(61));
		exerciseService.save(exercise);

		Collection<Exercise> exercisesafter = exerciseService.findAll();
		exercisesafter.removeAll(exercisesbefore);

		Assert.notEmpty(exercisesafter);
	}

	@Test(expected = IllegalArgumentException.class)
	public void nonRegisteredCantCreateExerciseTest() {

		Collection<Exercise> exercisesbefore = exerciseService.findAll();

		Exercise exercise = exerciseService.create();
		exercise.setCycles(2);
		exercise.setName("Test");
		exercise.setRepetitions(2);
		exercise.setMuscle(muscleService.findOne(61));
		exerciseService.save(exercise);

		Collection<Exercise> exercisesafter = exerciseService.findAll();
		exercisesafter.removeAll(exercisesbefore);

		Assert.notEmpty(exercisesafter);
	}

	@Test
	public void adminModifyExerciseTest() {

		authenticate("admin");

		Exercise exerciseToModify = exerciseService.findOne(74);
		exerciseToModify.setRepetitions(1);
		exerciseToModify.setName("name modified");
		exerciseToModify.setCycles(1);
		exerciseService.save(exerciseToModify);
		Exercise exerciseAfter = exerciseService.findOne(74);
		Assert.isTrue(exerciseAfter.getRepetitions() == (1));
		Assert.isTrue(exerciseAfter.getName().equals("name modified"));
		Assert.isTrue(exerciseAfter.getCycles() == (1));

	}

	@Test(expected = IllegalArgumentException.class)
	public void userCantModifyExerciseTest() {

		authenticate("customer1");

		Exercise exerciseToModify = exerciseService.findOne(74);
		exerciseToModify.setRepetitions(1);
		exerciseToModify.setName("name modified");
		exerciseToModify.setCycles(1);
		exerciseService.save(exerciseToModify);
		Exercise exerciseAfter = exerciseService.findOne(74);
		Assert.isTrue(exerciseAfter.getRepetitions() == (1));
		Assert.isTrue(exerciseAfter.getName().equals("name modified"));
		Assert.isTrue(exerciseAfter.getCycles() == (1));
	}

	@Test(expected = IllegalArgumentException.class)
	public void nonRegisteredCantModifyExerciseTest() {

		Exercise exerciseToModify = exerciseService.findOne(74);
		exerciseToModify.setRepetitions(1);
		exerciseToModify.setName("name modified");
		exerciseToModify.setCycles(1);
		exerciseService.save(exerciseToModify);
		Exercise exerciseAfter = exerciseService.findOne(74);
		Assert.isTrue(exerciseAfter.getRepetitions() == (1));
		Assert.isTrue(exerciseAfter.getName().equals("name modified"));
		Assert.isTrue(exerciseAfter.getCycles() == (1));
	}

	@Test
	public void adminDeleteExerciseTest() {

		authenticate("admin");

		Collection<Exercise> exercisesBefore = exerciseService.findAll();

		// El exercise elegido tiene que ser uno sin planes, ya que si esta
		// asignado a un customer no se puede borrar
		Exercise exerciseToDelete = exerciseService.findOne(83);
		exerciseService.delete(exerciseToDelete);

		Collection<Exercise> exercisesAfter = exerciseService.findAll();

		Assert.isTrue(!exercisesAfter.contains(exerciseToDelete));

		exercisesBefore.removeAll(exercisesAfter);

		Assert.notEmpty(exercisesBefore);

	}

	@Test(expected = DataIntegrityViolationException.class)
	public void adminCantDeleteExerciseWithGroupExerciseTest() {

		authenticate("admin");

		Collection<Exercise> exercisesBefore = exerciseService.findAll();

		Exercise exerciseToDelete = exerciseService.findOne(80);
		exerciseService.delete(exerciseToDelete);

		Collection<Exercise> exercisesAfter = exerciseService.findAll();

		Assert.isTrue(!exercisesAfter.contains(exerciseToDelete));

		exercisesBefore.removeAll(exercisesAfter);

		Assert.notEmpty(exercisesBefore);

	}

	@Test(expected = IllegalArgumentException.class)
	public void userCantDeleteExerciseTest() {

		authenticate("customer1");

		Collection<Exercise> exercisesBefore = exerciseService.findAll();

		// El exercise elegido tiene que ser uno sin planes, ya que si esta
		// asignado a un customer no se puede borrar
		Exercise exerciseToDelete = exerciseService.findOne(83);
		exerciseService.delete(exerciseToDelete);

		Collection<Exercise> exercisesAfter = exerciseService.findAll();

		Assert.isTrue(!exercisesAfter.contains(exerciseToDelete));

		exercisesBefore.removeAll(exercisesAfter);

		Assert.notEmpty(exercisesBefore);

	}

	@Test(expected = IllegalArgumentException.class)
	public void nonRegisteredCantDeleteExerciseTest() {

		Collection<Exercise> exercisesBefore = exerciseService.findAll();

		// El exercise elegido tiene que ser uno sin planes, ya que si esta
		// asignado a un customer no se puede borrar
		Exercise exerciseToDelete = exerciseService.findOne(83);
		exerciseService.delete(exerciseToDelete);

		Collection<Exercise> exercisesAfter = exerciseService.findAll();

		Assert.isTrue(!exercisesAfter.contains(exerciseToDelete));

		exercisesBefore.removeAll(exercisesAfter);

		Assert.notEmpty(exercisesBefore);
	}

}
