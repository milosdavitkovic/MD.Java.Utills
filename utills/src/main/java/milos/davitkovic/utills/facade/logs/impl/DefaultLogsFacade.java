package milos.davitkovic.utills.facade.logs.impl;

import milos.davitkovic.utills.annotations.Facade;
import milos.davitkovic.utills.facade.dto.ErrorLogDTO;
import milos.davitkovic.utills.facade.dto.ErrorLogsDTO;
import milos.davitkovic.utills.facade.logs.LogsFacade;
import milos.davitkovic.utills.services.logs.LogsService;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

@Facade
public class DefaultLogsFacade implements LogsFacade {

    public static final String DAIMLER_DCP_PACKAGE = "com.daimler.dcp";
    @Autowired
    private LogsService logsService;

    @Override
    public ErrorLogsDTO getErrorLogs(String sourceFileName, String folderName, String resultFileName) {
        final List<String> errorLogsPath = logsService.getErrorLogsLines(folderName, sourceFileName);
        final ErrorLogsDTO errorLog = getErrorLog(errorLogsPath, DAIMLER_DCP_PACKAGE);

        final List<String> logsFileContent = getLogFileContent(errorLog);
        logsService.writeErrorLogsInFile(folderName, resultFileName, logsFileContent);

        return errorLog;
    }

    public ErrorLogsDTO getErrorLog(final List<String> errorLogsLines, final String packageName) {
        final ErrorLogsDTO errorLogs = new ErrorLogsDTO();

        if (CollectionUtils.isEmpty(errorLogsLines)) {
            return errorLogs;
        }

        final List<ErrorLogDTO> result = new ArrayList<>();

        for (String line : errorLogsLines) {
            if (line.contains(packageName)) {
                final ErrorLogDTO errorLog = getErrorLog(line);

                final boolean errorLogValid = isErrorLogValid(errorLog);
                if (errorLogValid) {
                    result.add(errorLog);
                }
            }
        }

        errorLogs.setErrorLogsDTO(result);
        return errorLogs;
    }

    private ErrorLogDTO getErrorLog(String line) {
        final ErrorLogDTO errorLog = new ErrorLogDTO();

        final String lineWithoutAt = StringUtils.remove(line, "at ");
        final String[] divideLinWithOpenBrackets = StringUtils.split(lineWithoutAt, "(");
        if (divideLinWithOpenBrackets != null && divideLinWithOpenBrackets.length == 2) {

            final String classPathMethod = divideLinWithOpenBrackets[0];
            final String[] classPathMethodArray = StringUtils.split(classPathMethod, ".");
            final String method = classPathMethodArray[classPathMethodArray.length - 1];
            errorLog.setMethod(method);

            final String packagePath = StringUtils.remove(classPathMethod, "." + method);
            final String cleanPackagePath = StringUtils.remove(packagePath, "\t");
            errorLog.setPackagePath(cleanPackagePath);
        }

        final String classNameLineNumber = StringUtils.remove(divideLinWithOpenBrackets[1], ")");
        final String[] classNameLineNumberArray = StringUtils.split(classNameLineNumber, ":");
        if (classNameLineNumberArray != null && classNameLineNumberArray.length == 2) {
            errorLog.setClassName(classNameLineNumberArray[0]);
            errorLog.setLineNumber(Integer.valueOf(classNameLineNumberArray[1]));
        }
        return errorLog;
    }

    private boolean isErrorLogValid(final ErrorLogDTO errorLogDTO) {
        final String className = errorLogDTO.getClassName();
        if (StringUtils.isEmpty(className) || !className.contains("Dcp")) {
            return false;
        }

        final String packagePath = errorLogDTO.getPackagePath();
        if (StringUtils.isEmpty(packagePath) || packagePath.contains("$$")) {
            return false;
        }

        final String method = errorLogDTO.getMethod();
        if (StringUtils.isEmpty(method) || method.equals("invoke") || method.contains("doFilter")) {
            return false;
        }

        return true;
    }

    private List<String> getLogFileContent(final ErrorLogsDTO errorLogDTO) {
        if (errorLogDTO == null) {
            return new ArrayList<>();
        }

        final List<ErrorLogDTO> errorLogs = errorLogDTO.getErrorLogsDTO();
        if (CollectionUtils.isEmpty(errorLogs)) {
            return new ArrayList<>();
        }

        final List<String> logsFileLines = new ArrayList<>();

        for (ErrorLogDTO errorLog : errorLogs) {
            final StringBuilder stringBuilder = new StringBuilder();

            final String className = errorLog.getClassName();
            final String cleanClassName = StringUtils.remove(className, ".java");
            stringBuilder.append(cleanClassName);

            stringBuilder.append(".");
            final String method = errorLog.getMethod();
            stringBuilder.append(method);

            stringBuilder.append(":");
            final Integer lineNumber = errorLog.getLineNumber();
            stringBuilder.append(lineNumber);

            logsFileLines.add(stringBuilder.toString());
            logsFileLines.add("=>");
        }

        logsFileLines.remove(logsFileLines.size() - 1);
        return logsFileLines;
    }
}
