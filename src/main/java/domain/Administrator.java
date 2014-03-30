package domain;

import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Entity
@Access(AccessType.PROPERTY)
public class Administrator extends User {

	// RelationShip
	private Collection<Plan> plans;

	public Administrator() {
		super();
		plans = new ArrayList<Plan>();
	}

	@Valid
	@NotNull
	@OneToMany(mappedBy = "administrator")
	public Collection<Plan> getPlans() {
		return plans;
	}

	public void setPlans(Collection<Plan> plans) {
		this.plans = plans;
	}

}
