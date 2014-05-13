package controllers.administrator;

import java.util.Collection;

import javax.validation.Valid;

import org.apache.commons.io.output.ByteArrayOutputStream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import services.DayService;
import services.DietService;
import services.PlanService;
import services.SponsorService;
import utilities.PdfUtils;
import controllers.AbstractController;
import domain.Day;
import domain.Diet;
import domain.Language;
import domain.Plan;
import domain.Sponsor;

@Controller
@RequestMapping("/diet/administrator")
public class AdministratorDietController extends AbstractController {

	// Attributes
	// ----------------------------------------------------------------

	// Services ----------------------------------------------------------------

	@Autowired
	private DietService dietService;
	@Autowired
	private PlanService planService;
	@Autowired
	private SponsorService sponsorService;
	@Autowired
	private DayService dayService;

	// Constructor
	// ---------------------------------------------------------------
	public AdministratorDietController() {
		super();
	}

	// Listing
	// -------------------------------------------------------------------

	@RequestMapping("/list")
	public ModelAndView list() {
		ModelAndView result;
		String uri = "diet/administrator/list";
		String requestURI = "diet/administrator/list.do";
		String language = LocaleContextHolder.getLocale().getDisplayLanguage();

		Collection<Diet> diets = dietService.findAllLanguage(language
				.toLowerCase());
		result = createListModelAndView(requestURI, diets, uri);

		return result;
	}

	@RequestMapping("/listDietsByDay")
	public ModelAndView listDietsByDay(@RequestParam int dayId) {
		ModelAndView result;
		String uri = "diet/administrator/listDietsByDay";
		String requestURI = "diet/administrator/listDietsByDay.do";
		Collection<Diet> diets = dietService.findDietsByDay(dayId);
		result = createListModelAndView(requestURI, diets, uri);

		return result;
	}

	@RequestMapping("/export")
	public ResponseEntity<byte[]> export(@RequestParam int dietId) {
		Diet diet = dietService.findOne(dietId);
		ByteArrayOutputStream document = PdfUtils.exportDietToPdf(diet);

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.parseMediaType("application/pdf"));
		String filename = diet.getName() + ".pdf";
		headers.setContentDispositionFormData(filename, filename);
		headers.setCacheControl("must-revalidate, post-check=0, pre-check=0");
		ResponseEntity<byte[]> response = new ResponseEntity<byte[]>(
				document.toByteArray(), headers, HttpStatus.OK);
		return response;
	}

	@RequestMapping("/details")
	public ModelAndView details(@RequestParam int dietId) {
		ModelAndView result;
		String uri = "diet/administrator/details";
		String requestURI = "diet/administrador/details.do";
		Diet diet = dietService.findOne(dietId);
		result = createListModelAndView(requestURI, diet, uri);

		return result;
	}

	// Creation
	// ------------------------------------------------------------------
	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create() {

		ModelAndView result;

		Diet diet = dietService.create();

		result = createCreateModelAndView(diet);

		return result;
	}

	// Edition
	// -------------------------------------------------------------------

	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit(@RequestParam int dietId) {
		ModelAndView result;
		Diet diet = dietService.findOne(dietId);
		result = createEditModelAndView(diet);

		return result;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid Diet diet, BindingResult binding,
			RedirectAttributes redirect) {
		ModelAndView result;
		if (binding.hasErrors()) {
			if (diet.getId() == 0) {
				result = createCreateModelAndView(diet, "diet.commit.error");
			} else {
				result = createEditModelAndView(diet, "diet.commit.error");
			}
		} else {
			try {
				String language = LocaleContextHolder.getLocale()
						.getDisplayLanguage();
				Language lang = Language.valueOf(language.toLowerCase());
				diet.setEntityLanguage(lang);
				dietService.save(diet);
				redirect.addFlashAttribute("successMessage", "diet.editSuccess");
				result = new ModelAndView("redirect:list.do");
			} catch (Throwable oops) {
				if (diet.getId() == 0) {
					result = createCreateModelAndView(diet, "diet.commit.error");
				} else {
					result = createEditModelAndView(diet, "diet.commit.error");
				}
				result = createEditModelAndView(diet, "diet.commit.error");
			}

		}

		return result;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "delete")
	public ModelAndView delete(@ModelAttribute Diet diet,
			BindingResult bindingResult, RedirectAttributes redirect) {
		ModelAndView result;
		try {
			redirect.addFlashAttribute("successMessage", "diet.deleteSuccess");
			dietService.delete(diet);
			result = new ModelAndView("redirect:list.do");
		} catch (Throwable oops) {
			if (oops.getMessage() == "Error") {
				result = createEditModelAndView(diet, "diet.error");
			} else {
				result = createEditModelAndView(diet, "diet.commit.error");
			}
		}
		return result;
	}

	// Other bussiness method

	protected ModelAndView createEditModelAndView(Diet diet) {
		assert diet != null;

		ModelAndView result;

		result = createEditModelAndView(diet, null);

		return result;
	}

	protected ModelAndView createCreateModelAndView(Diet diet) {
		assert diet != null;

		ModelAndView result;

		result = createCreateModelAndView(diet, null);

		return result;
	}

	protected ModelAndView createEditModelAndView(Diet diet, String message) {
		assert diet != null;
		String language = LocaleContextHolder.getLocale().getDisplayLanguage();

		Collection<Plan> plans = planService.findAllLanguage(language
				.toLowerCase());
		Collection<Sponsor> sponsors = sponsorService.findAll();
		Collection<Day> days = dayService.findAllLanguage(language
				.toLowerCase());

		ModelAndView result;
		result = new ModelAndView("diet/administrator/edit");
		result.addObject("diet", diet);
		result.addObject("create", false);
		result.addObject("message", message);
		result.addObject("plans", plans);
		result.addObject("sponsors", sponsors);
		result.addObject("languages", Language.values());
		result.addObject("days", days);

		return result;
	}

	protected ModelAndView createCreateModelAndView(Diet diet, String message) {
		assert diet != null;
		String language = LocaleContextHolder.getLocale().getDisplayLanguage();

		Collection<Plan> plans = planService.findAllLanguage(language
				.toLowerCase());
		Collection<Sponsor> sponsors = sponsorService.findAll();
		Collection<Day> days = dayService.findAllLanguage(language
				.toLowerCase());

		ModelAndView result;
		result = new ModelAndView("diet/administrator/create");
		result.addObject("diet", diet);
		result.addObject("message", message);
		result.addObject("plans", plans);
		result.addObject("sponsors", sponsors);
		result.addObject("days", days);
		result.addObject("languages", Language.values());
		result.addObject("create", true);

		return result;
	}

	protected ModelAndView createListModelAndView(String requestURI,
			Collection<Diet> diets, String uri) {
		ModelAndView result;

		result = new ModelAndView(uri);
		result.addObject("diets", diets);
		result.addObject("requestURI", requestURI);

		return result;
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
