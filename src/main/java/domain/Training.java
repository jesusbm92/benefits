package domain;

import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.hibernate.validator.constraints.NotBlank;

@Entity
@Access(AccessType.PROPERTY)
public class Training extends DomainEntity {

	private String name;
	private int duration;

	// Relationship
	private Collection<Plan> plans;
	private Collection<TrainingDay> trainingDays;
	private Sponsor sponsor;

	public Training() {
		super();

		plans = new ArrayList<Plan>();
		trainingDays = new ArrayList<TrainingDay>();
	}

	@NotBlank
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Min(0)
	public int getDuration() {
		return duration;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}

	@Valid
	@NotNull
	@JsonIgnore
	@OneToMany(mappedBy = "training")
	public Collection<Plan> getPlans() {
		return plans;
	}

	public void setPlans(Collection<Plan> plans) {
		this.plans = plans;
	}

	@Valid
	@NotNull
	@OneToMany(mappedBy = "training", cascade = CascadeType.ALL)
	public Collection<TrainingDay> getTrainingDays() {
		return trainingDays;
	}

	public void setTrainingDays(Collection<TrainingDay> trainingDays) {
		this.trainingDays = trainingDays;
	}

	@Valid
	@NotNull
	@JsonIgnore
	@ManyToOne(optional = true)
	public Sponsor getSponsor() {
		return sponsor;
	}

	public void setSponsor(Sponsor sponsor) {
		this.sponsor = sponsor;
	}
}
