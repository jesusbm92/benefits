package controllers.administrator;

import java.util.Collection;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.DietService;
import services.PlanService;
import services.TrainingService;
import controllers.AbstractController;
import domain.Diet;
import domain.Goals;
import domain.Plan;
import domain.Training;

@Controller
@RequestMapping("/training/administrator")
public class AdministratorTrainingController extends AbstractController {

	// Services ----------------------------------------------------------------

	@Autowired
	private PlanService planService;
	@Autowired
	private DietService dietService;
	@Autowired
	private TrainingService trainingService;

	// Constructor
	// ---------------------------------------------------------------
	public AdministratorTrainingController() {
		super();
	}

	// Listing
	// -------------------------------------------------------------------

	@RequestMapping("/listAsignTraining")
	public ModelAndView listAsignTraining() {
		ModelAndView result;
		String uri = "training/administrator/listAsignTraining";
		String requestURI = "training/administrator/listAsignTraining.do";
		Collection<Training> trainings = trainingService
				.findAllTrainingAsigned();
		result = createListModelAndView(requestURI, trainings, uri);

		return result;
	}

	@RequestMapping("/listNotTrainingAsigned")
	public ModelAndView listNotTrainingAsigned() {
		ModelAndView result;
		String uri = "training/administrator/listNotTrainingAsigned";
		String requestURI = "training/administrator/listNotTrainingAsigned.do";
		Collection<Training> trainings = trainingService
				.findAllNotTrainingAsigned();
		result = createListModelAndView(requestURI, trainings, uri);

		return result;
	}

	@RequestMapping("/list")
	public ModelAndView list() {
		ModelAndView result;
		String uri = "training/administrator/list";
		String requestURI = "training/administrator/list.do";
		Collection<Training> trainings = trainingService.findAll();
		result = createListModelAndView(requestURI, trainings, uri);

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
			BindingResult bindingResult) {
		ModelAndView result;

		try {
			planService.delete(plan);
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
		Collection<Training> trainings = trainingService.findAll();
		Collection<Diet> diets = dietService.findAll();

		ModelAndView result;
		result = new ModelAndView("plan/administrator/edit");
		result.addObject("plan", plan);
		result.addObject("message", message);
		result.addObject("goals", Goals.values());
		result.addObject("diets", diets);
		result.addObject("trainings", trainings);

		return result;
	}

	protected ModelAndView createListModelAndView(String requestURI,
			Collection<Training> trainings, String uri) {
		ModelAndView result;

		result = new ModelAndView(uri);
		result.addObject("trainings", trainings);
		result.addObject("requestURI", requestURI);

		return result;
	}

}
