package cz.novros.lif.quotes.backend.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cz.novros.lif.quotes.backend.dao.QuoteDAO;
import cz.novros.lif.quotes.backend.entity.Quote;

@Service
@Transactional
public class QuoteService {
	
	@Autowired
	private QuoteDAO quoteDao;

	@Transactional(readOnly=true)
    public List<Quote> readAll() {
        return quoteDao.readAll();
    }

    @Transactional(readOnly=true)
    public Quote read(int id) {
        return quoteDao.read(id);
    }

    public void create(Quote quote) {
    }

	public void update(Quote quote) {
		quoteDao.update(quote);
	}

	public void delete(Quote quote) {
		quoteDao.delete(quote);
	}
}
