package cz.novros.lif.quotes.portlet.manager;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import cz.novros.lif.quotes.backend.entity.Quote;

/**
 * Simple validator for quotes.
 * 
 * @author Rostislav Novak
 */
@Component
public class QuoteValidator implements Validator {

	/**
	 * Check if this class is supported.
	 */
	@Override
    public final boolean supports(final Class<?> clazz) {
        return Quote.class.isAssignableFrom(clazz);
    }

	/**
	 * Validate quote if text and author is not empty.
	 */
	@Override
    public final void validate(final Object target, final Errors errors) {
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "text", "random_quote-err-null-value");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "author", "random_quote-err-null-value");
    }
	
}
