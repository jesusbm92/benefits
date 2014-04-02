package controllers.administrator;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.IssueService;
import services.PlanService;
import controllers.AbstractController;
import domain.Issue;
import domain.Plan;

@Controller
@RequestMapping("/issue/administrator")
public class IssueAdministradorController extends AbstractController {

	@Autowired
	private IssueService issueService;

	@Autowired
	private PlanService planService;

	// Constructors -----------------------------------------------------------

	public IssueAdministradorController() {
		super();
	}

	// list ---------------------------------------------------------------

	@RequestMapping("/list")
	public ModelAndView list(@RequestParam int planId) {
		ModelAndView result;

		Collection<Issue> issues = issueService.findByPlan(planId);

		String uri = "issue/administrator/list";
		String requestURI = "issue/administrator/list.do";
		Plan plan = planService.findOne(planId);
		result = createListModelAndView(requestURI, issues, uri, plan);
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