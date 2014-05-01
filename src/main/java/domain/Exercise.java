package domain;

import java.util.Collection;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.hibernate.validator.constraints.NotBlank;

@Entity
@Access(AccessType.PROPERTY)
public class Exercise extends DomainEntity {

	private String name;
	private Integer repetitions;
	private Integer cycles;
	private String description;
	private byte[] image;

	// Relationships
	private Muscle muscle;
	private Collection<ExerciseGroup> exerciseGroups;

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

	@NotNull
	@Min(1)
	public Integer getRepetitions() {
		return repetitions;
	}

	public void setRepetitions(Integer repetitions) {
		this.repetitions = repetitions;
	}

	@NotNull
	@Min(1)
	public Integer getCycles() {
		return cycles;
	}

	public void setCycles(Integer cycles) {
		this.cycles = cycles;
	}

	@NotBlank
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Lob
	public byte[] getImage() {
		return image;
	}

	public void setImage(byte[] image) {
		this.image = image;
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
	@JsonIgnore
	@ManyToMany(mappedBy = "exercises")
	public Collection<ExerciseGroup> getExerciseGroups() {
		return exerciseGroups;
	}

	public void setExerciseGroups(Collection<ExerciseGroup> exerciseGroups) {
		this.exerciseGroups = exerciseGroups;
	}

}
