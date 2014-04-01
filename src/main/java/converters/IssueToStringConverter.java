package converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import domain.Issue;

@Component
@Transactional
public class IssueToStringConverter implements Converter<Issue, String> {

	@Override
	public String convert(Issue issue) {
		String result;

		if (issue == null)
			result = null;
		else
			result = String.valueOf(issue.getId());

		return result;
	}

}
