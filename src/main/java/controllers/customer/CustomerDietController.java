package controllers.customer;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.output.ByteArrayOutputStream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.support.ByteArrayMultipartFileEditor;
import org.springframework.web.servlet.ModelAndView;

import services.DietService;
import utilities.PdfUtils;
import controllers.AbstractController;
import domain.Diet;

@Controller
@RequestMapping("/diet/customer")
public class CustomerDietController extends AbstractController {

	// Attributes
	// ----------------------------------------------------------------

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
