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

import org.hibernate.validator.constraints.NotBlank;

@Entity
@Access(AccessType.PROPERTY)
public class Meal extends DomainEntity {

	@Enumerated(EnumType.STRING)
	private Meals name;

	// RelationShip
	private Day day;
	private Collection<Amount> amounts;

	public Meal() {
		super();
		amounts = new ArrayList<Amount>();

	}

	@NotBlank
	public Meals getName() {
		return name;
	}

	public void setName(Meals name) {
		this.name = name;
	}

	@Valid
	@NotNull
	@ManyToOne(optional = false)
	public Day getDay() {
		return day;
	}

	public void setDay(Day day) {
		this.day = day;
	}

	@Valid
	@NotNull
	@OneToMany(mappedBy = "meal")
	public Collection<Amount> getAmounts() {
		return amounts;
	}

	public void setAmounts(Collection<Amount> amounts) {
		this.amounts = amounts;
	}

}
