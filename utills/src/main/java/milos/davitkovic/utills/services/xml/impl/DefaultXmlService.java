package milos.davitkovic.utills.services.xml.impl;

import lombok.extern.slf4j.Slf4j;
import milos.davitkovic.utills.services.impl.utils.File.create.CreateIOUtils;
import milos.davitkovic.utills.services.impl.utils.File.find.FindIOUtils;
import milos.davitkovic.utills.services.impl.utils.File.read.ReadIOUtils;
import milos.davitkovic.utills.services.impl.utils.File.write.WriteIOUtils;
import milos.davitkovic.utills.services.xml.XmlService;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.XMLConstants;
import javax.xml.transform.*;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.nio.file.Path;
import java.util.List;

@Service
@Slf4j
public class DefaultXmlService implements XmlService {

    private static final String userPath = "/Users/milosdavitkovic";
    private static final String mbio_projectPath = userPath + "/MEGAsync/Programming/davitko/projects/mbio/hybris";
    private static final String utils_projectPath = userPath + "/MEGAsync/Programming/davitko/projects/utils/milos.davitkovic.utills/utills";
    private static final String URL_SPLITTER = "/";
    private static final String XML_STRAT = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>";
    private static final String XML_ORDER_END = "</order:partial-order>";
    private static final String NEW_LINE = "\\n";
    private static final String QUOTE_SIGN = "\\\"";

    @Autowired
    private FindIOUtils findUtils;
    @Autowired
    private CreateIOUtils createIOUtils;
    @Autowired
    private WriteIOUtils writeIOUtils;
    @Autowired
    private ReadIOUtils readIOUtils;

    @Override
    public Path getCleanXML(String rawXmlFileName, String cleanXmlFileName, String folderName) {
        try {
            final Path rawXmlPath = findUtils.findFilesInWholeSystem(rawXmlFileName, utils_projectPath).stream().findFirst().orElse(null);
            if (rawXmlPath == null) {
                log.warn("FIND-CODE-BASE-FILE, XML File {} cannot be found in system.", rawXmlFileName);
                return null;
            }

            final List<String> rawXmlTextLines = readIOUtils.readFile(rawXmlPath);
            if (CollectionUtils.isEmpty(rawXmlTextLines)) {
                log.warn("GET-CODE-LINE-FROM-FILE, File {} is empty.", rawXmlFileName);
                return null;
            }

            final Path cleanXmlPath = findUtils.findFilesInWholeSystem(cleanXmlFileName, utils_projectPath).stream().findFirst().orElse(null);
            if (cleanXmlPath == null) {
                log.warn("FIND-CODE-BASE-FILE, XML File {} cannot be found in system.", cleanXmlFileName);
                return null;
            }

            final String cleanXML = getCleanXML(rawXmlTextLines);
            if (StringUtils.isEmpty(cleanXML)) {
                return rawXmlPath;
            }

            writeIOUtils.writeInFileWithPath(cleanXmlPath, cleanXML);
            return cleanXmlPath;

        } catch (IOException exception) {
            log.error("FIND-CODE-BASE-FILE, IOException {}", exception.getMessage(), exception);
        }

        return null;
    }

    @Override
    public String getCleanXML(String rawXmlFileName, String folderName) {
        try {
            final Path rawXmlPath = findUtils.findFilesInWholeSystem(rawXmlFileName, utils_projectPath).stream().findFirst().orElse(null);
            if (rawXmlPath == null) {
                log.warn("FIND-CODE-BASE-FILE, XML File {} cannot be found in system.", rawXmlFileName);
                return null;
            }

            final List<String> rawXmlTextLines = readIOUtils.readFile(rawXmlPath);
            if (CollectionUtils.isEmpty(rawXmlTextLines)) {
                log.warn("GET-CODE-LINE-FROM-FILE, File {} is empty.", rawXmlPath.getFileName());
                return null;
            }

            final String cleanXML = getCleanXML(rawXmlTextLines);
            if (StringUtils.isEmpty(cleanXML)) {
                return StringUtils.join(rawXmlTextLines, StringUtils.EMPTY);
            }

            return cleanXML;
        } catch (IOException exception) {
            log.error("FIND-CODE-BASE-FILE, IOException {}", exception.getMessage(), exception);
        }

        return StringUtils.EMPTY;
    }

    private String getCleanXML(final List<String> rawXmlText) {

        if (CollectionUtils.isEmpty(rawXmlText)) {
            return StringUtils.EMPTY;
        }

        try {
            final String rawXml = StringUtils.join(rawXmlText, StringUtils.EMPTY);
            final String cleanXml = getCleanXml(rawXml);
            return prettyFormat(cleanXml, 2);
        } catch (Exception ex) {
            log.error("XML formatting Exception {}.", ex.getMessage());
        }

        return StringUtils.EMPTY;
    }

    private String getCleanXml(final String rawXml) {
        // Remove \n and \"
        final String rawXMLWithoutNewLine = StringUtils.replace(rawXml, NEW_LINE, StringUtils.EMPTY);
        final String rawXmlWithoutChars = StringUtils.trimToEmpty(StringUtils.replace(rawXMLWithoutNewLine, "\\\"", "\""));

        // Remove first part of the log before XML starts
        final String rawXmlStart = XML_STRAT + StringUtils.trimToEmpty(StringUtils.substringAfter(rawXmlWithoutChars, XML_STRAT));
        // Remove last part of the log, after XML end
        final String cleanXml = StringUtils.trimToEmpty(StringUtils.substringBefore(rawXmlStart, XML_ORDER_END)) + XML_ORDER_END;

        return cleanXml.trim().replaceFirst("^([\\W]+)<", "<").replaceAll(QUOTE_SIGN, "\"").replaceAll(NEW_LINE, StringUtils.EMPTY);
    }

    private String prettyFormat(String input, int indent) {
        try {
            final StringWriter stringWriter = new StringWriter();
            final TransformerFactory transformerFactory = getTransformerFactory(indent);

            final StreamResult xmlOutput = getStreamResult(input, stringWriter, transformerFactory);
            return xmlOutput.getWriter().toString();
        } catch (Exception ex) {
            log.error("XML formatting Exception {}.", ex.getMessage());
        }

        return input;
    }

    private StreamResult getStreamResult(String input, StringWriter stringWriter, TransformerFactory transformerFactory) throws TransformerException {
        final Transformer transformer = transformerFactory.newTransformer();
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
        final Source xmlInput = new StreamSource(new StringReader(input));
        final StreamResult xmlOutput = new StreamResult(stringWriter);
        transformer.transform(xmlInput, xmlOutput);
        return xmlOutput;
    }

    private TransformerFactory getTransformerFactory(int indent) {
        final TransformerFactory transformerFactory = TransformerFactory.newInstance();
        transformerFactory.setAttribute("indent-number", indent);
        transformerFactory.setAttribute(XMLConstants.ACCESS_EXTERNAL_DTD, "");
        transformerFactory.setAttribute(XMLConstants.ACCESS_EXTERNAL_STYLESHEET, "");
        return transformerFactory;
    }
}
