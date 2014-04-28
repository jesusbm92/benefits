package services;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.AmountRepository;
import domain.Amount;
import domain.Meal;

@Transactional
@Service
public class AmountService {

	// Managed repository-----------------------

	@Autowired
	private AmountRepository amountRepository;

	// Supporting services -----------------

	@Autowired
	private UserService userService;
	
	@Autowired
	private MealService mealService;

	// Constructors --------------------------
	public AmountService() {
		super();
	}

	// Simple CRUD methods -----------------
	/**
	 * Constructor por defecto de AmountService
	 * 
	 * @return Amount amount
	 */
	public Amount create(int mealId) {
		Amount amount = new Amount();
		Meal meal = mealService.findOne(mealId);
		amount.setMeal(meal);
		

		return amount;
	}

	/**
	 * Devuelve una colección con todos los objetos de tipo Amount
	 * 
	 * @return Collection<Amount> amounts
	 */
	public Collection<Amount> findAll() {
		return amountRepository.findAll();
	}

	/**
	 * Devuelve una instancia de un objetos de tipo Amount En caso de no
	 * encontrarse, devuelve null
	 * 
	 * @return Amount amount
	 */
	public Amount findOne(int amountId) {
		return amountRepository.findOne(amountId);
	}

	/**
	 * Persiste (guarda o crea) el objeto de tipo Amount en la base de datos a
	 * través del repositorio AmountRepository
	 * 
	 * @return void
	 */
	public void save(Amount amount) {
		// TODO Restricciones de Save

		amountRepository.save(amount);
	}

	/**
	 * Elimina el objeto de tipo Amount de la base de datos a través del
	 * repositorio AmountRepository
	 * 
	 * @return void
	 */
	public void delete(Amount amount) {
		Assert.notNull(amount);
		// TODO Restricciones de Borrado

		amountRepository.delete(amount);
	}

	// Other business methods ----------------

	public Collection<Amount> findAmountsByMeal(int mealId) {
		Assert.isTrue(userService.IAmAnAdmin());
		Collection<Amount> amounts = amountRepository.findAmountsByMeal(mealId);
		return amounts;
	}

	// Assertions

}
