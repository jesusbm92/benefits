package domain;

import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.validation.Valid;

import org.hibernate.validator.constraints.NotBlank;

@Entity
@Access(AccessType.PROPERTY)
public class Food extends DomainEntity {

	private String name;
	private String description;

	// relationShip

	private Collection<Amount> amounts;

	public Food() {
		super();
		amounts = new ArrayList<Amount>();
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

	@Valid
	@OneToMany(mappedBy = "food")
	public Collection<Amount> getAmounts() {
		return amounts;
	}

	public void setAmounts(Collection<Amount> amounts) {
		this.amounts = amounts;
	}

}
