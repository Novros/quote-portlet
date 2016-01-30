package cz.novros.lif.quotes.portlet.entity;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

public class Category {

		private String name;
		private String created;
		private String author;
		private List<Quote> quotes;
		
		public Category(String name, String author) {
			super();
			this.name = name;
			this.author = author;
			
			final Calendar cal = Calendar.getInstance();
	        final SimpleDateFormat sdf = new SimpleDateFormat("d.M.Y");
	        created = sdf.format(cal.getTime());
		}

		public final String getName() {
			return name;
		}

		public final void setName(final String name) {
			this.name = name;
		}

		public final String getCreated() {
			return created;
		}

		public final void setCreated(final String created) {
			this.created = created;
		}

		public final String getAuthor() {
			return author;
		}

		public final void setAuthor(final String author) {
			this.author = author;
		}

		public final List<Quote> getQuotes() {
			return quotes;
		}
		
		public final Quote getQuote(final int index) {
			return quotes.get(index);
		}
		
		public final void addQuote(final Quote quote) {
			quotes.add(quote);
		}

		public final void setQuotes(final List<Quote> quotes) {
			this.quotes = quotes;
		}
}
