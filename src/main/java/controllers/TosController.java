package controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/tos")
public class TosController {

	public TosController() {
		super();
	}

	@RequestMapping("/tos")
	public ModelAndView list() {
		ModelAndView result;
		String uri = "tos/tos";
		String requestURI = "tos/tos.do";
		result = new ModelAndView(uri);
		result.addObject("requestUri", requestURI);
		return result;
	}
}
