package milos.davitkovic.javautil.utills.services.impl.utils.File.read.impl;

import lombok.extern.slf4j.Slf4j;
import milos.davitkovic.javautil.utills.annotations.UtilClass;
import milos.davitkovic.javautil.utills.file.FileUtils;
import milos.davitkovic.javautil.utills.services.impl.utils.File.read.ReadIOUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Collections;
import java.util.List;

@Slf4j
@UtilClass
public class DefaultReadIOUtils implements ReadIOUtils {

    @Autowired
    private FileUtils fileUtils;

    /**
     * @param fileName
     * @return String with whole content of specified file
     * @throws IOException
     */

    public List<String> readResourceFile(final String fileName, final String folderName) throws IOException {
        final Path path = fileUtils.getResourceFile(folderName, fileName);
        if (StringUtils.isBlank(path.toString())) {
            log.error("ERROR-READ-RESOURCE-FILE, Resource File {} cannot be found in folder {}. Nothing to read.", fileName, folderName);
            return Collections.emptyList();
        }

        log.debug("Resource File for reading, path: " + path.toAbsolutePath());
        return Files.isReadable(path) ? Files.readAllLines(path) : Collections.emptyList();
    }


    public List<String> readFile(final String fileName, final String folderName) throws IOException {
        final Path path = fileUtils.getFile(folderName, fileName).toPath();
        if (StringUtils.isBlank(path.toString())) {
            log.error("ERROR-READ-RESOURCE-FILE, File {} cannot be found in folder {}. Nothing to read.", fileName, folderName);
            return Collections.emptyList();
        }

        log.debug("File for reading, path: " + path.toAbsolutePath());
        return Files.isReadable(path) ? Files.readAllLines(path) : Collections.emptyList();
    }


    public List<String> readFile(final Path filePath) {
        if (filePath == null) {
            return Collections.emptyList();
        }

        if (StringUtils.isBlank(filePath.toString())) {
            return Collections.emptyList();
        }
        try {
            if (Files.isReadable(filePath)) {
                return Files.readAllLines(filePath);
            } else {
                Collections.emptyList();
            }
        } catch (IOException ex) {
            log.error("ERROR-READ-FILE, File {}. IOException {}.", filePath.toString(), ex.getMessage());
        }

        return Collections.emptyList();
    }


}
