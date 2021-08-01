package milos.davitkovic.utills.controllers;

import milos.davitkovic.utills.facade.dto.ErrorLogsDTO;
import milos.davitkovic.utills.facade.logs.LogsFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "logs/")
public class LogsController {

    private static final String userPath = "/Users/milosdavitkovic";
    private static final String projectPath = userPath + "/MEGAsync/Programming/davitko/projects/mbio/hybris";

    @Autowired
    private LogsFacade logsFacade;

    @GetMapping(value = "/getErrorLogs")
    @ResponseStatus(value = HttpStatus.OK)
    @ResponseBody
    public ErrorLogsDTO getLogsRoute() {
        final String inputFileName = "InputLogs.txt";
        final String folderName = "files/logs";
        final String resultFileName = "LogsRoute.txt";
        final String DAIMLER_DCP_PACKAGE = "com.daimler.dcp";
        return logsFacade.getErrorLogs(inputFileName, folderName, resultFileName, DAIMLER_DCP_PACKAGE, projectPath);
    }

    @PostMapping(value = "/getErrorLogs")
    @ResponseStatus(value = HttpStatus.OK)
    @ResponseBody
    public ErrorLogsDTO getErrorLogs(
            @RequestParam("packageName") final String packageName,
            @RequestParam("projectPath") final String projectPath
    ) {
        final String inputFileName = "InputLogs.txt";
        final String folderName = "files/logs";
        final String resultFileName = "LogsRoute.txt";
        final String project = userPath + projectPath;
        return logsFacade.getErrorLogs(inputFileName, folderName, resultFileName, packageName, project);
    }
}
