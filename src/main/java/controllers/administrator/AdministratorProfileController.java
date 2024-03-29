package controllers.administrator;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import services.AdministratorService;
import controllers.AbstractController;
import domain.Administrator;
import forms.ChangePasswordForm;

@Controller
@RequestMapping("/profile/administrator")
public class AdministratorProfileController extends AbstractController {

	@Autowired
	private AdministratorService administratorService;

	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit() {
		ModelAndView result;
		Administrator administrator = administratorService.findByPrincipal();
		ChangePasswordForm cpForm = new ChangePasswordForm();
		result = createEditModelAndView(administrator, cpForm);
		return result;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView saveAdmin(@Valid Administrator admin,
			BindingResult binding) {

		ModelAndView result;

		if (binding.hasErrors()) {
			result = createEditModelAndView(admin, new ChangePasswordForm());
		} else {
			try {
				administratorService.saveOnlyAdmin(admin);
				result = createEditModelAndView(
						administratorService.findByPrincipal(),
						new ChangePasswordForm(), null,
						"profile.administrator.editionSuccess");
			} catch (Throwable oops) {
				result = createEditModelAndView(admin,
						new ChangePasswordForm(),
						"profile.administrator.register", null);
			}
		}
		return result;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "changePassword")
	public ModelAndView changeAdminPassword(
			@ModelAttribute("cpForm") @Valid ChangePasswordForm cpForm,
			BindingResult binding) {

		ModelAndView result;

		if (binding.hasErrors()) {
			result = createEditModelAndView(
					administratorService.findByPrincipal(), cpForm);
		} else {
			try {
				Administrator admin = administratorService.findByPrincipal();
				// La contraseņa debe de ser correcta.
				Md5PasswordEncoder md5 = new Md5PasswordEncoder();
				String oldPassword = admin.getUserAccount().getPassword();
				// String newPassword = md5.encodePassword(oldPassword, null);

				if (md5.encodePassword(cpForm.getCurrentPassword(), null)
						.equals(oldPassword)) {
					if (cpForm.getNewPassword().equals(
							cpForm.getNewPasswordConfirmation())) {
						// Se cambia la contraseņa
						administratorService.savePassword(admin,
								cpForm.getNewPassword());
						result = createEditModelAndView(admin, cpForm, null,
								"profile.administrator.passwordChanged");
					} else {
						result = createEditModelAndView(admin, cpForm,
								"profile.administrator.notEqualPasswords", null);
					}
				} else {
					result = createEditModelAndView(
							administratorService.findByPrincipal(), cpForm,
							"profile.administrator.notCorrectPassword", null);
				}
			} catch (Throwable oops) {
				result = createEditModelAndView(
						administratorService.findByPrincipal(), cpForm,
						"profile.administrator.register", null);
			}
		}
		return result;
	}

	protected ModelAndView createEditModelAndView(Administrator administrator,
			ChangePasswordForm cpForm) {
		assert cpForm != null;
		assert administrator != null;

		ModelAndView result;

		result = createEditModelAndView(administrator, cpForm, null, null);

		return result;
	}

	protected ModelAndView createEditModelAndView(Administrator administrator,
			ChangePasswordForm cpForm, String errorMessage,
			String successMessage) {
		assert cpForm != null;
		assert administrator != null;

		ModelAndView result;

		result = new ModelAndView("profile/administrator/edit");
		result.addObject("cpForm", cpForm);
		result.addObject("administrator", administrator);
		result.addObject("message", errorMessage);
		result.addObject("successMessage", successMessage);

		return result;
	}

}
