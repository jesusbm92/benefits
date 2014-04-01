package converters;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import domain.ExerciseGroup;

@Component
@Transactional
public class StringToExerciseGroupConverter implements
		Converter<String, ExerciseGroup> {

	@Autowired
	ExerciseGroupRepository exercisegroupRepository;

	@Override
	public ExerciseGroup convert(String text) {
		ExerciseGroup result;
		int id;

		try {
			if (StringUtils.isEmpty(text))
				result = null;
			else {
				id = Integer.valueOf(text);
				result = exercisegroupRepository.findOne(id);
			}
		} catch (Throwable oops) {
			throw new IllegalArgumentException(oops);
		}

		return result;
	}

}
