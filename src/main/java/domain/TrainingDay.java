package domain;

import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

@Entity
public class TrainingDay {

	private String name;

	// Relationships
	private Training training;
	private Collection<ExerciseGroup> exerciseGroups;

	public TrainingDay() {
		super();
		exerciseGroups = new ArrayList<ExerciseGroup>();
	}

	@NotBlank
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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
	@ManyToMany
	public Collection<ExerciseGroup> getExerciseGroups() {
		return exerciseGroups;
	}

	public void setExerciseGroups(Collection<ExerciseGroup> exerciseGroups) {
		this.exerciseGroups = exerciseGroups;
	}

}
