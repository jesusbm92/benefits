package services;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.DietRepository;
import domain.Diet;

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
	 * Devuelve una colección con todos los objetos de tipo Diet
	 * 
	 * @return Collection<Diet> diets
	 */
	public Collection<Diet> findAll() {
		return dietRepository.findAll();
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

	/**
	 * Persiste (guarda o crea) el objeto de tipo Diet en la base de datos a
	 * través del repositorio DietRepository
	 * 
	 * @return void
	 */
	public void save(Diet diet) {
		// TODO Restricciones de Save
		Assert.isTrue(userService.IAmAnAdmin());

		dietRepository.save(diet);
	}

	/**
	 * Elimina el objeto de tipo Diet de la base de datos a través del
	 * repositorio DietRepository
	 * 
	 * @return void
	 */
	public void delete(Diet diet) {
		Assert.notNull(diet);
		// TODO Restricciones de Borrado

		dietRepository.delete(diet);
	}

	// Other business methods ----------------

	public Collection<Diet> findAssigned() {
		Assert.isTrue(userService.IAmAnAdmin());
		return dietRepository.dietsAssigned();
	}

	public Collection<Diet> findFree() {
		Assert.isTrue(userService.IAmAnAdmin());
		return dietRepository.dietsFree();
	}
	// Assertions

}
