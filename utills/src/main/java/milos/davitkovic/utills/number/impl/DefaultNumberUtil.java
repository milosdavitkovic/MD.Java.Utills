package sapmarketing.docstore.core.util.number.impl;

import sapmarketing.docstore.core.configuration.annotation.UtilClass;
import sapmarketing.docstore.core.util.number.NumberUtil;
import lombok.NonNull;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;


@UtilClass
@Log4j2
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
