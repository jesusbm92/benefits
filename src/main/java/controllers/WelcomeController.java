/* WelcomeController.java
 *
 * Copyright (C) 2014 Universidad de Sevilla
 * 
 * The use of this project is hereby constrained to the conditions of the 
 * TDG Licence, a copy of which you may download from 
 * http://www.tdg-seville.info/License.html
 * 
 */

package controllers;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import security.Credentials;

@Controller
@RequestMapping("/welcome")
public class WelcomeController extends AbstractController {

	// Constructors -----------------------------------------------------------

	public WelcomeController() {
		super();
	}

	// Index ------------------------------------------------------------------

	@RequestMapping(value = "/index")
	public ModelAndView index(@Valid @ModelAttribute Credentials credentials,
			BindingResult bindingResult,
			@RequestParam(required = false) boolean showError) {
		ModelAndView result;

		result = new ModelAndView("welcome/index");
		result.addObject("credentials", credentials);
		result.addObject("showError", showError);

		return result;
	}
}