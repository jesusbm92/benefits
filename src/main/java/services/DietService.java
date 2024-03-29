package services;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.DietRepository;
import domain.Diet;
import domain.Language;

@Transactional
@Service
public class DietService {

	// Managed repository-----------------------

	@Autowired
	private DietRepository dietRepository;

	// Supporting services -----------------
	@Autowired
	private UserService userService;

	// Constructors --------------------------
	public DietService() {
		super();
	}

	// Simple CRUD methods -----------------
	/**
	 * Constructor por defecto de DietService
	 * 
	 * @return Diet diet
	 */
	public Diet create() {
		Diet diet = new Diet();

		return diet;
	}

	/**
	 * Devuelve una colecci�n con todos los objetos de tipo Diet
	 * 
	 * @return Collection<Diet> diets
	 */
	public Collection<Diet> findAll() {
		Assert.isTrue(userService.IAmAnAdmin());
		return dietRepository.findAll();
	}

	public Collection<Diet> findAllLanguage(String language) {
		Assert.isTrue(userService.IAmAnAdmin());
		Collection<Diet> result;
		if (language.equals(Language.english.toString())) {
			result = dietRepository.findAllLanguage(Language.english);
		} else {
			result = dietRepository.findAllLanguage(Language.spanish);
		}
		return result;
	}

	/**
	 * Devuelve una instancia de un objetos de tipo Diet En caso de no
	 * encontrarse, devuelve null
	 * 
	 * @return Diet diet
	 */
	public Diet findOne(int dietId) {
		return dietRepository.findOne(dietId);
	}

	public Diet findOneEdit(int dietId) {
		Assert.isTrue(userService.IAmAnAdmin());
		return dietRepository.findOne(dietId);
	}

	/**
	 * Persiste (guarda o crea) el objeto de tipo Diet en la base de datos a
	 * trav�s del repositorio DietRepository
	 * 
	 * @return void
	 */
	public void save(Diet diet) {
		// TODO Restricciones de Save
		Assert.isTrue(userService.IAmAnAdmin());

		dietRepository.save(diet);
	}

	/**
	 * Elimina el objeto de tipo Diet de la base de datos a trav�s del
	 * repositorio DietRepository
	 * 
	 * @return void
	 */
	public void delete(Diet diet) {
		Assert.notNull(diet);

		// TODO Restricciones de Borrado
		Assert.isTrue(userService.IAmAnAdmin());
		// Assert.isTrue(diet.getPlans().isEmpty());
		dietRepository.delete(diet);
	}

	// Other business methods ----------------

	public Collection<Diet> findDietsByDay(int dayId) {
		Assert.isTrue(userService.IAmAnAdmin());
		return dietRepository.findDietsByDay(dayId);
	}
	// Assertions

}
