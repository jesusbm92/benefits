package forms;

import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.Range;

public class CustomerForm {

	private String name;
	private String surname;
	private String email;
	private String nationality;
	private String city;
	private String password;
	private String username;

	private Double weight;
	private Double height;
	private Double bodyfat;
	private Double waistlineMeasure;
	private Double hipMeasure;
	private Double chestMeasure;

	private int id;
	private int version;

	private boolean TOSAccepted;
	private String repeatPassword;

	public CustomerForm() {
		super();
	}

	public boolean isTOSAccepted() {
		return TOSAccepted;
	}

	public void setTOSAccepted(boolean tOSAccepted) {
		TOSAccepted = tOSAccepted;
	}

	@Size(min = 5, max = 32)
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	@Size(min = 5, max = 32)
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@NotBlank
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@NotBlank
	public String getNationality() {
		return nationality;
	}

	public void setNationality(String nationality) {
		this.nationality = nationality;
	}

	@NotBlank
	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	@NotBlank
	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	@NotBlank
	@Email
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}

	@NotBlank
	public String getRepeatPassword() {
		return repeatPassword;
	}

	public void setRepeatPassword(String repeatPassword) {
		this.repeatPassword = repeatPassword;
	}

	@Range(min = 0)
	public Double getWeight() {
		return weight;
	}

	public void setWeight(Double weight) {
		this.weight = weight;
	}

	@Range(min = 0)
	public Double getHeight() {
		return height;
	}

	public void setHeight(Double height) {
		this.height = height;
	}

	@Range(min = 0)
	public Double getBodyfat() {
		return bodyfat;
	}

	public void setBodyfat(Double bodyfat) {
		this.bodyfat = bodyfat;
	}

	@Range(min = 0)
	public Double getWaistlineMeasure() {
		return waistlineMeasure;
	}

	public void setWaistlineMeasure(Double waistlineMeasure) {
		this.waistlineMeasure = waistlineMeasure;
	}

	@Range(min = 0)
	public Double getHipMeasure() {
		return hipMeasure;
	}

	public void setHipMeasure(Double hipMeasure) {
		this.hipMeasure = hipMeasure;
	}

	@Range(min = 0)
	public Double getChestMeasure() {
		return chestMeasure;
	}

	public void setChestMeasure(Double chestMeasure) {
		this.chestMeasure = chestMeasure;
	}

}
