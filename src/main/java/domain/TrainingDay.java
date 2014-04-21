package domain;

import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ManyToMany;
import javax.validation.Valid;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Access(AccessType.PROPERTY)
public class TrainingDay extends DomainEntity {

	private Days name;

	// Relationships
	private Collection<Training> trainings;
	private Collection<ExerciseGroup> exerciseGroups;

	public TrainingDay() {
		super();
		exerciseGroups = new ArrayList<ExerciseGroup>();
	}

	@Enumerated(EnumType.STRING)
	public Days getName() {
		return name;
	}

	public void setName(Days name) {
		this.name = name;
	}

	@Valid
	@JsonIgnore
	@ManyToMany(mappedBy = "trainingDays")
	public Collection<Training> getTrainings() {
		return trainings;
	}

	public void setTrainings(Collection<Training> trainings) {
		this.trainings = trainings;
	}

	@Valid
	@NotEmpty
	@ManyToMany
	public Collection<ExerciseGroup> getExerciseGroups() {
		return exerciseGroups;
	}

	public void setExerciseGroups(Collection<ExerciseGroup> exerciseGroups) {
		this.exerciseGroups = exerciseGroups;
	}

}
