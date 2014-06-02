package domain;

import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ManyToMany;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.codehaus.jackson.annotate.JsonIgnore;

@Entity
@Access(AccessType.PROPERTY)
public class Day extends DomainEntity {

	private Days name;
	private String descriptiveName;
	private Language entityLanguage;

	// relationship
	private Collection<Diet> diets;
	private Collection<Meal> meals;

	public Day() {
		super();
		meals = new ArrayList<Meal>();
	}

	@Enumerated(EnumType.STRING)
	public Days getName() {
		return name;
	}

	public void setName(Days name) {
		this.name = name;
	}

	@Valid
	@NotNull
	public String getDescriptiveName() {
		return descriptiveName;
	}

	public void setDescriptiveName(String descriptiveName) {
		this.descriptiveName = descriptiveName;
	}

	@Valid
	@JsonIgnore
	@ManyToMany(mappedBy = "days")
	public Collection<Diet> getDiets() {
		return diets;
	}

	public void setDiets(Collection<Diet> diets) {
		this.diets = diets;
	}

	@Valid
	@JsonIgnore
	@NotNull
	@ManyToMany
	public Collection<Meal> getMeals() {
		return meals;
	}

	public void setMeals(Collection<Meal> meals) {
		this.meals = meals;
	}

	@Enumerated(EnumType.STRING)
	public Language getEntityLanguage() {
		return entityLanguage;
	}

	public void setEntityLanguage(Language entityLanguage) {
		this.entityLanguage = entityLanguage;
	}

}
