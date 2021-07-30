package milos.davitkovic.utills.services.impl.utils.File.read;

import milos.davitkovic.utills.annotations.Interface;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;

@Interface
public interface ReadIOUtils {

    // *************************
    // READ
    // *************************

    List<String> readResourceFile(final String fileName, final String folderName) throws IOException;

    List<String> readFile(final String fileName, final String folderName) throws IOException;

    List<String> readFile(final Path filePath);
}
