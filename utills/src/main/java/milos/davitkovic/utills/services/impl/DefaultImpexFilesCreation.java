package milos.davitkovic.utills.services.impl;

import lombok.extern.slf4j.Slf4j;
import milos.davitkovic.utills.services.ImpexFilesCreation;
import milos.davitkovic.utills.services.MDUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 *
 *
 */
@Service
@Slf4j
public class DefaultImpexFilesCreation implements ImpexFilesCreation {

    private static final String IMPEX_DELIMITER = ";";

    @Resource
    private MDUtils mdUtils;

    @Override
    public void createUpdateImpexWithPKs(final String folderName, final String sourceFileName, final String resultFileName, final String header) throws IOException {
        final Set<String> pkList = new HashSet<>(mdUtils.readResourceFile(sourceFileName, folderName));
        log.debug(String.format("Number of lines read from file [%s] is [%s] elements.", sourceFileName, pkList.size()));

        final List<String> result = createUpdateImpexWithPKs(header, pkList);
        log.debug(String.format("Number of lines ready to write in the file [%s] is [%s] elements.", resultFileName, result.size()));

        mdUtils.writeInResourceFile(resultFileName, folderName, result);
    }

    private List<String> createUpdateImpexWithPKs(final String header, final Collection<String> pkList) {
        final List<String> impexFile = new ArrayList<>();
        impexFile.add(header);
        for(String pk : pkList) {
            impexFile.add(IMPEX_DELIMITER + pk + IMPEX_DELIMITER + "false");
        }
        return impexFile;
    }
}
