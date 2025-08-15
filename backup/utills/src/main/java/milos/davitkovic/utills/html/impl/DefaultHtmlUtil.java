package sapmarketing.docstore.core.util.html.impl;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.googlecode.htmlcompressor.compressor.HtmlCompressor;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.MapUtils;
import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.lang3.RegExUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.Strings;
import org.apache.commons.text.StringEscapeUtils;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.safety.Safelist;
import org.jsoup.select.Elements;
import org.owasp.html.PolicyFactory;
import org.owasp.html.Sanitizers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.web.util.HtmlUtils;
import sapmarketing.docstore.core.configuration.annotation.UtilClass;
import sapmarketing.docstore.core.util.LoggingUtil;
import sapmarketing.docstore.core.util.file.Base64Util;
import sapmarketing.docstore.core.util.file.FileUtils;
import sapmarketing.docstore.core.util.file.XStringutil;
import sapmarketing.docstore.core.util.html.HtmlUtil;
import sapmarketing.docstore.core.util.url.UrlUtil;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.AccessDeniedException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Default implementation of {@link HtmlUtil}
 */
@Slf4j
@UtilClass
public class DefaultHtmlUtil implements HtmlUtil {

    private static final String NEW_LINE_BR = "br";
    private static final String PARAGRAPH_P = "p";
    private static final String NEW_LINE = "\\n";
    private static final String NON_BREAKING_SPACE = "&nbsp;";
    private static final Safelist SAFELIST = Safelist.relaxed();
    private static final String HTML_REGEX = "<([a-zA-Z][a-zA-Z0-9]*)\\b[^>]*>(.*?)</\\1>";
    private static final PolicyFactory POLICY = Sanitizers.FORMATTING.and(Sanitizers.LINKS);
    private static final String COOKIES_FILE_NAME = "cookies";
    private static final String COOKIES_FILE_EXTENSION = "txt";
    private static final String EQUAL = "=";
    private static final String IMAGES_HTML_TAG = "img";
    private static final String SOURCE_HTML_TAG = "src";
    private static final String HTTP_METHOD_HEAD = "HEAD";


    @Autowired
    private FileUtils fileUtils;

    @Override
    public String getHtmlForDocument(@NonNull final String xhtml) {

        final String cleanHtml1 = getCleanHtml(autoComplete(xhtml));
        final Document jsoupDoc = Jsoup.parse(cleanHtml1);
        final Document.OutputSettings outputSettings = new Document.OutputSettings();
        outputSettings.prettyPrint(false);
        jsoupDoc.outputSettings(outputSettings);
        jsoupDoc.select(NEW_LINE_BR).before(NEW_LINE);
        jsoupDoc.select(PARAGRAPH_P).before(NEW_LINE);

        final String cleanString = getCleanHtml(jsoupDoc.html());
        return Jsoup.clean(cleanString, StringUtils.EMPTY, Safelist.none(), outputSettings);
    }

    @Override
    public String getCleanHtml(@NonNull final String rawHTML) {
        if (StringUtils.contains(rawHTML, "<!doctype html>\n")) {
            log.debug("[Sap Marketing DocStore] [{}] Input HTML is already clean and ready to use. No need to clean it again.",
                    LoggingUtil.getLogTimeStamp());
            return rawHTML;
        }

        try {
            String cleanHtml = Base64Util.decodeHtmlBase64(rawHTML);
            cleanHtml = getUtf8String(cleanHtml);
            cleanHtml = getCleanedUpHtml(cleanHtml);
            cleanHtml = removeSubject(cleanHtml);

            if (StringUtils.isEmpty(cleanHtml)) {
                log.error("[Sap Marketing DocStore] [{}] Clean HTML is empty. Raw HTML will be returned.",
                        LoggingUtil.getLogTimeStamp());
                return rawHTML;
            }

            // Validate for JSON parsing
            final ObjectMapper mapper = new ObjectMapper();
            final JsonNode jsonNode = mapper.readTree(StringEscapeUtils.unescapeHtml4(cleanHtml));
            final String decodedJsonString = jsonNode.toPrettyString();

            log.debug("[Sap Marketing DocStore] [{}] Base64 decoded HTML, ready for JSON serialisation: {}",
                    LoggingUtil.getLogTimeStamp(), decodedJsonString);
            return decodedJsonString;
        } catch (Exception e) {
            log.error("[Sap Marketing DocStore] [{}] Error while cleaning HTML: {}", LoggingUtil.getLogTimeStamp(), e.getMessage());
            return rawHTML;
        }
    }

