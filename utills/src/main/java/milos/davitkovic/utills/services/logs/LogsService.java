package milos.davitkovic.utills.services.logs;

import milos.davitkovic.utills.annotations.Interface;

import java.io.IOException;
import java.util.List;

@Interface
public interface LogsService {

    void createClearLogsFile(final String folderName, final String sourceFileName, final String resultFileName, final String keyMessage);

    List<String> getErrorLogsLines(final String folderName, final String sourceFileName);

    void writeErrorLogsInFile(final String resultFileName, final List<String> content);

    String getErrorLineFromProjectFile(final String fileName, final int lineNumber);
}
