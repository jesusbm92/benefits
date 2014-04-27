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

import services.FoodService;
import domain.Amount;
import domain.Food;

@Controller
@RequestMapping("/food/administrator")
public class AdministratorFoodController {

	@Autowired
	private FoodService foodService;

	// Constructors -----------------------------------------------------------

	public AdministratorFoodController() {
		super();
	}

	// list ---------------------------------------------------------------

	@RequestMapping("/list")
	public ModelAndView list() {
		ModelAndView result;

		Collection<Food> foods = foodService.findAll();
		String uri = "food/administrator/list";
		String requestURI = "food/administrator/list.do";
		result = createListModelAndView(requestURI, foods, uri);
		return result;
	}

	// Creation
	// ------------------------------------------------------------------
	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create() {

		ModelAndView result;

		Food food = foodService.create();

		result = createCreateModelAndView(food);

		return result;
	}

	// Edition
	// -------------------------------------------------------------------

	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit(@RequestParam int foodId) {
		ModelAndView result;
		Food food = foodService.findOne(foodId);

		result = createEditModelAndView(food);

		return result;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid Food food, BindingResult binding) {
		ModelAndView result;

		if (binding.hasErrors()) {
			if (food.getId() == 0) {
				result = createEditModelAndView(food, "food.commit.error");
			} else {
				result = createCreateModelAndView(food, "food.commit.error");
			}
		} else {
			try {
				foodService.save(food);
				result = new ModelAndView("redirect:list.do");
			} catch (Throwable oops) {
				if (food.getId() == 0) {
					result = createEditModelAndView(food, "food.commit.error");
				} else {
					result = createCreateModelAndView(food, "food.commit.error");
				}

			}

		}

		return result;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "delete")
	public ModelAndView delete(@ModelAttribute Food food,
			BindingResult bindingResult) {
		ModelAndView result;

		try {
			foodService.delete(food);
			result = new ModelAndView("redirect:list.do");
		} catch (Throwable oops) {
			if (oops.getMessage() == "Error") {
				result = createEditModelAndView(food, "food.error");
			} else {
				result = createEditModelAndView(food, "food.commit.error");
			}
		}
		return result;
	}

	// Other bussiness method
	protected ModelAndView createEditModelAndView(Food food) {
		assert food != null;

		ModelAndView result;

		result = createEditModelAndView(food, null);

		return result;
	}

	protected ModelAndView createCreateModelAndView(Food food) {
		assert food != null;

		ModelAndView result;

		result = createCreateModelAndView(food, null);

		return result;
	}

	protected ModelAndView createEditModelAndView(Food food, String message) {
		assert food != null;
		Collection<Amount> amounts = new ArrayList<Amount>();

		ModelAndView result;
		result = new ModelAndView("food/administrator/edit");
		result.addObject("food", food);
		result.addObject("amounts", amounts);
		result.addObject("create", false);

		return result;
	}

	protected ModelAndView createCreateModelAndView(Food food, String message) {
		assert food != null;
		Collection<Amount> amounts = new ArrayList<Amount>();

		ModelAndView result;
		result = new ModelAndView("food/administrator/create");
		result.addObject("food", food);
		result.addObject("amounts", amounts);
		result.addObject("create", true);

		return result;
	}

	protected ModelAndView createListModelAndView(String requestURI,
			Collection<Food> foods, String uri) {
		ModelAndView result;

		result = new ModelAndView(uri);
		result.addObject("foods", foods);
		result.addObject("requestURI", requestURI);

		return result;
	}

}
