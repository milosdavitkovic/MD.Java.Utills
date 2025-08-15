package milos.davitkovic.utills.services.impl.utils.File.write;

import milos.davitkovic.utills.annotations.Interface;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;

@Interface
public interface WriteIOUtils {

    // *************************
    // WRITE
    // *************************

    void writeInFile(final Path filePath, final List<String> inputText) throws IOException;

    void writeInFileWithPath(final Path filePath, final List<String> inputText);

    void writeInFile(final String fileName, final String folderName, final List<String> inputText) throws IOException;

    void writeInResourceFile(final String fileName, final String folderName, final List<String> inputText) throws IOException;
}
