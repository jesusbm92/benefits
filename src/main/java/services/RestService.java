package services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import repositories.PlanRepository;
import domain.Plan;

@Transactional
@Service
public class RestService {

	@Autowired
	private PlanRepository planRepository;

	public RestService() {
		super();
	}

	public Plan findPlanByCustomerId(int customerId) {
		return planRepository.findByCustomer(customerId);
	}

}
