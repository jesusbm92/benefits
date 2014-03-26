package domain;

import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

@Entity
public class ExerciseGroup {

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
	@NotNull
	@OneToMany(mappedBy = "training", cascade = CascadeType.ALL)
	public Collection<Exercise> getExercises() {
		return exercises;
	}

	public void setExercises(Collection<Exercise> exercises) {
		this.exercises = exercises;
	}

	@Valid
	@NotNull
	@ManyToMany(mappedBy = "exerciseGroup")
	public Collection<TrainingDay> getTrainingDays() {
		return trainingDays;
	}

	public void setTrainingDays(Collection<TrainingDay> trainingDays) {
		this.trainingDays = trainingDays;
	}

}
