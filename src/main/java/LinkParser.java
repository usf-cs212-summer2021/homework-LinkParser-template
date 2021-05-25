import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Parses URL links from the anchor tags within HTML text.
 *
 * @author CS 212 Software Development
 * @author University of San Francisco
 * @version Summer 2021
 */
public class LinkParser {
	/**
	 * Removes the fragment component of a URL (if present), and properly encodes
	 * the query string (if necessary).
	 *
	 * @param url the url to normalize
	 * @return normalized url
	 * @throws URISyntaxException if unable to craft new URI
	 * @throws MalformedURLException if unable to craft new URL
	 */
	public static URL normalize(URL url) throws MalformedURLException, URISyntaxException {
		return new URI(url.getProtocol(), url.getUserInfo(), url.getHost(), 
				url.getPort(), url.getPath(), url.getQuery(), null).toURL();
	}

	/**
	 * Returns a list of all the valid HTTP(S) links found in the href attribute
	 * of the anchor tags in the provided HTML. The links will be converted to
	 * absolute using the base URL and normalized (removing fragments and encoding
	 * special characters as necessary).
	 * 
	 * Any links that are unable to be properly parsed (throwing an
	 * {@link MalformedURLException}) or that do not have the HTTP/S protocol will
	 * not be included.
	 *
	 * @param base the base url used to convert relative links to absolute3
	 * @param html the raw html associated with the base url
	 * @return list of all valid http(s) links in the order they were found
	 */
	public static ArrayList<URL> getValidLinks(URL base, String html) {
		/*
		 * TODO A starter template is given below, but you do not have to use it.
		 */
		String regex = ".+"; // TODO Fill in your regex here
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(html);

		while (matcher.find()) {
			// TODO Fill in your code here. 
			// TODO You may want to use different groups depending on your regex!
			// TODO Don't forget to use the normalize(...) method!
			String matched = matcher.group();
			System.out.println(matched);
		}
		
		return null;
	}
	
	/**
	 * Demonstrates this class.
	 * @param args unused
	 * @throws Exception if any issues occur
	 */
	public static void main(String[] args) throws Exception {
		// this demonstrates cleaning
		URL valid = new URL("https://docs.python.org/3/library/functions.html?highlight=string#format");
		System.out.println(" Link: " + valid);
		System.out.println("Clean: " + normalize(valid));
		System.out.println();

		// this demonstrates encoding
		URL space = new URL("https://www.google.com/search?q=hello world");
		System.out.println(" Link: " + space);
		System.out.println("Clean: " + normalize(space));
		System.out.println();

		// this throws an exception
		URL invalid = new URL("javascript:alert('Hello!');");
		System.out.println(invalid);
	}
}
