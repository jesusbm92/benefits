package controllers.administrator;

import java.util.ArrayList;
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

import services.MealService;
import domain.Amount;
import domain.Meal;
import domain.Meals;

@Controller
@RequestMapping("/meal/administrator")
public class AdministratorMealController {

	@Autowired
	private MealService mealService;

	// Constructors -----------------------------------------------------------

	public AdministratorMealController() {
		super();
	}

	// list ---------------------------------------------------------------

	@RequestMapping("/list")
	public ModelAndView list() {
		ModelAndView result;

		Collection<Meal> meals = mealService.findAll();
		String uri = "meal/administrator/list";
		String requestURI = "meal/administrator/list.do";
		result = createListModelAndView(requestURI, meals, uri);
		return result;
	}

	@RequestMapping("/listMealsByDay")
	public ModelAndView listMealsByDay(@RequestParam int dayId) {
		ModelAndView result;

		Collection<Meal> meals = mealService.findMealsByDay(dayId);
		String uri = "meal/administrator/listMealsByDay";
		String requestURI = "meal/administrator/listMealsByDay.do";
		result = createListModelAndView(requestURI, meals, uri);
		return result;
	}

	// Creation
	// ------------------------------------------------------------------
	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create() {

		ModelAndView result;

		Meal meal = mealService.create();

		result = createEditModelAndView(meal);
		result.addObject("create", true);

		return result;
	}

	// Edition
	// -------------------------------------------------------------------

	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit(@RequestParam int mealId) {
		ModelAndView result;
		Meal meal = mealService.findOne(mealId);

		result = createEditModelAndView(meal);
		result.addObject("create", false);

		return result;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid Meal meal, BindingResult binding) {
		ModelAndView result;

		if (binding.hasErrors()) {
			result = createEditModelAndView(meal);
		} else {
			try {
				mealService.save(meal);
				result = new ModelAndView("redirect:list.do");
			} catch (Throwable oops) {
				result = createEditModelAndView(meal, "meal.commit.error");
			}
			result.addObject("create", false);
		}

		return result;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "delete")
	public ModelAndView delete(@ModelAttribute Meal meal,
			BindingResult bindingResult) {
		ModelAndView result;

		try {
			mealService.delete(meal);
			result = new ModelAndView("redirect:list.do");
		} catch (Throwable oops) {
			if (oops.getMessage() == "Error") {
				result = createEditModelAndView(meal, "meal.error");
			} else {
				result = createEditModelAndView(meal, "meal.commit.error");
			}
		}
		return result;
	}

	// Other bussiness method
	protected ModelAndView createEditModelAndView(Meal meal) {
		assert meal != null;

		ModelAndView result;

		result = createEditModelAndView(meal, null);

		return result;
	}

	protected ModelAndView createEditModelAndView(Meal meal, String message) {
		assert meal != null;
		Collection<Amount> amounts = new ArrayList<Amount>();

		ModelAndView result;
		result = new ModelAndView("meal/administrator/edit");
		result.addObject("meal", meal);
		result.addObject("names", Meals.values());
		result.addObject("amounts", amounts);

		return result;
	}

	protected ModelAndView createListModelAndView(String requestURI,
			Collection<Meal> meals, String uri) {
		ModelAndView result;

		result = new ModelAndView(uri);
		result.addObject("meals", meals);
		result.addObject("requestURI", requestURI);

		return result;
	}

}
