package cz.novros.lif.quotes.portlet.generator;

import cz.novros.lif.quotes.backend.entity.Quote;

public interface IQuoteGenerator {
	Quote randomQuote();
}