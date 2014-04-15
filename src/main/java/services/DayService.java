package services;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;

import repositories.DayRepository;
import domain.Day;

public class DayService {

	// Managed repository-----------------------

	@Autowired
	private DayRepository dayRepository;

	// Supporting services -----------------

	// Constructors --------------------------
	public DayService() {
		super();
	}

	// Simple CRUD methods -----------------
	/**
	 * Constructor por defecto de DayService
	 * 
	 * @return Day day
	 */
	public Day create() {
		Day day = new Day();

		return day;
	}

	/**
	 * Devuelve una colección con todos los objetos de tipo Day
	 * 
	 * @return Collection<Day> days
	 */
	public Collection<Day> findAll() {
		return dayRepository.findAll();
	}

	/**
	 * Devuelve una instancia de un objetos de tipo Day En caso de no
	 * encontrarse, devuelve null
	 * 
	 * @return Day day
	 */
	public Day findOne(int dayId) {
		return dayRepository.findOne(dayId);
	}

	/**
	 * Persiste (guarda o crea) el objeto de tipo Day en la base de datos a
	 * través del repositorio DayRepository
	 * 
	 * @return void
	 */
	public void save(Day day) {
		// TODO Restricciones de Save

		dayRepository.save(day);
	}

	/**
	 * Elimina el objeto de tipo Day de la base de datos a través del
	 * repositorio DayRepository
	 * 
	 * @return void
	 */
	public void delete(Day day) {
		Assert.notNull(day);
		// TODO Restricciones de Borrado

		dayRepository.delete(day);
	}

	// Other business methods ----------------

	// Assertions

}
