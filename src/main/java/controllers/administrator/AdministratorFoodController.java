package controllers.administrator;

import java.util.ArrayList;
import java.util.Collection;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.support.ByteArrayMultipartFileEditor;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import services.FoodService;
import domain.Amount;
import domain.Food;
import domain.Language;

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

		String language = LocaleContextHolder.getLocale().getDisplayLanguage();

		Collection<Food> foods = foodService.findAllLanguage(language
				.toLowerCase());
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
	public ModelAndView save(@Valid Food food, BindingResult binding,
			RedirectAttributes redirect) {
		ModelAndView result;

		if (binding.hasErrors()) {
			if (food.getId() == 0) {
				result = createEditModelAndView(food, "food.commit.error");
			} else {
				result = createCreateModelAndView(food, "food.commit.error");
			}
		} else {
			try {
				String language = LocaleContextHolder.getLocale()
						.getDisplayLanguage();
				Language lang = Language.valueOf(language.toLowerCase());
				food.setEntityLanguage(lang);
				redirect.addFlashAttribute("successMessage", "food.editSuccess");
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
			BindingResult bindingResult, RedirectAttributes redirect) {
		ModelAndView result;

		try {
			redirect.addFlashAttribute("successMessage", "food.deleteSuccess");
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

	@InitBinder
	protected void initBinder(HttpServletRequest request,
			ServletRequestDataBinder binder) throws ServletException {
		binder.registerCustomEditor(byte[].class,
				new ByteArrayMultipartFileEditor());
	}

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
		result.addObject("languages", Language.values());
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
		result.addObject("languages", Language.values());
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
