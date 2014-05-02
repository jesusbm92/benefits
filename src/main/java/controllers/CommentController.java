package controllers;

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

import services.CommentService;
import services.PlanService;
import services.UserService;
import domain.Comment;
import domain.Plan;

@Controller
@RequestMapping("/comment")
public class CommentController extends AbstractController {

	@Autowired
	private CommentService commentService;

	@Autowired
	private PlanService planService;

	@Autowired
	private UserService userService;

	// Constructors -----------------------------------------------------------

	public CommentController() {
		super();
	}

	// list ---------------------------------------------------------------

	@RequestMapping("/list")
	public ModelAndView list(@RequestParam int planId) {
		ModelAndView result;

		Collection<Comment> comments = commentService.findCommentByPlan(planId);

		String uri = "comment/list";
		String requestURI = "comment/list.do";
		Plan plan = planService.findOne(planId);
		result = createListModelAndView(requestURI, comments, uri, plan);
		return result;
	}

	// Creation
	// ------------------------------------------------------------------
	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create(@RequestParam int planId) {
		ModelAndView result;

		Comment comment = commentService.create(planId);

		result = createEditModelAndView(comment);
		result.addObject("create", true);

		return result;
	}

	// Edition
	// -------------------------------------------------------------------

	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit(@RequestParam int commentId) {
		ModelAndView result;
		Comment comment = commentService.findOne(commentId);

		result = createEditModelAndView(comment);
		result.addObject("create", false);

		return result;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid Comment comment, BindingResult binding) {
		ModelAndView result;

		if (binding.hasErrors()) {
			result = createEditModelAndView(comment);
		} else {
			try {
				commentService.save(comment);
				if (userService.IAmAnAdmin()) {
					result = new ModelAndView("redirect:list.do?planId="
							+ comment.getPlan().getId());
				} else {
					result = createListModelAndView("plan/customer/list.do",
							comment.getPlan(), "plan/customer/list", true,
							new Comment());
				}
			} catch (Throwable oops) {
				result = createEditModelAndView(comment, "comment.commit.error");
			}
		}

		return result;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "delete")
	public ModelAndView delete(@ModelAttribute Comment comment,
			BindingResult bindingResult) {
		ModelAndView result;

		try {
			commentService.delete(comment);
			result = new ModelAndView("redirect:list.do?planId="
					+ comment.getPlan().getId());
		} catch (Throwable oops) {
			if (oops.getMessage() == "I'm sorry but the Comment have one or more paitings.") {
				result = createEditModelAndView(comment,
						"comment.painting.error");
			} else {
				result = createEditModelAndView(comment, "comment.commit.error");
			}
		}

		return result;
	}

	// Other bussiness method
	protected ModelAndView createEditModelAndView(Comment comment) {
		assert comment != null;

		ModelAndView result;

		result = createEditModelAndView(comment, null);

		return result;
	}

	protected ModelAndView createEditModelAndView(Comment comment,
			String message) {
		assert comment != null;

		ModelAndView result;

		result = new ModelAndView("comment/edit");
		result.addObject("comment", comment);
		result.addObject("message", message);
		result.addObject("planId", comment.getPlan().getId());

		return result;
	}

	protected ModelAndView createListModelAndView(String requestURI,
			Collection<Comment> comments, String uri, Plan plan) {
		ModelAndView result;

		result = new ModelAndView(uri);
		result.addObject("comments", comments);
		result.addObject("plan", plan);
		result.addObject("requestURI", requestURI);

		return result;
	}

	protected ModelAndView createListModelAndView(String requestURI, Plan plan,
			String uri, Boolean res, Comment newComment) {
		ModelAndView result;

		result = new ModelAndView(uri);
		result.addObject("plan", plan);
		result.addObject("comments", plan.getComments());
		result.addObject("requestURI", requestURI);
		result.addObject("res", res);
		result.addObject("newComment", newComment);
		result.addObject("successMessage", "plan.comment.success");

		return result;
	}

}