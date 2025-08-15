package sapmarketing.docstore.core.util.html.impl;

import lombok.extern.slf4j.Slf4j;
import org.ccil.cowan.tagsoup.Parser;
import org.dom4j.Document;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

@Slf4j
@Service
public class EmailUtil {

    public String getValidHtml(String html) {
        try {
            ByteArrayOutputStream baos = null;
            SAXReader reader = new SAXReader(Parser.class.getName());
            Document doc = reader.read(new ByteArrayInputStream(html.getBytes()));
            baos = new ByteArrayOutputStream();
            XMLWriter writer;
            writer = new XMLWriter(baos);
            writer.write(doc);
            if (baos != null) {
                log.info("HTML is valid");
                return baos.toString();
            }
        } catch (Exception e) {
            log.error("Error while parsing html", e);
        }

        return null;
    }
}
