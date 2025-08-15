package sapmarketing.docstore.core.util.html;

import lombok.NonNull;
import org.jsoup.nodes.Document;

/**
 * Html Util class for adding html paragraph to the document
 */
public interface HtmlUtil {

    /**
     * Get the html content
     *
     * @param xhtml xhtml content
     * @return html content
     */
    String getHtmlForDocument(@NonNull final String xhtml);

    /**
     * Get the clean html content
     *
     * @param html html content
     * @return clean html content
     */
    String getCleanHtml(@NonNull final String html);

    /**
     * Get the html content from xhtml
     *
     * @param xHtml xhtml content
     * @return html content
     */
    String getCleanHtmlFromXHtml(String xHtml);

    /**
     * Get the html content from json
     *
     * @param jsonString json content
     * @return html content
     */
    String getHtmlContentFromJSON(String jsonString);

    String getCompressHtml(final String cleanHtml);

    /**
     * Check if the given HTML is valid
     *
     * @param html the HTML to check
     * @return true if the HTML is valid, false otherwise
     */
    boolean isHtmlValid(String html);

    /**
     * Get the minified version of the given HTML
     *
     * @param html the HTML to minify
     * @return the minified HTML
     */
    String getMinifiedHtml (String html);

    /**
     * Parse the given HTML
     *
     * @param html the HTML to parse
     * @return the parsed HTML
     */
    Document getParsedHtml(String html);

    /**
     * Get the sourced HTML from the given URL
     *
     * @param url the URL to get the HTML from
     * @return the sourced HTML
     */
    String getSourcedHtmlFromURL(final String url);
}
