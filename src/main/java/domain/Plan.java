package domain;

import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

@Entity
@Access(AccessType.PROPERTY)
public class Plan extends DomainEntity {

	private String goal;

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

	@NotBlank
	public String getGoal() {
		return goal;
	}

	public void setGoal(String goal) {
		this.goal = goal;
	}

	@Valid
	@NotNull
	@OneToMany(mappedBy = "plan")
	public Collection<Issue> getIssues() {
		return issues;
	}

	public void setIssues(Collection<Issue> issues) {
		this.issues = issues;
	}

	@Valid
	@NotNull
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
	@OneToMany(mappedBy = "plan")
	public Collection<Comment> getComments() {
		return comments;
	}

	public void setComments(Collection<Comment> comments) {
		this.comments = comments;
	}

}
