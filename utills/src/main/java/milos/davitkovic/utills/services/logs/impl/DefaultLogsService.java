package milos.davitkovic.utills.services.logs.impl;

import lombok.extern.slf4j.Slf4j;
import milos.davitkovic.utills.services.impl.utils.File.create.CreateIOUtils;
import milos.davitkovic.utills.services.impl.utils.File.find.FindIOUtils;
import milos.davitkovic.utills.services.impl.utils.File.read.ReadIOUtils;
import milos.davitkovic.utills.services.impl.utils.File.write.WriteIOUtils;
import milos.davitkovic.utills.services.logs.LogsService;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.xml.sax.SAXException;

import javax.xml.XMLConstants;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
@Slf4j
public class DefaultLogsService implements LogsService {

    private static final String userPath = "/Users/milosdavitkovic";
    private static final String mbio_projectPath = userPath + "/MEGAsync/Programming/davitko/projects/mbio/hybris";
    private static final String utils_projectPath = userPath + "/MEGAsync/Programming/davitko/projects/utils/milos.davitkovic.utills/utills";
    public static final String URL_SPLITTER = "/";

    @Autowired
    private FindIOUtils findUtils;
    @Autowired
    private CreateIOUtils createIOUtils;
    @Autowired
    private WriteIOUtils writeIOUtils;
    @Autowired
    private ReadIOUtils readIOUtils;

    @Override
    public void createClearLogsFile(final String folderName, final String sourceFileName, final String resultFileName, final String keyMessage) {
        final Path sourceFilePath = findUtils.findFileInSystem(sourceFileName, folderName);
        if (sourceFilePath == null) {
            log.warn("WARN-FIND-LOGS-FILE, File {} cannot be found in the folder {}.", sourceFileName, folderName);
            return;
        }

        final List<String> systemLogs = readIOUtils.readFile(sourceFilePath);
        final List<String> clearLogs = cleanLogs(systemLogs, keyMessage);

        Path resultFilePath = findUtils.findFileInSystem(resultFileName, folderName);
        if (resultFilePath == null) {
            resultFilePath = createIOUtils.createFile(resultFileName, folderName);
            log.info("CREATE-CLEAR-LOGS-FILE, File {} is created in the folder {}.", resultFileName, folderName);
        }

        writeIOUtils.writeInFileWithPath(resultFilePath, clearLogs);
    }

    @Override
    public List<String> getErrorLogsLines(String folderName, String sourceFileName) {
        try {
            final Path sourceFilePath = findUtils.findFilesInWholeSystem(sourceFileName).stream().findFirst().orElse(null);
            if (sourceFilePath == null) {
                log.warn("WARN-FIND-ERROR-LOGS-FILE, File {} cannot be found in the folder {}.", sourceFileName, folderName);
                return new ArrayList<>();
            }

            final List<String> systemLogs = readIOUtils.readFile(sourceFilePath);
            if (CollectionUtils.isEmpty(systemLogs)) {
                return new ArrayList<>();
            }

            Collections.reverse(systemLogs);
            return systemLogs;
        } catch (IOException exception) {
            log.error("ERROR-LOGS-FILE, IOException {}", exception.getMessage());
        }

        return new ArrayList<>();
    }

    @Override
    public void writeErrorLogsInFile(final String resultFileName, final List<String> content) {
        try {
            final Path resultFilePath = findUtils.findFilesInWholeSystem(resultFileName).stream().findFirst().orElse(null);
            if (resultFilePath == null) {
                log.warn("ERROR-LOGS-FILE, File {} is not found.", resultFileName);
                return;
            }
            log.info("CREATE-ERROR-LOGS-FILE, File {} is found.", resultFileName);

            writeIOUtils.writeInFileWithPath(resultFilePath, content);
        } catch (Exception exception) {
            log.error("ERROR-LOGS-FILE, Exception {}", exception.getMessage());
        }
    }

    @Override
    public String getErrorLineFromProjectFile(final String sourceFileName, final int lineNumber, final String projectPath) {
        try {
            final Path sourceFilePath = findUtils.findFilesInWholeSystem(sourceFileName, projectPath).stream().findFirst().orElse(null);
            if (sourceFilePath == null) {
                log.warn("FIND-CODE-BASE-FILE, File {} cannot be found in system.", sourceFileName);
                return StringUtils.EMPTY;
            }

            final List<String> codeLines = readIOUtils.readFile(sourceFilePath);
            if (CollectionUtils.isEmpty(codeLines)) {
                log.warn("GET-CODE-LINE-FROM-FILE, File {} is empty.", sourceFileName);
                return StringUtils.EMPTY;
            }

            return codeLines.get(lineNumber);
        } catch (IOException exception) {
            log.error("FIND-CODE-BASE-FILE, IOException {}", exception.getMessage());
        }

        return StringUtils.EMPTY;
    }

    @Override
    public String getXmlErrors(String xmlFileName, String xsdFileName, String folderName, String resultFileName) {
        try {
            final Path xmlPath = findUtils.findFilesInWholeSystem(xmlFileName, utils_projectPath).stream().findFirst().orElse(null);
            if (xmlPath == null) {
                log.warn("FIND-CODE-BASE-FILE, XML File {} cannot be found in system.", xmlPath);
                return StringUtils.EMPTY;
            }

            final Path xsdPath = findUtils.findFilesInWholeSystem(xsdFileName, utils_projectPath).stream().findFirst().orElse(null);
            if (xsdPath == null) {
                log.warn("FIND-CODE-BASE-FILE, XSD File {} cannot be found in system.", xmlPath);
                return StringUtils.EMPTY;
            }

            final String validationMessage = validateXMLSchema(xsdPath.toFile(), xmlPath.toFile());
            writeErrorLogsInFile(resultFileName, Collections.singletonList(validationMessage));
            return validationMessage;

        } catch (IOException exception) {
            log.error("FIND-CODE-BASE-FILE, IOException {}", exception.getMessage(), exception);
        }

        return StringUtils.EMPTY;
    }

    private String validateXMLSchema(final File xsdFile, final File xmlFile) {
        try {
            final SchemaFactory factory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
//            final File xsdFile = new File(xsdPath);
            final Schema schema = factory.newSchema(xsdFile);
            final Validator validator = schema.newValidator();
//            final File xmlFile = new File(xmlPath);
            final StreamSource xmlStream = new StreamSource(xmlFile);
            validator.validate(xmlStream);
        } catch (IOException | SAXException e) {
            return String.format("XML file [%s] is NOT valid, against XSD [%s]. Exception [%s].", xmlFile.getName(), xsdFile.getName(), e.getMessage());
        }

        return String.format("XML file [%s] is validated successfully against XSD [%s].", xmlFile.getName(), xsdFile.getName());
    }

    private List<String> cleanLogs(final List<String> systemLogs, final String keyMessage) {
        if (CollectionUtils.isEmpty(systemLogs)) {
            return Collections.emptyList();
        }

        final List<String> cleanLogs = new ArrayList<>();

        for (String systemLog : systemLogs) {
            if (systemLog.contains(keyMessage)) {
                cleanLogs.add(systemLog);
            }
        }

        log.info("CLEAR-LOGS, Created {} lines of clean logs.", cleanLogs.size());
        return cleanLogs;
    }
}
