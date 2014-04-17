package controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import services.CustomerService;
import services.RestService;
import domain.Customer;
import domain.Plan;

@Controller
@RequestMapping("/rest")
public class RestController extends AbstractController {

	@Autowired
	private RestService restService;

	@Autowired
	private CustomerService customerService;

	public RestController() {
		super();
	}

	@RequestMapping(value = "/plan", method = RequestMethod.GET)
	@ResponseBody
	public Plan getUserPlan(@RequestParam String customerId) {
		Plan plan = restService.findPlanByCustomerId(Integer
				.parseInt(customerId));
		return plan;
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	@ResponseBody
	public int authenticateUser(@Param(value = "username") String username,
			@Param(value = "password") String password) {
		Customer customer = customerService.findByUsername(username);
		int res = -1;
		if (customer != null) {
			String realPassword = customer.getUserAccount().getPassword();
			Md5PasswordEncoder encoder = new Md5PasswordEncoder();
			String encodedPassword = encoder.encodePassword(password, null);
			if (encodedPassword.equals(realPassword)) {
				res = customer.getId();
			}
		}
		return res;
	}

}
