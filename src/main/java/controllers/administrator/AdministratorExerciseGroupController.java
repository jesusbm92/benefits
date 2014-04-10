package controllers.administrator;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import services.ExerciseGroupService;
import services.MuscleService;
import controllers.AbstractController;
import domain.ExerciseGroup;

@Controller
@RequestMapping("/exerciseGroup/administator")
public class AdministratorExerciseGroupController extends AbstractController {

	// Services ----------------------------------------------------------------

	@Autowired
	private ExerciseGroupService exerciseGroupService;
	@Autowired
	private MuscleService muscleService;

	// Constructor
	// ---------------------------------------------------------------
	public AdministratorExerciseGroupController() {
		super();
	}

	// Listing
	// -------------------------------------------------------------------

	@RequestMapping("/list")
	public ModelAndView list() {
		ModelAndView result;
		String uri = "exerciseGroup/administator/list";
		String requestURI = "exerciseGroup/administator/list.do";
		Collection<ExerciseGroup> exerciseGroups = exerciseGroupService
				.findAll();
		result = createListModelAndView(requestURI, exerciseGroups, uri);

		return result;
	}

	// Creation
	// ------------------------------------------------------------------

	protected ModelAndView createListModelAndView(String requestURI,
			Collection<ExerciseGroup> exerciseGroups, String uri) {
		ModelAndView result;

		result = new ModelAndView(uri);
		result.addObject("exerciseGroups", exerciseGroups);
		result.addObject("requestURI", requestURI);

		return result;
	}

}
