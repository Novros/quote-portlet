package cz.novros.lif.quotes.portlet.generator;

import cz.novros.lif.quotes.backend.entity.Quote;

/**
 * Interface for quote generator.
 * 
 * @author Rostislav Novak
 */
public interface IQuoteGenerator {
	
	/**
	 * Generate quote.
	 * 
	 * @return Return quote.
	 */
	Quote randomQuote();
}
