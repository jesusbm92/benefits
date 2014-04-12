package controllers.administrator;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.MuscleService;
import services.TrainingDayService;
import controllers.AbstractController;
import domain.TrainingDay;

@Controller
@RequestMapping("/trainingDay/administrator")
public class AdministratorTrainingDayController extends AbstractController {

	// Services ----------------------------------------------------------------

	@Autowired
	private TrainingDayService trainingDayService;
	@Autowired
	private MuscleService muscleService;

	// Constructor
	// ---------------------------------------------------------------
	public AdministratorTrainingDayController() {
		super();
	}

	// Listing
	// -------------------------------------------------------------------

	@RequestMapping("/listByExerciseGroup")
	public ModelAndView listByGroup(@RequestParam int exerciseGroupId) {
		ModelAndView result;
		Boolean other = true;
		String uri = "trainingDay/administrator/listByExerciseGroup";
		String requestURI = "trainingDay/administrator/listByExerciseGroup.do";
		Collection<TrainingDay> trainingDays = trainingDayService
				.findAllByExerciseGroup(exerciseGroupId);
		result = createListModelAndView(requestURI, trainingDays, uri);
		result.addObject("other", other);

		return result;
	}

	// Creation
	// ------------------------------------------------------------------

	protected ModelAndView createListModelAndView(String requestURI,
			Collection<TrainingDay> trainingDays, String uri) {
		ModelAndView result;

		result = new ModelAndView(uri);
		result.addObject("trainingDays", trainingDays);
		result.addObject("requestURI", requestURI);

		return result;
	}

}
