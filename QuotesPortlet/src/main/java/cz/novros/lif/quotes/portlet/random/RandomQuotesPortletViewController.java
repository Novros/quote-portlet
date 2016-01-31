package cz.novros.lif.quotes.portlet.random;

import static cz.novros.lif.quotes.portlet.random.RandomQuotesConstants.*;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletPreferences;
import javax.portlet.RenderRequest;
import javax.xml.namespace.QName;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.portlet.bind.annotation.ActionMapping;
import org.springframework.web.portlet.bind.annotation.RenderMapping;

import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.StringPool;

import cz.novros.lif.quotes.backend.ServiceProvider;
import cz.novros.lif.quotes.backend.entity.Quote;
import cz.novros.lif.quotes.portlet.generator.IQuoteGenerator;
import cz.novros.lif.quotes.portlet.generator.QuoteGenerator;
import cz.novros.lif.quotes.portlet.generator.QuoteRestClient;

@Controller
@RequestMapping("VIEW")
public class RandomQuotesPortletViewController {
	/** Logger for this class. */
	protected final Logger LOG = Logger.getLogger(RandomQuotesPortletViewController.class);
	
	/** Generator of quotes. */
	private IQuoteGenerator generator = new QuoteRestClient();

	/**
	 * Default rendering of this portlet.
	 * 
	 * @param request Request for getting parameters and logged user.
	 * @param model Model for jsp view.
	 * @return Path of jsp which will be showed.
	 */
    @RenderMapping
    public String renderDefault(RenderRequest request, Model model) {
        LOG.debug("Rendering default view.");
        
        PortletPreferences portletPreferences = request.getPreferences();
        boolean localQuotes = GetterUtil.getBoolean(portletPreferences.getValue("localQuotes", StringPool.TRUE));
        
        final Quote quote = generateQuote(localQuotes);
        
        // If quotes was not generated.
        if(quote == null) {
        	model.addAttribute("GeneratorError", "GeneratorError");
        // Add logged user id to quote.
        } else {
        	String userId = request.getRemoteUser();
            quote.setAuthorOfEntity(userId);
        }
        
        model.addAttribute(PARAM_QUOTE, quote);
        
        return MAIN_VIEW;
    }
    
    /**
     * Generate next quote from generator.
     *  
     * @param model Model for jsp view.
     */
    @ActionMapping(NEXT_ACTION)
    public void nextAction(Model model) {
    	LOG.debug("Genereting next quote.");
    	final Quote quote = generator.randomQuote();
    	model.addAttribute(PARAM_QUOTE, quote);
    }
    
    /**
     * Save quote to local database.
     * 
     * @param actionRequest Request to get text and author of quote.
     * @param response Response to set event for saving quote in database.
     */
    @ActionMapping(SAVE_ACTION)
	public void saveQuoteAction(ActionRequest actionRequest, ActionResponse response) {
    	String text = actionRequest.getParameter(PARAM_QUOTE_TEXT);
    	String author = actionRequest.getParameter(PARAM_QUOTE_AUTHOR);
    	Quote quote = new Quote(text, author, actionRequest.getRemoteUser());
    	
    	LOG.info("Saving quote with text:" + quote.getText() + " and author:" + quote.getAuthor() + ".");
    	
    	QName name = new QName("http://localhost:8080/events", "saveQuote");
    	response.setEvent(name, quote);
	}
    
    /**
     * Generate quote from generator.
     * 
     * @param local True generate quote from local database, False generate from rest.
     * @return Generated quote from generator.
     */
    private Quote generateQuote(boolean local) {
    	setQuoteGenerator(local);
    	return generator.randomQuote();
    }
    
    /**
     * Set generator by localQuotes value.
     */
    private void setQuoteGenerator(boolean local) {
    	if(local) {
    		LOG.info("Setting local generator.");
    		generator = new QuoteGenerator();
    		((QuoteGenerator)generator).setQuotes(ServiceProvider.getQuoteService().readAll());
    	} else {
    		LOG.info("Setting rest generator.");
    		generator = new QuoteRestClient();
    	}
    		
    }
    
}
