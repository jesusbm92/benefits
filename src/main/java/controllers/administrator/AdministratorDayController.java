package controllers.administrator;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.DayService;
import controllers.AbstractController;
import domain.Day;

@Controller
@RequestMapping("/day/administrator")
public class AdministratorDayController extends AbstractController {
	// Services ----------------------------------------------------------------

	@Autowired
	private DayService dayService;

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

	// Creation
	// ------------------------------------------------------------------

	// Edition
	// -------------------------------------------------------------------

	// Other bussiness method

	// protected ModelAndView createEditModelAndView(Day day) {
	// assert day != null;
	//
	// ModelAndView result;
	//
	// result = createEditModelAndView(day, null);
	//
	// return result;
	// }

	// protected ModelAndView createEditModelAndView(Day day, String message) {
	// assert day != null;
	//
	// ModelAndView result;
	//
	// return result;
	// }

	protected ModelAndView createListModelAndView(String requestURI,
			Collection<Day> days, String uri) {
		ModelAndView result;

		result = new ModelAndView(uri);
		result.addObject("days", days);
		result.addObject("requestURI", requestURI);

		return result;
	}

}
