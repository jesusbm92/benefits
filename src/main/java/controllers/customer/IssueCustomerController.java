package controllers.customer;

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

import services.CustomerService;
import services.IssueService;
import services.PlanService;
import controllers.AbstractController;
import domain.Issue;
import domain.Plan;

@Controller
@RequestMapping("issue/customer")
public class IssueCustomerController extends AbstractController {

	@Autowired
	private IssueService issueService;

	@Autowired
	private CustomerService customerService;

	@Autowired
	private PlanService planService;

	// Constructors -----------------------------------------------------------

	public IssueCustomerController() {
		super();
	}

	// list ---------------------------------------------------------------

	@RequestMapping("/list")
	public ModelAndView list(@RequestParam int planId) {
		ModelAndView result;

		Collection<Issue> issues = issueService.findByPlan(planId);

		String uri = "issue/customer/list";
		String requestURI = "issue/customer/list.do";
		Plan plan = planService.findOne(planId);
		result = createListModelAndView(requestURI, issues, uri, plan);
		return result;
	}

	// Creation
	// ------------------------------------------------------------------
	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create(@RequestParam int planId) {
		ModelAndView result;

		Issue issue = issueService.create(planId);

		result = createEditModelAndView(issue);
		result.addObject("create", true);

		return result;
	}

	// Edition
	// -------------------------------------------------------------------

	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit(@RequestParam int issueId) {
		ModelAndView result;
		Issue issue = issueService.findOne(issueId);

		result = createEditModelAndView(issue);
		result.addObject("create", false);

		return result;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid Issue issue, BindingResult binding) {
		ModelAndView result;

		if (binding.hasErrors()) {
			result = createEditModelAndView(issue);
		} else {
			try {
				issueService.save(issue);
				result = new ModelAndView("redirect:list.do?planId="
						+ issue.getPlan().getId());
			} catch (Throwable oops) {
				result = createEditModelAndView(issue, "issue.commit.error");
			}
		}

		return result;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "delete")
	public ModelAndView delete(@ModelAttribute Issue issue,
			BindingResult bindingResult) {
		ModelAndView result;

		try {
			issueService.delete(issue);
			result = new ModelAndView("redirect:list.do?planId="
					+ issue.getPlan().getId());
		} catch (Throwable oops) {
			if (oops.getMessage() == "Error") {
				result = createEditModelAndView(issue, "issue.error");
			} else {
				result = createEditModelAndView(issue, "issue.commit.error");
			}
		}

		return result;
	}

	// Other bussiness method
	protected ModelAndView createEditModelAndView(Issue issue) {
		assert issue != null;

		ModelAndView result;

		result = createEditModelAndView(issue, null);

		return result;
	}

	protected ModelAndView createEditModelAndView(Issue issue, String message) {
		assert issue != null;

		ModelAndView result;

		result = new ModelAndView("issue/customer/edit");
		result.addObject("issue", issue);
		result.addObject("message", message);
		result.addObject("create", false);

		return result;
	}

	protected ModelAndView createListModelAndView(String requestURI,
			Collection<Issue> issues, String uri, Plan plan) {
		ModelAndView result;

		result = new ModelAndView(uri);
		result.addObject("issues", issues);
		result.addObject("plan", plan);
		result.addObject("requestURI", requestURI);

		return result;
	}

}