    private String removeSubject(String inputHtml) {
        final String preHeader = StringUtils.substringBetween(inputHtml, "<!-- PREHEADER -->", "<!-- PREHEADER END -->");
        return StringUtils.replace(inputHtml, preHeader, StringUtils.EMPTY);
    }


    private String getUnescapeHtml(final String inputHtml) {
        final String outputHtml = HtmlUtils.htmlUnescape(inputHtml).replaceAll(">\\s+<", "><");
        return StringUtils.isNotEmpty(outputHtml) ? outputHtml : inputHtml;
    }

    @Override
    public String getSourcedHtmlFromURL(final String htmlPageUrl) {
        final String repairedUrl = UrlUtil.getRepairedUrl(htmlPageUrl);
        if (BooleanUtils.isFalse(UrlUtil.isUrlValid(repairedUrl))) {
            log.error("[Sap Marketing DocStore] [{}] URL is not valid, and HTML can't be fetched from the URL: {}",
                    LoggingUtil.getLogTimeStamp(), repairedUrl);
            return StringUtils.EMPTY;
        }

        try {
            final Map<String, String> cookies = getCookies(repairedUrl);
            if (MapUtils.isEmpty(cookies)) {
                log.error("[Sap Marketing DocStore] [{}] HTML can't be fetched from the URL: {}. Check the URL and try again.",
                        LoggingUtil.getLogTimeStamp(), repairedUrl);
                return StringUtils.EMPTY;
            }

            final Document doc = Jsoup.connect(repairedUrl).cookies(cookies).get();
            final List<String> imageUrls = getImagesFromSourceHtml(doc, fileUtils.getResourceFolderPath());
            log.debug("[Sap Marketing DocStore] [{}] {} Image URLs from the Source HTML code {}",
                    LoggingUtil.getLogTimeStamp(), CollectionUtils.size(imageUrls), imageUrls);

            final String htmlSourceCode = doc.html();
            log.debug("[Sap Marketing DocStore] [{}] HTML Source Code Charset: {}",
                    LoggingUtil.getLogTimeStamp(), doc.charset());

            log.debug("[Sap Marketing DocStore] [{}] HTML Source Code Title: {}",
                    LoggingUtil.getLogTimeStamp(), doc.title());
            return HtmlUtils.htmlUnescape(htmlSourceCode);
        } catch (IOException exception) {
            log.error("[Sap Marketing DocStore] [{}] Error while fetching the HTML content from the URL: {}",
                    LoggingUtil.getLogTimeStamp(), exception.getMessage());
        }

        return StringUtils.EMPTY;
    }

    private Map<String, String> getCookies(final String originalUrl) {
        final String repairedUrl = UrlUtil.getRepairedUrl(originalUrl);
        if (BooleanUtils.isFalse(UrlUtil.isUrlValid(repairedUrl))) {
            logInvalidUrl(originalUrl);
            return new HashMap<>();
        }
        try {
            final Connection.Response response = Jsoup.connect(repairedUrl)
                    .method(Connection.Method.GET)
                    .execute();
            final Map<String, String> cookies = response.cookies();
            saveCookies(cookies);
            logFetchedCookies(cookies);
            return cookies;
        } catch (Exception e) {
            logFetchingError(repairedUrl, e);
            return new HashMap<>();
        }
    }

    private void logInvalidUrl(String url) {
        log.error("[Sap Marketing DocStore] [{}] HTML URL is not valid, and cookies can't be fetched from the URL: {}",
                LoggingUtil.getLogTimeStamp(), url);
    }

    private void logFetchedCookies(Map<String, String> cookies) {
        log.debug("[Sap Marketing DocStore] [{}] {} Cookies have been fetched: {}",
                LoggingUtil.getLogTimeStamp(), MapUtils.size(cookies), cookies);
    }

