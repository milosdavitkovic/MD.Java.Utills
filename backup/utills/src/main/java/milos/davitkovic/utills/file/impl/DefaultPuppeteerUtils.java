package sapmarketing.docstore.core.util.file.impl;

import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import sapmarketing.docstore.core.configuration.annotation.UtilClass;
import sapmarketing.docstore.core.util.LoggingUtil;
import sapmarketing.docstore.core.util.file.FileUtils;
import sapmarketing.docstore.core.util.file.PuppeteerUtils;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import static java.util.stream.Collectors.joining;

@Slf4j
@UtilClass
public class DefaultPuppeteerUtils implements PuppeteerUtils {

    private static final String NODE_COMMAND = "node";
    private static final String NODE_JS_SCRIPT = "html2pdf.js";
    private static final String HTML_EXTENSION = ".html";
    private static final String PDF_EXTENSION = ".pdf";

    @Autowired
    private FileUtils fileUtils;

    @Override
    public void convertHtml2Pdf(@NonNull final File inputHtml, @NonNull final File outputPdf) {
        try {
            final List<String> command = getScriptCommands(inputHtml, outputPdf);

            final Runtime runtime = Runtime.getRuntime();
            final String[] commands = command.toArray(new String[0]);
            final Process proc = runtime.exec(commands);

            final BufferedReader stdInput = new BufferedReader(new InputStreamReader(proc.getInputStream()));
            log.debug("[Sap Marketing DocStore] [{}] Html 2 Pdf Script output: {}",
                    LoggingUtil.getLogTimeStamp(), getContent(stdInput));

            final BufferedReader stdError = new BufferedReader(new InputStreamReader(proc.getErrorStream()));
            final String scriptErrors = getContent(stdError);
            if (StringUtils.isNotEmpty(scriptErrors)) {
                log.error("[Sap Marketing DocStore] [{}] Html 2 Pdf Script errors: {}",
                        LoggingUtil.getLogTimeStamp(), scriptErrors);
            }
            stdInput.close();
            stdError.close();
        } catch (Exception e) {
            log.error("[Sap Marketing DocStore] [{}] Error while converting HTML to PDF {}. Exception has occurred: {}",
                    LoggingUtil.getLogTimeStamp(), outputPdf.getName(), e.getMessage(), e);
        }
    }

    @Override
    public File getGeneratedPdfFromHtml(@NonNull final File inputHtml) {
        final String inputHtmlName = inputHtml.getName();
        try {
            final String outputPdfName = StringUtils.replace(inputHtmlName, HTML_EXTENSION, PDF_EXTENSION);
            log.debug("[Sap Marketing DocStore] [{}] PDF File name: {}",
                    LoggingUtil.getLogTimeStamp(), outputPdfName);
            final File file = fileUtils.getFile(outputPdfName);
            if(file != null) {
                log.info("[Sap Marketing DocStore] [{}] PDF File {} has been found.",
                        LoggingUtil.getLogTimeStamp(), outputPdfName);
                return file;
            }

            final String outputPdfPath = StringUtils.replace(inputHtml.getPath(), HTML_EXTENSION, PDF_EXTENSION);
            log.debug("[Sap Marketing DocStore] [{}] PDF File path: {}",
                    LoggingUtil.getLogTimeStamp(), outputPdfName);
            return fileUtils.getFile(outputPdfPath);
        } catch (Exception e) {
            log.error("[Sap Marketing DocStore] [{}] Error while converting HTML to PDF {}. Exception has occurred: {}",
                    LoggingUtil.getLogTimeStamp(), inputHtmlName, e.getMessage(), e);
        }

        return null;
    }

    private List<String> getScriptCommands(File inputHtml, File outputPdf) {
        final List<String> command = new ArrayList<>();
        command.add(NODE_COMMAND);

        final String puppeteetScriptPath = fileUtils.getStringFilePath(NODE_JS_SCRIPT);
        command.add(puppeteetScriptPath);

        command.add(inputHtml.getPath());
        command.add(outputPdf.getPath());

        commandsValidation(command);

        return command;
    }

    private static void commandsValidation(@NonNull final List<String> command) {
        final List<String> emptyCommands = command.stream()
                .filter(StringUtils::isEmpty)
                .toList();

        if (CollectionUtils.isNotEmpty(emptyCommands)) {
            throw new IllegalStateException("[Sap Marketing DocStore] Html to Pdf Script commands set has to have at least four parameters: " + command);
        }
        log.info("[Sap Marketing DocStore] [{}] Html to Pdf Script commands: {}",
                LoggingUtil.getLogTimeStamp(), command);
    }

    private String getContent(@NonNull final BufferedReader reader) {
        return reader.lines()
                .collect(joining(System.lineSeparator()));
    }
}
