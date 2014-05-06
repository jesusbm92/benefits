package controllers.administrator;

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

import services.SponsorService;
import controllers.AbstractController;
import domain.Plan;
import domain.Sponsor;

@Controller
@RequestMapping("/sponsor/administrator")
public class AdministratorSponsorController extends AbstractController {

	// Services
	@Autowired
	private SponsorService sponsorService;

	// Constructor
	public AdministratorSponsorController() {
		super();
		// TODO Auto-generated constructor stub
	}

	// Listing
	// -------------------------------------------------------------------

	@RequestMapping("/list")
	public ModelAndView list() {
		ModelAndView result;
		String uri = "sponsor/administrator/list";
		String requestURI = "sponsor/administrator/list.do";
		Collection<Sponsor> sponsors = sponsorService.findAll();
		result = createListModelAndView(requestURI, sponsors, uri);

		return result;
	}

	// Creation
	// ------------------------------------------------------------------
	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create() {

		ModelAndView result;

		Sponsor sponsor = sponsorService.create();

		result = createEditModelAndView(sponsor);
		result.addObject("create", true);

		return result;
	}

	// Edition
	// -------------------------------------------------------------------

	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit(@RequestParam int sponsorId) {
		ModelAndView result;
		Sponsor sponsor = sponsorService.findOne(sponsorId);

		result = createEditModelAndView(sponsor);
		result.addObject("create", false);

		return result;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid Sponsor sponsor, BindingResult binding) {
		ModelAndView result;

		if (binding.hasErrors()) {
			result = createEditModelAndView(sponsor);
		} else {
			try {
				sponsorService.save(sponsor);
				result = new ModelAndView("redirect:list.do");
			} catch (Throwable oops) {
				result = createEditModelAndView(sponsor, "sponsor.commit.error");
			}
			result.addObject("create", false);
		}

		return result;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "delete")
	public ModelAndView delete(@ModelAttribute Sponsor sponsor,
			BindingResult bindingResult) {
		ModelAndView result;

		try {
			sponsorService.delete(sponsor);
			result = new ModelAndView("redirect:list.do");
		} catch (Throwable oops) {
			if (oops.getMessage() == "Error") {
				result = createEditModelAndView(sponsor, "sponsor.error");
			} else {
				result = createEditModelAndView(sponsor, "sponsor.commit.error");
			}
		}
		return result;
	}

	// Other bussines method

	protected ModelAndView createListModelAndView(String requestURI,
			Collection<Sponsor> sponsors, String uri) {
		ModelAndView result;

		result = new ModelAndView(uri);
		result.addObject("sponsors", sponsors);
		result.addObject("requestURI", requestURI);

		return result;
	}

	protected ModelAndView createEditModelAndView(Sponsor sponsor) {
		assert sponsor != null;

		ModelAndView result;

		result = createEditModelAndView(sponsor, null);

		return result;
	}

	protected ModelAndView createEditModelAndView(Sponsor sponsor,
			String message) {
		assert sponsor != null;

		ModelAndView result;
		result = new ModelAndView("sponsor/administrator/edit");
		result.addObject("sponsor", sponsor);
		result.addObject("message", message);
		return result;
	}

}
