package services;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.TrainingDayRepository;
import domain.Language;
import domain.TrainingDay;

@Transactional
@Service
public class TrainingDayService {

	// Managed repository-----------------------

	@Autowired
	private TrainingDayRepository trainingDayRepository;

	// Supporting services -----------------

	@Autowired
	private AdministratorService administratorService;

	// Constructors --------------------------
	public TrainingDayService() {
		super();
	}

	// Simple CRUD methods -----------------
	/**
	 * Constructor por defecto de TrainingDayService
	 * 
	 * @return TrainingDay trainingDay
	 */
	public TrainingDay create() {
		TrainingDay trainingDay = new TrainingDay();

		return trainingDay;
	}

	/**
	 * Devuelve una colección con todos los objetos de tipo TrainingDay
	 * 
	 * @return Collection<TrainingDay> trainingDays
	 */
	public Collection<TrainingDay> findAll() {
		Assert.isTrue(administratorService.IAmAnAdmin());
		return trainingDayRepository.findAll();
	}

	public Collection<TrainingDay> findAllLanguage(String language) {
		Assert.isTrue(administratorService.IAmAnAdmin());
		Collection<TrainingDay> result;
		if (language == Language.English.toString()) {
			result = trainingDayRepository.findAllLanguage(Language.English);
		} else {
			result = trainingDayRepository.findAllLanguage(Language.Spanish);
		}
		return result;
	}

	/**
	 * Devuelve una instancia de un objetos de tipo TrainingDay En caso de no
	 * encontrarse, devuelve null
	 * 
	 * @return TrainingDay trainingDay
	 */
	public TrainingDay findOne(int trainingDayId) {
		Assert.isTrue(administratorService.IAmAnAdmin());
		return trainingDayRepository.findOne(trainingDayId);
	}

	/**
	 * Persiste (guarda o crea) el objeto de tipo TrainingDay en la base de
	 * datos a través del repositorio TrainingDayRepository
	 * 
	 * @return void
	 */
	public void save(TrainingDay trainingDay) {
		// TODO Restricciones de Save
		Assert.isTrue(administratorService.IAmAnAdmin());

		trainingDayRepository.save(trainingDay);
	}

	/**
	 * Elimina el objeto de tipo TrainingDay de la base de datos a través del
	 * repositorio TrainingDayRepository
	 * 
	 * @return void
	 */
	public void delete(TrainingDay trainingDay) {
		Assert.notNull(trainingDay);
		// TODO Restricciones de Borrado
		Assert.isTrue(administratorService.IAmAnAdmin());
		trainingDayRepository.delete(trainingDay);
	}

	// Other business methods ----------------

	public Collection<TrainingDay> findAllByExerciseGroup(int exerciseGroup) {
		Assert.isTrue(administratorService.IAmAnAdmin());
		return trainingDayRepository.findAllByExerciseGroup(exerciseGroup);
	}

	// Assertions

}
