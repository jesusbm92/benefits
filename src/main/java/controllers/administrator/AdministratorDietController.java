package controllers.administrator;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
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

import services.DayService;
import services.DietService;
import services.PlanService;
import services.SponsorService;

import com.lowagie.text.Document;
import com.lowagie.text.Font;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfWriter;

import controllers.AbstractController;
import domain.Amount;
import domain.Day;
import domain.Diet;
import domain.Meal;
import domain.Plan;
import domain.Sponsor;

@Controller
@RequestMapping("/diet/administrator")
public class AdministratorDietController extends AbstractController {

	// Attributes
	// ----------------------------------------------------------------
	private static Font catFont = new Font(Font.TIMES_ROMAN, 20, Font.BOLD);
	private static Font subFont = new Font(Font.TIMES_ROMAN, 18, Font.BOLD);
	private static Font smallBold = new Font(Font.TIMES_ROMAN, 16, Font.BOLD);
	private static Font text = new Font(Font.TIMES_ROMAN, 12);
	private final static String SANGRIA = "    ";

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
		Collection<Diet> diets = dietService.findAll();
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
	public ModelAndView export(@RequestParam int dietId) {

		ModelAndView result;
		String uri = "diet/administrator/details";
		String requestURI = "diet/administrator/details.do";
		Diet diet = dietService.findOne(dietId);

		exportToPdf(diet);

		result = createListModelAndView(requestURI, diet, uri);
		return result;
	}

	private void exportToPdf(Diet diet) {
		try {
			OutputStream file = new FileOutputStream(new File(
					"C://Documents and Settings/Student/My Documents/diet"
							+ diet.getName() + ".pdf"), true);

			Document document = new Document();
			PdfWriter.getInstance(document, file);
			document.open();

			document.add(new Paragraph(diet.getName(), catFont));
			for (Day day : diet.getDays()) {
				document.add(new Paragraph(SANGRIA + day.getName(), subFont));
				for (Meal meal : day.getMeals()) {
					document.add(new Paragraph(SANGRIA + SANGRIA
							+ meal.getName(), smallBold));
					for (Amount amount : meal.getAmounts()) {
						document.add(new Paragraph(SANGRIA + SANGRIA + SANGRIA
								+ "Food Name: " + amount.getFood().getName(),
								text));
						document.add(new Paragraph(SANGRIA + SANGRIA + SANGRIA
								+ SANGRIA + "Food Description: "
								+ amount.getFood().getDescription(), text));
						document.add(new Paragraph(SANGRIA + SANGRIA + SANGRIA
								+ SANGRIA + "Quantity : "
								+ amount.getQuantity(), text));
						document.add(new Paragraph(SANGRIA + SANGRIA + SANGRIA
								+ SANGRIA + "Repetitions : "
								+ amount.getMeasure(), text));
						document.add(new Paragraph(" "));
						document.add(new Paragraph(" "));
					}
				}
			}

			document.close();
			file.close();
		} catch (Exception e) {

			e.printStackTrace();
		}
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
	public ModelAndView save(@Valid Diet diet, BindingResult binding) {
		ModelAndView result;
		if (binding.hasErrors()) {
			if (diet.getId() == 0) {
				result = createCreateModelAndView(diet, "diet.commit.error");
			} else {
				result = createEditModelAndView(diet, "diet.commit.error");
			}
		} else {
			try {
				dietService.save(diet);
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
			BindingResult bindingResult) {
		ModelAndView result;
		try {
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
		Collection<Plan> plans = planService.findAll();
		Collection<Sponsor> sponsors = sponsorService.findAll();
		Collection<Day> days = dayService.findAll();

		ModelAndView result;
		result = new ModelAndView("diet/administrator/edit");
		result.addObject("diet", diet);
		result.addObject("create", false);
		result.addObject("message", message);
		result.addObject("plans", plans);
		result.addObject("sponsors", sponsors);
		result.addObject("days", days);

		return result;
	}

	protected ModelAndView createCreateModelAndView(Diet diet, String message) {
		assert diet != null;
		Collection<Plan> plans = planService.findAll();
		Collection<Sponsor> sponsors = sponsorService.findAll();
		Collection<Day> days = dayService.findAll();

		ModelAndView result;
		result = new ModelAndView("diet/administrator/create");
		result.addObject("diet", diet);
		result.addObject("message", message);
		result.addObject("plans", plans);
		result.addObject("sponsors", sponsors);
		result.addObject("days", days);
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
