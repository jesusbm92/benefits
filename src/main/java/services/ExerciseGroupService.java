package services;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.ExerciseGroupRepository;
import domain.ExerciseGroup;

@Transactional
@Service
public class ExerciseGroupService {

	// Managed repository-----------------------

	@Autowired
	private ExerciseGroupRepository exerciseGroupRepository;

	// Supporting services -----------------

	@Autowired
	private AdministratorService administratorService;

	// Constructors --------------------------
	public ExerciseGroupService() {
		super();
	}

	// Simple CRUD methods -----------------
	/**
	 * Constructor por defecto de ExerciseGroupService
	 * 
	 * @return ExerciseGroup exerciseGroup
	 */
	public ExerciseGroup create() {
		ExerciseGroup exerciseGroup = new ExerciseGroup();

		return exerciseGroup;
	}

	/**
	 * Devuelve una colección con todos los objetos de tipo ExerciseGroup
	 * 
	 * @return Collection<ExerciseGroup> exerciseGroups
	 */
	public Collection<ExerciseGroup> findAll() {
		Assert.isTrue(administratorService.IAmAnAdmin());
		return exerciseGroupRepository.findAll();
	}

	public Map<String, Integer> findAllMap() {
		Assert.isTrue(administratorService.IAmAnAdmin());
		Map<String, Integer> map = new HashMap<String, Integer>();

		Collection<ExerciseGroup> exerciseGroup = exerciseGroupRepository
				.findAll();

		for (ExerciseGroup aux : exerciseGroup) {
			map.put(aux.getName(), aux.getId());
		}

		return map;
	}

	/**
	 * Devuelve una instancia de un objetos de tipo ExerciseGroup En caso de no
	 * encontrarse, devuelve null
	 * 
	 * @return ExerciseGroup exerciseGroup
	 */
	public ExerciseGroup findOne(int exerciseGroupId) {
		Assert.isTrue(administratorService.IAmAnAdmin());
		return exerciseGroupRepository.findOne(exerciseGroupId);
	}

	/**
	 * Persiste (guarda o crea) el objeto de tipo ExerciseGroup en la base de
	 * datos a través del repositorio ExerciseGroupRepository
	 * 
	 * @return void
	 */
	public void save(ExerciseGroup exerciseGroup) {
		// TODO Restricciones de Save
		Assert.isTrue(administratorService.IAmAnAdmin());

		exerciseGroupRepository.save(exerciseGroup);
	}

	/**
	 * Elimina el objeto de tipo ExerciseGroup de la base de datos a través del
	 * repositorio ExerciseGroupRepository
	 * 
	 * @return void
	 */
	public void delete(ExerciseGroup exerciseGroup) {
		Assert.notNull(exerciseGroup);
		// TODO Restricciones de Borrado
		Assert.isTrue(administratorService.IAmAnAdmin());
		exerciseGroupRepository.delete(exerciseGroup);
	}

	// Other business methods ----------------

	// Assertions

}
