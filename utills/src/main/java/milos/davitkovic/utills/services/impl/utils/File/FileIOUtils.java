package milos.davitkovic.utills.services.impl.utils.File;

import milos.davitkovic.utills.annotations.Interface;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;

@Interface
public interface FileIOUtils {

    // *************************
    // FILE
    // *************************

    String listFilesInDefaultFolder() throws IOException;

    String listFilesInSpecificFolder(final String folderName) throws IOException;
}
