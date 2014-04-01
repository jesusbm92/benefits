package converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import domain.Food;

@Component
@Transactional
public class FoodToStringConverter implements Converter<Food, String> {

	@Override
	public String convert(Food food) {
		String result;

		if (food == null)
			result = null;
		else
			result = String.valueOf(food.getId());

		return result;
	}

}
