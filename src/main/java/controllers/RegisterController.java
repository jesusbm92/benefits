package controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import services.AdministratorService;
import services.CustomerService;
import domain.Administrator;
import domain.Customer;
import forms.AdministratorForm;
import forms.CustomerForm;

@Controller
@RequestMapping("/register")
public class RegisterController extends AbstractController{
	
	// Services ----------------------------------------------------------------
	
		@Autowired
		private AdministratorService administratorService;

		@Autowired
		private CustomerService customerService;
		
		// Constructor ---------------------------------------------------------------
		public RegisterController() {
			super();
		}
		
		// Listing -------------------------------------------------------------------
		
		
		// Creation ------------------------------------------------------------------

		@RequestMapping(value = "/registerAdministrator", method = RequestMethod.GET)
		public ModelAndView registerA() {
			ModelAndView result;
			
			AdministratorForm administratorForm= administratorService.constructNew();
		
			result = createEditModelAndViewAdministrator(administratorForm);
			
			return result;
		}
		
		@RequestMapping(value = "/registerCustomer", method = RequestMethod.GET)
		public ModelAndView registerCustomer() {
			ModelAndView result;
			
			CustomerForm customerForm= customerService.constructNew();

			result = createEditModelAndViewCustomer(customerForm);
			
			return result;
		}
		
		
		// Edition -------------------------------------------------------------------

		@RequestMapping(value = "/saveAdministrator", method = RequestMethod.POST, params = "save")
		public ModelAndView saveAdministrator(@Valid AdministratorForm administratorForm, BindingResult binding) {
			ModelAndView result;
			
			if (binding.hasErrors()) {
				result = createEditModelAndViewAdministrator(administratorForm);
			} else {
				try {
					Administrator administrator = this.administratorService.reconstruct(administratorForm);
					administratorService.save(administrator);				
					
					result = new ModelAndView("redirect:../welcome/index.do");
					
				} catch (Throwable oops) {
					if (oops.getMessage() == "Passwords are different") {
						result = createEditModelAndViewAdministrator(administratorForm, "register.password.error");
					}else if (oops.getMessage() == "TOS is False"){
						result = createEditModelAndViewAdministrator(administratorForm, "register.tos.error");
					}else if (oops instanceof DataIntegrityViolationException) {
						result = createEditModelAndViewAdministrator(administratorForm, "register.duplicated.customername");
					} else {
						result = createEditModelAndViewAdministrator(administratorForm, "register.commit.error");	
					}
				}
			}

			return result;
		}
		
		@RequestMapping(value = "/saveCustomer", method = RequestMethod.POST, params = "save")
		public ModelAndView saveCustomer(@Valid CustomerForm customerForm, BindingResult binding) {
			
			ModelAndView result;
			
			if (binding.hasErrors()) {
				result = createEditModelAndViewCustomer(customerForm);
			} else {
				try {
					Customer customer = this.customerService.reconstruct(customerForm);
					customerService.save(customer);				
					result = new ModelAndView("redirect:../welcome/index.do");
				} catch (Throwable oops) {
					if (oops.getMessage() == "Passwords are different") {
						result = createEditModelAndViewCustomer(customerForm, "register.password.error");
					}else if (oops.getMessage() == "TOS is False"){
						result = createEditModelAndViewCustomer(customerForm, "register.tos.error");
					}else if (oops instanceof DataIntegrityViolationException) {
						result = createEditModelAndViewCustomer(customerForm, "register.duplicated.customername");
					} else {
						result = createEditModelAndViewCustomer(customerForm, "register.commit.error");	
					}
				}
			}

			return result;
		}
			
		protected ModelAndView createEditModelAndViewAdministrator(AdministratorForm administratorForm) {
			ModelAndView result;

			result = createEditModelAndViewAdministrator(administratorForm, null);
			
			return result;
		}
		
		protected ModelAndView createEditModelAndViewAdministrator(AdministratorForm administratorForm, String message) {
			ModelAndView result;				

			result = new ModelAndView("register/registerAdministrator");
			result.addObject("message", message);
			result.addObject("administratorForm", administratorForm);
			
			return result;
		}
		protected ModelAndView createEditModelAndViewCustomer(CustomerForm customerForm) {
			ModelAndView result;

			result = createEditModelAndViewCustomer(customerForm, null);
			
			return result;
		}
		
		protected ModelAndView createEditModelAndViewCustomer(CustomerForm customerForm, String message) {
			ModelAndView result;				
			
			result = new ModelAndView("register/registerCustomer");
			result.addObject("customerForm", customerForm);
			result.addObject("message", message);
			return result;
		}
	}
