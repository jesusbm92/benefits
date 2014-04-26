package controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import services.CustomerService;
import services.RestService;
import domain.Customer;
import domain.Diet;
import domain.Plan;
import domain.Training;

@Controller
@RequestMapping("/rest")
public class RestController extends AbstractController {

	@Autowired
	private RestService restService;

	@Autowired
	private CustomerService customerService;

	public RestController() {
		super();
	}

	@RequestMapping(value = "/plan", method = RequestMethod.GET)
	@ResponseBody
	public Plan getUserPlan(@RequestParam String customerId) {
		Plan plan = restService.findPlanByCustomerId(Integer
				.parseInt(customerId));
		return plan;
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	@ResponseBody
	public Plan authenticateUser() {
		Customer customer = customerService.findByPrincipal();
		Plan plan = restService.findPlanByCustomerId(customer.getId());
		return plan;
	}

	@RequestMapping(value = "/diet", method = RequestMethod.POST)
	@ResponseBody
	public Diet dietUser() {
		Customer customer = customerService.findByPrincipal();
		Diet diet = restService.findDietByCustomerId(customer.getId());
		return diet;
	}

	@RequestMapping(value = "/training", method = RequestMethod.POST)
	@ResponseBody
	public Training trainingUser() {
		Customer customer = customerService.findByPrincipal();
		Training training = restService.findTrainingByCustomerId(customer
				.getId());
		return training;
	}

}
