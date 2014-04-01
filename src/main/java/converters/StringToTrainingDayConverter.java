package converters;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import domain.TrainingDay;

@Component
@Transactional
public class StringToTrainingDayConverter implements
		Converter<String, TrainingDay> {

	@Autowired
	TrainingDayRepository trainingdayRepository;

	@Override
	public TrainingDay convert(String text) {
		TrainingDay result;
		int id;

		try {
			if (StringUtils.isEmpty(text))
				result = null;
			else {
				id = Integer.valueOf(text);
				result = trainingdayRepository.findOne(id);
			}
		} catch (Throwable oops) {
			throw new IllegalArgumentException(oops);
		}

		return result;
	}

}
