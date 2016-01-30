package cz.novros.lif.quotes.portlet;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import cz.novros.lif.quotes.portlet.entity.Category;
import cz.novros.lif.quotes.portlet.entity.Quote;

public class QuoteGenerator implements IQuoteGenerator {

	Random rand;
	List<Category> categories;
	List<Quote> quotes;
	
	public QuoteGenerator() {
		categories = new ArrayList<Category>();
		quotes = new ArrayList<Quote>();
	}
	
	public final Quote randomQuote() {
		final int random = rand.nextInt(quotes.size());
		return quotes.get(random);
	}
	
	public final Quote randomQuote(final String category) {
		for(final Category cat : categories) {
			if(cat.getName() == category) {
				final int random = rand.nextInt(quotes.size());
				return cat.getQuote(random);
			}
		}
		return null;
	}
	
	public final void addQuote(Quote quote, String category) {
		for(final Category cat : categories) {
			if(cat.getName() == category) {
				cat.addQuote(quote);
				quotes.add(quote);
				return;
			}
		}
		Category cat = new Category(category, quote.getAuthorOfEntity());
		cat.addQuote(quote);
		quotes.add(quote);
		categories.add(cat);
	}
	
}
