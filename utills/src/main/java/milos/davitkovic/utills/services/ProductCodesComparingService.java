package milos.davitkovic.utills.services;

import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public interface ProductCodesComparingService {

    /**
     *
     * @param folderName
     * @param fileName1
     * @param fileName2
     * @throws IOException
     */
    void compare2Files(String folderName, String fileName1, String fileName2) throws IOException;
}
