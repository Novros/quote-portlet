package cz.novros.lif.quotes.backend;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import cz.novros.lif.quotes.backend.service.QuoteService;

/**
 * Class which providing all services in backend.
 * 
 * @author Rostislav Novak
 */
@Component
public class ServiceProvider {
	
	/** Quote service */
	private static QuoteService quoteService;
	
	/**
	 * Set quote service. This method is autowired.
	 * 
	 * @param quoteService Quote service which will be set.
	 */
	@Autowired
    public void setQuoteService(QuoteService quoteService) {
		ServiceProvider.quoteService = quoteService;
    }
	
	/**
	 * Return quote service.
	 * 
	 * @return QuoteService
	 */
	public static QuoteService getQuoteService() {
        return quoteService;
    }

}
