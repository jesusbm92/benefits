package services;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.TrainingRepository;
import domain.Training;

@Transactional
@Service
public class TrainingService {

	// Managed repository-----------------------

	@Autowired
	private TrainingRepository trainingRepository;

	// Supporting services -----------------

	// Constructors --------------------------
	public TrainingService() {
		super();
	}

	// Simple CRUD methods -----------------
	/**
	 * Constructor por defecto de TrainingService
	 * 
	 * @return Training training
	 */
	public Training create() {
		Training training = new Training();

		return training;
	}

	/**
	 * Devuelve una colección con todos los objetos de tipo Training
	 * 
	 * @return Collection<Training> trainings
	 */
	public Collection<Training> findAll() {
		return trainingRepository.findAll();
	}

	/**
	 * Devuelve una instancia de un objetos de tipo Training En caso de no
	 * encontrarse, devuelve null
	 * 
	 * @return Training training
	 */
	public Training findOne(int trainingId) {
		return trainingRepository.findOne(trainingId);
	}

	/**
	 * Persiste (guarda o crea) el objeto de tipo Training en la base de datos a
	 * través del repositorio TrainingRepository
	 * 
	 * @return void
	 */
	public void save(Training training) {
		// TODO Restricciones de Save

		trainingRepository.save(training);
	}

	/**
	 * Elimina el objeto de tipo Training de la base de datos a través del
	 * repositorio TrainingRepository
	 * 
	 * @return void
	 */
	public void delete(Training training) {
		Assert.notNull(training);
		// TODO Restricciones de Borrado

		trainingRepository.delete(training);
	}

	// Other business methods ----------------

	// Assertions

}
