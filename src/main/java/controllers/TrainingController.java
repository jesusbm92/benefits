package controllers;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.apache.commons.io.output.ByteArrayOutputStream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
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
import utilities.PdfUtils;
import domain.Training;

@Controller
@RequestMapping("/training")
public class TrainingController extends AbstractController {

	// Attributes
	// ----------------------------------------------------------------

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
	public ResponseEntity<byte[]> export(@RequestParam int trainingId) {
		Training training = trainingService.findOne(trainingId);
		ByteArrayOutputStream document = PdfUtils.exportTrainingToPdf(training);

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.parseMediaType("application/pdf"));
		String filename = training.getName() + ".pdf";
		headers.setContentDispositionFormData(filename, filename);
		headers.setCacheControl("must-revalidate, post-check=0, pre-check=0");
		ResponseEntity<byte[]> response = new ResponseEntity<byte[]>(
				document.toByteArray(), headers, HttpStatus.OK);
		return response;
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
