package sapmarketing.docstore.core.util.file;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.binary.Hex;
import sapmarketing.docstore.core.configuration.annotation.UtilClass;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import org.springframework.util.xml.XmlValidationModeDetector;

@Slf4j
@UtilClass
public class XStringutil {

    private XStringutil() {
        // Prevent instantiation
    }

    public static String convertXStringToString(String xString) {
        try {
            byte[] bytes = Hex.decodeHex(xString.toCharArray());
            return new String(bytes, StandardCharsets.UTF_8);
        } catch (Exception e) {
            log.debug("Error while converting XString to String", e);
        }
        return xString;
    }
}
