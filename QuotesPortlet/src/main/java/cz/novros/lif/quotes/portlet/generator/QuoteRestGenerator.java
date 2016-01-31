package cz.novros.lif.quotes.portlet.generator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

import cz.novros.lif.quotes.backend.entity.Quote;

/**
 * Generate quotes from rest endpoint.
 * 
 * @author Rostislav Novak
 */
public class QuoteRestGenerator implements IQuoteGenerator {

	/** Endpoint of rest. */
	public static final String ENDPOINT = "http://quotesondesign.com/wp-json/posts";
	/** Parameters for rest endpoint. */
	public static final String RAND_PARAM = "?filter[orderby]=rand&filter[posts_per_page]=1";
	/** Regex for html tags. */
	public static final String HTML_TAG_REGEX = "<[^>]*>";
	/** Regex pattern for author matching. */
	private static final Pattern authorPattern = Pattern.compile("title\":\"[^\"]*");
	/** Regex pattern for text matching. */
	private static final Pattern textPattern = Pattern.compile("content\":\"[^\"]*");
	
	/**
	 * Generate quote from rest endpoint.
	 * 
	 * @return Return quote.
	 */
	@Override
	public final Quote randomQuote() {
		final Client client = Client.create();
		final WebResource webResource = client.resource(ENDPOINT + RAND_PARAM);
		final ClientResponse response = webResource.accept("application/json").get(ClientResponse.class);

		String quoteString = "";
		if (response.getStatus() != 200) {
			System.err.println("Failed : HTTP error code : " + response.getStatus());
		} else {
			quoteString = response.getEntity(String.class);
		}

		/** 
		 * TODO Rewrite: Use rest entity to parse json. (Jackson)
		 */
		final Matcher authorMatcher = authorPattern.matcher(quoteString);
		final Matcher textMatcher = textPattern.matcher(quoteString);
		
		Quote quote = null;
		if (authorMatcher.find() && textMatcher.find()) {
			String text = textMatcher.group(0).substring(10).replaceAll(HTML_TAG_REGEX, "").replaceAll("\\n", "");
			text = text.substring(0, text.length()-3);
			final String author = authorMatcher.group(0).substring(8);
			quote = new Quote(text, author, null);
		}	
		
		
		client.destroy();
		
		return quote;
	}
}