    private void logFetchingError(String url, Exception e) {
        log.error("[Sap Marketing DocStore] [{}] Error while reading the URL {}. Exception: {}",
                LoggingUtil.getLogTimeStamp(), url, e.getMessage());
    }


    private void saveCookies(@NonNull final Map<String, String> cookies) {
        final File cookiesFile = fileUtils.getOrCreateResourceFile(COOKIES_FILE_NAME, COOKIES_FILE_EXTENSION);
        try (FileWriter writer = new FileWriter(cookiesFile)) {
            for (Map.Entry<String, String> cookie : cookies.entrySet()) {
                writer.write(cookie.getKey() + EQUAL + cookie.getValue() + System.lineSeparator());
            }
        } catch (IOException e) {
            log.error("[Sap Marketing DocStore] [{}] Error while saving the cookies to the file: {}",
                    LoggingUtil.getLogTimeStamp(), e.getMessage());
        }

        log.debug("[Sap Marketing DocStore] [{}] Cookies have been saved to the file: {}",
                LoggingUtil.getLogTimeStamp(), cookiesFile.getAbsolutePath());
    }

    private List<String> getImagesFromSourceHtml(@NonNull final Document doc, final String resourceFolderPath) {
        final List<String> allImageURLs = new ArrayList<>();
        final Elements imgageElements = doc.select(IMAGES_HTML_TAG);
        for (Element img : imgageElements) {
            final String src = img.attr(SOURCE_HTML_TAG);
            allImageURLs.add(src);
        }

        log.debug("[Sap Marketing DocStore] [{}] Found {} image URLs in the HTML source code.",
                LoggingUtil.getLogTimeStamp(), CollectionUtils.size(allImageURLs));
        return allImageURLs;
    }

    private boolean areAllWebImagesReachable(final List<String> allImageURLs, final String resourceFolderPath) {
        final List<String> reachableImages = getReachableImages(allImageURLs, resourceFolderPath);
        final List<String> notReachableImages = getNotReachableImages(allImageURLs);

        if (CollectionUtils.size(reachableImages) < CollectionUtils.size(allImageURLs)) {
            log.warn("[Sap Marketing DocStore] [{}] {} Reachable Image URLs: {}",
                    LoggingUtil.getLogTimeStamp(), reachableImages.size(), reachableImages);
            return false;
        }

        if (CollectionUtils.size(notReachableImages) > 0) {
            log.warn("[Sap Marketing DocStore] [{}] {} Not reachable Image URLs: {}",
                    LoggingUtil.getLogTimeStamp(), notReachableImages.size(), notReachableImages);
            return false;
        }

        if (CollectionUtils.size(notReachableImages) == CollectionUtils.size(allImageURLs)) {
            log.error("[Sap Marketing DocStore] [{}] All {} images from Web are not reachable: {}",
                    LoggingUtil.getLogTimeStamp(), notReachableImages.size(), notReachableImages);
            return false;
        }

        return true;
    }

    private List<String> getReachableImages(@NonNull final List<String> allImageURLs, String resourceFolderPath) {
        final List<String> reachableImages = new ArrayList<>();

        for (String imgUrl : allImageURLs) {
            if (isUrlReachable(imgUrl)) {
                reachableImages.add(imgUrl);
                downloadImageFromWebSource(imgUrl, resourceFolderPath);
            }
        }

        return reachableImages;
    }

    private List<String> getNotReachableImages(@NonNull final List<String> allImageURLs) {
        final List<String> notReachableImages = new ArrayList<>();

        for (String imgUrl : allImageURLs) {
            if (!isUrlReachable(imgUrl)) {
                notReachableImages.add(imgUrl);
            }
        }

        return notReachableImages;
    }

    private boolean isUrlReachable(final String urlStr) {
        try {
            int responseCode = getHttpResponseCode(urlStr, HTTP_METHOD_HEAD);
            return (responseCode == HttpURLConnection.HTTP_OK);
        } catch (Exception e) {
            log.error("[Sap Marketing DocStore] [{}] Unexpected error while checking the URL: {}",
                    LoggingUtil.getLogTimeStamp(), e.getMessage());
            return false;
        }
    }

