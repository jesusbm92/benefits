package controllers.customer;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.support.ByteArrayMultipartFileEditor;
import org.springframework.web.servlet.ModelAndView;

import services.CustomerService;
import services.DietService;
import controllers.AbstractController;
import domain.Diet;

@Controller
@RequestMapping("/diet/customer")
public class CustomerDietController extends AbstractController {

	// Services ----------------------------------------------------------------

	@Autowired
	private DietService dietService;

	@Autowired
	private CustomerService customerService;

	// Constructor
	// ---------------------------------------------------------------
	public CustomerDietController() {
		super();
	}

	// Listing
	// -------------------------------------------------------------------

	@RequestMapping("/details")
	public ModelAndView details(@RequestParam int dietId) {
		ModelAndView result;
		String uri = "diet/customer/details";
		String requestURI = "diet/customer/details.do";
		Diet diet = dietService.findOne(dietId);
		result = createListModelAndView(requestURI, diet, uri);

		return result;
	}

	// Creation
	// -------------------------------------------------------------------

	// Edition
	// -------------------------------------------------------------------

	@InitBinder
	protected void initBinder(HttpServletRequest request,
			ServletRequestDataBinder binder) throws ServletException {
		binder.registerCustomEditor(byte[].class,
				new ByteArrayMultipartFileEditor());
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
