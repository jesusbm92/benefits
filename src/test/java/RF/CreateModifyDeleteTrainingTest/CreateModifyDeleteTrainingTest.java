package RF.CreateModifyDeleteTrainingTest;

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
import services.PlanService;
import services.SponsorService;
import services.TrainingDayService;
import services.TrainingService;
import utilities.PopulateDatabase;
import domain.Training;
import domain.TrainingDay;
import funcionalRequirement.GlobalTest;

@ContextConfiguration(locations = { "classpath:spring/datasource.xml",
		"classpath:spring/config/packages.xml" })
@RunWith(SpringJUnit4ClassRunner.class)
public class CreateModifyDeleteTrainingTest extends GlobalTest {

	@Autowired
	AdministratorService administratorService;

	@Autowired
	PlanService planService;

	@Autowired
	DietService dietService;

	@Autowired
	TrainingService trainingService;

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
	public void createTrainingTest() {

		authenticate("admin");

		Collection<Training> trainingsbefore = trainingService.findAll();
		Collection<TrainingDay> trainingDays = new ArrayList<TrainingDay>();
		trainingDays.add(trainingDaysService.findOne(92));

		Training training = trainingService.create();
		training.setDuration(90);
		training.setTrainingDays(trainingDays);
		training.setName("Name Test");
		training.setSponsor(sponsorService.findOne(5));
		trainingService.save(training);

		Collection<Training> trainingsafter = trainingService.findAll();
		trainingsafter.removeAll(trainingsbefore);

		Assert.notEmpty(trainingsafter);

	}

	@Test(expected = IllegalArgumentException.class)
	public void userCantCreateTrainingTest() {

		authenticate("customer1");

		Collection<Training> trainingsbefore = trainingService.findAll();
		Collection<TrainingDay> trainingDays = new ArrayList<TrainingDay>();
		trainingDays.add(trainingDaysService.findOne(92));

		Training training = trainingService.create();
		training.setDuration(90);
		training.setTrainingDays(trainingDays);
		training.setName("Name Test");
		training.setSponsor(sponsorService.findOne(5));
		trainingService.save(training);

		Collection<Training> trainingsafter = trainingService.findAll();
		trainingsafter.removeAll(trainingsbefore);

		Assert.notEmpty(trainingsafter);
	}

	@Test(expected = IllegalArgumentException.class)
	public void nonRegisteredCantCreateTrainingTest() {

		Collection<Training> trainingsbefore = trainingService.findAll();
		Collection<TrainingDay> trainingDays = new ArrayList<TrainingDay>();
		trainingDays.add(trainingDaysService.findOne(92));

		Training training = trainingService.create();
		training.setDuration(90);
		training.setTrainingDays(trainingDays);
		training.setName("Name Test");
		training.setSponsor(sponsorService.findOne(5));
		trainingService.save(training);

		Collection<Training> trainingsafter = trainingService.findAll();
		trainingsafter.removeAll(trainingsbefore);

		Assert.notEmpty(trainingsafter);
	}

	@Test
	public void adminModifyTrainingTest() {

		authenticate("admin");

		Training trainingToModify = trainingService.findOne(98);
		trainingToModify.setDuration(25);
		trainingToModify.setName("name modified");
		trainingToModify.setSponsor(sponsorService.findOne(6));
		trainingService.save(trainingToModify);
		Training trainingAfter = trainingService.findOne(98);
		Assert.isTrue(trainingAfter.getDuration() == (25));
		Assert.isTrue(trainingAfter.getName().equals("name modified"));
		Assert.isTrue(trainingAfter.getSponsor().equals(
				sponsorService.findOne(6)));

	}

	@Test(expected = IllegalArgumentException.class)
	public void userCantModifyTrainingTest() {

		authenticate("customer1");

		Training trainingToModify = trainingService.findOne(98);
		trainingToModify.setDuration(25);
		trainingToModify.setName("name modified");
		trainingToModify.setSponsor(sponsorService.findOne(6));
		trainingService.save(trainingToModify);
		Training trainingAfter = trainingService.findOne(98);
		Assert.isTrue(trainingAfter.getDuration() == (25));
		Assert.isTrue(trainingAfter.getName().equals("name modified"));
		Assert.isTrue(trainingAfter.getSponsor().equals(
				sponsorService.findOne(6)));
	}

	@Test(expected = IllegalArgumentException.class)
	public void nonRegisteredCantModifyTrainingTest() {

		Training trainingToModify = trainingService.findOne(98);
		trainingToModify.setDuration(25);
		trainingToModify.setName("name modified");
		trainingToModify.setSponsor(sponsorService.findOne(6));
		trainingService.save(trainingToModify);
		Training trainingAfter = trainingService.findOne(98);
		Assert.isTrue(trainingAfter.getDuration() == (25));
		Assert.isTrue(trainingAfter.getName().equals("name modified"));
		Assert.isTrue(trainingAfter.getSponsor().equals(
				sponsorService.findOne(6)));
	}

	@Test
	public void adminDeleteTrainingTest() {

		authenticate("admin");

		Collection<Training> trainingsBefore = trainingService.findAll();

		// El training elegido tiene que ser uno sin planes, ya que si esta
		// asignado a un customer no se puede borrar
		Training trainingToDelete = trainingService.findOne(100);
		trainingService.delete(trainingToDelete);

		Collection<Training> trainingsAfter = trainingService.findAll();

		Assert.isTrue(!trainingsAfter.contains(trainingToDelete));

		trainingsBefore.removeAll(trainingsAfter);

		Assert.notEmpty(trainingsBefore);

	}

	@Test(expected = DataIntegrityViolationException.class)
	public void adminCantDeleteTrainingWithPlanTest() {

		authenticate("admin");

		Collection<Training> trainingsBefore = trainingService.findAll();

		Training trainingToDelete = trainingService.findOne(98);
		trainingService.delete(trainingToDelete);

		Collection<Training> trainingsAfter = trainingService.findAll();

		Assert.isTrue(!trainingsAfter.contains(trainingToDelete));

		trainingsBefore.removeAll(trainingsAfter);

		Assert.notEmpty(trainingsBefore);

	}

	@Test(expected = IllegalArgumentException.class)
	public void userCantDeleteTrainingTest() {

		authenticate("customer1");

		Collection<Training> trainingsBefore = trainingService.findAll();

		// El training elegido tiene que ser uno sin planes, ya que si esta
		// asignado a un customer no se puede borrar
		Training trainingToDelete = trainingService.findOne(100);
		trainingService.delete(trainingToDelete);

		Collection<Training> trainingsAfter = trainingService.findAll();

		Assert.isTrue(!trainingsAfter.contains(trainingToDelete));

		trainingsBefore.removeAll(trainingsAfter);

		Assert.notEmpty(trainingsBefore);

	}

	@Test(expected = IllegalArgumentException.class)
	public void nonRegisteredCantDeleteTrainingTest() {

		Collection<Training> trainingsBefore = trainingService.findAll();

		// El training elegido tiene que ser uno sin planes, ya que si esta
		// asignado a un customer no se puede borrar
		Training trainingToDelete = trainingService.findOne(100);
		trainingService.delete(trainingToDelete);

		Collection<Training> trainingsAfter = trainingService.findAll();

		Assert.isTrue(!trainingsAfter.contains(trainingToDelete));

		trainingsBefore.removeAll(trainingsAfter);

		Assert.notEmpty(trainingsBefore);
	}

}
