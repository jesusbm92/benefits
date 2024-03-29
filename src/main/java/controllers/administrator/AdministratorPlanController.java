package controllers.administrator;

import java.util.Collection;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import services.CustomerService;
import services.DietService;
import services.PlanService;
import services.TrainingService;
import controllers.AbstractController;
import domain.Customer;
import domain.Diet;
import domain.Goals;
import domain.Language;
import domain.Plan;
import domain.Training;

@Controller
@RequestMapping("/plan/administrator")
public class AdministratorPlanController extends AbstractController {

	// Services ----------------------------------------------------------------

	@Autowired
	private PlanService planService;
	@Autowired
	private DietService dietService;
	@Autowired
	private TrainingService trainingService;
	@Autowired
	private CustomerService customerService;

	// Constructor
	// ---------------------------------------------------------------
	public AdministratorPlanController() {
		super();
	}

	// Listing
	// -------------------------------------------------------------------

	@RequestMapping("/list")
	public ModelAndView list() {
		ModelAndView result;
		String uri = "plan/administrator/list";
		String requestURI = "plan/administrator/list.do";
		String language = LocaleContextHolder.getLocale().getDisplayLanguage();

		Collection<Plan> plans = planService.findAllLanguage(language
				.toLowerCase());
		result = createListModelAndView(requestURI, plans, uri);

		return result;
	}

	@RequestMapping("/listPlansByDiet")
	public ModelAndView list(@RequestParam int dietId) {
		ModelAndView result;

		Collection<Plan> plans = planService.findPlansByDiet(dietId);

		String uri = "plan/administrator/listPlansByDiet";
		String requestURI = "issue/administrator/listPlansByDiet.do";
		result = createListModelAndView(requestURI, plans, uri);
		return result;
	}

	// Creation
	// ------------------------------------------------------------------
	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create() {

		ModelAndView result;

		Plan plan = planService.create();

		result = createEditModelAndView(plan);
		result.addObject("create", true);

		return result;
	}

	@RequestMapping(value = "/change", method = RequestMethod.GET)
	public ModelAndView change(@RequestParam int customerId) {
		ModelAndView result;
		Customer customer = customerService.findOne(customerId);
		Goals goal = customer.getPlan().getGoal();
		Collection<Plan> plans = planService.findByGoal(goal);

		result = new ModelAndView("plan/administrator/change");
		result.addObject("customer", customer);
		result.addObject("plans", plans);

		return result;
	}

	@RequestMapping(value = "/change", method = RequestMethod.POST, params = "saveChange")
	public ModelAndView saveChange(@Valid Customer customer,
			BindingResult binding, RedirectAttributes redirect) {
		ModelAndView result;

		if (binding.hasErrors()) {
			result = new ModelAndView("plan/administrator/change");
			String language = LocaleContextHolder.getLocale()
					.getDisplayLanguage();

			Collection<Plan> plans = planService.findAllLanguage(language
					.toLowerCase());
			result.addObject("plans", plans);
			result.addObject("customer", customer);
		} else {
			try {
				customerService.saveCustomerForPlanChange(customer);
				result = new ModelAndView(
						"redirect:../../issue/administrator/listAll.do");
				redirect.addFlashAttribute("successMessage", "plan.cambiado");

			} catch (Throwable oops) {
				result = new ModelAndView("plan/administrator/change");
				String language = LocaleContextHolder.getLocale()
						.getDisplayLanguage();

				Collection<Plan> plans = planService.findAllLanguage(language
						.toLowerCase());
				result.addObject("plans", plans);
				result.addObject("customer", customer);
			}
		}

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
	public ModelAndView save(@Valid Plan plan, BindingResult binding,
			RedirectAttributes redirect) {
		ModelAndView result;

		if (binding.hasErrors()) {
			result = createEditModelAndView(plan);
		} else {
			try {
				String language = LocaleContextHolder.getLocale()
						.getDisplayLanguage();
				Language lang = Language.valueOf(language.toLowerCase());
				plan.setEntityLanguage(lang);
				planService.save(plan);
				redirect.addFlashAttribute("successMessage", "plan.editSuccess");
				result = new ModelAndView("redirect:list.do");
			} catch (Throwable oops) {
				result = createEditModelAndView(plan, "plan.commit.error");
			}
			result.addObject("create", false);
		}

		return result;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "delete")
	public ModelAndView delete(@ModelAttribute Plan plan,
			BindingResult bindingResult, RedirectAttributes redirect) {
		ModelAndView result;

		try {
			planService.delete(plan);
			redirect.addFlashAttribute("successMessage", "plan.deleteSuccess");
			result = new ModelAndView("redirect:list.do");
		} catch (Throwable oops) {
			if (oops.getMessage() == "Error") {
				result = createEditModelAndView(plan, "plan.error");
			} else {
				result = createEditModelAndView(plan, "plan.commit.error");
			}
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
		String language = LocaleContextHolder.getLocale().getDisplayLanguage();

		Collection<Training> trainings = trainingService
				.findAllLanguage(language.toLowerCase());
		Collection<Diet> diets = dietService.findAllLanguage(language
				.toLowerCase());

		ModelAndView result;
		result = new ModelAndView("plan/administrator/edit");
		result.addObject("plan", plan);
		result.addObject("message", message);
		result.addObject("goals", Goals.values());
		result.addObject("languages", Language.values());
		result.addObject("diets", diets);
		result.addObject("trainings", trainings);

		return result;
	}

	protected ModelAndView createListModelAndView(String requestURI,
			Collection<Plan> plans, String uri) {
		ModelAndView result;

		result = new ModelAndView(uri);
		result.addObject("plans", plans);
		result.addObject("requestURI", requestURI);

		return result;
	}

}
