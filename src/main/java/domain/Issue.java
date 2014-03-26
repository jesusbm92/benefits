package domain;

import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

@Entity
@Access(AccessType.PROPERTY)
public class Issue extends DomainEntity {

	private String description;

	// RelationShip
	private Customer customer;
	private Collection<Administrator> administrators;

	public Issue() {
		super();
		administrators = new ArrayList<Administrator>();

	}

	@NotBlank
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Valid
	@NotNull
	@ManyToOne(optional = false)
	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	@Valid
	@NotNull
	@ManyToMany
	public Collection<Administrator> getAdministrator() {
		return administrators;
	}

	public void setAdministrator(Collection<Administrator> administrators) {
		this.administrators = administrators;
	}

}
