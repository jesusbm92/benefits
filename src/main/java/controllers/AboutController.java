package controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/about")
public class AboutController {

	public AboutController() {
		super();
	}

	@RequestMapping("/about")
	public ModelAndView list() {
		ModelAndView result;
		String uri = "about/about";
		String requestURI = "about/about.do";
		result = new ModelAndView(uri);
		result.addObject("requestUri", requestURI);
		return result;
	}

}
