package cz.novros.lif.quotes.portlet.manager;

import static cz.novros.lif.quotes.portlet.manager.QuotesManagerConstants.*;

import java.util.List;

import javax.portlet.ActionRequest;
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

/**
 * Controller for manager portlet.
 * 
 * @author Rostislav Novak
 */
@Controller
@RequestMapping("VIEW")
public class QuotesManagerPortletViewController {
	/** Logger */
	protected final Logger LOG = Logger.getLogger(QuotesManagerPortletViewController.class);
	
	/** Validator for quotes. */
	@Autowired
	private QuoteValidator quoteValidator;
	
	/**
	 * Default rendering of this portlet.
	 * 
	 * @param request Request for getting logged user.
	 * @param model Model for jsp view.
	 * @return Path of jsp which will be showed.
	 */
	@RenderMapping
    public final String renderDefault(final RenderRequest request, final Model model) {
        LOG.debug("Rendering default view.");

		final Quote quote = new Quote();
		final String userId = request.getRemoteUser();
        quote.setAuthorOfEntity(userId);
        
        final List<Quote> quotes = getQuoteService().readAll();
        
		model.addAttribute(FORM_MODEL, quote);
		model.addAttribute(QUOTES_LIST, quotes);
		model.addAttribute(QUOTES_LIST_SIZE, quotes.size());
        
     	return MAIN_VIEW;
    }
	
	/* @ActionMapping(EDIT_ACTION)
	public final void editQuoteAction(final @ModelAttribute(FORM_MODEL) Quote quote, final BindingResult result, final Model model) {
		quoteValidator.validate(quote, result);
		
		if (!result.hasErrors()) {
			final QuoteService quoteService =  getQuoteService();
			quoteService.update(quote);
			model.addAttribute("successMessage", "Quote was updated!");
		}
	} */
	
	/**
	 * Delete quote from databse.
	 * 
	 * @param request Request for get quote id.
	 * @param model Model for return message.
	 */
	@ActionMapping(DELETE_ACTION)
	public final void deleteQuoteAction(final ActionRequest request, final Model model) {
		final int quoteId = Integer.parseInt(request.getParameter(PARAMETER_QUOTE_ID));
		final boolean deleted = getQuoteService().delete(quoteId);
		
		if (deleted) {
			model.addAttribute("successMessage", "Quote was deleted.");
		} else {
			model.addAttribute("errorMessage", "Quote was not deleted!");
		}
	}
	
	/**
	 * Add quote to databse.
	 * 
	 * @param quote Quote which will be saved.
	 * @param result For result of validation.
	 * @param model Model to show message.
	 */
	@ActionMapping(ADD_ACTION)
	public final void addQuoteAction(final @ModelAttribute(FORM_MODEL) Quote quote, final BindingResult result, final Model model) {
		quoteValidator.validate(quote, result);
		
		if (!result.hasErrors()) {
			final QuoteService quoteService = getQuoteService();
			quoteService.create(quote);
			model.addAttribute("successMessage", "Quote was saved!");
		}
	}
	
	/**
	 * Save quote from event.
	 * 
	 * @param request Request to get event.
	 * @param model Model to return message.
	 */
	@EventMapping(SAVE_ACTION)
	public final void saveQuoteAction(final EventRequest request, final Model model) {
		final Quote quote = (Quote) request.getEvent().getValue();
		getQuoteService().create(quote);
		model.addAttribute("successMessage", "Quote was saved!");
	}
	
	/**
	 * Return quote service from backend.
	 * 
	 * @return Return quote service.
	 */
	private final QuoteService getQuoteService() {
		return ServiceProvider.getQuoteService();
	}
}
