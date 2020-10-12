package milos.davitkovic.utills.services.impl.utils.File.find;

import milos.davitkovic.utills.annotations.Interface;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;

@Interface
public interface FindIOUtils {

    // *************************
    // FIND
    // *************************

    List<String> findFolderList(String folderName);

    Path findFolderInSystem(final String folderName);

    List<Path> findFilesInWholeSystem(final String fileName, final String folderName) throws IOException;

    Path findFileInSystem(final String fileName, final String folderName);

    Path findSpecificFilePathInSpecificFolder(String fileName, String folderName, Integer ordinalNumberOfItem) throws IOException;

    List<Path> findFilesInWholeSystem(final String fileName) throws IOException;
}
