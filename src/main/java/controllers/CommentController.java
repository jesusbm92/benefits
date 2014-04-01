package controllers;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.CommentService;
import domain.Comment;

@Controller
@RequestMapping("/comment")
public class CommentController extends AbstractController {

	@Autowired
	private CommentService commentService;

	// Constructors -----------------------------------------------------------

	public CommentController() {
		super();
	}

	// list ---------------------------------------------------------------

	@RequestMapping("/list")
	public ModelAndView listByCustomer(@RequestParam int planId) {
		ModelAndView result;

		Collection<Comment> comments = commentService.findCommentByPlan(planId);

		String requestURI = "comment/list.do";
		result = createListModelAndView(null, comments, requestURI);
		return result;
	}

	protected ModelAndView createListModelAndView(String message,
			Collection<Comment> comments, String requestURI) {
		ModelAndView result;

		result = new ModelAndView("comment/list");
		result.addObject("comments", comments);
		result.addObject("requestURI", requestURI);

		return result;
	}

}