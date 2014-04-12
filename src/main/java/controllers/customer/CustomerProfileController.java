package controllers.customer;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import services.CustomerService;
import domain.Customer;
import forms.ChangePasswordForm;

@Controller
@RequestMapping("/profile/customer")
public class CustomerProfileController {

	@Autowired
	private CustomerService customerService;

	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit() {
		ModelAndView result;
		Customer customer = customerService.findByPrincipal();
		ChangePasswordForm cpForm = new ChangePasswordForm();
		result = createEditModelAndView(customer, cpForm);
		return result;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView saveCustomer(@Valid Customer customer,
			BindingResult binding) {

		ModelAndView result;

		if (binding.hasErrors()) {
			result = createEditModelAndView(customer, new ChangePasswordForm());
		} else {
			try {
				customerService.saveOnlyCustomer(customer);
				result = createEditModelAndView(
						customerService.findByPrincipal(),
						new ChangePasswordForm(),
						"profile.customer.editionSuccess");
			} catch (Throwable oops) {
				result = createEditModelAndView(customer,
						new ChangePasswordForm(), "profile.customer.register");
			}
		}
		return result;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "changePassword")
	public ModelAndView changeCustomerPassword(
			@Valid ChangePasswordForm cpForm, BindingResult binding) {

		ModelAndView result;

		if (binding.hasErrors()) {
			result = createEditModelAndView(customerService.findByPrincipal(),
					cpForm);
		} else {
			try {
				Customer customer = customerService.findByPrincipal();
				// La contraseņa debe de ser correcta.
				Md5PasswordEncoder md5 = new Md5PasswordEncoder();
				String oldPassword = customer.getUserAccount().getPassword();
				// String newPassword = md5.encodePassword(oldPassword, null);

				if (md5.encodePassword(cpForm.getCurrentPassword(), null)
						.equals(oldPassword)) {
					if (cpForm.getNewPassword().equals(
							cpForm.getNewPasswordConfirmation())) {
						// Se cambia la contraseņa
						customerService.savePassword(customer,
								cpForm.getNewPassword());
						result = createEditModelAndView(customer, cpForm,
								"profile.administrator.passwordChanged");
					} else {
						result = createEditModelAndView(customer, cpForm,
								"profile.administrator.notEqualPasswords");
					}
				} else {
					result = createEditModelAndView(
							customerService.findByPrincipal(), cpForm,
							"profile.administrator.notCorrectPassword");
				}
			} catch (Throwable oops) {
				result = createEditModelAndView(
						customerService.findByPrincipal(), cpForm,
						"profile.administrator.register");
			}
		}
		return result;
	}

	protected ModelAndView createEditModelAndView(Customer customer,
			ChangePasswordForm cpForm) {
		assert cpForm != null;
		assert customer != null;

		ModelAndView result;

		result = createEditModelAndView(customer, cpForm, null);

		return result;
	}

	protected ModelAndView createEditModelAndView(Customer customer,
			ChangePasswordForm cpForm, String message) {
		assert cpForm != null;
		assert customer != null;

		ModelAndView result;

		result = new ModelAndView("profile/customer/edit");
		result.addObject("cpForm", cpForm);
		result.addObject("customer", customer);
		result.addObject("message", message);

		return result;
	}

}
