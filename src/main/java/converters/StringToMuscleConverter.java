package converters;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import repositories.MuscleRepository;
import domain.Muscle;

@Component
@Transactional
public class StringToMuscleConverter implements Converter<String, Muscle> {

	@Autowired
	MuscleRepository muscleRepository;

	@Override
	public Muscle convert(String text) {
		Muscle result;
		int id;

		try {
			if (StringUtils.isEmpty(text))
				result = null;
			else {
				id = Integer.valueOf(text);
				result = muscleRepository.findOne(id);
			}
		} catch (Throwable oops) {
			throw new IllegalArgumentException(oops);
		}

		return result;
	}

}
