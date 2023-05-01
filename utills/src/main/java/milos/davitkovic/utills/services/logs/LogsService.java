package milos.davitkovic.utills.services.logs;

import milos.davitkovic.utills.annotations.Interface;

@Interface
public interface LogsService {

    void createClearLogsFile(final String folderName, final String sourceFileName, final String resultFileName, final String keyMessage);

    String getO2OEmailPayload(final String inputLog);
}
