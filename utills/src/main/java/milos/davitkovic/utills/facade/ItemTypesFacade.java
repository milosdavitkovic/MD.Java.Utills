package milos.davitkovic.utills.facade;

import milos.davitkovic.utills.annotations.Interface;

import java.io.IOException;

@Interface
public interface ItemTypesFacade {

    /**
     * Comparing 2 lists of ProductCodes.
     * @param folderName
     * @param sourceFileName1
     * @param sourceFileName2
     * @param resultFileName
     */
    void compare2Files(String folderName, String sourceFileName1, String sourceFileName2, String resultFileName) throws IOException;

    void getDuplicates(final String folderName, final String sourceFileName, final String resultFileName) throws IOException;

    void createUpdateImpexWithPKs(final String folderName, final String sourceFileName, final String resultFileName, final String header) throws IOException;

    void createUpdateImpexWithPKs(final String folderName, final String sourceFileName, final String resultFileName, final String header, final String lineAddition) throws IOException;
}
