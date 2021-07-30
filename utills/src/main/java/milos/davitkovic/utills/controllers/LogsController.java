package milos.davitkovic.utills.controllers;

import milos.davitkovic.utills.facade.dto.ErrorLogsDTO;
import milos.davitkovic.utills.facade.logs.LogsFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping(value = "logs/")
public class LogsController {

    @Autowired
    private LogsFacade logsFacade;

    @GetMapping(value = "/getErrorLogs")
    @ResponseBody
    public ErrorLogsDTO getLogsRoute() throws IOException {
        final String inputFileName = "InputLogs.txt";
        final String folderName = "files/logs";
        final String resultFileName = "LogsRoute.txt";
        final String DAIMLER_DCP_PACKAGE = "com.daimler.dcp";
        return logsFacade.getErrorLogs(inputFileName, folderName, resultFileName, DAIMLER_DCP_PACKAGE);
    }
}
