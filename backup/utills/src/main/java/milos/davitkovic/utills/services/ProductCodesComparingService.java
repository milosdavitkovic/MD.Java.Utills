package milos.davitkovic.utills.services;

import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public interface ProductCodesComparingService {

    /**
     * Compare Files with Lists of elements.
     * Extract elements from List 1 and List as Intersection from both Lists.
     * Extract difference from List 1 and List 2.
     * Extract difference from List 2 and List 1.
     *
     * @param folderName
     * @throws IOException
     */
    void compare2Files(String folderName, String sourceFileName1, String sourceFileName2, String resultFileName) throws IOException;
}
