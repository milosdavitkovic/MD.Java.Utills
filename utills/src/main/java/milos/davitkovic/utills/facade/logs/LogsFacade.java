package milos.davitkovic.utills.facade.logs;

import milos.davitkovic.utills.annotations.Interface;
import milos.davitkovic.utills.facade.dto.ErrorLogsDTO;

@Interface
public interface LogsFacade {

    ErrorLogsDTO getErrorLogs(String inputFileName, String folderName, String resultFileName, String packageName);
}