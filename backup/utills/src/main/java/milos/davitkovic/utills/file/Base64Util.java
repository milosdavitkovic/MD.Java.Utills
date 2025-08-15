package sapmarketing.docstore.core.util.file;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.text.StringEscapeUtils;
import sapmarketing.docstore.core.configuration.annotation.UtilClass;
import sapmarketing.docstore.core.util.LoggingUtil;

import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.regex.Pattern;

@Slf4j
@UtilClass
public class Base64Util {

    private static final Pattern BASE64_PATTERN_SIMPLE = Pattern.compile("^[A-Za-z0-9+/=]+$");
    private static final Pattern BASE64_PATTERN_ADVANCE = Pattern.compile("^([A-Za-z0-9+/]{4})*([A-Za-z0-9+/]{3}=|[A-Za-z0-9+/]{2}==)?$");

    public static boolean isBase64Encoded(final String input) {
        if (StringUtils.isEmpty(input)) {
            log.debug("[Sap Marketing DocStore] [{}] Input string is empty. Therefore, input String is not Base64 encoded.",
                    LoggingUtil.getLogTimeStamp());
            return false;
        }

        // Check length is multiple of 4
        if (StringUtils.length(input) % 4 != 0) {
            return false;
        }

        if (BooleanUtils.isFalse(BASE64_PATTERN_SIMPLE.matcher(input).matches())) {
            log.debug("[Sap Marketing DocStore] [{}] Input string {} is not Base64 encoded",
                    LoggingUtil.getLogTimeStamp(), input);
            return false;
        }

        // Check for valid Base64 characters
        if (BooleanUtils.isFalse(BASE64_PATTERN_ADVANCE.matcher(input).matches())) {
            return false;
        }

        return true;
    }

    public static String decodeBase64(final String base64EncodedString) {
        if (isBase64Encoded(base64EncodedString)) {
            byte[] decodedBytes = Base64.getDecoder().decode(base64EncodedString);
            final String decodedString = StringUtils.toEncodedString(decodedBytes, StandardCharsets.UTF_8);
            if (StringUtils.isEmpty(decodedString)) {
                log.error("[Sap Marketing DocStore] [{}] Decoded string is empty. It is not a correct state.",
                        LoggingUtil.getLogTimeStamp());
                return StringUtils.EMPTY;
            }

            log.debug("[Sap Marketing DocStore] [{}] Base64 decoded string: {}",
                    LoggingUtil.getLogTimeStamp(), decodedString);
            return new String(decodedBytes);
        }

        log.debug("[Sap Marketing DocStore] [{}] Input string {} is not Base64 encoded",
                LoggingUtil.getLogTimeStamp(), base64EncodedString);
        return StringUtils.trimToEmpty(base64EncodedString);
    }

    public static String decodeHtmlBase64(final String htmlBase64Encoded) {
        if (BooleanUtils.isFalse(isBase64Encoded(htmlBase64Encoded))) {
            log.debug("[Sap Marketing DocStore] [{}] Input string {} is not Base64 encoded",
                    LoggingUtil.getLogTimeStamp(), htmlBase64Encoded);
            return htmlBase64Encoded;
        }

        final byte[] decodedBytes = Base64.getDecoder().decode(htmlBase64Encoded);
        try {
            final String decodedString = StringUtils.toEncodedString(decodedBytes, StandardCharsets.UTF_8);
            if (StringUtils.isEmpty(decodedString)) {
                log.error("[Sap Marketing DocStore] [{}] Decoded string is empty. It is not a correct state.",
                        LoggingUtil.getLogTimeStamp());
                return new String(decodedBytes);
            }

            // Validate for JSON parsing
            final ObjectMapper mapper = new ObjectMapper();
            final JsonNode jsonNode = mapper.readTree(StringEscapeUtils.unescapeHtml4(decodedString));
            final String decodedJsonString = jsonNode.toPrettyString();

            log.debug("[Sap Marketing DocStore] [{}] Base64 decoded HTML, ready for JSON serialisation: {}",
                    LoggingUtil.getLogTimeStamp(), decodedString);
           return decodedJsonString;
        } catch (Exception exception) {
            log.error("[Sap Marketing DocStore] [{}] Error decoding Base64 HTML: {}",
                    LoggingUtil.getLogTimeStamp(), exception.getMessage(), exception);
        }
        return StringEscapeUtils.unescapeHtml4(new String(decodedBytes, StandardCharsets.UTF_8));
    }
}
