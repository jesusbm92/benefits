package domain;

import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

@Entity
@Access(AccessType.PROPERTY)
public class Day extends DomainEntity {

	@Enumerated(EnumType.STRING)
	private Days name;

	// relationship
	private Diet diet;
	private Collection<Meal> meals;

	public Day() {
		super();
		meals = new ArrayList<Meal>();
	}

	@NotBlank
	public Days getName() {
		return name;
	}

	public void setName(Days name) {
		this.name = name;
	}

	@Valid
	@NotNull
	@ManyToOne(optional = false)
	public Diet getDiet() {
		return diet;
	}

	public void setDiet(Diet diet) {
		this.diet = diet;
	}

	@Valid
	@NotNull
	@OneToMany(mappedBy = "day", cascade = CascadeType.ALL)
	public Collection<Meal> getMeals() {
		return meals;
	}

	public void setMeals(Collection<Meal> meals) {
		this.meals = meals;
	}

}
