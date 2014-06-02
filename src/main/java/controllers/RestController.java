package controllers;

import java.util.Collection;

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
import domain.Meal;
import domain.Meals;
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

	// Un método al que le pasas el id del día y te devuelve una lista con los
	// tipos de meals que tiene ese día.
	@RequestMapping(value = "/listmeals", method = RequestMethod.POST)
	@ResponseBody
	public Collection<Meals> listmeals(@RequestParam int dayId) {
		return restService.findDayMealList(dayId);
	}

	// Un método al que le pasas el id del día y el enumerado de tipo de meal y
	// te devuelve el meal completo con las imágenes.
	@RequestMapping(value = "/meal", method = RequestMethod.POST)
	@ResponseBody
	public Meal meal(@RequestParam int dayId, @RequestParam String mealName) {
		Meals mealEnum = null;
		if (mealName.equals(Meals.BREAKFAST.toString())) {
			mealEnum = Meals.BREAKFAST;
		} else if (mealName.equals(Meals.MID_MORNING.toString())) {
			mealEnum = Meals.MID_MORNING;
		} else if (mealName.equals(Meals.LUNCH.toString())) {
			mealEnum = Meals.LUNCH;
		} else if (mealName.equals(Meals.TEA_TIME.toString())) {
			mealEnum = Meals.TEA_TIME;
		} else if (mealName.equals(Meals.DINNER.toString())) {
			mealEnum = Meals.DINNER;
		}

		return restService.findMealByDayIdAndMealName(dayId, mealEnum);
	}
}
