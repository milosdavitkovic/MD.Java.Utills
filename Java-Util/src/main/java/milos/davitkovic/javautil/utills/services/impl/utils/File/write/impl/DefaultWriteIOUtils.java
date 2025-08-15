package milos.davitkovic.javautil.utills.services.impl.utils.File.write.impl;

import lombok.extern.slf4j.Slf4j;
import milos.davitkovic.javautil.utills.annotations.UtilClass;
import milos.davitkovic.javautil.utills.file.FileUtils;
import milos.davitkovic.javautil.utills.services.impl.utils.File.write.WriteIOUtils;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import static java.nio.charset.StandardCharsets.UTF_8;
import static java.nio.file.StandardOpenOption.CREATE;

@Slf4j
@UtilClass
public class DefaultWriteIOUtils implements WriteIOUtils {

    @Autowired
    private FileUtils fileUtils;

    /**
     * @param fileName
     * @param folderName
     * @param inputText
     * @throws IOException
     */

    public void writeInResourceFile(final String fileName, final String folderName, final List<String> inputText) throws IOException {
        final Path path = fileUtils.getOrCreateResourceFile(folderName, fileName).toPath();
        log.debug("Resource File for writing, path: " + path.toAbsolutePath());
        writeInFile(path, inputText);
    }

    /**
     * Write down inputText in the file with filePath.
     * Create a new file if it does not exist.
     *
     * @param filePath
     * @param inputText
     */

    public void writeInFile(final Path filePath, final List<String> inputText) throws IOException {
        if (filePath == null) {
            log.error("WRITE-IN-FILE-ERROR File path is null. Not able to write.");
            return;
        }

        if (CollectionUtils.isEmpty(inputText)) {
            log.error("WRITE-IN-FILE-ERROR list of strings for write is empty. Nothing to write.");
            return;
        }

        if (Files.isWritable(filePath)) {
            try {
                if (Files.exists(filePath)) {
                    log.debug("WRITE-IN-FILE-INFO File {} already exists. All data from file will be deleted.", filePath);
                    Files.deleteIfExists(filePath);
                }

                Files.write(filePath, inputText, UTF_8, CREATE);
                log.debug("WRITE-IN-FILE-INFO {} lines have been successfully written in the file {}", inputText.size(), filePath);
            } catch (IOException ex) {
                log.error("WRITE-IN-FILE-ERROR with file path {}. IOException {}", filePath, ex.getMessage());
                throw ex;
            }
        }
    }


    public void writeInFileWithPath(final Path filePath, final List<String> inputText) {
        try {
            writeInFile(filePath, inputText);
        } catch (IOException ex) {
            log.error("ERROR-WRITE-IN-FILE, FILE with file path {}. IOException {}", filePath, ex.getMessage());
        }
    }


    public void writeInFile(final String fileName, final String folderName, final List<String> inputText) throws IOException {
        final Path path = fileUtils.getFile(folderName, fileName).toPath();
        writeInFile(path, inputText);
    }
}
