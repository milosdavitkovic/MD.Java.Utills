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

    private static final String TAB_SIGN = "\t";
    private static final String SEMICOLON_SIGN = ";";
    private static final String CLOSE_BRACKET_SIGN = "(";
    private static final String DOT_SIGN = ".";
    private static final String CLASS_PREFIX = "Dcp";
    private static final String JAVA_CLASS = ".java";

    @Autowired
    private LogsService logsService;

    @Override
    public ErrorLogsDTO getErrorLogs(final String sourceFileName, final String folderName, final String resultFileName, final String packageName, final String projectPath) {
        final List<String> errorLogsPath = logsService.getErrorLogsLines(folderName, sourceFileName);
        final ErrorLogsDTO errorLog = getErrorLog(errorLogsPath, packageName, projectPath);

        final List<String> logsFileContent = getLogFileContent(errorLog);
        errorLog.setErrorLogsLines(logsFileContent);
        logsService.writeErrorLogsInFile(resultFileName, logsFileContent);

        return errorLog;
    }

    public ErrorLogsDTO getErrorLog(final List<String> errorLogsLines, final String packageName, String projectPath) {
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
                    setErrorLine(errorLog, projectPath);
                    result.add(errorLog);
                }
            }
        }

        errorLogs.setErrorLogsDTO(result);
        return errorLogs;
    }

    private void setErrorLine(ErrorLogDTO errorLog, String projectPath) {
        final String className = errorLog.getClassName();
        final Integer lineNumber = errorLog.getLineNumber();

        final String errorLine = getErrorLine(className, lineNumber, projectPath);
        errorLog.setErrorLine(errorLine);
    }

    private String getErrorLine(final String className, final Integer lineNumber, String projectPath) {
        final StringBuilder errorLine = new StringBuilder();

        final int readLineNumber = lineNumber - 1;
        for (int i = readLineNumber; i < readLineNumber + 3; i++) {
            final String errorLineFromProjectFile = logsService.getErrorLineFromProjectFile(className, i, projectPath);

            final String cleanErrorLineFromProjectFile = StringUtils.remove(errorLineFromProjectFile, TAB_SIGN);
            errorLine.append(cleanErrorLineFromProjectFile).append(StringUtils.EMPTY);

            if (cleanErrorLineFromProjectFile.contains(SEMICOLON_SIGN)) {
                return errorLine.toString();
            }
        }

        return errorLine.toString();
    }

    private ErrorLogDTO getErrorLog(String line) {
        final ErrorLogDTO errorLog = new ErrorLogDTO();

        final String lineWithoutAt = StringUtils.remove(line, "at ");
        final String[] divideLinWithOpenBrackets = StringUtils.split(lineWithoutAt, CLOSE_BRACKET_SIGN);
        if (divideLinWithOpenBrackets != null && divideLinWithOpenBrackets.length > 1) {

            final String classPathMethod = divideLinWithOpenBrackets[0];
            final String[] classPathMethodArray = StringUtils.split(classPathMethod, DOT_SIGN);
            final String method = classPathMethodArray[classPathMethodArray.length - 1];
            errorLog.setMethod(method);

            setPackagePath(errorLog, classPathMethod, method);
        }

        setClassNameLineNumber(errorLog, divideLinWithOpenBrackets[1]);
        return errorLog;
    }

    private void setClassNameLineNumber(ErrorLogDTO errorLog, String divideLinWithOpenBracket) {
        if (StringUtils.isEmpty(divideLinWithOpenBracket)) {
            return;
        }

        final String classNameLineNumber = StringUtils.remove(divideLinWithOpenBracket, ")");
        final String[] classNameLineNumberArray = StringUtils.split(classNameLineNumber, ":");
        if (classNameLineNumberArray != null && classNameLineNumberArray.length > 1) {
            errorLog.setClassName(classNameLineNumberArray[0]);
            errorLog.setLineNumber(Integer.valueOf(classNameLineNumberArray[1]));
        }
    }

    private void setPackagePath(ErrorLogDTO errorLog, String classPathMethod, String method) {
        final String packagePath = StringUtils.remove(classPathMethod, DOT_SIGN + method);
        final String cleanPackagePath = StringUtils.remove(packagePath, TAB_SIGN);
        errorLog.setPackagePath(cleanPackagePath);
    }

    private boolean isErrorLogValid(final ErrorLogDTO errorLogDTO) {
        final String className = errorLogDTO.getClassName();
        if (StringUtils.isEmpty(className) || !className.contains(CLASS_PREFIX)) {
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
            final String cleanClassName = StringUtils.remove(className, JAVA_CLASS);
            stringBuilder.append(cleanClassName);

            stringBuilder.append(DOT_SIGN);
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
