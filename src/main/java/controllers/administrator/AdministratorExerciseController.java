package controllers.administrator;

import java.util.Collection;
import java.util.Map;

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
		String language = LocaleContextHolder.getLocale().getDisplayLanguage();
		Collection<Exercise> exercises = exerciseService
				.findAllLanguage(language);
		result = createListModelAndView(requestURI, exercises, uri);

		return result;
	}

	@RequestMapping("/listByExerciseGroup")
	public ModelAndView listByGroup(@RequestParam int exerciseGroupId) {
		ModelAndView result;
		Boolean other = true;
		String uri = "exercise/administrator/listByExerciseGroup";
		String requestURI = "exercise/administrator/listByExerciseGroup.do";
		Collection<Exercise> exercises = exerciseService
				.findByExerciseGroup(exerciseGroupId);
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

		result = createCreateModelAndView(exercise, null);

		return result;
	}

	// Edition
	// -------------------------------------------------------------------

	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit(@RequestParam int exerciseId) {
		ModelAndView result;
		Exercise exercise = exerciseService.findOne(exerciseId);

		result = createEditModelAndView(exercise);

		return result;
	}

	@InitBinder
	protected void initBinder(HttpServletRequest request,
			ServletRequestDataBinder binder) throws ServletException {
		binder.registerCustomEditor(byte[].class,
				new ByteArrayMultipartFileEditor());
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid Exercise exercise, BindingResult binding) {
		ModelAndView result;

		if (binding.hasErrors()) {
			if (exercise.getId() == 0) {
				result = createCreateModelAndView(exercise,
						"exercise.commit.error");
			} else {
				result = createEditModelAndView(exercise,
						"exercise.commit.error");
			}
		} else {
			try {
				exerciseService.save(exercise);
				result = new ModelAndView("redirect:list.do");
			} catch (Throwable oops) {
				if (exercise.getId() == 0) {
					result = createCreateModelAndView(exercise,
							"exercise.commit.error");
				} else {
					result = createEditModelAndView(exercise,
							"exercise.commit.error");
				}
			}

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
		String language = LocaleContextHolder.getLocale().getDisplayLanguage();
		Map<String, Integer> map = muscleService.findAllIdName();
		ModelAndView result;
		result = new ModelAndView("exercise/administrator/edit");
		result.addObject("exercise", exercise);
		result.addObject("message", message);
		result.addObject("create", false);
		result.addObject("map", map);

		return result;
	}

	protected ModelAndView createCreateModelAndView(Exercise exercise,
			String message) {
		assert exercise != null;
		Map<String, Integer> map = muscleService.findAllIdName();
		ModelAndView result;
		result = new ModelAndView("exercise/administrator/create");
		result.addObject("exercise", exercise);
		result.addObject("map", map);
		result.addObject("message", message);
		result.addObject("create", true);

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
