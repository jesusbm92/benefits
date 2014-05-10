package services;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.MealRepository;
import domain.Amount;
import domain.Language;
import domain.Meal;

@Transactional
@Service
public class MealService {

	// Managed repository-----------------------

	@Autowired
	private MealRepository mealRepository;

	// Supporting services -----------------
	@Autowired
	private UserService userService;

	@Autowired
	private AmountService amountService;

	// Constructors --------------------------
	public MealService() {
		super();
	}

	// Simple CRUD methods -----------------
	/**
	 * Constructor por defecto de MealService
	 * 
	 * @return Meal meal
	 */
	public Meal create() {
		Meal meal = new Meal();

		return meal;
	}

	/**
	 * Devuelve una colección con todos los objetos de tipo Meal
	 * 
	 * @return Collection<Meal> meals
	 */
	public Collection<Meal> findAll() {
		Assert.isTrue(userService.IAmAnAdmin());
		return mealRepository.findAll();
	}

	public Collection<Meal> findAllLanguage(String language) {
		Assert.isTrue(userService.IAmAnAdmin());
		Collection<Meal> result;
		if (language == Language.English.toString()) {
			result = mealRepository.findAllLanguage(Language.English);
		} else {
			result = mealRepository.findAllLanguage(Language.Spanish);
		}
		return result;
	}

	/**
	 * Devuelve una instancia de un objetos de tipo Meal En caso de no
	 * encontrarse, devuelve null
	 * 
	 * @return Meal meal
	 */
	public Meal findOne(int mealId) {
		return mealRepository.findOne(mealId);
	}

	/**
	 * Persiste (guarda o crea) el objeto de tipo Meal en la base de datos a
	 * través del repositorio MealRepository
	 * 
	 * @return void
	 */
	public Meal save(Meal meal) {
		// TODO Restricciones de Save
		Assert.isTrue(userService.IAmAnAdmin());
		return mealRepository.save(meal);
	}

	/**
	 * Elimina el objeto de tipo Meal de la base de datos a través del
	 * repositorio MealRepository
	 * 
	 * @return void
	 */
	public void delete(Meal meal) {
		Assert.notNull(meal);
		// TODO Restricciones de Borrado

		Assert.isTrue(userService.IAmAnAdmin());

		Collection<Amount> amounts = meal.getAmounts();
		for (Amount a : amounts) {
			amountService.delete(a);
		}

		mealRepository.delete(meal);
	}

	// Other business methods ----------------

	public Collection<Meal> findMealsByDay(int dayId) {
		Assert.isTrue(userService.IAmAnAdmin());
		Collection<Meal> meals = mealRepository.findMealsByDay(dayId);
		return meals;
	}
	// Assertions

}
