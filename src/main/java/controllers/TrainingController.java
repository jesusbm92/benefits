package controllers;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.support.ByteArrayMultipartFileEditor;
import org.springframework.web.servlet.ModelAndView;

import services.TrainingService;

import com.lowagie.text.Document;
import com.lowagie.text.Font;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfWriter;

import domain.Exercise;
import domain.ExerciseGroup;
import domain.Training;
import domain.TrainingDay;

@Controller
@RequestMapping("/training")
public class TrainingController extends AbstractController {

	// Attributes
	// ----------------------------------------------------------------
	private static Font catFont = new Font(Font.TIMES_ROMAN, 20, Font.BOLD);
	private static Font subFont = new Font(Font.TIMES_ROMAN, 18, Font.BOLD);
	private static Font smallBold = new Font(Font.TIMES_ROMAN, 16, Font.BOLD);
	private static Font text = new Font(Font.TIMES_ROMAN, 12);
	private final static String SANGRIA = "    ";

	// Services ----------------------------------------------------------------

	@Autowired
	private TrainingService trainingService;

	// Constructor
	// ---------------------------------------------------------------
	public TrainingController() {
		super();
	}

	// Listing
	// -------------------------------------------------------------------

	@RequestMapping("/details")
	public ModelAndView details(@RequestParam int trainingId) {
		ModelAndView result;
		String uri = "training/details";
		String requestURI = "training/details.do";
		Training training = trainingService.findOne(trainingId);
		result = createListModelAndView(requestURI, training, uri);

		return result;
	}

	// Export
	// -------------------------------------------------------------------

	@RequestMapping("/export")
	public ModelAndView export(@RequestParam int trainingId) {

		ModelAndView result;
		String uri = "training/details";
		String requestURI = "training/details.do";
		Training training = trainingService.findOne(trainingId);

		exportToPdf(training);

		result = createListModelAndView(requestURI, training, uri);
		return result;
	}

	private void exportToPdf(Training training) {
		try {
			OutputStream file = new FileOutputStream(new File(
					"C://Documents and Settings/Student/My Documents/diet"
							+ training.getName() + ".pdf"), true);

			Document document = new Document();
			PdfWriter.getInstance(document, file);
			document.open();

			document.add(new Paragraph(training.getName(), catFont));
			for (TrainingDay day : training.getTrainingDays()) {
				document.add(new Paragraph(SANGRIA + day.getName(), subFont));
				for (ExerciseGroup exerciseGroup : day.getExerciseGroups()) {
					document.add(new Paragraph(SANGRIA + SANGRIA
							+ exerciseGroup.getName(), smallBold));
					for (Exercise exercise : exerciseGroup.getExercises()) {
						document.add(new Paragraph(SANGRIA + SANGRIA + SANGRIA
								+ "Exercise : " + exercise.getName(), text));
						document.add(new Paragraph(SANGRIA + SANGRIA + SANGRIA
								+ SANGRIA + "Cycles : " + exercise.getCycles(),
								text));
						document.add(new Paragraph(SANGRIA + SANGRIA + SANGRIA
								+ SANGRIA + "Repetitions : "
								+ exercise.getRepetitions(), text));
						document.add(new Paragraph(SANGRIA + SANGRIA + SANGRIA
								+ SANGRIA + "Muscle : "
								+ exercise.getMuscle().getName(), text));
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
	// ------------------------------------------------------------------
	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create(@RequestParam int paintingId) {
		ModelAndView result;

		Training training = trainingService.create();

		result = createEditModelAndView(training);
		result.addObject("create", true);

		return result;
	}

	// Edition
	// -------------------------------------------------------------------

	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit(@RequestParam int trainingId) {
		ModelAndView result;
		Training training = trainingService.findOne(trainingId);

		result = createEditModelAndView(training);
		result.addObject("create", false);

		return result;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid Training training, BindingResult binding) {
		ModelAndView result;

		if (binding.hasErrors()) {
			result = createEditModelAndView(training);
		} else {
			try {
				trainingService.save(training);
				result = new ModelAndView("");
			} catch (Throwable oops) {
				result = createEditModelAndView(training,
						"training.commit.error");
			}
			result.addObject("create", false);
		}

		return result;
	}

	// Other bussiness method
	@InitBinder
	protected void initBinder(HttpServletRequest request,
			ServletRequestDataBinder binder) throws ServletException {
		binder.registerCustomEditor(byte[].class,
				new ByteArrayMultipartFileEditor());
	}

	protected ModelAndView createEditModelAndView(Training training) {
		assert training != null;

		ModelAndView result;

		result = createEditModelAndView(training, null);

		return result;
	}

	protected ModelAndView createEditModelAndView(Training training,
			String message) {
		assert training != null;

		ModelAndView result;

		result = new ModelAndView("training/administrator/edit");
		result.addObject("training", training);
		result.addObject("message", message);

		return result;
	}

	protected ModelAndView createListModelAndView(String requestURI,
			Training training, String uri) {
		ModelAndView result;

		result = new ModelAndView(uri);
		result.addObject("training", training);
		result.addObject("requestURI", requestURI);

		return result;
	}

}
