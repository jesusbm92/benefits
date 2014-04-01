package converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import domain.ExerciseGroup;

@Component
@Transactional
public class ExerciseGroupToStringConverter implements
		Converter<ExerciseGroup, String> {

	@Override
	public String convert(ExerciseGroup exercisegroup) {
		String result;

		if (exercisegroup == null)
			result = null;
		else
			result = String.valueOf(exercisegroup.getId());

		return result;
	}

}
