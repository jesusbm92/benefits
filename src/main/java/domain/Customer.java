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

import org.hibernate.validator.constraints.Range;

@Entity
@Access(AccessType.PROPERTY)
public class Customer extends User {

	private Double weight;
	private Double height;
	private Double bodyfat;
	private Double waistlineMeasure;
	private Double hipMeasure;
	private Double chestMeasure;

	// RelationShip
	private Plan plan;
	private Collection<Issue> issues;

	public Customer() {
		super();
		issues = new ArrayList<Issue>();
	}

	@Range(min = 0)
	public Double getWeight() {
		return weight;
	}

	public void setWeight(Double weight) {
		this.weight = weight;
	}

	@Range(min = 0)
	public Double getHeight() {
		return height;
	}

	public void setHeight(Double height) {
		this.height = height;
	}

	@Range(min = 0)
	public Double getBodyfat() {
		return bodyfat;
	}

	public void setBodyfat(Double bodyfat) {
		this.bodyfat = bodyfat;
	}

	@Range(min = 0)
	public Double getWaistlineMeasure() {
		return waistlineMeasure;
	}

	public void setWaistlineMeasure(Double waistlineMeasure) {
		this.waistlineMeasure = waistlineMeasure;
	}

	@Range(min = 0)
	public Double getHipMeasure() {
		return hipMeasure;
	}

	public void setHipMeasure(Double hipMeasure) {
		this.hipMeasure = hipMeasure;
	}

	@Range(min = 0)
	public Double getChestMeasure() {
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
	@OneToMany(mappedBy = "customer")
	public Collection<Issue> getIssues() {
		return issues;
	}

	public void setIssues(Collection<Issue> issues) {
		this.issues = issues;
	}

}
