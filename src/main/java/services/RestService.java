package services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.DietRepository;
import repositories.PlanRepository;
import repositories.TrainingRepository;
import domain.Diet;
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

}
