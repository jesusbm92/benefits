package converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import domain.Muscle;

@Component
@Transactional
public class MuscleToStringConverter implements Converter<Muscle, String> {

	@Override
	public String convert(Muscle muscle) {
		String result;

		if (muscle == null)
			result = null;
		else
			result = String.valueOf(muscle.getId());

		return result;
	}

}
