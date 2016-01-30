package cz.novros.lif.quotes.portlet.manager;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import cz.novros.lif.quotes.backend.entity.Quote;

@Component
public class QuoteValidator implements Validator {

	@Override
    public boolean supports(Class<?> clazz) {
        return Quote.class.isAssignableFrom(clazz);
    }

	@Override
    public void validate(Object target, Errors errors) {
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "text", "random_quote-err-null-value");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "author", "random_quote-err-null-value");
    }
	
}
