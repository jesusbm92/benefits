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

import services.ExerciseGroupService;
import services.ExerciseService;
import controllers.AbstractController;
import domain.Exercise;
import domain.ExerciseGroup;

@Controller
@RequestMapping("/exerciseGroup/administrator")
public class AdministratorExerciseGroupController extends AbstractController {

	// Services ----------------------------------------------------------------

	@Autowired
	private ExerciseGroupService exerciseGroupService;
	@Autowired
	private ExerciseService exerciseService;

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
		String uri = "exerciseGroup/administrator/list";
		String requestURI = "exerciseGroup/administrator/list.do";
		Collection<ExerciseGroup> exerciseGroups = exerciseGroupService
				.findAll();
		result = createListModelAndView(requestURI, exerciseGroups, uri);

		return result;
	}

	@RequestMapping("/listExerciseGroup")
	public ModelAndView listExerciseGroups(@RequestParam int trainingDayId) {
		ModelAndView result;
		String uri = "exerciseGroup/administrator/listExerciseGroup";
		String requestURI = "exerciseGroup/administrator/listExerciseGroup.do";
		Collection<ExerciseGroup> exerciseGroups = exerciseGroupService
				.findByTrainingDay(trainingDayId);
		result = createListModelAndView(requestURI, exerciseGroups, uri);

		return result;
	}

	// Creation
	// ------------------------------------------------------------------
	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create() {

		ModelAndView result;

		ExerciseGroup exerciseGroup = exerciseGroupService.create();

		result = createEditModelAndView(exerciseGroup);
		result.addObject("create", true);

		return result;
	}

	// Edition
	// -------------------------------------------------------------------

	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit(@RequestParam int exerciseId) {
		ModelAndView result;
		ExerciseGroup exerciseGroup = exerciseGroupService.findOne(exerciseId);

		result = createEditModelAndView(exerciseGroup);
		result.addObject("create", false);

		return result;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid ExerciseGroup exerciseGroup,
			BindingResult binding) {
		ModelAndView result;

		if (binding.hasErrors()) {
			result = createEditModelAndView(exerciseGroup);
		} else {
			try {
				exerciseGroupService.save(exerciseGroup);
				result = new ModelAndView("redirect:list.do");
			} catch (Throwable oops) {
				if (exerciseGroup.getExercises().isEmpty()) {
					result = createEditModelAndView(exerciseGroup,
							"exerciseGroup.commit.exercises");
				} else {
					result = createEditModelAndView(exerciseGroup,
							"exerciseGroup.commit.error");
				}
			}
			result.addObject("create", false);
		}

		return result;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "delete")
	public ModelAndView delete(@ModelAttribute ExerciseGroup exerciseGroup,
			BindingResult bindingResult) {
		ModelAndView result;

		try {
			exerciseGroupService.delete(exerciseGroup);
			result = new ModelAndView("redirect:list.do");
		} catch (Throwable oops) {
			if (oops.getMessage() == "Error") {
				result = createEditModelAndView(exerciseGroup, "exercise.error");
			} else {
				result = createEditModelAndView(exerciseGroup,
						"exercise.commit.error");
			}
		}
		return result;
	}

	// Other bussiness method
	protected ModelAndView createEditModelAndView(ExerciseGroup exerciseGroup) {
		assert exerciseGroup != null;

		ModelAndView result;

		result = createEditModelAndView(exerciseGroup, null);

		return result;
	}

	protected ModelAndView createEditModelAndView(ExerciseGroup exerciseGroup,
			String message) {
		assert exerciseGroup != null;
		Collection<Exercise> exercises = exerciseService.findAll();
		Collection<Exercise> exerciseSelected = new ArrayList<Exercise>();
		if (exerciseGroup.getId() != 0) {
			exerciseSelected = exerciseService
					.findByExerciseGroup(exerciseGroup.getId());
		}

		ModelAndView result;
		result = new ModelAndView("exerciseGroup/administrator/edit");
		result.addObject("exerciseGroup", exerciseGroup);
		result.addObject("message", message);
		result.addObject("exercises", exercises);
		result.addObject("exerciseSelected", exerciseSelected);

		return result;
	}

	protected ModelAndView createListModelAndView(String requestURI,
			Collection<ExerciseGroup> exerciseGroups, String uri) {
		ModelAndView result;

		result = new ModelAndView(uri);
		result.addObject("exerciseGroups", exerciseGroups);
		result.addObject("requestURI", requestURI);

		return result;
	}

}
