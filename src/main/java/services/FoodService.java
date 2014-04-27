package services;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.FoodRepository;
import domain.Amount;
import domain.Food;

@Transactional
@Service
public class FoodService {

	// Managed repository-----------------------

	@Autowired
	private FoodRepository foodRepository;

	// Supporting services -----------------

	@Autowired
	private UserService userService;

	@Autowired
	private AmountService amountService;

	// Constructors --------------------------
	public FoodService() {
		super();
	}

	// Simple CRUD methods -----------------
	/**
	 * Constructor por defecto de FoodService
	 * 
	 * @return Food food
	 */
	public Food create() {
		Food food = new Food();

		return food;
	}

	/**
	 * Devuelve una colección con todos los objetos de tipo Food
	 * 
	 * @return Collection<Food> foods
	 */
	public Collection<Food> findAll() {
		Assert.isTrue(userService.IAmAnAdmin());
		return foodRepository.findAll();
	}

	/**
	 * Devuelve una instancia de un objetos de tipo Food En caso de no
	 * encontrarse, devuelve null
	 * 
	 * @return Food food
	 */
	public Food findOne(int foodId) {
		return foodRepository.findOne(foodId);
	}

	public Food findOneEdit(int foodId) {
		Assert.isTrue(userService.IAmAnAdmin());
		return foodRepository.findOne(foodId);
	}

	/**
	 * Persiste (guarda o crea) el objeto de tipo Food en la base de datos a
	 * través del repositorio FoodRepository
	 * 
	 * @return void
	 */
	public void save(Food food) {
		// TODO Restricciones de Save
		Assert.isTrue(userService.IAmAnAdmin());
		foodRepository.save(food);
	}

	/**
	 * Elimina el objeto de tipo Food de la base de datos a través del
	 * repositorio FoodRepository
	 * 
	 * @return void
	 */
	public void delete(Food food) {
		Assert.notNull(food);
		Assert.isTrue(userService.IAmAnAdmin());
		// TODO Restricciones de Borrado

		Collection<Amount> amounts = food.getAmounts();
		for (Amount a : amounts) {
			amountService.delete(a);
		}

		foodRepository.delete(food);
	}

	// Other business methods ----------------

	public Food findFoodByAmount(int amountId) {
		Assert.isTrue(userService.IAmAnAdmin());
		Food food = foodRepository.findFoodByAmount(amountId);
		return food;
	}

	// Assertions

}
