package cz.novros.lif.quotes.portlet;

import cz.novros.lif.quotes.portlet.entity.Quote;

public interface IQuoteGenerator {
	Quote randomQuote();
	Quote randomQuote(final String category);
}
