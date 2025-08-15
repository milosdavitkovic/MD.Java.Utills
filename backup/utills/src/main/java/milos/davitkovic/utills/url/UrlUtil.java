package sapmarketing.docstore.core.util.url;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.validator.routines.UrlValidator;
import sapmarketing.docstore.core.configuration.annotation.UtilClass;
import sapmarketing.docstore.core.util.LoggingUtil;

import java.util.regex.Pattern;

@Slf4j
@UtilClass
public class UrlUtil {

    private static final String HTTPS_PROTOCOL = "https://";
    private static final String URL_BASIC_REGEX = "^(https?|ftp)://[^\\s/$.?#].[^\\s]*$";
    private static final Pattern URL_BASIC_PATTERN = Pattern.compile(URL_BASIC_REGEX);

    private static final String URL_ADVANCE_REGEX =
            "^(https?://)?(www\\.)?[-a-zA-Z0-9@:%._+~#=]{1,256}\\.[a-zA-Z0-9()]{1,6}\\b([-a-zA-Z0-9()@:%_+.~#?&/=]*)$";
    private static final Pattern URL_ADVANCE_PATTERN = Pattern.compile(URL_ADVANCE_REGEX);

    private UrlUtil() {
        // Prevent instantiation
    }

    public static String getRepairedUrl(String inputUrl) {
        if (StringUtils.isEmpty(inputUrl)) {
            log.error("[Sap Marketing DocStore] URL is empty or null, and HTML can't be fetched.");
            return StringUtils.EMPTY;
        }

        String repairedUrl = inputUrl;

        if (BooleanUtils.isFalse(StringUtils.startsWith(inputUrl, "http"))) {
            log.debug("[Sap Marketing DataBuffer] [{}] HTML URL doesn't contain the required protocol, prefixing 'http://' or 'https://'",
                    LoggingUtil.getLogTimeStamp());
            repairedUrl = HTTPS_PROTOCOL + inputUrl;
        }

        return repairedUrl;
    }

    public static boolean isUrlValid(String inputUrl) {
        if (StringUtils.isEmpty(inputUrl)) {
            log.error("[Sap Marketing DocStore] [{}] URL is empty or null.",
                    LoggingUtil.getLogTimeStamp());
            return false;
        }

        if(BooleanUtils.isFalse(URL_BASIC_PATTERN.matcher(inputUrl).matches())) {
            log.error("[Sap Marketing DocStore] [{}] [URL_BASIC_PATTERN] URL [{}] is not valid.",
                    LoggingUtil.getLogTimeStamp(), inputUrl);
            return false;
        }

        if(BooleanUtils.isFalse(URL_ADVANCE_PATTERN.matcher(inputUrl.trim()).matches())) {
            log.error("[Sap Marketing DocStore] [{}] [URL_ADVANCE_PATTERN] URL [{}] is not valid.",
                    LoggingUtil.getLogTimeStamp(), inputUrl);
            return false;
        }

        final String[] schemes = {"http","https"};
        final UrlValidator urlValidator = new UrlValidator(schemes);
        if(BooleanUtils.isFalse(urlValidator.isValid(inputUrl))) {
            log.error("[Sap Marketing DocStore] [{}] [UrlValidator] URL [{}] is not valid.",
                    LoggingUtil.getLogTimeStamp(), inputUrl);
            return false;
        }

        log.debug("[Sap Marketing DocStore] [{}] URL [{}] is valid.",
                LoggingUtil.getLogTimeStamp(), inputUrl);
        return true;
    }
}
