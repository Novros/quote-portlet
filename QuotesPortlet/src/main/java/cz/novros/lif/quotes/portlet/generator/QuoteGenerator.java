package cz.novros.lif.quotes.portlet.generator;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import cz.novros.lif.quotes.backend.entity.Quote;

/**
 * Generator, which generate from his own list.
 * 
 * @author Rostislav Novak
 */
public class QuoteGenerator implements IQuoteGenerator {
	
	/** Rand for random numbers. */
	private Random rand;
	/** List of quotes from which will be quote selected. */
	private List<Quote> quotes;
	
	/** Basic initialization constructor. */
	public QuoteGenerator() {
		rand = new Random();
		quotes = new ArrayList<Quote>();
	}
	
	/**
	 * Generate quote from list.
	 * 
	 * @return Return quote.
	 */
	public final Quote randomQuote() {
		if(quotes.size() <= 0) {
			return null;
		}
		final int random = rand.nextInt(quotes.size());
		return quotes.get(random);
	}
	
	/**
	 * Add quote to list.
	 * 
	 * @param quote Quote, which will be added.
	 */
	public final void addQuote(final Quote quote) {
		quotes.add(quote);
	}

	/**
	 * Set list of quotes.
	 * 
	 * @param quotes List of quotes, which will be stored in this instance.
	 */
	public void setQuotes(final List<Quote> quotes) {
		this.quotes = quotes;	
	}
}
