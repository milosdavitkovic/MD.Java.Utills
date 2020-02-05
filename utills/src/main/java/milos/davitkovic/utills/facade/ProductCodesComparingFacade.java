package milos.davitkovic.utills.facade;

import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public interface ProductCodesComparingFacade {

    /**
     * Comparing 2 lists of ProductCodes.
     * @param folderName
     * @param sourceFileName1
     * @param sourceFileName2
     * @param resultFileName
     */
    void compare2Files(String folderName, String sourceFileName1, String sourceFileName2, String resultFileName) throws IOException;
}
