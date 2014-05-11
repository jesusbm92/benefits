package domain;

import java.util.Collection;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;
import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.hibernate.validator.constraints.NotBlank;

@Entity
@Access(AccessType.PROPERTY)
public class Exercise extends DomainEntity {

	private String name;
	private String repetitions;
	private Integer cycles;
	private String description;
	private String urlYoutube;
	private byte[] image;
	private Language languageExercise;

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

	@Enumerated(EnumType.STRING)
	public Language getLanguageExercise() {
		return languageExercise;
	}

	public void setLanguageExercise(Language languageExercise) {
		this.languageExercise = languageExercise;
	}

	@NotNull
	@Pattern(regexp = "(\\d+,)*\\d+", message = "Error. Ejemplo: 12,10,8")
	public String getRepetitions() {
		return repetitions;
	}

	public void setRepetitions(String repetitions) {
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

	@Transient
	public boolean getValidImage() {
		return !(this.getImage() == null || this.getImage().length == 0);
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

	public String getUrlYoutube() {
		return urlYoutube;
	}

	public void setUrlYoutube(String urlYoutube) {
		this.urlYoutube = urlYoutube;
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
