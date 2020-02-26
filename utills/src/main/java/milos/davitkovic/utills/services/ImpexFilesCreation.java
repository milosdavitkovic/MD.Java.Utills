package milos.davitkovic.utills.services;

import java.io.IOException;

/**
 *
 */
public interface ImpexFilesCreation {

    void createUpdateImpexWithPKs(String folderName, String sourceFileName, String resultFileName, String header) throws IOException;
}
