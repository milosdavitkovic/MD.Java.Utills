package milos.davitkovic.utills.services.impl;

import lombok.extern.slf4j.Slf4j;
import milos.davitkovic.utills.services.MDUtils;
import milos.davitkovic.utills.services.UniqueIndexesService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.List;
import java.util.Set;

@Service
@Slf4j
public class DefaultUniqueIndexesService implements UniqueIndexesService {

    @Resource
    private MDUtils mdUtils;

    public void getDuplicates(final String folderName, final String sourceFileName, final String resultFileName) throws IOException {
        final List<String> elements = mdUtils.readResourceFile(sourceFileName, folderName);
        log.debug(String.format("Number of lines read from file [%s] is [%s] elements", sourceFileName, elements.size()));

        final Set<String> duplicates = mdUtils.getDuplicates(elements);
        log.debug(String.format("Number of duplicated lines in the file [%s] is [%s] elements", sourceFileName, duplicates.size()));

        log.debug(String.format("Number of lines ready to write in the file [%s] is [%s] elements", resultFileName, duplicates.size()));
        mdUtils.writeInResourceFile(resultFileName, folderName, duplicates);
    }
}
