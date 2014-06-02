package services;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.DayRepository;
import repositories.DietRepository;
import repositories.MealRepository;
import repositories.PlanRepository;
import repositories.TrainingRepository;
import domain.Diet;
import domain.Meal;
import domain.Meals;
import domain.Plan;
import domain.Training;

@Transactional
@Service
public class RestService {

	@Autowired
	private PlanRepository planRepository;
	@Autowired
	private DietRepository dietRepository;
	@Autowired
	private TrainingRepository trainingRepository;
	@Autowired
	private DayRepository dayRepository;
	@Autowired
	private MealRepository mealRepository;

	public RestService() {
		super();
	}

	public Plan findPlanByCustomerId(int customerId) {
		Assert.notNull(customerId);
		return planRepository.findByCustomer(customerId);
	}

	public Diet findDietByCustomerId(int customerId) {
		Assert.notNull(customerId);
		return dietRepository.findDietByCustomer(customerId);
	}

	public Training findTrainingByCustomerId(int customerId) {
		Assert.notNull(customerId);
		return trainingRepository.findTrainingByCustomer(customerId);
	}

	public Collection<Meals> findDayMealList(int dayId) {
		Assert.notNull(dayId);
		return dayRepository.findMealsByDay(dayId);
	}

	public Meal findMealByDayIdAndMealName(int dayId, Meals mealName) {
		Assert.notNull(dayId);
		Assert.notNull(mealName);
		return mealRepository.findMealByDayIdAndMealName(dayId, mealName);
	}
}
