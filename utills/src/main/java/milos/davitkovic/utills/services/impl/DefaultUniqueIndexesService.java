package milos.davitkovic.utills.services.impl;

import milos.davitkovic.utills.services.MDUtils;
import milos.davitkovic.utills.services.UniqueIndexesService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Set;

@Service
public class DefaultUniqueIndexesService implements UniqueIndexesService {

    @Resource
    private MDUtils mdUtils;

    public void getDuplicates(final String folderName, final String sourceFileName, final String resultFileName) {
        final List<String> elements = mdUtils.readFile(sourceFileName, folderName);
        final Set<String> duplicates = mdUtils.getDuplicates(elements);
        mdUtils.writeInFile(resultFileName, folderName, duplicates);
    }
}
