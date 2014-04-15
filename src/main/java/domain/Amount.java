package domain;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.hibernate.validator.constraints.NotBlank;

@Entity
@Access(AccessType.PROPERTY)
public class Amount extends DomainEntity {
	private int quantity;
	private String measure;

	// relationship

	private Meal meal;
	private Food food;

	public Amount() {
		super();
	}

	@Min(0)
	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	@NotBlank
	public String getMeasure() {
		return measure;
	}

	public void setMeasure(String measure) {
		this.measure = measure;
	}

	@Valid
	@NotNull
	@JsonIgnore
	@ManyToOne(optional = false)
	public Meal getMeal() {
		return meal;
	}

	public void setMeal(Meal meal) {
		this.meal = meal;
	}

	@Valid
	@NotNull
	@ManyToOne(optional = false)
	public Food getFood() {
		return food;
	}

	public void setFood(Food food) {
		this.food = food;
	}

}
