package cz.novros.lif.quotes.portlet.generator;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import cz.novros.lif.quotes.backend.entity.Quote;

public class QuoteGenerator implements IQuoteGenerator {

	Random rand;
	List<Quote> quotes;
	
	public QuoteGenerator() {
		rand = new Random();
		quotes = new ArrayList<Quote>();
	}
	
	public final Quote randomQuote() {
		if(quotes.size() <= 0) {
			return null;
		}
		final int random = rand.nextInt(quotes.size());
		return quotes.get(random);
	}
	
	public final void addQuote(Quote quote) {
		quotes.add(quote);
	}

	public void setQuotes(List<Quote> quotes) {
		this.quotes = quotes;	
	}
}
