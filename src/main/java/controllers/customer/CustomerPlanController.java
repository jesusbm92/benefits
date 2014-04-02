package controllers.customer;

import java.util.Collection;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.CustomerService;
import services.PlanService;
import controllers.AbstractController;
import domain.Customer;
import domain.Plan;

@Controller
@RequestMapping("/plan/customer")
public class CustomerPlanController extends AbstractController {

	// Services ----------------------------------------------------------------

	@Autowired
	private PlanService planService;

	@Autowired
	private CustomerService customerService;

	// Constructor
	// ---------------------------------------------------------------
	public CustomerPlanController() {
		super();
	}

	// Listing
	// -------------------------------------------------------------------

	@RequestMapping("/list")
	public ModelAndView list() {
		ModelAndView result;
		Customer customer = customerService.findByPrincipal();
		String uri = "plan/customer/list";
		String requestURI = "plan/customer/list.do";
		Collection<Plan> plans = planService.findPlanByCustomer(customer
				.getId());
		Boolean hasPlan = plans.iterator().next() != null;

		result = createListModelAndView(requestURI, plans, uri, hasPlan);

		return result;
	}

	// Creation
	// ------------------------------------------------------------------
	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create(@RequestParam int paintingId) {
		ModelAndView result;

		Plan plan = planService.create();

		result = createEditModelAndView(plan);
		result.addObject("create", true);

		return result;
	}

	@RequestMapping(value = "/request", method = RequestMethod.GET)
	public ModelAndView request() {
		ModelAndView result;

		Collection<String> goals = planService.findAllGoals();

		Plan plan = planService.create();

		result = createEditModelAndView(plan);
		result.addObject("goals", goals);

		return result;
	}

	// Edition
	// -------------------------------------------------------------------

	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit(@RequestParam int planId) {
		ModelAndView result;
		Plan plan = planService.findOne(planId);

		result = createEditModelAndView(plan);
		result.addObject("create", false);

		return result;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid Plan plan, BindingResult binding) {
		ModelAndView result;

		if (binding.hasErrors()) {
			result = createEditModelAndView(plan);
		} else {
			try {
				planService.save(plan);
				result = new ModelAndView("");
			} catch (Throwable oops) {
				result = createEditModelAndView(plan, "plan.commit.error");
			}
			result.addObject("create", false);
		}

		return result;
	}

	// Other bussiness method
	protected ModelAndView createEditModelAndView(Plan plan) {
		assert plan != null;

		ModelAndView result;

		result = createEditModelAndView(plan, null);

		return result;
	}

	protected ModelAndView createEditModelAndView(Plan plan, String message) {
		assert plan != null;

		ModelAndView result;

		result = new ModelAndView("plan/administrator/edit");
		result.addObject("plan", plan);
		result.addObject("message", message);

		return result;
	}

	protected ModelAndView createListModelAndView(String requestURI,
			Collection<Plan> plans, String uri, Boolean res) {
		ModelAndView result;

		result = new ModelAndView(uri);
		result.addObject("plans", plans);
		result.addObject("requestURI", requestURI);
		result.addObject("res", res);

		return result;
	}

}
