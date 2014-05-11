package services;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.MuscleRepository;
import domain.Language;
import domain.Muscle;

@Transactional
@Service
public class MuscleService {

	// Managed repository-----------------------

	@Autowired
	private MuscleRepository muscleRepository;

	// Supporting services -----------------

	@Autowired
	private AdministratorService administratorService;

	// Constructors --------------------------
	public MuscleService() {
		super();
	}

	// Simple CRUD methods -----------------
	/**
	 * Constructor por defecto de MuscleService
	 * 
	 * @return Muscle muscle
	 */
	public Muscle create() {
		Muscle muscle = new Muscle();

		return muscle;
	}

	/**
	 * Devuelve una colecci�n con todos los objetos de tipo Muscle
	 * 
	 * @return Collection<Muscle> muscles
	 */
	public Collection<Muscle> findAll() {
		Assert.isTrue(administratorService.IAmAnAdmin());
		return muscleRepository.findAll();
	}

	public Collection<Muscle> findAllLanguage(String language) {
		Assert.isTrue(administratorService.IAmAnAdmin());
		Collection<Muscle> result;
		if (language == Language.english.toString()) {
			result = muscleRepository.findAllLanguage(Language.english);
		} else {
			result = muscleRepository.findAllLanguage(Language.spanish);
		}
		return result;
	}

	public Map<String, Integer> findAllIdName() {
		Assert.isTrue(administratorService.IAmAnAdmin());
		Map<String, Integer> map = new HashMap<String, Integer>();
		String language = LocaleContextHolder.getLocale().getDisplayLanguage();
		Collection<Muscle> muscles = findAllLanguage(language);

		for (Muscle aux : muscles) {
			map.put(aux.getName(), aux.getId());
		}

		return map;
	}

	/**
	 * Devuelve una instancia de un objetos de tipo Muscle En caso de no
	 * encontrarse, devuelve null
	 * 
	 * @return Muscle muscle
	 */
	public Muscle findOne(int muscleId) {
		Assert.isTrue(administratorService.IAmAnAdmin());
		return muscleRepository.findOne(muscleId);
	}

	/**
	 * Persiste (guarda o crea) el objeto de tipo Muscle en la base de datos a
	 * trav�s del repositorio MuscleRepository
	 * 
	 * @return void
	 */
	public void save(Muscle muscle) {
		// TODO Restricciones de Save
		Assert.isTrue(administratorService.IAmAnAdmin());

		muscleRepository.save(muscle);
	}

	/**
	 * Elimina el objeto de tipo Muscle de la base de datos a trav�s del
	 * repositorio MuscleRepository
	 * 
	 * @return void
	 */
	public void delete(Muscle muscle) {
		Assert.notNull(muscle);
		// TODO Restricciones de Borrado
		Assert.isTrue(administratorService.IAmAnAdmin());
		muscleRepository.delete(muscle);
	}

	// Other business methods ----------------

	// Assertions

}
