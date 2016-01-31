package cz.novros.lif.quotes.portlet.manager;

import static cz.novros.lif.quotes.portlet.manager.QuotesManagerConstants.*;

import java.util.List;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.EventRequest;
import javax.portlet.RenderRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.portlet.bind.annotation.ActionMapping;
import org.springframework.web.portlet.bind.annotation.EventMapping;
import org.springframework.web.portlet.bind.annotation.RenderMapping;

import cz.novros.lif.quotes.backend.ServiceProvider;
import cz.novros.lif.quotes.backend.entity.Quote;
import cz.novros.lif.quotes.backend.service.QuoteService;

@Controller
@RequestMapping("VIEW")
public class QuotesManagerPortletViewController {
	protected final Logger LOG = Logger.getLogger(QuotesManagerPortletViewController.class);
	
	@Autowired
	private QuoteValidator quoteValidator;
	
	@RenderMapping
    public String renderDefault(RenderRequest request, Model model) {
        LOG.debug("Rendering default view.");

		Quote quote = new Quote();
		String userId = request.getRemoteUser();
        quote.setAuthorOfEntity(userId);
        
        List<Quote> quotes = getQuoteService().readAll();
        
		model.addAttribute(FORM_MODEL, quote);
		model.addAttribute(QUOTES_LIST, quotes);
		model.addAttribute(QUOTES_LIST_SIZE, quotes.size());
        
     	return MAIN_VIEW;
    }
	
	@ActionMapping(EDIT_ACTION)
	public void editQuoteAction() {
	}
	
	@ActionMapping(DELETE_ACTION)
	public void deleteQuoteAction(ActionRequest request, ActionResponse response, Model model) {
		int quoteId = Integer.parseInt(request.getParameter(PARAMETER_QUOTE_ID));
		boolean deleted = getQuoteService().delete(quoteId);
		if (deleted) {
			model.addAttribute("successMessage", "Quote was deleted.");
		} else {
			model.addAttribute("errorMessage", "Quote was not deleted!");
		}
	}
	
	@ActionMapping(ADD_ACTION)
	public void addQuoteAction(@ModelAttribute(FORM_MODEL) Quote quote, BindingResult result, Model model) {
		quoteValidator.validate(quote, result);
		if (!result.hasErrors()) {
			QuoteService quoteService = ServiceProvider.getQuoteService();
			quoteService.create(quote);
			model.addAttribute("successMessage", "Quote was saved!");
		}
	}
	
	@EventMapping(SAVE_ACTION)
	public void saveQuoteAction(EventRequest request, Model model) {
		Quote quote = (Quote) request.getEvent().getValue();
		getQuoteService().create(quote);
		model.addAttribute("successMessage", "Quote was saved!");
	}
	
	private QuoteService getQuoteService() {
		return ServiceProvider.getQuoteService();
	}
}
