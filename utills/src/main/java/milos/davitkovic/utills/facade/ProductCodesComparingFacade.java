package milos.davitkovic.utills.facade;

import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public interface ProductCodesComparingFacade {

    /**
     * Comparing 2 lists of ProductCodes.
     * @param fileName1
     * @param fileName2
     */
    void compare2Files(String folderName, String fileName1, String fileName2) throws IOException;
}
