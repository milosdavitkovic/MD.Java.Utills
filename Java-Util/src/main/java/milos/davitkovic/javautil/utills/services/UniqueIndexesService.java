package milos.davitkovic.javautil.utills.services;



import java.io.IOException;


public interface UniqueIndexesService {

    void getDuplicates(final String folderName, final String sourceFileName, final String resultFileName) throws IOException;
}
