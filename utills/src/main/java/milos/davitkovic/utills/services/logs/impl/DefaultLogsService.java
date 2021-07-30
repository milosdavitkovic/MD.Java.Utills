package milos.davitkovic.utills.services.logs.impl;

import lombok.extern.slf4j.Slf4j;
import milos.davitkovic.utills.services.impl.utils.File.create.CreateIOUtils;
import milos.davitkovic.utills.services.impl.utils.File.find.FindIOUtils;
import milos.davitkovic.utills.services.impl.utils.File.read.ReadIOUtils;
import milos.davitkovic.utills.services.impl.utils.File.write.WriteIOUtils;
import milos.davitkovic.utills.services.logs.LogsService;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
@Slf4j
public class DefaultLogsService implements LogsService {

    private static final String DAIMLER_DCP_PACKAGE = "com.daimler.dcp";

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
            Collections.reverse(systemLogs);
            return systemLogs;
        } catch (IOException exception) {
            log.error("IOException {}", exception.getMessage());
        }

        return new ArrayList<>();
    }

    @Override
    public void writeErrorLogsInFile(String folderName, String resultFileName, final List<String> content) {
        try {
            final Path resultFilePath = findUtils.findFilesInWholeSystem(resultFileName).stream().findFirst().orElse(null);
            log.info("CREATE-ERROR-LOGS-FILE, File {} is created in the folder {}.", resultFileName, folderName);

            writeIOUtils.writeInFileWithPath(resultFilePath, content);
        } catch (Exception exception) {
            log.error("Exception {}", exception.getMessage());
        }
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
