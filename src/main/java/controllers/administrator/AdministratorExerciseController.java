package controllers.administrator;

import java.util.Collection;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.ExerciseGroupService;
import services.ExerciseService;
import services.MuscleService;
import controllers.AbstractController;
import domain.Exercise;

@Controller
@RequestMapping("/exercise/administrator")
public class AdministratorExerciseController extends AbstractController {

	// Services ----------------------------------------------------------------

	@Autowired
	private ExerciseService exerciseService;
	@Autowired
	private ExerciseGroupService exerciseGroupService;
	@Autowired
	private MuscleService muscleService;

	// Constructor
	// ---------------------------------------------------------------
	public AdministratorExerciseController() {
		super();
	}

	// Listing
	// -------------------------------------------------------------------

	@RequestMapping("/list")
	public ModelAndView list() {
		ModelAndView result;
		String uri = "exercise/administrator/list";
		String requestURI = "exercise/administrator/list.do";
		Collection<Exercise> exercises = exerciseService.findAll();
		result = createListModelAndView(requestURI, exercises, uri);

		return result;
	}

	@RequestMapping("/listByExerciseGroup")
	public ModelAndView listByGroup(@RequestParam int exerciseGroup) {
		ModelAndView result;
		Boolean other = true;
		String uri = "exercise/administrator/listByExerciseGroup";
		String requestURI = "exercise/administrator/listByExerciseGroup.do";
		Collection<Exercise> exercises = exerciseService
				.findByExerciseGroup(exerciseGroup);
		result = createListModelAndView(requestURI, exercises, uri);
		result.addObject("other", other);

		return result;
	}

	// Creation
	// ------------------------------------------------------------------
	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create() {

		ModelAndView result;

		Exercise exercise = exerciseService.create();

		result = createEditModelAndView(exercise);
		result.addObject("create", true);

		return result;
	}

	// Edition
	// -------------------------------------------------------------------

	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit(@RequestParam int exerciseId) {
		ModelAndView result;
		Exercise exercise = exerciseService.findOne(exerciseId);

		result = createEditModelAndView(exercise);
		result.addObject("create", false);

		return result;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid Exercise exercise, BindingResult binding) {
		ModelAndView result;

		if (binding.hasErrors()) {
			result = createEditModelAndView(exercise);
		} else {
			try {
				exerciseService.save(exercise);
				result = new ModelAndView("redirect:list.do");
			} catch (Throwable oops) {
				result = createEditModelAndView(exercise,
						"exercise.commit.error");
			}
			result.addObject("create", false);
		}

		return result;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "delete")
	public ModelAndView delete(@ModelAttribute Exercise exercise,
			BindingResult bindingResult) {
		ModelAndView result;

		try {
			exerciseService.delete(exercise);
			result = new ModelAndView("redirect:list.do");
		} catch (Throwable oops) {
			if (oops.getMessage() == "Error") {
				result = createEditModelAndView(exercise, "exercise.error");
			} else {
				result = createEditModelAndView(exercise,
						"exercise.commit.error");
			}
		}
		return result;
	}

	// Other bussiness method
	protected ModelAndView createEditModelAndView(Exercise exercise) {
		assert exercise != null;

		ModelAndView result;

		result = createEditModelAndView(exercise, null);

		return result;
	}

	protected ModelAndView createEditModelAndView(Exercise exercise,
			String message) {
		assert exercise != null;
		Map<String, Integer> map = muscleService.findAllIdName();

		Map<String, Integer> mapExercises = exerciseGroupService.findAllMap();

		ModelAndView result;
		result = new ModelAndView("exercise/administrator/edit");
		result.addObject("exercise", exercise);
		result.addObject("message", message);
		result.addObject("mapExercises", mapExercises);
		result.addObject("map", map);

		return result;
	}

	protected ModelAndView createListModelAndView(String requestURI,
			Collection<Exercise> exercises, String uri) {
		ModelAndView result;

		result = new ModelAndView(uri);
		result.addObject("exercises", exercises);
		result.addObject("requestURI", requestURI);

		return result;
	}

}
