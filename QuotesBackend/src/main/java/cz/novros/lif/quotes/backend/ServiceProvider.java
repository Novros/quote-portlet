package cz.novros.lif.quotes.backend;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import cz.novros.lif.quotes.backend.service.QuoteService;

@Component
public class ServiceProvider {
	
	private static QuoteService quoteService;
	
	@Autowired
    public void setQuoteService(QuoteService quote) {
		ServiceProvider.quoteService = quote;
    }
	
	public static QuoteService getQuoteService() {
        return quoteService;
    }

}
