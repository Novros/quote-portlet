package cz.novros.lif.quotes.backend.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import cz.novros.lif.quotes.backend.entity.Quote;

@Repository
public class QuoteDAO {

	 @PersistenceContext
	 private EntityManager entityManager;
	 
	 public void create(Quote quote) {
		 entityManager.persist(quote);
		 entityManager.flush();
	 }
	 
	 public Quote read(long id) {
		 Quote result = entityManager.find(Quote.class, id);
		 if (result == null) {
			 throw new EntityNotFoundException();
		 }
		 return result;
	 }
	 
	 public List<Quote> readAll() {
		 String readAllQuery = "SELECT e FROM " + Quote.class.getSimpleName() + " e";
		 TypedQuery<Quote> query = entityManager.createQuery(readAllQuery, Quote.class);
	     return query.getResultList();
	 }
	 
	 public void update(Quote quote) {
		 entityManager.merge(quote);
		 entityManager.flush();
	 }
	 
	 public void delete(Quote quote) {
		 entityManager.remove(quote);
	 }
	 
	 public boolean delete(long id) {
		 Quote quote = read(id);
		 if(quote == null) {
			 return false;
		 }
		 delete(quote);
		 return true;
	 }
}
