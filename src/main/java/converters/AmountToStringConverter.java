package converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import domain.Amount;

@Component
@Transactional
public class AmountToStringConverter implements Converter<Amount, String> {

	@Override
	public String convert(Amount amount) {
		String result;

		if (amount == null)
			result = null;
		else
			result = String.valueOf(amount.getId());

		return result;
	}

}
