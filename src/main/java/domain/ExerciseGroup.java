package domain;

import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.validation.Valid;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Access(AccessType.PROPERTY)
public class ExerciseGroup extends DomainEntity {

	private String name;

	// Relationships
	private Collection<Exercise> exercises;
	private Collection<TrainingDay> trainingDays;

	public ExerciseGroup() {
		super();
		exercises = new ArrayList<Exercise>();
		trainingDays = new ArrayList<TrainingDay>();
	}

	@NotBlank
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Valid
	@NotEmpty
	@ManyToMany(mappedBy = "exerciseGroups",cascade=CascadeType.ALL)
	public Collection<Exercise> getExercises() {
		return exercises;
	}

	public void setExercises(Collection<Exercise> exercises) {
		this.exercises = exercises;
	}

	@Valid
	@ManyToMany(mappedBy = "exerciseGroups")
	public Collection<TrainingDay> getTrainingDays() {
		return trainingDays;
	}

	public void setTrainingDays(Collection<TrainingDay> trainingDays) {
		this.trainingDays = trainingDays;
	}

}