    private int getHttpResponseCode(final String urlStr, final String method) {
        HttpURLConnection connection = null;
        int responseCode = 500;
        try {
            final URI uri = new URI(urlStr);
            final URL url = uri.toURL();
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod(method);
            connection.connect();

            responseCode = connection.getResponseCode();
            connection.disconnect();

            return responseCode;
        } catch (IOException | URISyntaxException e) {
            log.error("[Sap Marketing DocStore] [{}] Error while getting the HTTP response code for URL: {}. Exception: {}",
                    LoggingUtil.getLogTimeStamp(), urlStr, e.getMessage());
        } finally {
            if (connection != null) {
                connection.disconnect();
            }
        }

        return responseCode;
    }

    private void downloadImageFromWebSource(@NonNull final String imageWebSource, @NonNull final String imageLocalTarget) {
        URL url;

        try {
            URI uri = new URI(imageWebSource);
            url = uri.toURL();
        } catch (IOException e) {
            log.error("[Sap Marketing DocStore] [{}] Error while creating the URL: {}",
                    LoggingUtil.getLogTimeStamp(), e.getMessage());
            return;
        } catch (URISyntaxException e) {
            log.error("[Sap Marketing DocStore] [{}] Error while creating the URI: {}",
                    LoggingUtil.getLogTimeStamp(), e.getMessage());
            return;
        }

        final String imageName = imageWebSource.substring(imageWebSource.lastIndexOf("/") + 1);
        final Path imagePath = Paths.get(imageLocalTarget + File.separator + imageName).normalize();
        final String imageTargetPath = imagePath.toString();
        try (InputStream inputStream = url.openStream();
             OutputStream outputStream = new BufferedOutputStream(new FileOutputStream(imageTargetPath))) {
            byte[] buffer = new byte[1024];
            int bytesRead;
            while ((bytesRead = inputStream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, bytesRead);
            }
            log.debug("[Sap Marketing DocStore] [{}] Image downloaded from the URL: {} to the local folder: {}",
                    LoggingUtil.getLogTimeStamp(), imageWebSource, imageTargetPath);
        } catch (AccessDeniedException exception) {
            log.error("[Sap Marketing DocStore] [{}] Access Denied while downloading the image from the URL: {} to {}. AccessDeniedException has been occurred {}",
                    LoggingUtil.getLogTimeStamp(), imageWebSource, imageTargetPath, exception.getMessage());
        } catch (IOException e) {
            log.error("[Sap Marketing DocStore] [{}] Error while downloading the image from the URL: {} to {}. IOException has been occurred {}",
                    LoggingUtil.getLogTimeStamp(), imageWebSource, imageTargetPath, e.getMessage(), e);
        }
    }

    private String getUtf8String(final String html) {
        byte[] utf8Bytes = StringUtils.defaultString(html).getBytes(StandardCharsets.UTF_8);
        return new String(utf8Bytes, StandardCharsets.UTF_8);
    }

    private String getUtf16String(final String html) {
        byte[] utf8Bytes = StringUtils.defaultString(html).getBytes(StandardCharsets.UTF_16);
        return new String(utf8Bytes, StandardCharsets.UTF_16);
    }

    @Override
    public String getCleanHtmlFromXHtml(@NonNull final String xHtml) {
        try {
            final Document document = Jsoup.parse(xHtml);
            if (!isValidHTML(document)) {
                log.error("[Sap Marketing DocStore] Invalid HTML: {}", xHtml);
            }
            return document.html();
        } catch (Exception e) {
            log.error("[Sap Marketing DocStore] Error while converting XHTML to HTML", e);
        }

        return StringUtils.EMPTY;
    }

    private boolean isValidHTML(Document document) {
        return !document.select("html").isEmpty() &&
                !document.select("head").isEmpty() &&
                !document.select("body").isEmpty();
    }

    @Override
    public String getHtmlContentFromJSON(String jsonString) {
        try {
            // Parse the JSON string
            final JSONObject jsonObject = new JSONObject(jsonString);

            // Extract the HTML content
            return jsonObject.getString("htmlContent");
        } catch (Exception e) {
            log.error("[Sap Marketing DocStore] Error while extracting HTML content from JSON string", e);
        }

        return StringUtils.EMPTY;
    }

