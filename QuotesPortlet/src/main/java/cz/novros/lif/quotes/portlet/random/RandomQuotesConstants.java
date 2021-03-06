package cz.novros.lif.quotes.portlet.random;

import cz.novros.lif.quotes.portlet.QuotesConstants;

/**
 * Constants for random quote portlet.
 * 
 * @author Rostislav Novak
 */
public class RandomQuotesConstants extends QuotesConstants {
	
	/* Views */
	public static final String MAIN_VIEW = "random_quote/view";
	public static final String CONFIGURATION_VIEW = "/random_quote/configuration.jsp";
	
	/* Params */
	public static final String PARAM_QUOTE = "quote";
	public static final String PARAM_QUOTE_TEXT = "quote_text";
	public static final String PARAM_QUOTE_AUTHOR = "quote_author";
	public static final String PARAM_LOCAL_QUTOES = "localQuotesParam";
	
	/* Actions */
	public static final String SAVE_ACTION = "saveQuoteAction";
	public static final String NEXT_ACTION = "nextQuoteAction";
	public static final String CONFIGURATION_ACTION = "configurationAction";
}
