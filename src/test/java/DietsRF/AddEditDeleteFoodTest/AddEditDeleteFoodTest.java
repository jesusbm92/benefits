package DietsRF.AddEditDeleteFoodTest;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.Assert;

import services.AmountService;
import services.DayService;
import services.FoodService;
import services.SponsorService;
import utilities.PopulateDatabase;
import domain.Amount;
import domain.Food;
import funcionalRequirement.GlobalTest;

@ContextConfiguration(locations = { "classpath:spring/datasource.xml",
		"classpath:spring/config/packages.xml" })
@RunWith(SpringJUnit4ClassRunner.class)
public class AddEditDeleteFoodTest extends GlobalTest {

	@Autowired
	FoodService foodService;

	@Autowired
	DayService dayService;

	@Autowired
	AmountService amountService;

	@Autowired
	SponsorService sponsorService;

	@Before
	public void setUp() {
		PopulateDatabase.main(null);
	}

//	@Test
//	public void testAddFoodAdmin() {
//
//		authenticate("admin");
//
//		int sizeBefore = foodService.findAll().size();
//
//		Food food = foodService.create();
//		food.setName("prueba");
//		food.setDescription("prueba");
//		Amount amount = amountService.create(mea);
//		foodService.save(food);
//
//		int sizeAfter = foodService.findAll().size();
//
//		Assert.isTrue(sizeAfter > sizeBefore);
//	}

	@Test(expected = IllegalArgumentException.class)
	public void testAddFoodCustomer() {

		authenticate("customer1");

		foodService.save(foodService.create());

	}

	@Test(expected = java.lang.Exception.class)
	public void testAddEditFoodNotLogin() {

		foodService.save(foodService.create());

	}

	@Test(expected = java.lang.Exception.class)
	public void testAddFoodException() {
		authenticate("admin");
		Food food = foodService.create();
		foodService.save(food);

	}

	@Test
	public void testEditFood() {

		authenticate("admin");
		Food food = foodService.findOne(21);
		food.setDescription("prueba");
		foodService.save(food);

	}

	// @Test
	// public void testRemoveFood() {
	//
	// authenticate("admin");
	//
	// int sizeBefore = foodService.findAll().size();
	//
	// Food food = foodService.findOne(21);
	// Collection<Amount> amounts = food.getAmounts();
	// for (Amount aux : amounts) {
	// amountService.delete(aux);
	// }
	// foodService.delete(food);
	//
	// int sizeAfter = foodService.findAll().size();
	//
	// Assert.isTrue(sizeAfter < sizeBefore);
	//
	// }
}
