package milos.davitkovic.utills.services;

import java.io.IOException;

/**
 *
 */
public interface ImpexFilesCreationService {

    void createUpdateImpexWithPKs(String folderName, String sourceFileName, String resultFileName, String header) throws IOException;

    void createUpdateImpexWithPKs(final String folderName, final String sourceFileName, final String resultFileName, final String header, final String lineAddition) throws IOException;
}
