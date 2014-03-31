package services;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import repositories.UserRepository;
import security.Authority;
import security.LoginService;

@Transactional
@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;

	// Constructors --------------------------

	public UserService() {
		super();
	}

	// Other business methods ----------------
	public boolean AmIMySelf(int userId) {
		return LoginService.getPrincipal().getId() == userId;
	}

	public boolean IAmACustomer() {
		return checkRole(Authority.CUSTOMER);
	}

	public boolean IAmAnAdmin() {
		return checkRole(Authority.ADMIN);
	}

	// TODO
	public boolean AmIAGuest() {
		return false;
	}

	private boolean checkRole(String role) {
		Collection<Authority> authorities = LoginService.getPrincipal()
				.getAuthorities();

		boolean res = false;

		for (Authority auth : authorities)
			res = res || auth.getAuthority().toUpperCase().compareTo(role) == 0;

		return res;
	}

}
