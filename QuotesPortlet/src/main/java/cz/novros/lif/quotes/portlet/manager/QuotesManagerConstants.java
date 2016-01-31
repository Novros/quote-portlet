package cz.novros.lif.quotes.portlet.manager;

import cz.novros.lif.quotes.portlet.QuotesConstants;

/**
 * Constants for manager portlet.
 * 
 * @author Rostislav Novak
 */
public class QuotesManagerConstants extends QuotesConstants {
	
	/* Views */
	public static final String MAIN_VIEW = "quote_manager/view";
	
	/* Actions */
	public static final String ADD_ACTION = "addQuoteAction";
	public static final String SAVE_ACTION = "{http://localhost:8080/events}saveQuote";
	public static final String EDIT_ACTION = "editQuoteAction";
	public static final String DELETE_ACTION = "deleteQuoteAction";
	
	/* Models */
	public static final String FORM_MODEL = "quote";
	public static final String QUOTES_LIST = "quotes";
	public static final String QUOTES_LIST_SIZE = "quotes_size";
	
	/* Parameters */
	public static final String PARAMETER_QUOTE_ID = "quoteId";
}
