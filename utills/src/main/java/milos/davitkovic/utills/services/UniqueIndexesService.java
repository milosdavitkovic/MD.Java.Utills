package milos.davitkovic.utills.services;

import milos.davitkovic.utills.annotations.Interface;

@Interface
public interface UniqueIndexesService {

    void getDuplicates(final String folderName, final String sourceFileName, final String resultFileName);
}
