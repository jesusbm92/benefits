package controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import services.RestService;
import domain.Plan;

@Controller
@RequestMapping("/rest")
public class RestController extends AbstractController {

	// @Autowired
	// private CommentService commentService;

	@Autowired
	private RestService restService;

	public RestController() {
		super();
	}

	// @RequestMapping(value = "/listall", method = RequestMethod.GET)
	// @ResponseBody
	// public Collection<Comment> list() {
	// Collection<Comment> comments = commentService.findAll();
	// return comments;
	// }

	@RequestMapping(value = "/plan", method = RequestMethod.GET)
	@ResponseBody
	public Plan getUserPlan(@RequestParam String customerId) {
		Plan plan = restService.findPlanByCustomerId(Integer
				.parseInt(customerId));
		return plan;
	}

}
