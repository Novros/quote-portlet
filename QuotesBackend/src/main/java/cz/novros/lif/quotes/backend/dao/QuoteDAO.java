package cz.novros.lif.quotes.backend.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import cz.novros.lif.quotes.backend.entity.Quote;

public class QuoteDAO {

	 @PersistenceContext
	 private EntityManager entityManager;
	 
	 public void create(Quote quote) {
		 entityManager.persist(quote);
	 }
	 
	 public Quote read(long id) {
		 Quote result = entityManager.find(Quote.class, id);
		 if (result == null) {
			 throw new EntityNotFoundException();
		 }
		 return result;
	 }
	 
	 public List<Quote> readAll() {
		 TypedQuery<Quote> query = entityManager.createQuery("SELECT e FROM " + Quote.class.getSimpleName() + " e", Quote.class);
	     return query.getResultList();
	 }
	 
	 public void update(Quote quote) {
		 entityManager.merge(quote);
	 }
	 
	 public void delete(Quote quote) {
		 entityManager.remove(quote);
	 }
}
