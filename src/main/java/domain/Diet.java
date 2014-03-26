package domain;

import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

@Entity
public class Diet extends DomainEntity {

	private String name;
	private String description;

	// Relationship

	private Collection<Plan> plans;
	private Collection<Day> days;
	private Sponsor sponsor;

	public Diet() {
		super();

		plans = new ArrayList<Plan>();
		days = new ArrayList<Day>();
	}

	@NotBlank
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@NotBlank
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Valid
	@NotNull
	@OneToMany(mappedBy = "diet")
	public Collection<Plan> getPlans() {
		return plans;
	}

	public void setPlans(Collection<Plan> plans) {
		this.plans = plans;
	}

	@Valid
	@NotNull
	@OneToMany(mappedBy = "diet", cascade = CascadeType.ALL)
	public Collection<Day> getDays() {
		return days;
	}

	public void setDays(Collection<Day> days) {
		this.days = days;
	}

	@Valid
	@NotNull
	@ManyToOne(optional = true)
	public Sponsor getSponsor() {
		return sponsor;
	}

	public void setSponsor(Sponsor sponsor) {
		this.sponsor = sponsor;
	}

}
