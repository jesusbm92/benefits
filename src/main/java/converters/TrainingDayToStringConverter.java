package converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import domain.TrainingDay;

@Component
@Transactional
public class TrainingDayToStringConverter implements
		Converter<TrainingDay, String> {

	@Override
	public String convert(TrainingDay trainingday) {
		String result;

		if (trainingday == null)
			result = null;
		else
			result = String.valueOf(trainingday.getId());

		return result;
	}

}
