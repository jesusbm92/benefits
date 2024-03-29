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

import services.DayService;
import services.MealService;
import controllers.AbstractController;
import domain.Day;
import domain.Days;
import domain.Language;
import domain.Meal;

@Controller
@RequestMapping("/day/administrator")
public class AdministratorDayController extends AbstractController {
	// Services ----------------------------------------------------------------

	@Autowired
	private DayService dayService;
	@Autowired
	private MealService mealService;

	// Constructor
	// ---------------------------------------------------------------
	public AdministratorDayController() {
		super();
	}

	// Listing
	// -------------------------------------------------------------------

	@RequestMapping("/listDaysByDiet")
	public ModelAndView list(@RequestParam int dietId) {
		ModelAndView result;
		String uri = "day/administrator/list";
		String requestURI = "day/administrator/list.do";
		Collection<Day> days = dayService.findDaysByDiet(dietId);
		result = createListModelAndView(requestURI, days, uri);

		return result;
	}

	@RequestMapping("/listDaysByMeal")
	public ModelAndView listDaysByMeal(@RequestParam int mealId) {
		ModelAndView result;
		String uri = "day/administrator/listDaysByMeal";
		String requestURI = "day/administrator/listDaysByMeal.do";
		Collection<Day> days = dayService.findDaysByMeal(mealId);
		result = createListModelAndView(requestURI, days, uri);

		return result;
	}

	@RequestMapping("/list")
	public ModelAndView listDays() {
		ModelAndView result;
		String uri = "day/administrator/list";
		String requestURI = "day/administrator/list.do";
		String language = LocaleContextHolder.getLocale().getDisplayLanguage();

		Collection<Day> days = dayService.findAllLanguage(language
				.toLowerCase());
		result = createListModelAndView(requestURI, days, uri);

		return result;
	}

	// Creation
	// ------------------------------------------------------------------

	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create() {

		ModelAndView result;

		Day day = dayService.create();

		result = createCreateModelAndView(day);

		return result;
	}

	// Edition
	// -------------------------------------------------------------------

	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit(@RequestParam int dayId) {
		ModelAndView result;
		Day day = dayService.findOne(dayId);
		result = createEditModelAndView(day);

		return result;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid Day day, BindingResult binding,
			RedirectAttributes redirect) {
		ModelAndView result;
		if (binding.hasErrors()) {
			if (day.getId() == 0) {
				result = createCreateModelAndView(day, "day.commit.error");
			} else {
				result = createEditModelAndView(day, "day.commit.error");
			}
		} else {
			try {
				String language = LocaleContextHolder.getLocale()
						.getDisplayLanguage();
				Language lang = Language.valueOf(language.toLowerCase());
				day.setEntityLanguage(lang);
				dayService.save(day);
				redirect.addFlashAttribute("successMessage", "day.editSuccess");
				result = new ModelAndView("redirect:list.do");
			} catch (Throwable oops) {
				if (day.getId() == 0) {
					result = createCreateModelAndView(day, "day.commit.error");
				} else {
					result = createEditModelAndView(day, "day.commit.error");
				}

			}

		}

		return result;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "delete")
	public ModelAndView delete(@ModelAttribute Day day,
			BindingResult bindingResult, RedirectAttributes redirect) {
		ModelAndView result;
		try {
			dayService.delete(day);
			redirect.addFlashAttribute("successMessage", "day.deleteSuccess");
			result = new ModelAndView("redirect:list.do");
		} catch (Throwable oops) {
			if (oops.getMessage() == "Error") {
				result = createEditModelAndView(day, "diet.error");
			} else {
				result = createEditModelAndView(day, "diet.commit.error");
			}
		}
		return result;
	}

	// Other bussiness method

	protected ModelAndView createEditModelAndView(Day day) {
		assert day != null;

		ModelAndView result;

		result = createEditModelAndView(day, null);

		return result;
	}

	protected ModelAndView createCreateModelAndView(Day day) {
		assert day != null;

		ModelAndView result;

		result = createCreateModelAndView(day, null);

		return result;
	}

	protected ModelAndView createEditModelAndView(Day day, String message) {
		assert day != null;
		String language = LocaleContextHolder.getLocale().getDisplayLanguage();

		Collection<Meal> meals = mealService.findAllLanguage(language
				.toLowerCase());

		ModelAndView result;
		result = new ModelAndView("day/administrator/edit");
		result.addObject("day", day);
		result.addObject("names", Days.values());
		result.addObject("message", message);
		result.addObject("meals", meals);
		result.addObject("languages", Language.values());
		result.addObject("create", false);
		return result;
	}

	protected ModelAndView createCreateModelAndView(Day day, String message) {
		assert day != null;
		String language = LocaleContextHolder.getLocale().getDisplayLanguage();

		Collection<Meal> meals = mealService.findAllLanguage(language
				.toLowerCase());

		ModelAndView result;
		result = new ModelAndView("day/administrator/create");
		result.addObject("day", day);
		result.addObject("names", Days.values());
		result.addObject("message", message);
		result.addObject("languages", Language.values());
		result.addObject("create", true);
		result.addObject("meals", meals);
		return result;
	}

	protected ModelAndView createListModelAndView(String requestURI,
			Collection<Day> days, String uri) {
		ModelAndView result;

		result = new ModelAndView(uri);
		result.addObject("days", days);
		result.addObject("requestURI", requestURI);

		return result;
	}

}
