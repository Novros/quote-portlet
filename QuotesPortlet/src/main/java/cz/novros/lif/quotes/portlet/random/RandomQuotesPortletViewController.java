package cz.novros.lif.quotes.portlet.random;

import static cz.novros.lif.quotes.portlet.random.RandomQuotesConstants.FORM_MODEL;
import static cz.novros.lif.quotes.portlet.random.RandomQuotesConstants.MAIN_VIEW;
import static cz.novros.lif.quotes.portlet.random.RandomQuotesConstants.NEXT_ACTION;
import static cz.novros.lif.quotes.portlet.random.RandomQuotesConstants.PARAM_QUOTE;
import static cz.novros.lif.quotes.portlet.random.RandomQuotesConstants.SAVE_ACTION;

import javax.portlet.RenderRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.portlet.bind.annotation.ActionMapping;
import org.springframework.web.portlet.bind.annotation.RenderMapping;

import cz.novros.lif.quotes.portlet.IQuoteGenerator;
import cz.novros.lif.quotes.portlet.entity.Quote;
import cz.novros.lif.quotes.portlet.rest.QuoteRestClient;

@Controller
@RequestMapping("VIEW")
public class RandomQuotesPortletViewController {
	protected final Logger LOG = Logger.getLogger(RandomQuotesPortletViewController.class);
	
	@Autowired
	private QuoteValidator quoteValidator;
	
	private IQuoteGenerator generator = new QuoteRestClient();

    @RenderMapping
    public String renderDefault(RenderRequest request, Model model) {
        LOG.debug("Rendering default view.");
        
        final Quote quote = generator.randomQuote();
        if(quote == null) {
        	model.addAttribute("danger", "GeneratorError");
        } else {
        	String userId = request.getRemoteUser();
            quote.setAuthorOfEntity(userId);
        }
        
        model.addAttribute(PARAM_QUOTE, quote);
        
        return MAIN_VIEW;
    }

    @ActionMapping(NEXT_ACTION)
    public void nextAction(RenderRequest request, Model model) {
        LOG.debug("Activating next action.");
        
        final Quote quote = generator.randomQuote();
        if(quote == null) {
        	model.addAttribute("danger", "GeneratorError");
        } else {
        	String userId = request.getRemoteUser();
            quote.setAuthorOfEntity(userId);
        }
        
        model.addAttribute(PARAM_QUOTE, quote);
    }
    
    @ActionMapping(SAVE_ACTION)
	public void saveEmployeeAction(@ModelAttribute(FORM_MODEL) Quote quote, BindingResult result, Model model) {
    	LOG.info("Saving quote with text:" + quote.getText() + " and author:" + quote.getAuthor() + ".");
    	/* quoteValidator.validate(quote,result);
		if (!result.hasErrors()) {
			model.addAttribute("successMessage", "Quote was saved!");
		} */
	}
}
