package services;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.ExerciseRepository;
import domain.Exercise;
import domain.Language;

@Transactional
@Service
public class ExerciseService {

	// Managed repository-----------------------

	@Autowired
	private ExerciseRepository exerciseRepository;

	// Supporting services -----------------

	@Autowired
	private AdministratorService administratorService;
	@Autowired
	private ExerciseGroupService exerciseGroupService;
	@Autowired
	private MuscleService muscleService;

	// Constructors --------------------------
	public ExerciseService() {
		super();
	}

	// Simple CRUD methods -----------------
	/**
	 * Constructor por defecto de ExerciseService
	 * 
	 * @return Exercise exercise
	 */
	public Exercise create() {
		Exercise exercise = new Exercise();

		return exercise;
	}

	/**
	 * Devuelve una colección con todos los objetos de tipo Exercise
	 * 
	 * @return Collection<Exercise> exercises
	 */
	public Collection<Exercise> findAll() {
		Assert.isTrue(administratorService.IAmAnAdmin());
		return exerciseRepository.findAll();
	}

	public Collection<Exercise> findAllLanguage(String language) {
		Assert.isTrue(administratorService.IAmAnAdmin());
		Collection<Exercise> result;
		if (language == Language.English.toString()) {
			result = exerciseRepository.findAllLanguage(Language.English);
		} else {
			result = exerciseRepository.findAllLanguage(Language.Spanish);
		}
		return result;
	}

	/**
	 * Devuelve una instancia de un objetos de tipo Exercise En caso de no
	 * encontrarse, devuelve null
	 * 
	 * @return Exercise exercise
	 */
	public Exercise findOne(int exerciseId) {
		Assert.isTrue(administratorService.IAmAnAdmin());
		return exerciseRepository.findOne(exerciseId);
	}

	/**
	 * Persiste (guarda o crea) el objeto de tipo Exercise en la base de datos a
	 * través del repositorio ExerciseRepository
	 * 
	 * @return void
	 */
	public void save(Exercise exercise) {
		// TODO Restricciones de Save
		Assert.isTrue(administratorService.IAmAnAdmin());
		// Muscle muscle = exercise.getMuscle();
		//
		// muscle.getExercises().add(exercise);
		//
		// muscleService.save(muscle);
		//
		// ExerciseGroup exerciseGroup = exercise.getExerciseGroup();
		//
		// exerciseGroup.getExercises().add(exercise);
		//
		// exerciseGroupService.save(exerciseGroup);

		exerciseRepository.save(exercise);
	}

	/**
	 * Elimina el objeto de tipo Exercise de la base de datos a través del
	 * repositorio ExerciseRepository
	 * 
	 * @return void
	 */
	public void delete(Exercise exercise) {
		Assert.notNull(exercise);
		// TODO Restricciones de Borrado
		Assert.isTrue(administratorService.IAmAnAdmin());

		// FALTA SABER LO QUE NO SE PUEDE BORRAR SI ESTA ASIGNADO A ALGUN
		// EJERCICIO DE GRUPO DE UN PLAN

		exerciseRepository.delete(exercise);
	}

	public Collection<Exercise> findByExerciseGroup(int exerciseGroup) {
		// TODO Auto-generated method stub
		return exerciseRepository.findByExerciseGroup(exerciseGroup);
	}

	// Other business methods ----------------

	// Assertions

}
