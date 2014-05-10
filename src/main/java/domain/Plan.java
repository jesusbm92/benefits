package domain;

import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.Valid;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.hibernate.validator.constraints.NotBlank;

@Entity
@Access(AccessType.PROPERTY)
public class Plan extends DomainEntity {

	private String name;
	private Goals goal;
	private String description;
	private Double minWeight;
	private Double maxWeight;
	private Double minBodyFat;
	private Double maxBodyFat;
	private Language language;

	// RelationShip
	private Collection<Issue> issues;
	private Collection<Customer> customers;
	private Diet diet;
	private Training training;
	private Collection<Comment> comments;

	public Plan() {
		super();
		customers = new ArrayList<Customer>();
		comments = new ArrayList<Comment>();
		issues = new ArrayList<Issue>();
	}

	@Enumerated(EnumType.STRING)
	public Goals getGoal() {
		return goal;
	}

	public void setGoal(Goals goal) {
		this.goal = goal;
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

	@Min(0)
	@Digits(integer = 3, fraction = 2)
	public Double getMinWeight() {
		return minWeight;
	}

	public void setMinWeight(Double minWeight) {
		this.minWeight = minWeight;
	}

	@Min(0)
	@Digits(integer = 3, fraction = 2)
	public Double getMaxWeight() {
		return maxWeight;
	}

	public void setMaxWeight(Double maxWeight) {
		this.maxWeight = maxWeight;
	}

	@Min(0)
	@Digits(integer = 3, fraction = 2)
	public Double getMinBodyFat() {
		return minBodyFat;
	}

	public void setMinBodyFat(Double minBodyFat) {
		this.minBodyFat = minBodyFat;
	}

	@Min(0)
	@Digits(integer = 3, fraction = 2)
	public Double getMaxBodyFat() {
		return maxBodyFat;
	}

	public void setMaxBodyFat(Double maxBodyFat) {
		this.maxBodyFat = maxBodyFat;
	}

	@Valid
	@NotNull
	@JsonIgnore
	@OneToMany(mappedBy = "plan")
	public Collection<Issue> getIssues() {
		return issues;
	}

	public void setIssues(Collection<Issue> issues) {
		this.issues = issues;
	}

	@Valid
	@NotNull
	@JsonIgnore
	@OneToMany(mappedBy = "plan")
	public Collection<Customer> getCustomers() {
		return customers;
	}

	public void setCustomers(Collection<Customer> customers) {
		this.customers = customers;
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
	@ManyToOne(optional = false)
	public Training getTraining() {
		return training;
	}

	public void setTraining(Training training) {
		this.training = training;
	}

	@Valid
	@NotNull
	@JsonIgnore
	@OneToMany(mappedBy = "plan")
	public Collection<Comment> getComments() {
		return comments;
	}

	public void setComments(Collection<Comment> comments) {
		this.comments = comments;
	}

	@Enumerated(EnumType.STRING)
	public Language getLanguage() {
		return language;
	}

	public void setLanguage(Language language) {
		this.language = language;
	}

}
