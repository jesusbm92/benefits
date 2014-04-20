package services;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.FoodRepository;
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

	/**
	 * Persiste (guarda o crea) el objeto de tipo Food en la base de datos a
	 * través del repositorio FoodRepository
	 * 
	 * @return void
	 */
	public void save(Food food) {
		// TODO Restricciones de Save

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
		// TODO Restricciones de Borrado

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
