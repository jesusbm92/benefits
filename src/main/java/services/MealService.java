package services;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.MealRepository;
import domain.Meal;

@Transactional
@Service
public class MealService {

	// Managed repository-----------------------

	@Autowired
	private MealRepository mealRepository;

	// Supporting services -----------------

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
		return mealRepository.findAll();
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
	public void save(Meal meal) {
		// TODO Restricciones de Save

		mealRepository.save(meal);
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

		mealRepository.delete(meal);
	}

	// Other business methods ----------------

	// Assertions

}
