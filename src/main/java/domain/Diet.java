package domain;

import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.hibernate.validator.constraints.NotBlank;

@Entity
@Access(AccessType.PROPERTY)
public class Diet extends DomainEntity {

	private String name;
	private String description;
	private Language entityLanguage;

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
	@JsonIgnore
	@OneToMany(mappedBy = "diet")
	public Collection<Plan> getPlans() {
		return plans;
	}

	public void setPlans(Collection<Plan> plans) {
		this.plans = plans;
	}

	@Valid
	@NotNull
	@ManyToMany
	public Collection<Day> getDays() {
		return days;
	}

	public void setDays(Collection<Day> days) {
		this.days = days;
	}

	@Valid
	@JsonIgnore
	@ManyToOne(optional = true)
	public Sponsor getSponsor() {
		return sponsor;
	}

	public void setSponsor(Sponsor sponsor) {
		this.sponsor = sponsor;
	}

	@Enumerated(EnumType.STRING)
	public Language getEntityLanguage() {
		return entityLanguage;
	}

	public void setEntityLanguage(Language entityLanguage) {
		this.entityLanguage = entityLanguage;
	}

}
