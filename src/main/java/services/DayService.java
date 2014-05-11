package services;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.DayRepository;
import domain.Day;
import domain.Language;

@Transactional
@Service
public class DayService {

	// Managed repository-----------------------

	@Autowired
	private DayRepository dayRepository;

	// Supporting services -----------------
	@Autowired
	private UserService userService;

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
		Assert.isTrue(userService.IAmAnAdmin());
		return dayRepository.findAll();
	}

	public Collection<Day> findAllLanguage(String language) {
		Assert.isTrue(userService.IAmAnAdmin());
		Collection<Day> result;
		int dev;
		if (language == Language.english.toString()) {
			result = dayRepository.findAllLanguage(Language.english);
		} else {
			result = dayRepository.findAllLanguage(Language.spanish);
		}
		return result;
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

		Assert.isTrue(userService.IAmAnAdmin());

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
		Assert.isTrue(userService.IAmAnAdmin());

		dayRepository.delete(day);
	}

	// Other business methods ----------------

	public Collection<Day> findDaysByDiet(int dietId) {
		Assert.isTrue(userService.IAmAnAdmin());
		Collection<Day> days = dayRepository.findDaysByDiet(dietId);
		return days;
	}

	public Collection<Day> findDaysByMeal(int mealId) {
		Assert.isTrue(userService.IAmAnAdmin());
		Collection<Day> days = dayRepository.findDaysByMeal(mealId);
		return days;
	}

	// Assertions

}