    private String getCleanedUpHtml(final String inputHtml) {
        String cleanHtml = inputHtml;

        cleanHtml = StringEscapeUtils.unescapeXml(cleanHtml);
        cleanHtml = XStringutil.convertXStringToString(cleanHtml);

        cleanHtml = StringEscapeUtils.unescapeHtml4(cleanHtml);

        cleanHtml = Strings.CS.replace(cleanHtml, "\\\\(.)", "$1");
        cleanHtml = Strings.CS.replace(cleanHtml, "\\\\[\\\\\"'nt]", StringUtils.EMPTY);
        cleanHtml = RegExUtils.replaceAll(cleanHtml, "\\n", StringUtils.EMPTY);
        cleanHtml = RegExUtils.replaceAll(cleanHtml, "\\\\n", StringUtils.EMPTY);
        cleanHtml = RegExUtils.replaceAll(cleanHtml, "\\\\\"", StringUtils.EMPTY);
        cleanHtml = RegExUtils.replaceAll(cleanHtml, "\"", StringUtils.EMPTY);
        cleanHtml = RegExUtils.replaceAll(cleanHtml, "<(img|br|hr|input|meta|link)([^>]*)/>\", \"<$1$2>", StringUtils.SPACE);
        cleanHtml = RegExUtils.replaceAll(cleanHtml, NON_BREAKING_SPACE, StringUtils.SPACE);

        return StringUtils.isNotEmpty(cleanHtml) ? cleanHtml : inputHtml;
    }

    private String autoComplete(String xHtml) {
        // Split the HTML code into lines
        List<String> linesOfCode = new ArrayList<>();
        StringBuilder line = new StringBuilder();
        for (char ch : xHtml.toCharArray()) {
            if (ch == '\n') {
                linesOfCode.add(line.toString());
                line.setLength(0);
            } else {
                line.append(ch);
            }
        }
        if (line.length() > 0) {
            linesOfCode.add(line.toString());
        }

        // Tags which are self-closed and don't need closing
        List<String> selfClosedTags = Arrays.asList("area", "base", "br", "col", "embed", "hr", IMAGES_HTML_TAG, "input", "link", "meta", "param", "source", "track", "wbr");

        Stack<String> stack = new Stack<>();

        // Loop to iterate over the lines of code
        for (String lineCode : linesOfCode) {
            for (int j = 0; j < lineCode.length(); ) {
                // Check for end tags
                if (j + 1 < lineCode.length() && lineCode.charAt(j) == '<' && lineCode.charAt(j + 1) == '/') {
                    StringBuilder tag = new StringBuilder();
                    j += 2;
                    while (j < lineCode.length() && Character.isLowerCase(lineCode.charAt(j))) {
                        tag.append(lineCode.charAt(j));
                        j++;
                    }
                    while (j < lineCode.length() && lineCode.charAt(j) != '>') {
                        j++;
                    }
                    if (!stack.isEmpty() && !stack.peek().equals(tag.toString())) {
                        return "</" + stack.peek() + ">";
                    }
                    stack.pop();
                }
                // Skip the HTML 5 condition
                else if (j + 1 < lineCode.length() && lineCode.charAt(j) == '<' && lineCode.charAt(j + 1) == '!') {
                    j += 2;
                }
                // Check for start tags
                else if (lineCode.charAt(j) == '<') {
                    StringBuilder tag = new StringBuilder();
                    j++;
                    while (j < lineCode.length() && Character.isLowerCase(lineCode.charAt(j))) {
                        tag.append(lineCode.charAt(j));
                        j++;
                    }
                    while (j < lineCode.length() && lineCode.charAt(j) != '>') {
                        j++;
                    }
                    if (!selfClosedTags.contains(tag.toString())) {
                        stack.push(tag.toString());
                    }
                }
                j++;
            }
        }

        // Check if any tag is unbalanced then return that tag
        if (!stack.isEmpty()) {
            return "</" + stack.peek() + ">";
        }
        return "-1";
    }

