package milos.davitkovic.utills.services.logs.impl;

import lombok.extern.slf4j.Slf4j;
import milos.davitkovic.utills.services.MDUtils;
import milos.davitkovic.utills.services.impl.utils.File.create.CreateIOUtils;
import milos.davitkovic.utills.services.impl.utils.File.find.FindIOUtils;
import milos.davitkovic.utills.services.impl.utils.File.read.ReadIOUtils;
import milos.davitkovic.utills.services.impl.utils.File.write.WriteIOUtils;
import milos.davitkovic.utills.services.logs.LogsService;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
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

    @Autowired
    private FindIOUtils findUtils;
    @Autowired
    private CreateIOUtils createIOUtils;
    @Autowired
    private WriteIOUtils writeIOUtils;
    @Autowired
    private ReadIOUtils readIOUtils;
    @Autowired
    private MDUtils mdUtils;

    @Override
    public void createClearLogsFile(final String folderName, final String sourceFileName, final String resultFileName, final String keyMessage) {
        try {
            final List<String> systemLogs = mdUtils.readResourceFile(sourceFileName, folderName);
            final List<String> cleanLogs = cleanLogs(systemLogs, keyMessage);
            if (CollectionUtils.isEmpty(cleanLogs)) {
                log.warn("WARN-CREATE-CLEAR-LOGS-FILE, No clean logs to write.");
                return;
            }

            mdUtils.writeInResourceFile(resultFileName, folderName, cleanLogs);
        } catch (IOException ex) {
            log.error("ERROR-CREATE-CLEAR-LOGS-FILE, IOException {}", ex.getMessage());
        }
    }

    private Path getResultFile(final String folderName, final String resultFileName) {
        Path resultFilePath = findUtils.findFileInSystem(resultFileName, folderName);
        if (resultFilePath != null) {
            return resultFilePath;
        }

        log.warn("WARN-CREATE-CLEAR-LOGS-FILE, Result File {} cannot be found in the folder {}.", resultFileName, folderName);
        resultFilePath = createIOUtils.createFile(resultFileName, folderName);
        log.info("CREATE-CLEAR-LOGS-FILE, Result File {} is created in the folder {}.", resultFileName, folderName);
        return resultFilePath;
    }

    private List<String> cleanLogs(final List<String> systemLogs, final String keyMessage) {
        if (CollectionUtils.isEmpty(systemLogs)) {
            log.warn("WARN-CLEAN-LOGS, Input logs list is empty! Nothing to clean.");
            return Collections.emptyList();
        }

        log.info("CLEAN-LOGS, Input logs list has {} elements.", systemLogs.size());
        final List<String> cleanLogs = new ArrayList<>();
        for (String systemLog : systemLogs) {
            if (systemLog.contains(keyMessage)) {
                cleanLogs.add(systemLog);
            }
        }

        log.info("CLEAN-LOGS, Created {} lines of clean logs for key message word {}.", cleanLogs.size(), keyMessage);
        return cleanLogs;
    }

    @Override
    public String getO2OEmailPayload(final String inputLog)
    {
        if (StringUtils.isEmpty(inputLog)) {
            log.warn("WARN-CLEAN-LOGS, Input logs is empty! Nothing to clean.");
            return "Please send Log Messages from the Business Process step: sendOrderPlacedNotification.";
        }

        if(StringUtils.containsNone(inputLog, "O2O Email from market")) {
            log.warn("WARN-CLEAN-LOGS, Input logs does not contain O2O Email.");
            return "Provided Log Messages from the Business Process step: sendOrderPlacedNotification does not have O2O Email message payload.";
        }

        final String cleanLogs = StringUtils.trim(inputLog);

        final String o2oPayload = StringUtils.substringBefore(StringUtils.substringAfter(cleanLogs, "O2O Email from market"),
              "\",\"endOfBatch\":false");

        return StringUtils.substringAfter(o2oPayload, ": {")
              .replaceAll("\\\\n", "")
              .replaceAll("\\\\", "");
    }
}
