package cz.novros.lif.quotes.backend.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cz.novros.lif.quotes.backend.dao.QuoteDAO;
import cz.novros.lif.quotes.backend.entity.Quote;

/**
 * Quote service, which wraps DAO.
 * 
 * @author Rostislav Novak
 */
@Service
@Transactional
public class QuoteService {
	
	/** Quote dao, which is autowired. */
	@Autowired
	private QuoteDAO quoteDao;

	/**
	 * Create quote in database.
	 * 
	 * @param quote Quote which will be created.
	 */
    public final void create(final Quote quote) {
    	quoteDao.create(quote);
    }
    
	/**
	 * Read quote from database by id.
	 * 
	 * @param id Id of quote.
	 * @return Returns quote from database. If not found, returns null.
	 */
    @Transactional(readOnly=true)
    public final Quote read(final long id) {
        return quoteDao.read(id);
    }
    
	/**
	 * Reads all quotes from database.
	 * 
	 * @return Return list with quotes.
	 */
	@Transactional(readOnly=true)
    public final List<Quote> readAll() {
        return quoteDao.readAll();
    }

	/**
	 * Update quote in database.
	 * 
	 * @param quote Quote which will be updated.
	 */
	public final void update(final Quote quote) {
		quoteDao.update(quote);
	}

	/**
	 * Delete quote from database.
	 * 
	 * @param quote Quote which will be deleted.
	 */
	public final void delete(final Quote quote) {
		quoteDao.delete(quote);
	}
	
	/**
	 * Delete quote from database by id.
	 * 
	 * @param id Id of quote, which will be deleted.
	 * @return True if quote was deleted, otherwise false.(quote not found).
	 */
	public final boolean delete(final long id) {
		return quoteDao.delete(id);
	}
}
