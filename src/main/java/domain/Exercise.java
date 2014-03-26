package domain;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

@Entity
public class Exercise {

	private String name;
	private int repetitions;
	private int cycles;

	// Relationships
	private Muscle muscle;
	private ExerciseGroup exerciseGroup;

	public Exercise() {
		super();
	}

	@NotBlank
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Min(1)
	public int getRepetitions() {
		return repetitions;
	}

	public void setRepetitions(int repetitions) {
		this.repetitions = repetitions;
	}

	@Min(1)
	public int getCycles() {
		return cycles;
	}

	public void setCycles(int cycles) {
		this.cycles = cycles;
	}

	@Valid
	@NotNull
	@ManyToOne(optional = false)
	public Muscle getMuscle() {
		return muscle;
	}

	public void setMuscle(Muscle muscle) {
		this.muscle = muscle;
	}

	@Valid
	@NotNull
	@ManyToOne(optional = false)
	public ExerciseGroup getExerciseGroup() {
		return exerciseGroup;
	}

	public void setExerciseGroup(ExerciseGroup exerciseGroup) {
		this.exerciseGroup = exerciseGroup;
	}

}
