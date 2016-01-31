package cz.novros.lif.quotes.portlet.generator;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

import cz.novros.lif.quotes.backend.entity.Quote;

public class QuoteRestClient implements IQuoteGenerator {

	public static final String ENDPOINT = "http://quotesondesign.com/wp-json/posts";
	public static final String RAND_PARAM = "?filter[orderby]=rand&filter[posts_per_page]=1";
	public static final String HTML_TAG_REGEX = "<[^>]*>";
	private static final Pattern authorPattern = Pattern.compile("title\":\"[^\"]*");
	private static final Pattern textPattern = Pattern.compile("content\":\"[^\"]*");
	
	@Override
	public Quote randomQuote() {
		Client client = Client.create();
		WebResource webResource = client.resource(ENDPOINT + RAND_PARAM);
		ClientResponse response = webResource.accept("application/json").get(ClientResponse.class);

		String quoteString = "";
		if (response.getStatus() != 200) {
			System.err.println("Failed : HTTP error code : " + response.getStatus());
		} else {
			quoteString = response.getEntity(String.class);
		}

		/** 
		 * TODO Rewrite: Use rest entity to parse json. (Jackson)
		 */
		Matcher authorMatcher = authorPattern.matcher(quoteString);
		Matcher textMatcher = textPattern.matcher(quoteString);
		
		Quote quote = null;
		if (authorMatcher.find() && textMatcher.find()) {
			String text = textMatcher.group(0).substring(10).replaceAll(HTML_TAG_REGEX, "").replaceAll("\\n", "");
			text = text.substring(0, text.length()-3);
			final String author = authorMatcher.group(0).substring(8);
			quote = new Quote(text, author, null);
		}	
		
		return quote;
	}
}