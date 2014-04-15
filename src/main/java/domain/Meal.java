package domain;

import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.validation.Valid;

@Entity
@Access(AccessType.PROPERTY)
public class Meal extends DomainEntity {

	private Meals name;

	// RelationShip
	private Collection<Day> days;
	private Collection<Amount> amounts;

	public Meal() {
		super();
		amounts = new ArrayList<Amount>();

	}

	@Enumerated(EnumType.STRING)
	public Meals getName() {
		return name;
	}

	public void setName(Meals name) {
		this.name = name;
	}

	@Valid
	@ManyToMany()
	public Collection<Day> getDays() {
		return days;
	}

	public void setDays(Collection<Day> days) {
		this.days = days;
	}

	@Valid
	@OneToMany(mappedBy = "meal")
	public Collection<Amount> getAmounts() {
		return amounts;
	}

	public void setAmounts(Collection<Amount> amounts) {
		this.amounts = amounts;
	}

}
