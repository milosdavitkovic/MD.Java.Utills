package milos.davitkovic.javautil.utills.services.impl.utils.File.read;



import java.io.IOException;
import java.nio.file.Path;
import java.util.List;


public interface ReadIOUtils {

    // *************************
    // READ
    // *************************

    List<String> readResourceFile(final String fileName, final String folderName) throws IOException;

    List<String> readFile(final String fileName, final String folderName) throws IOException;

    List<String> readFile(final Path filePath);
}
