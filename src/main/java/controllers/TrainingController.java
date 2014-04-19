package controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.TrainingService;
import domain.Training;

@Controller
@RequestMapping("/training")
public class TrainingController extends AbstractController {

	// Services ----------------------------------------------------------------

	@Autowired
	private TrainingService trainingService;

	// Constructor
	// ---------------------------------------------------------------
	public TrainingController() {
		super();
	}

	// Listing
	// -------------------------------------------------------------------

	@RequestMapping("/details")
	public ModelAndView details(@RequestParam int trainingId) {
		ModelAndView result;
		String uri = "training/details";
		String requestURI = "training/details.do";
		Training training = trainingService.findOne(trainingId);
		result = createListModelAndView(requestURI, training, uri);

		return result;
	}

	// Creation
	// ------------------------------------------------------------------
	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create(@RequestParam int paintingId) {
		ModelAndView result;

		Training training = trainingService.create();

		result = createEditModelAndView(training);
		result.addObject("create", true);

		return result;
	}

	// Edition
	// -------------------------------------------------------------------

	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit(@RequestParam int trainingId) {
		ModelAndView result;
		Training training = trainingService.findOne(trainingId);

		result = createEditModelAndView(training);
		result.addObject("create", false);

		return result;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid Training training, BindingResult binding) {
		ModelAndView result;

		if (binding.hasErrors()) {
			result = createEditModelAndView(training);
		} else {
			try {
				trainingService.save(training);
				result = new ModelAndView("");
			} catch (Throwable oops) {
				result = createEditModelAndView(training,
						"training.commit.error");
			}
			result.addObject("create", false);
		}

		return result;
	}

	// Other bussiness method
	protected ModelAndView createEditModelAndView(Training training) {
		assert training != null;

		ModelAndView result;

		result = createEditModelAndView(training, null);

		return result;
	}

	protected ModelAndView createEditModelAndView(Training training,
			String message) {
		assert training != null;

		ModelAndView result;

		result = new ModelAndView("training/administrator/edit");
		result.addObject("training", training);
		result.addObject("message", message);

		return result;
	}

	protected ModelAndView createListModelAndView(String requestURI,
			Training training, String uri) {
		ModelAndView result;

		result = new ModelAndView(uri);
		result.addObject("training", training);
		result.addObject("requestURI", requestURI);

		return result;
	}

}
