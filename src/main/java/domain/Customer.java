package domain;

import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.codehaus.jackson.annotate.JsonIgnore;

@Entity
@Access(AccessType.PROPERTY)
public class Customer extends User {

	private double weight;
	private double height;
	private double bodyfat;
	private double waistlineMeasure;
	private double hipMeasure;
	private double chestMeasure;

	// RelationShip
	private Plan plan;
	private Collection<Issue> issues;

	public Customer() {
		super();
		issues = new ArrayList<Issue>();
	}

	@Min(0)
	public double getWeight() {
		return weight;
	}

	public void setWeight(double weight) {
		this.weight = weight;
	}

	@Min(0)
	public double getHeight() {
		return height;
	}

	public void setHeight(double height) {
		this.height = height;
	}

	@Min(0)
	public double getBodyfat() {
		return bodyfat;
	}

	public void setBodyfat(double bodyfat) {
		this.bodyfat = bodyfat;
	}

	@Min(0)
	public double getWaistlineMeasure() {
		return waistlineMeasure;
	}

	public void setWaistlineMeasure(double waistlineMeasure) {
		this.waistlineMeasure = waistlineMeasure;
	}

	@Min(0)
	public double getHipMeasure() {
		return hipMeasure;
	}

	public void setHipMeasure(double hipMeasure) {
		this.hipMeasure = hipMeasure;
	}

	@Min(0)
	public double getChestMeasure() {
		return chestMeasure;
	}

	public void setChestMeasure(Double chestMeasure) {
		this.chestMeasure = chestMeasure;
	}

	@Valid
	@ManyToOne(optional = true)
	public Plan getPlan() {
		return plan;
	}

	public void setPlan(Plan plan) {
		this.plan = plan;
	}

	@Valid
	@NotNull
	@JsonIgnore
	@OneToMany(mappedBy = "customer")
	public Collection<Issue> getIssues() {
		return issues;
	}

	public void setIssues(Collection<Issue> issues) {
		this.issues = issues;
	}

}
