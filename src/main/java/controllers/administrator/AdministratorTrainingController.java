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

import services.SponsorService;
import services.TrainingDayService;
import services.TrainingService;
import controllers.AbstractController;
import domain.Sponsor;
import domain.Training;
import domain.TrainingDay;

@Controller
@RequestMapping("/training/administrator")
public class AdministratorTrainingController extends AbstractController {

	// Services ----------------------------------------------------------------

	@Autowired
	private TrainingDayService trainingDayService;
	@Autowired
	private TrainingService trainingService;
	@Autowired
	private SponsorService sponsorService;

	// Constructor
	// ---------------------------------------------------------------
	public AdministratorTrainingController() {
		super();
	}

	// Listing
	// -------------------------------------------------------------------

	@RequestMapping("/listAsignTraining")
	public ModelAndView listAsignTraining() {
		ModelAndView result;
		String uri = "training/administrator/listAsignTraining";
		String requestURI = "training/administrator/listAsignTraining.do";
		Collection<Training> trainings = trainingService
				.findAllTrainingAsigned();
		result = createListModelAndView(requestURI, trainings, uri);

		return result;
	}

	@RequestMapping("/listNotTrainingAsigned")
	public ModelAndView listNotTrainingAsigned() {
		ModelAndView result;
		String uri = "training/administrator/listNotTrainingAsigned";
		String requestURI = "training/administrator/listNotTrainingAsigned.do";
		Collection<Training> trainings = trainingService
				.findAllNotTrainingAsigned();
		result = createListModelAndView(requestURI, trainings, uri);

		return result;
	}

	@RequestMapping("/list")
	public ModelAndView list() {
		ModelAndView result;
		String uri = "training/administrator/list";
		String requestURI = "training/administrator/list.do";
		Collection<Training> trainings = trainingService.findAll();
		result = createListModelAndView(requestURI, trainings, uri);
		Boolean prin = true;
		result.addObject("prin", prin);

		return result;
	}

	// Creation
	// ------------------------------------------------------------------
	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create() {

		ModelAndView result;

		Training training = trainingService.create();

		result = createCreateModelAndView(training, null);

		return result;
	}

	// Edition
	// -------------------------------------------------------------------

	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit(@RequestParam int trainingId) {
		ModelAndView result;
		Training training = trainingService.findOne(trainingId);

		result = createEditModelAndView(training);

		return result;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid Training training, BindingResult binding) {
		ModelAndView result;

		if (binding.hasErrors()) {
			if (training.getId() == 0) {
				result = createCreateModelAndView(training,
						"training.commit.error");
			} else {
				result = createEditModelAndView(training,
						"training.commit.error");
			}
		} else {
			try {
				trainingService.save(training);
				result = new ModelAndView("redirect:list.do");
			} catch (Throwable oops) {
				if (training.getId() == 0) {
					result = createCreateModelAndView(training,
							"training.commit.error");
				} else {
					result = createEditModelAndView(training,
							"training.commit.error");
				}

			}
		}

		return result;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "delete")
	public ModelAndView delete(@ModelAttribute Training training,
			BindingResult bindingResult) {
		ModelAndView result;

		try {
			trainingService.delete(training);
			result = new ModelAndView("redirect:list.do");
		} catch (Throwable oops) {
			if (oops.getMessage() == "Error") {
				result = createEditModelAndView(training, "training.error");
			} else {
				result = createEditModelAndView(training,
						"training.commit.error");
			}
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
		Collection<TrainingDay> trainingDays = trainingDayService.findAll();
		Collection<Sponsor> sponsors = sponsorService.findAll();

		ModelAndView result;
		result = new ModelAndView("training/administrator/edit");
		result.addObject("training", training);
		result.addObject("message", message);
		result.addObject("trainingDays", trainingDays);
		result.addObject("create", false);
		result.addObject("sponsors", sponsors);

		return result;
	}

	protected ModelAndView createCreateModelAndView(Training training,
			String message) {
		assert training != null;
		Collection<TrainingDay> trainingDays = trainingDayService.findAll();
		Collection<Sponsor> sponsors = sponsorService.findAll();

		ModelAndView result;
		result = new ModelAndView("training/administrator/create");
		result.addObject("training", training);
		result.addObject("trainingDays", trainingDays);
		result.addObject("sponsors", sponsors);
		result.addObject("create", true);
		result.addObject("message", message);

		return result;
	}

	protected ModelAndView createListModelAndView(String requestURI,
			Collection<Training> trainings, String uri) {
		ModelAndView result;

		result = new ModelAndView(uri);
		result.addObject("trainings", trainings);
		result.addObject("requestURI", requestURI);

		return result;
	}

}
