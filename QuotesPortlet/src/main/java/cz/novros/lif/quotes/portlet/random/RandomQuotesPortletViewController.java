package cz.novros.lif.quotes.portlet.random;

import static cz.novros.lif.quotes.portlet.random.RandomQuotesConstants.*;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.RenderRequest;
import javax.xml.namespace.QName;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.portlet.bind.annotation.ActionMapping;
import org.springframework.web.portlet.bind.annotation.RenderMapping;

import cz.novros.lif.quotes.backend.entity.Quote;
import cz.novros.lif.quotes.portlet.generator.IQuoteGenerator;
import cz.novros.lif.quotes.portlet.rest.QuoteRestClient;

@Controller
@RequestMapping("VIEW")
public class RandomQuotesPortletViewController {
	protected final Logger LOG = Logger.getLogger(RandomQuotesPortletViewController.class);
	
	private IQuoteGenerator generator = new QuoteRestClient();

    @RenderMapping
    public String renderDefault(RenderRequest request, Model model) {
        LOG.debug("Rendering default view.");
        
        final Quote quote = generator.randomQuote();
        if(quote == null) {
        	model.addAttribute("GeneratorError", "GeneratorError");
        } else {
        	String userId = request.getRemoteUser();
            quote.setAuthorOfEntity(userId);
        }
        
        model.addAttribute(PARAM_QUOTE, quote);
        
        return MAIN_VIEW;
    }
    
    @ActionMapping(NEXT_ACTION)
    public void nextAction(ActionRequest actionRequest, ActionResponse response, Model model) {
    	final Quote quote = generator.randomQuote();
    	model.addAttribute(PARAM_QUOTE, quote);
    }
    
    @ActionMapping(SAVE_ACTION)
	public void saveQuoteAction(ActionRequest actionRequest, ActionResponse response) {
    	String text = actionRequest.getParameter(PARAM_QUOTE_TEXT);
    	String author = actionRequest.getParameter(PARAM_QUOTE_AUTHOR);
    	Quote quote = new Quote(text, author, actionRequest.getRemoteUser());
    	LOG.info("Saving quote with text:" + quote.getText() + " and author:" + quote.getAuthor() + ".");
    	
    	QName name = new QName("http://localhost:8080/events", "saveQuote");
    	response.setEvent(name, quote);
	}
}
