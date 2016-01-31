package cz.novros.lif.quotes.backend.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import cz.novros.lif.quotes.backend.entity.Quote;

/**
 * DAO for quotes.
 * 
 * @author Rostislav Novak
 */
@Repository
public class QuoteDAO {
	
	/** Entity manager for database connection. */
	@PersistenceContext
	private EntityManager entityManager;

	/**
	 * Create quote in database.
	 * 
	 * @param quote Quote which will be created.
	 */
	public final void create(final Quote quote) {
		entityManager.persist(quote);
		entityManager.flush();
	}
	
	/**
	 * Read quote from database by id.
	 * 
	 * @param id Id of quote.
	 * @return Returns quote from database. If not found, returns null.
	 */
	public final Quote read(final long id) {
		final Quote result = entityManager.find(Quote.class, id);
		return result;
	}
	
	/**
	 * Reads all quotes from database.
	 * 
	 * @return Return list with quotes.
	 */
	public final List<Quote> readAll() {
		final String readAllQuery = "SELECT e FROM " + Quote.class.getSimpleName() + " e";
		final TypedQuery<Quote> query = entityManager.createQuery(readAllQuery, Quote.class);
		return query.getResultList();
	}
	
	/**
	 * Update quote in database.
	 * 
	 * @param quote Quote which will be updated.
	 */
	public final void update(final Quote quote) {
		entityManager.merge(quote);
		entityManager.flush();
	}
	
	/**
	 * Delete quote from database.
	 * 
	 * @param quote Quote which will be deleted.
	 */
	public final void delete(final Quote quote) {
		entityManager.remove(quote);
	}
	
	/**
	 * Delete quote from database by id.
	 * 
	 * @param id Id of quote, which will be deleted.
	 * @return True if quote was deleted, otherwise false.(quote not found).
	 */
	public final boolean delete(final long id) {
		final Quote quote = read(id);
		if(quote == null) {
			return false;
		}
		delete(quote);
		return true;
 }
}