    private boolean isValidHtmlInputByHtmlRegex(final String html) {
        final Pattern pattern = Pattern.compile(HTML_REGEX);
        final Matcher matcher = pattern.matcher(html);
        return matcher.matches();
    }

    private boolean isHtmlParsable(final String html) {
        try {
            return Jsoup.isValid(html, SAFELIST);
        } catch (Exception e) {
            log.error("[Sap Marketing DocStore] Error validating HTML: {}", e.getMessage());
            return false;
        }
    }

    private String escapeHtml(final String inputHtml) {
        try {
            return StringEscapeUtils.escapeHtml4(inputHtml);
        } catch (Exception e) {
            log.error("[Sap Marketing DocStore] Error escaping HTML: {}", e.getMessage());
            return inputHtml;
        }
    }

    private String sanitizeHtml(final String inputHtml) {
        try {
            return POLICY.sanitize(inputHtml);
        } catch (Exception e) {
            log.error("[Sap Marketing DocStore] Error sanitizing HTML: {}", e.getMessage());
            return inputHtml;
        }
    }

    public boolean isHtmlValid(final String html) {
        if (StringUtils.isEmpty(html)) {
            log.error("[Sap Marketing DocStore] HTML is empty or null, therefore, is not valid.");
            return false;
        }

        if (BooleanUtils.isFalse(isHtmlValidBySafelist(html))) {
            log.error("[Sap Marketing DocStore] HTML is not valid, according to the Safest, and cannot be parsed.");
            return false;
        }

        log.debug("[Sap Marketing DocStore] HTML is valid.");
        return true;
    }


    private boolean isHtmlValidBySafelist(final String input) {
        try {
            final Document document = getParsedHtml(input);
            log.debug("[Sap Marketing DocStore] Parsed HTML: {}", document);
            return true;
        } catch (Exception e) {
            log.error("[Sap Marketing DocStore] Error validating HTML: {}", e.getMessage());
            return false;
        }
    }


    public String getMinifiedHtml(final String inputHtml) {
        if (StringUtils.isEmpty(inputHtml)) {
            log.error("[Sap Marketing DocStore] HTML is empty or null and minification cannot be performed.");
            return StringUtils.EMPTY;
        }

        boolean isHtmlValid = isHtmlValid(inputHtml);
        if (!isHtmlValid) {
            log.error("[Sap Marketing DocStore] HTML is not valid and minification cannot be performed.");
            return StringUtils.EMPTY;
        }

        try {
            final String cleanHtml = getCleanHtml(inputHtml);
            final String minifiedHtml = getCompressHtml(cleanHtml);
            log.debug("[Sap Marketing DocStore] Minified HTML has been created: {}", minifiedHtml);
            return minifiedHtml;
        } catch (Exception e) {
            log.error("[Sap Marketing DocStore] Error during minifying HTML: {}", e.getMessage());
            return StringUtils.EMPTY;
        }
    }

    @Override
    public String getCompressHtml(final String cleanHtml) {
        final HtmlCompressor compressor = new HtmlCompressor();
        // Removes HTML comments
        compressor.setRemoveComments(false);
        // Removes spaces between tags
        compressor.setRemoveIntertagSpaces(true);
        //  Removes quotes around attribute values when safe
        compressor.setRemoveQuotes(false);
        // Compresses inline CSS using YUI Compressor.
        // compressor.setCompressCss(true);
        // Compresses inline JavaScript using YUI Compressor or Google Closure Compiler
        compressor.setCompressJavaScript(true);
        // Removes spaces between tags.
        compressor.setRemoveIntertagSpaces(true);
        // Replaces the doctype with a simple <!DOCTYPE html>.
        compressor.setSimpleDoctype(true);

        return compressor.compress(cleanHtml);
    }

    @Override
    public Document getParsedHtml(final String html) {
        try {
            final Document htmlDocument = Jsoup.parse(html);
            log.debug("HTML Title: {}", htmlDocument.title());
            log.debug("HTML Body: {}", htmlDocument.body().html());
            return htmlDocument;
        } catch (Exception e) {
            log.error("Error parsing HTML: {}", e.getMessage());
            return null;
        }
    }

}
