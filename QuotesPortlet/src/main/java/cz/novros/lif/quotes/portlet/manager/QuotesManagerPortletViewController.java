package cz.novros.lif.quotes.portlet.manager;

import static cz.novros.lif.quotes.portlet.manager.QuotesManagerConstants.ADD_ACTION;
import static cz.novros.lif.quotes.portlet.manager.QuotesManagerConstants.DELETE_ACTION;
import static cz.novros.lif.quotes.portlet.manager.QuotesManagerConstants.EDIT_ACTION;
import static cz.novros.lif.quotes.portlet.manager.QuotesManagerConstants.MAIN_VIEW;
import static cz.novros.lif.quotes.portlet.manager.QuotesManagerConstants.QUOTES_LIST;
import static cz.novros.lif.quotes.portlet.random.RandomQuotesConstants.FORM_MODEL;

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

import cz.novros.lif.quotes.backend.ServiceProvider;
import cz.novros.lif.quotes.backend.entity.Quote;

@Controller
@RequestMapping("VIEW")
public class QuotesManagerPortletViewController {
	protected final Logger LOG = Logger.getLogger(QuotesManagerPortletViewController.class);
	
	@Autowired
	private QuoteValidator quoteValidator;
	
	@RenderMapping
    public String renderDefault(RenderRequest request, Model model) {
        LOG.debug("Rendering default view.");
     	model.addAttribute(QUOTES_LIST, ServiceProvider.getQuoteService().readAll());
        return MAIN_VIEW;
    }
	
	@ActionMapping(EDIT_ACTION)
	public void editQuoteAction(@ModelAttribute(FORM_MODEL) Quote quote, BindingResult result, Model model) {
	}
	
	@ActionMapping(DELETE_ACTION)
	public void deleteQuoteAction(@ModelAttribute(FORM_MODEL) Quote quote, BindingResult result, Model model) {
	}
	
	@ActionMapping(ADD_ACTION)
	public void addQuoteAction(@ModelAttribute(FORM_MODEL) Quote quote, BindingResult result, Model model) {
	}
}
