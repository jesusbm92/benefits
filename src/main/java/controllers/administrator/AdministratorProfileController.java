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
						new ChangePasswordForm(),
						"profile.administrator.editionSuccess");
			} catch (Throwable oops) {
				result = createEditModelAndView(admin,
						new ChangePasswordForm(),
						"profile.administrator.register");
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
				// La contraseña debe de ser correcta.
				Md5PasswordEncoder md5 = new Md5PasswordEncoder();
				String oldPassword = admin.getUserAccount().getPassword();
				// String newPassword = md5.encodePassword(oldPassword, null);

				if (md5.encodePassword(cpForm.getCurrentPassword(), null)
						.equals(oldPassword)) {
					if (cpForm.getNewPassword().equals(
							cpForm.getNewPasswordConfirmation())) {
						// Se cambia la contraseña
						administratorService.savePassword(admin,
								cpForm.getNewPassword());
						result = createEditModelAndView(admin, cpForm,
								"profile.administrator.passwordChanged");
					} else {
						result = createEditModelAndView(admin, cpForm,
								"profile.administrator.notEqualPasswords");
					}
				} else {
					result = createEditModelAndView(
							administratorService.findByPrincipal(), cpForm,
							"profile.administrator.notCorrectPassword");
				}
			} catch (Throwable oops) {
				result = createEditModelAndView(
						administratorService.findByPrincipal(), cpForm,
						"profile.administrator.register");
			}
		}
		return result;
	}

	protected ModelAndView createEditModelAndView(Administrator administrator,
			ChangePasswordForm cpForm) {
		assert cpForm != null;
		assert administrator != null;

		ModelAndView result;

		result = createEditModelAndView(administrator, cpForm, null);

		return result;
	}

	protected ModelAndView createEditModelAndView(Administrator administrator,
			ChangePasswordForm cpForm, String message) {
		assert cpForm != null;
		assert administrator != null;

		ModelAndView result;

		result = new ModelAndView("profile/administrator/edit");
		result.addObject("cpForm", cpForm);
		result.addObject("administrator", administrator);
		result.addObject("message", message);

		return result;
	}

}
