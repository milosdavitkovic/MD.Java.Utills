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

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
@Slf4j
public class DefaultLogsService implements LogsService {

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
            log.warn("WARN-CREATE-CLEAR-LOGS-FILE, File {} cannot be found in the folder {}.", sourceFileName, folderName);
            return;
        }

        final List<String> systemLogs = readIOUtils.readFile(sourceFilePath);
        final List<String> clearLogs = cleanLogs(systemLogs, keyMessage);

        Path resultFilePath = findUtils.findFileInSystem(resultFileName, folderName);
        if (resultFilePath == null) {
            resultFilePath = createIOUtils.createFile(resultFileName, folderName);
            log.info("CREATE-CLEAR-LOGS-FILE, File {} is created in the folder {}.", sourceFileName, folderName);
        }

        writeIOUtils.writeInFileWithPath(resultFilePath, clearLogs);
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
