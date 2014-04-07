package services;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.CustomerRepository;
import security.Authority;
import security.LoginService;
import security.UserAccount;
import domain.Customer;
import domain.Plan;
import forms.CustomerForm;

@Transactional
@Service
public class CustomerService extends UserService {

	// Managed repository-----------------------

	@Autowired
	private CustomerRepository customerRepository;

	// Supporting Services-----------------------
	private Md5PasswordEncoder encoder;

	// Constructors --------------------------

	public CustomerService() {
		super();
	}

	// Simple CRUD methods -----------------
	public Customer create() {
		Customer admin = new Customer();

		Authority auth = new Authority();
		Collection<Authority> lAuthorty = new ArrayList<Authority>();
		UserAccount usserA = new UserAccount();

		auth.setAuthority("CUSTOMER");
		lAuthorty.add(auth);
		usserA.setAuthorities(lAuthorty);
		admin.setUserAccount(usserA);

		return admin;
	}

	public Collection<Customer> findAll() {

		return customerRepository.findAll();
	}

	public Customer findOne(int customerId) {

		return customerRepository.findOne(customerId);
	}

	public Customer findOneEdit(int customerId) {
		Customer now = findByPrincipal();

		Customer customer = customerRepository.findOne(customerId);
		return customer;

	}

	public void save(Customer customer) {
		Assert.notNull(customer);

		encoder = new Md5PasswordEncoder();

		String oldPassword = customer.getUserAccount().getPassword();
		String newPassword = encoder.encodePassword(oldPassword, null);

		customer.getUserAccount().setPassword(newPassword);

		customerRepository.save(customer);
	}

	public Customer findByPrincipal() {

		Customer result;
		UserAccount userAccount;
		userAccount = LoginService.getPrincipal();
		Assert.notNull(userAccount);
		result = findByUserAccount(userAccount);
		Assert.notNull(result);

		return result;
	}

	public Customer findByPrincipalComment() {

		Customer result;
		UserAccount userAccount;
		userAccount = LoginService.getPrincipal();

		result = findByUserAccountComment(userAccount);

		return result;
	}

	public Customer findByUserAccount(UserAccount userAccount) {

		Assert.notNull(userAccount);
		Customer result;
		result = customerRepository.findByUserAccountId(userAccount.getId());

		return result;
	}

	public Customer findByUserAccountComment(UserAccount userAccount) {

		Customer result;
		result = customerRepository.findByUserAccountId(userAccount.getId());

		return result;
	}

	public void changeCustomerPlan(Plan plan) {
		Assert.notNull(plan);
		Customer customer = findByPrincipal();
		customer.setPlan(plan);
		customerRepository.save(customer);
	}

	// Reconstruct

	public Customer reconstruct(CustomerForm customerForm) {
		Customer customer = this.create();

		customer.setEmail(customerForm.getEmail());
		customer.setId(customerForm.getId());
		customer.setName(customerForm.getName());
		customer.setSurname(customer.getName());
		customer.setBodyfat(customerForm.getBodyfat());
		customer.setChestMeasure(customerForm.getChestMeasure());
		customer.setNationality(customerForm.getNationality());
		customer.setWeight(customerForm.getWeight());
		customer.setHeight(customerForm.getHeight());
		customer.setCity(customerForm.getCity());
		customer.setHipMeasure(customerForm.getHipMeasure());
		customer.setWaistlineMeasure(customerForm.getWaistlineMeasure());
		customer.setVersion(customerForm.getVersion());
		customer.getUserAccount().setPassword(customerForm.getPassword());
		customer.getUserAccount().setUsername(customerForm.getUsername());

		// Vemos si el nombre de usuario no está repetido
		// Assert.isTrue(this.findActorByUsername(customerForm.getUsername()).isEmpty());
		// Vemos que las passwords sean iguales
		Assert.isTrue(customer.getUserAccount().getPassword()
				.equals(customerForm.getRepeatPassword()));
		// Vemos si ha aceptado los TOS
		Assert.isTrue(customerForm.isTOSAccepted());
		return customer;
	}

	public CustomerForm constructNew() {

		Customer customer = this.create();

		CustomerForm customerForm = new CustomerForm();

		customerForm.setEmail(customer.getEmail());
		customerForm.setId(customer.getId());
		customerForm.setName(customer.getName());
		customerForm.setSurname(customer.getSurname());
		customerForm.setVersion(customer.getVersion());
		customerForm.setBodyfat(customer.getBodyfat());
		customerForm.setChestMeasure(customer.getChestMeasure());
		customerForm.setNationality(customer.getNationality());
		customerForm.setWeight(customer.getWeight());
		customerForm.setHeight(customer.getHeight());
		customerForm.setCity(customer.getCity());
		customerForm.setHipMeasure(customer.getHipMeasure());
		customerForm.setWaistlineMeasure(customer.getWaistlineMeasure());

		customerForm.setPassword(customer.getUserAccount().getPassword());
		customerForm.setUsername(customer.getUserAccount().getUsername());

		return customerForm;

	}

	// Other business methods ----------------

}
