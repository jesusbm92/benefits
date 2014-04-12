package controllers.administrator;

import javax.validation.Valid;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import security.UserAccount;
import services.AdministratorService;
import domain.Administrator;
import forms.AdministratorForm;

@Controller
@RequestMapping("/profile/administrator")
public class AdministratorProfileController {

	@Autowired
	private AdministratorService administratorService;

	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit() {
		ModelAndView result;
		Administrator admin = administratorService.findByPrincipal();

		AdministratorForm administratorForm = administratorService
				.constructNew();
		administratorForm.setId(admin.getId());
		administratorForm.setEmail(admin.getEmail());
		administratorForm.setCity(admin.getCity());
		// Ojo al apaño weno.
		administratorForm.setPassword("prueba");
		administratorForm.setRepeatPassword("prueba");
		administratorForm.setNationality(admin.getNationality());
		administratorForm.setSurname(admin.getSurname());
		administratorForm.setName(admin.getName());

		result = createEditModelAndView(administratorForm);

		return result;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid AdministratorForm adminForm,
			BindingResult binding) {
		ModelAndView result;

		if (binding.hasErrors()) {
			result = createEditModelAndView(adminForm);
		} else {
			try {
				Administrator admin = administratorService.findByPrincipal();
				admin.setEmail(adminForm.getEmail());
				// Si no se hace esto, forma una lista.
				admin.setCity(StringUtils.EMPTY);
				admin.setCity(adminForm.getCity());
				Md5PasswordEncoder encoder = new Md5PasswordEncoder();

				// Si alguno de los tres campos de la contraseña tiene
				// contenido, empezamos a mirar.
				if (!StringUtils.isBlank(adminForm.getOriginalPassword())
						|| !StringUtils.isBlank(adminForm.getNewPassword())
						|| !StringUtils.isBlank(adminForm
								.getNewConfirmedPassword())) {
					/*
					 * Para que se produzca el cambio de contraseña:
					 * 
					 * 1: La contraseña original introducida debe de ser
					 * correcta. 2: La contraseña nueva introducida debe de
					 * tener 5 o más caracteres. 3: Contraseña de confirmación y
					 * nueva contraseña deben coincidir.
					 */

					if (admin
							.getUserAccount()
							.getPassword()
							.equals(encoder.encodePassword(
									adminForm.getOriginalPassword(), null))) {
						if (adminForm.getNewPassword().length() > 4) {
							if (adminForm.getNewPassword().equals(
									adminForm.getNewConfirmedPassword())) {
								UserAccount userAccount = admin
										.getUserAccount();
								userAccount.setPassword(adminForm
										.getNewPassword());
								admin.setUserAccount(userAccount);
								administratorService.save(admin);
								result = createEditModelAndView(adminForm,
										"profile.administrator.editionSuccess");
							} else {
								result = createEditModelAndView(adminForm,
										"profile.administrator.notEqualPasswords");
							}
						} else {
							result = createEditModelAndView(adminForm,
									"profile.administrator.newPasswordIncorrect");
						}
					} else {
						result = createEditModelAndView(adminForm,
								"profile.administrator.notCorrectPassword");
					}

				} else {
					administratorService.save(admin);
					result = createEditModelAndView(adminForm,
							"profile.administrator.editionSuccess");
				}
			} catch (Throwable oops) {
				result = createEditModelAndView(adminForm,
						"profile.administrator.register");
			}
		}
		return result;
	}

	protected ModelAndView createEditModelAndView(AdministratorForm adminForm) {
		assert adminForm != null;

		ModelAndView result;

		result = createEditModelAndView(adminForm, null);

		return result;
	}

	protected ModelAndView createEditModelAndView(AdministratorForm adminForm,
			String message) {
		assert adminForm != null;

		ModelAndView result;

		result = new ModelAndView("profile/administrator/edit");
		result.addObject("administratorForm", adminForm);
		result.addObject("message", message);

		return result;
	}

}
