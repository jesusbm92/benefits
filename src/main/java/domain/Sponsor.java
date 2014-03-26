package domain;

import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;

@Entity
public class Sponsor extends DomainEntity{
	
	private String name;
	private String address;
	private String email;
	private int phone;
	
	//relationSHip
	private Collection<Training> trainings;
	private Collection<Diet> diets;
	public Sponsor() {
		super();
		trainings= new ArrayList<Training>();
		diets = new ArrayList<Diet>();
		
	}
	
	@NotBlank
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	@NotBlank
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	
	@Email
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	@Min(0)
	public int getPhone() {
		return phone;
	}
	public void setPhone(int phone) {
		this.phone = phone;
	}
	
	@Valid
	@NotNull
	@OneToMany(mappedBy="sponsor")
	public Collection<Training> getTrainings() {
		return trainings;
	}
	public void setTrainings(Collection<Training> trainings) {
		this.trainings = trainings;
	}
	
	@Valid
	@NotNull
	@OneToMany(mappedBy="sponsor")
	public Collection<Diet> getDiets() {
		return diets;
	}
	public void setDiets(Collection<Diet> diets) {
		this.diets = diets;
	}
	
	
	
	
}
