package sapmarketing.docstore.core.util.xml;

import lombok.extern.slf4j.Slf4j;
import org.w3c.dom.Document;
import sapmarketing.docstore.core.configuration.annotation.UtilClass;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.StringReader;
import java.io.StringWriter;

@Slf4j
@UtilClass
public class XmlUtil {

    private XmlUtil() {
        // Prevent instantiation
    }

    public static String getXmlHtml(final String rawHTML) {
        try {
            final DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            factory.setFeature("http://apache.org/xml/features/disallow-doctype-decl", false);
            factory.setFeature("http://xml.org/sax/features/external-general-entities", false);
            factory.setFeature("http://xml.org/sax/features/external-parameter-entities", false);

            final Document doc = factory.newDocumentBuilder()
                    .parse(new org.xml.sax.InputSource(new StringReader(rawHTML)));

            // Process DOM document here

            // Convert back to string
            final StringWriter writer = new StringWriter();
            TransformerFactory.newInstance()
                    .newTransformer()
                    .transform(new DOMSource(doc), new StreamResult(writer));

            return writer.toString();
        } catch (Exception exception) {
            log.error("[DocStore] Error while parsing XML: {}", exception.getMessage());
        }

        return rawHTML;
    }
}
