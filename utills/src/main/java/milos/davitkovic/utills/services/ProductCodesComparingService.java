package milos.davitkovic.utills.services;

import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public interface ProductCodesComparingService {

    /**
     *
     * @param folderName
     * @throws IOException
     */
    void compare2Files(String folderName, String sourceFileName1, String sourceFileName2, String resultFileName) throws IOException;
}
