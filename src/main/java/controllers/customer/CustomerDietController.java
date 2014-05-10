package controllers.customer;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.support.ByteArrayMultipartFileEditor;
import org.springframework.web.servlet.ModelAndView;

import services.DietService;

import com.lowagie.text.Document;
import com.lowagie.text.Font;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfWriter;

import controllers.AbstractController;
import domain.Amount;
import domain.Day;
import domain.Diet;
import domain.Meal;

@Controller
@RequestMapping("/diet/customer")
public class CustomerDietController extends AbstractController {

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

	// Constructor
	// ---------------------------------------------------------------
	public CustomerDietController() {
		super();
	}

	// Listing
	// -------------------------------------------------------------------

	@RequestMapping("/details")
	public ModelAndView details(@RequestParam int dietId) {
		ModelAndView result;
		String uri = "diet/customer/details";
		String requestURI = "diet/customer/details.do";
		Diet diet = dietService.findOne(dietId);
		result = createListModelAndView(requestURI, diet, uri);

		return result;
	}

	@RequestMapping("/export")
	public ModelAndView export(@RequestParam int dietId) {

		ModelAndView result;
		String uri = "diet/customer/details";
		String requestURI = "diet/customer/details.do";
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
								+ SANGRIA + "Measure : "
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

	// Creation
	// -------------------------------------------------------------------

	// Edition
	// -------------------------------------------------------------------

	@InitBinder
	protected void initBinder(HttpServletRequest request,
			ServletRequestDataBinder binder) throws ServletException {
		binder.registerCustomEditor(byte[].class,
				new ByteArrayMultipartFileEditor());
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
