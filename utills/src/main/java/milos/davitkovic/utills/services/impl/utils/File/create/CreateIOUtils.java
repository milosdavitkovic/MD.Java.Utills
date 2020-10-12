package milos.davitkovic.utills.services.impl.utils.File.create;

import milos.davitkovic.utills.annotations.Interface;

import java.nio.file.Path;

@Interface
public interface CreateIOUtils {

    // *************************
    // CREATE
    // *************************

    Path createFile(final String fileName, final String folderName);
}
