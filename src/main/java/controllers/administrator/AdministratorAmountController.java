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

import services.AmountService;
import services.FoodService;
import domain.Amount;
import domain.Food;

@Controller
@RequestMapping("/amount/administrator")
public class AdministratorAmountController {

	@Autowired
	private AmountService amountService;

	@Autowired
	private FoodService foodService;

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
	public ModelAndView create() {

		ModelAndView result;

		Amount amount = amountService.create();

		result = createEditModelAndView(amount);
		result.addObject("create", true);

		return result;
	}

	// Edition
	// -------------------------------------------------------------------

	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit(@RequestParam int amountId) {
		ModelAndView result;
		Amount amount = amountService.findOne(amountId);

		result = createEditModelAndView(amount);
		result.addObject("create", false);

		return result;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid Amount amount, BindingResult binding) {
		ModelAndView result;

		if (binding.hasErrors()) {
			result = createEditModelAndView(amount);
		} else {
			try {
				amountService.save(amount);
				result = new ModelAndView("redirect:list.do");
			} catch (Throwable oops) {
				result = createEditModelAndView(amount, "amount.commit.error");
			}
			result.addObject("create", false);
		}

		return result;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "delete")
	public ModelAndView delete(@ModelAttribute Amount amount,
			BindingResult bindingResult) {
		ModelAndView result;

		try {
			amountService.delete(amount);
			result = new ModelAndView("redirect:list.do");
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
	protected ModelAndView createEditModelAndView(Amount amount) {
		assert amount != null;

		ModelAndView result;

		result = createEditModelAndView(amount, null);

		return result;
	}

	protected ModelAndView createEditModelAndView(Amount amount, String message) {
		assert amount != null;
		Collection<Amount> amounts = new ArrayList<Amount>();

		ModelAndView result;
		result = new ModelAndView("amount/administrator/edit");
		result.addObject("amount", amount);
		result.addObject("amounts", amounts);

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
