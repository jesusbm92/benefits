package services;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.AdministratorRepository;
import security.Authority;
import security.LoginService;
import security.UserAccount;
import domain.Administrator;
import forms.AdministratorForm;

@Transactional
@Service
public class AdministratorService extends UserService {

	// Managed repository-----------------------

	@Autowired
	private AdministratorRepository administratorRepository;

	// Supporting Services-----------------------
	private Md5PasswordEncoder encoder;

	// Constructors --------------------------

	public AdministratorService() {
		super();
	}

	// Simple CRUD methods -----------------
	public Administrator create() {
		Administrator admin = new Administrator();

		Authority auth = new Authority();
		Collection<Authority> lAuthorty = new ArrayList<Authority>();
		UserAccount usserA = new UserAccount();

		auth.setAuthority("ADMIN");
		lAuthorty.add(auth);
		usserA.setAuthorities(lAuthorty);
		admin.setUserAccount(usserA);

		return admin;
	}

	public Collection<Administrator> findAll() {

		return administratorRepository.findAll();
	}

	public Administrator findOne(int administratorId) {

		return administratorRepository.findOne(administratorId);
	}

	public Administrator findOneEdit(int administratorId) {
		Assert.isTrue(this.IAmAnAdmin());
		Administrator now = findByPrincipal();
		Assert.isTrue(this.AmIMySelf(now.getId()));

		Administrator administrator = administratorRepository
				.findOne(administratorId);
		return administrator;

	}

	public void save(Administrator administrator) {
		Assert.notNull(administrator);
		Assert.isTrue(this.IAmAnAdmin());

		encoder = new Md5PasswordEncoder();

		String oldPassword = administrator.getUserAccount().getPassword();
		String newPassword = encoder.encodePassword(oldPassword, null);

		administrator.getUserAccount().setPassword(newPassword);

		administratorRepository.save(administrator);
	}

	public Administrator findByPrincipal() {

		Administrator result;
		UserAccount userAccount;
		userAccount = LoginService.getPrincipal();
		Assert.notNull(userAccount);
		result = findByUserAccount(userAccount);
		Assert.notNull(result);

		return result;
	}

	public Administrator findByUserAccount(UserAccount userAccount) {

		Assert.notNull(userAccount);
		Administrator result;
		result = administratorRepository.findByUserAccountId(userAccount
				.getId());

		return result;
	}

	public Administrator findByPrincipalComment() {

		Administrator result;
		UserAccount userAccount;
		userAccount = LoginService.getPrincipal();
		result = findByUserAccount(userAccount);

		return result;
	}

	public Administrator findByUserAccountComment(UserAccount userAccount) {

		Administrator result;
		result = administratorRepository.findByUserAccountId(userAccount
				.getId());

		return result;
	}

	// Reconstruct

	public Administrator reconstruct(AdministratorForm administratorForm) {
		Administrator administrator = this.create();

		administrator.setEmail(administratorForm.getEmail());
		administrator.setId(administratorForm.getId());
		administrator.setName(administratorForm.getName());
		administrator.setSurname(administratorForm.getSurname());
		administrator.setVersion(administratorForm.getVersion());
		administrator.setCity(administratorForm.getCity());
		administrator.setNationality(administratorForm.getNationality());
		administrator.getUserAccount().setPassword(
				administratorForm.getPassword());
		administrator.getUserAccount().setUsername(
				administratorForm.getUsername());

		// Vemos si el nombre de usuario no está repetido
		// Assert.isTrue(this.findActorByUsername(administratorForm.getUsername()).isEmpty());
		// Vemos que las passwords sean iguales
		Assert.isTrue(administrator.getUserAccount().getPassword()
				.equals(administratorForm.getRepeatPassword()));
		// Vemos si ha aceptado los TOS
		Assert.isTrue(administratorForm.isTOSAccepted());
		return administrator;
	}

	public AdministratorForm constructNew() {

		Administrator administrator = this.create();

		AdministratorForm administratorForm = new AdministratorForm();

		administratorForm.setEmail(administrator.getEmail());
		administratorForm.setId(administrator.getId());
		administratorForm.setName(administrator.getName());
		administratorForm.setSurname(administrator.getSurname());
		administratorForm.setVersion(administrator.getVersion());
		administratorForm.setCity(administrator.getCity());
		administratorForm.setNationality(administrator.getNationality());
		administratorForm.setPassword(administrator.getUserAccount()
				.getPassword());
		administratorForm.setUsername(administrator.getUserAccount()
				.getUsername());

		return administratorForm;

	}

	// Other business methods ----------------

	// Assertions

}
