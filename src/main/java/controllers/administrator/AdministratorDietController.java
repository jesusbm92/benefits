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

import services.DayService;
import services.DietService;
import services.PlanService;
import services.SponsorService;
import controllers.AbstractController;
import domain.Day;
import domain.Diet;
import domain.Plan;
import domain.Sponsor;

@Controller
@RequestMapping("/diet/administrator")
public class AdministratorDietController extends AbstractController {

	// Services ----------------------------------------------------------------

	@Autowired
	private DietService dietService;
	@Autowired
	private PlanService planService;
	@Autowired
	private SponsorService sponsorService;
	@Autowired
	private DayService dayService;

	// Constructor
	// ---------------------------------------------------------------
	public AdministratorDietController() {
		super();
	}

	// Listing
	// -------------------------------------------------------------------

	@RequestMapping("/list")
	public ModelAndView list() {
		ModelAndView result;
		String uri = "diet/administrator/list";
		String requestURI = "diet/administrator/list.do";
		Collection<Diet> diets = dietService.findAll();
		result = createListModelAndView(requestURI, diets, uri);

		return result;
	}

	@RequestMapping("/listAssigned")
	public ModelAndView listAssigned() {
		ModelAndView result;
		String uri = "diet/administrator/listAssigned";
		String requestURI = "diet/administrator/listAssigned.do";
		Collection<Diet> diets = dietService.findAssigned();
		result = createListModelAndView(requestURI, diets, uri);

		return result;
	}

	@RequestMapping("/listFree")
	public ModelAndView listFree() {
		ModelAndView result;
		String uri = "diet/administrator/listFree";
		String requestURI = "diet/administrator/listFree.do";
		Collection<Diet> diets = dietService.findFree();
		result = createListModelAndView(requestURI, diets, uri);

		return result;
	}

	@RequestMapping("/listDietsByDay")
	public ModelAndView listDietsByDay(@RequestParam int dayId) {
		ModelAndView result;
		String uri = "diet/administrator/listDietsByDay";
		String requestURI = "diet/administrator/listDietsByDay.do";
		Collection<Diet> diets = dietService.findDietsByDay(dayId);
		result = createListModelAndView(requestURI, diets, uri);

		return result;
	}

	@RequestMapping("/details")
	public ModelAndView details(@RequestParam int dietId) {
		ModelAndView result;
		String uri = "diet/administrator/details";
		String requestURI = "diet/administrador/details.do";
		Diet diet = dietService.findOne(dietId);
		result = createListModelAndView(requestURI, diet, uri);

		return result;
	}

	// Creation
	// ------------------------------------------------------------------
	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create() {

		ModelAndView result;

		Diet diet = dietService.create();

		result = createCreateModelAndView(diet);
		result.addObject("create", true);

		return result;
	}

	// Edition
	// -------------------------------------------------------------------

	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit(@RequestParam int dietId) {
		ModelAndView result;
		Diet diet = dietService.findOne(dietId);
		result = createEditModelAndView(diet);
		result.addObject("create", false);

		return result;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid Diet diet, BindingResult binding) {
		ModelAndView result;
		if (binding.hasErrors()) {
			result = createEditModelAndView(diet);
		} else {
			try {
				dietService.save(diet);
				result = new ModelAndView("redirect:list.do");
			} catch (Throwable oops) {
				result = createEditModelAndView(diet, "diet.commit.error");
			}
			result.addObject("create", false);
		}

		return result;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "delete")
	public ModelAndView delete(@ModelAttribute Diet diet,
			BindingResult bindingResult) {
		ModelAndView result;
		try {
			dietService.delete(diet);
			result = new ModelAndView("redirect:list.do");
		} catch (Throwable oops) {
			if (oops.getMessage() == "Error") {
				result = createEditModelAndView(diet, "diet.error");
			} else {
				result = createEditModelAndView(diet, "diet.commit.error");
			}
		}
		return result;
	}

	// Other bussiness method

	protected ModelAndView createEditModelAndView(Diet diet) {
		assert diet != null;

		ModelAndView result;

		result = createEditModelAndView(diet, null);

		return result;
	}

	protected ModelAndView createCreateModelAndView(Diet diet) {
		assert diet != null;

		ModelAndView result;

		result = createCreateModelAndView(diet, null);

		return result;
	}

	protected ModelAndView createEditModelAndView(Diet diet, String message) {
		assert diet != null;
		Collection<Plan> plans = planService.findAll();
		Collection<Sponsor> sponsors = sponsorService.findAll();
		Collection<Day> days = dayService.findAll();

		ModelAndView result;
		result = new ModelAndView("diet/administrator/edit");
		result.addObject("diet", diet);
		result.addObject("message", message);
		result.addObject("plans", plans);
		result.addObject("sponsors", sponsors);
		result.addObject("days", days);

		return result;
	}

	protected ModelAndView createCreateModelAndView(Diet diet, String message) {
		assert diet != null;
		Collection<Plan> plans = planService.findAll();
		Collection<Sponsor> sponsors = sponsorService.findAll();
		Collection<Day> days = dayService.findAll();

		ModelAndView result;
		result = new ModelAndView("diet/administrator/create");
		result.addObject("diet", diet);
		result.addObject("message", message);
		result.addObject("plans", plans);
		result.addObject("sponsors", sponsors);
		result.addObject("days", days);

		return result;
	}

	protected ModelAndView createListModelAndView(String requestURI,
			Collection<Diet> diets, String uri) {
		ModelAndView result;

		result = new ModelAndView(uri);
		result.addObject("diets", diets);
		result.addObject("requestURI", requestURI);

		return result;
	}

	protected ModelAndView createListModelAndView(String requestURI, Diet diet,
			String uri) {
		ModelAndView result;

		result = new ModelAndView(uri);
		result.addObject("diet", diet);
		result.addObject("requestURI", requestURI);

		return result;
	}
}
