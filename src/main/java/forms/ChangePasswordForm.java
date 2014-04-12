package forms;

import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;

/**
 * Esta clase contiene el formulario necesario para realizar un cambio de
 * contraseña de un usuario ya sea administrador o cliente.
 * 
 * @author Félix Miguel Sanjuán Segovia <felsanseg@alum.us.es>
 * 
 */
public class ChangePasswordForm {

	/**
	 * Contraseña Actual
	 */
	private String currentPassword;

	/**
	 * Nueva contraseña.
	 */
	private String newPassword;

	/**
	 * Confirmación de la nueva contraseña.
	 */
	private String newPasswordConfirmation;

	public ChangePasswordForm() {
		super();
	}

	@NotBlank
	public String getCurrentPassword() {
		return currentPassword;
	}

	public void setCurrentPassword(String currentPassword) {
		this.currentPassword = currentPassword;
	}

	@Size(min = 5, max = 32)
	public String getNewPassword() {
		return newPassword;
	}

	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}

	@Size(min = 5, max = 32)
	public String getNewPasswordConfirmation() {
		return newPasswordConfirmation;
	}

	public void setNewPasswordConfirmation(String newPasswordConfirmation) {
		this.newPasswordConfirmation = newPasswordConfirmation;
	}

}
