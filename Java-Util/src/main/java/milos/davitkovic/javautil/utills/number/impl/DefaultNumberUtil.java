package milos.davitkovic.javautil.utills.number.impl;


import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import milos.davitkovic.javautil.utills.annotations.UtilClass;
import milos.davitkovic.javautil.utills.number.NumberUtil;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;


@Slf4j
@UtilClass
public class DefaultNumberUtil implements NumberUtil {
    private static final String REGEX_ONLY_NUMBERS = "[^0-9]";

    @Override
    public int valueOf(@NonNull final String value) {
        try {
            return Integer.parseInt(value);
        } catch (NumberFormatException ex) {
            log.debug("String value does not contain only digits and +, - sings!");
        }

        final String stringValue = value.replaceAll(REGEX_ONLY_NUMBERS, StringUtils.EMPTY);
        return NumberUtils.toInt(stringValue);
    }
}
