package controllers.administrator;

import java.util.ArrayList;
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

import services.AmountService;
import services.FoodService;
import services.MealService;
import domain.Amount;
import domain.Food;

@Controller
@RequestMapping("/amount/administrator")
public class AdministratorAmountController {

	@Autowired
	private AmountService amountService;

	@Autowired
	private FoodService foodService;

	@Autowired
	private MealService mealService;

	// Constructors -----------------------------------------------------------

	public AdministratorAmountController() {
		super();
	}

	// list ---------------------------------------------------------------

	@RequestMapping("/listDetails")
	public ModelAndView list(@RequestParam int mealId) {
		ModelAndView result;

		Collection<Amount> amounts = amountService.findAmountsByMeal(mealId);
		String uri = "amount/administrator/list";
		String requestURI = "amount/administrator/list.do";
		Collection<Food> foods = new ArrayList<Food>();
		for (Amount a : amounts) {
			foods.add(foodService.findFoodByAmount(a.getId()));
		}
		result = createListModelAndView(requestURI, amounts, foods, uri);
		return result;
	}

	// Creation
	// ------------------------------------------------------------------
	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create(@RequestParam int mealId) {

		ModelAndView result;

		Amount amount = amountService.create(mealId);

		result = createCreateModelAndView(amount, null);

		return result;
	}

	// Edition
	// -------------------------------------------------------------------

	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit(@RequestParam int amountId,
			@RequestParam int mealId) {
		ModelAndView result;
		Amount amount = amountService.findOne(amountId);

		result = createEditModelAndView(amount, mealId);

		return result;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid Amount amount, BindingResult binding, RedirectAttributes redirect) {
		ModelAndView result;

		if (binding.hasErrors()) {
			if (amount.getId() == 0) {
				result = createCreateModelAndView(amount, "amount.commit.error");
			} else {
				result = createEditModelAndView(amount, "amount.commit.error");
			}
		} else {
			try {
				amountService.save(amount);
				redirect.addFlashAttribute("successMessage", "amount.editSuccess");
				result = new ModelAndView("redirect:listDetails.do?mealId="
						+ amount.getMeal().getId());
			} catch (Throwable oops) {
				if (amount.getId() == 0) {
					result = createCreateModelAndView(amount,
							"amount.commit.error");
				} else {
					result = createEditModelAndView(amount,
							"amount.commit.error");
				}
			}

		}

		return result;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "delete")
	public ModelAndView delete(@ModelAttribute Amount amount,
			BindingResult bindingResult, RedirectAttributes redirect) {
		ModelAndView result;

		try {
			amountService.delete(amount);
			redirect.addFlashAttribute("successMessage", "amount.deleteSuccess");
			result = new ModelAndView("redirect:listDetails.do?mealId="
					+ amount.getMeal().getId());
		} catch (Throwable oops) {
			if (oops.getMessage() == "Error") {
				result = createEditModelAndView(amount, "amount.error");
			} else {
				result = createEditModelAndView(amount, "amount.commit.error");
			}
		}
		return result;
	}

	// Other bussiness method
	protected ModelAndView createEditModelAndView(Amount amount, int mealId) {
		assert amount != null;

		ModelAndView result;
		amount.setMeal(mealService.findOne(mealId));
		result = createEditModelAndView(amount, null);

		return result;
	}

	protected ModelAndView createCreateMealModelAndView(Amount amount,
			String message) {
		assert amount != null;

		ModelAndView result;
		amount.setMeal(mealService.findOne(amount.getId()));
		result = createCreateModelAndView(amount, null);
		result.addObject("message", message);
		result.addObject("create", true);

		return result;
	}

	protected ModelAndView createEditModelAndView(Amount amount, String message) {
		assert amount != null;
		String language = LocaleContextHolder.getLocale().getDisplayLanguage();

		Collection<Food> foods = foodService.findAllLanguage(language
				.toLowerCase());

		ModelAndView result;
		result = new ModelAndView("amount/administrator/edit");
		result.addObject("amount", amount);
		result.addObject("foods", foods);
		result.addObject("message", message);
		result.addObject("create", false);

		return result;
	}

	protected ModelAndView createCreateModelAndView(Amount amount,
			String message) {
		assert amount != null;
		String language = LocaleContextHolder.getLocale().getDisplayLanguage();

		Collection<Food> foods = foodService.findAllLanguage(language
				.toLowerCase());

		ModelAndView result;
		result = new ModelAndView("amount/administrator/create");
		result.addObject("amount", amount);
		result.addObject("foods", foods);
		result.addObject("message", message);

		return result;
	}

	protected ModelAndView createListModelAndView(String requestURI,
			Collection<Amount> amounts, Collection<Food> foods, String uri) {
		ModelAndView result;

		result = new ModelAndView(uri);
		result.addObject("amounts", amounts);
		result.addObject("foods", foods);
		result.addObject("requestURI", requestURI);

		return result;
	}

}
