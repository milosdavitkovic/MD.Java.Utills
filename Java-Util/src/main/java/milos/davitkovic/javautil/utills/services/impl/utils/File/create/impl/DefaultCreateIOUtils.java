package milos.davitkovic.javautil.utills.services.impl.utils.File.create.impl;

import lombok.extern.slf4j.Slf4j;
import milos.davitkovic.javautil.utills.annotations.UtilClass;
import milos.davitkovic.javautil.utills.services.impl.utils.File.create.CreateIOUtils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;

@Slf4j
@UtilClass
public class DefaultCreateIOUtils implements CreateIOUtils {

    @Override
    public Path createFile(final String fileName, final String folderName) {
        try {
            final File file = new File(folderName, fileName);
            if (file.exists()) {
                log.info("CREATE-FILE, File {} in folder {} already exists.", fileName, folderName);
                return file.toPath();
            }

            final boolean isNewFileCreated = file.createNewFile();
            if (isNewFileCreated) {
                log.info("CREATE-FILE, file {} in folder {} is created successfully.", fileName, folderName);
                return file.toPath();
            }

            return null;
        } catch (IOException ex) {
            log.error("ERROR-CREATE-FILE, Create file {} in folder {}. IOException {}", fileName, folderName, ex.getMessage());
        }

        return null;
    }

}
