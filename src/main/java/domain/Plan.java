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
import javax.validation.constraints.NotNull;

import org.codehaus.jackson.annotate.JsonIgnore;

@Entity
@Access(AccessType.PROPERTY)
public class Plan extends DomainEntity {

	private Goals goal;

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

}
