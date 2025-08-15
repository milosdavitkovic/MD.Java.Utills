package milos.davitkovic.javautil.utills.text;

import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import milos.davitkovic.javautil.utills.annotations.UtilClass;
import org.apache.commons.lang3.StringUtils;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * String Util class for supporting string operations
 */
@Slf4j
@UtilClass
@NoArgsConstructor
public final class StringUtil {

    private static final String WHITE_SPACE_CHARACTERS_REGEX = "\\s";
    private static final String ALL_CHARACTERS_REGEX = "\\r\\n|\\r|\\n";

    /**
     * Remove all whitespace from the given string
     *
     * @param value the string to remove whitespace from
     * @return the string with all whitespace removed
     */
    public static String removeAllWhitespace(@NonNull String value) {
        return StringUtils.normalizeSpace(value).replaceAll(WHITE_SPACE_CHARACTERS_REGEX, StringUtils.EMPTY);
    }

    /**
     * Remove all special characters from the given string
     *
     * @param value the string to remove special characters from
     * @return the string with all special characters removed
     */
    public static String removeAllSpecialCharacters(@NonNull final String value) {
        return StringUtils.normalizeSpace(value).replaceAll(ALL_CHARACTERS_REGEX, StringUtils.EMPTY);
    }

    public static String generateString(final List<String> strings, final String delimiter) {
        return String.join(delimiter, strings);
    }

    public static List<String> splitStringIntoChunks(final String longString, int maxChunkLength) {
        if (StringUtils.isEmpty(longString)) {
            log.debug("longString is empty");
            return new ArrayList<>();
        }

        final List<String> chunks = new ArrayList<>();
        int length = longString.length();
        for (int i = 0; i < length; i += maxChunkLength) {
            int endIndex = Math.min(i + maxChunkLength, length);
            chunks.add(longString.substring(i, endIndex));
        }

        return chunks;
    }

    public static boolean equals(@NonNull final String string1, @NonNull final String string2) {
        // Convert to UTF-8 byte arrays
        byte[] string1Utf8 = string1.getBytes(StandardCharsets.UTF_8);
        byte[] string2Utf8 = string2.getBytes(StandardCharsets.UTF_8);

        final String encodedString1 = StringUtils.toEncodedString(string1Utf8, StandardCharsets.UTF_8);
        final String encodedString2 = StringUtils.toEncodedString(string2Utf8, StandardCharsets.UTF_8);

        // Compare byte arrays
        return Arrays.equals(string1Utf8, string2Utf8) || StringUtils.equals(encodedString1, encodedString2);
    }
}
