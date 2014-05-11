package controllers.administrator;

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

import services.ExerciseGroupService;
import services.TrainingDayService;
import controllers.AbstractController;
import domain.Days;
import domain.ExerciseGroup;
import domain.Language;
import domain.TrainingDay;

@Controller
@RequestMapping("/trainingDay/administrator")
public class AdministratorTrainingDayController extends AbstractController {

	// Services ----------------------------------------------------------------

	@Autowired
	private TrainingDayService trainingDayService;

	@Autowired
	private ExerciseGroupService exerciseGroupService;

	// Constructor
	// ---------------------------------------------------------------
	public AdministratorTrainingDayController() {
		super();
	}

	// Listing
	// -------------------------------------------------------------------

	@RequestMapping("/list")
	public ModelAndView list() {
		ModelAndView result;
		String uri = "trainingDay/administrator/list";
		String requestURI = "trainingDay/administrator/list.do";
		String language = LocaleContextHolder.getLocale().getDisplayLanguage();

		Collection<TrainingDay> trainingDays = trainingDayService
				.findAllLanguage(language.toLowerCase());
		result = createListModelAndView(requestURI, trainingDays, uri);

		return result;
	}

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
	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create() {

		ModelAndView result;

		TrainingDay trainingDay = trainingDayService.create();

		result = createCreateModelAndView(trainingDay, null);

		return result;
	}

	// Edition
	// -------------------------------------------------------------------

	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit(@RequestParam int trainingDayId) {
		ModelAndView result;
		TrainingDay trainingDay = trainingDayService.findOne(trainingDayId);

		result = createEditModelAndView(trainingDay);

		return result;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid TrainingDay trainingDay,
			BindingResult binding) {
		ModelAndView result;

		if (binding.hasErrors()) {
			if (trainingDay.getId() == 0) {
				result = createCreateModelAndView(trainingDay,
						"trainingDay.commit.error");
			} else {
				result = createEditModelAndView(trainingDay,
						"trainingDay.commit.error");
			}
		} else {
			try {
				trainingDayService.save(trainingDay);
				result = new ModelAndView("redirect:list.do");
			} catch (Throwable oops) {
				if (trainingDay.getId() == 0) {
					result = createCreateModelAndView(trainingDay,
							"trainingDay.commit.error");
				} else {
					result = createEditModelAndView(trainingDay,
							"trainingDay.commit.error");
				}

			}
		}

		return result;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "delete")
	public ModelAndView delete(@ModelAttribute TrainingDay trainingDay,
			BindingResult bindingResult) {
		ModelAndView result;

		try {
			trainingDayService.delete(trainingDay);
			result = new ModelAndView("redirect:list.do");
		} catch (Throwable oops) {
			if (oops.getMessage() == "Error") {
				result = createEditModelAndView(trainingDay,
						"trainingDay.error");
			} else {
				result = createEditModelAndView(trainingDay,
						"trainingDay.commit.error");
			}
		}
		return result;
	}

	// Other bussiness method
	protected ModelAndView createEditModelAndView(TrainingDay trainingDay) {
		assert trainingDay != null;

		ModelAndView result;

		result = createEditModelAndView(trainingDay, null);

		return result;
	}

	protected ModelAndView createEditModelAndView(TrainingDay trainingDay,
			String message) {
		assert trainingDay != null;
		String language = LocaleContextHolder.getLocale().getDisplayLanguage();

		Collection<ExerciseGroup> exerciseGroups = exerciseGroupService
				.findAllLanguage(language.toLowerCase());

		// Collection<Days> days = new ArrayList<Days>();
		// days.add("MONDAY");
		// days.add("TUESDAY");
		// days.add("WEDNESDAY");
		// days.add("THURSDAY");
		// days.add("FRIDAY");
		// days.add("SATURDAY");
		// days.add("SUNDAY");
		ModelAndView result;
		result = new ModelAndView("trainingDay/administrator/edit");
		result.addObject("trainingDay", trainingDay);
		result.addObject("message", message);
		result.addObject("create", false);
		result.addObject("exerciseGroups", exerciseGroups);
		result.addObject("languages", Language.values());
		result.addObject("days", Days.values());

		return result;
	}

	protected ModelAndView createCreateModelAndView(TrainingDay trainingDay,
			String message) {
		assert trainingDay != null;
		String language = LocaleContextHolder.getLocale().getDisplayLanguage();

		Collection<ExerciseGroup> exerciseGroups = exerciseGroupService
				.findAllLanguage(language.toLowerCase());

		// Collection<Days> days = new ArrayList<Days>();
		// days.add("MONDAY");
		// days.add("TUESDAY");
		// days.add("WEDNESDAY");
		// days.add("THURSDAY");
		// days.add("FRIDAY");
		// days.add("SATURDAY");
		// days.add("SUNDAY");
		ModelAndView result;
		result = new ModelAndView("trainingDay/administrator/create");
		result.addObject("create", true);
		result.addObject("trainingDay", trainingDay);
		result.addObject("exerciseGroups", exerciseGroups);
		result.addObject("days", Days.values());
		result.addObject("languages", Language.values());
		result.addObject("message", message);

		return result;
	}

	protected ModelAndView createListModelAndView(String requestURI,
			Collection<TrainingDay> trainingDays, String uri) {
		ModelAndView result;

		result = new ModelAndView(uri);
		result.addObject("trainingDays", trainingDays);
		result.addObject("requestURI", requestURI);

		return result;
	}

}
