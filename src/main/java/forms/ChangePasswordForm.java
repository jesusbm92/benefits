package forms;

import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;

/**
 * Esta clase contiene el formulario necesario para realizar un cambio de
 * contrase�a de un usuario ya sea administrador o cliente.
 * 
 * @author F�lix Miguel Sanju�n Segovia <felsanseg@alum.us.es>
 * 
 */
public class ChangePasswordForm {

	/**
	 * Contrase�a Actual
	 */
	private String currentPassword;

	/**
	 * Nueva contrase�a.
	 */
	private String newPassword;

	/**
	 * Confirmaci�n de la nueva contrase�a.
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
