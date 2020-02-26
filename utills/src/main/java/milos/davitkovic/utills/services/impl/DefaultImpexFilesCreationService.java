package milos.davitkovic.utills.services.impl;

import lombok.extern.slf4j.Slf4j;
import milos.davitkovic.utills.services.ImpexFilesCreationService;
import milos.davitkovic.utills.services.MDUtils;
import org.apache.commons.lang3.StringUtils;
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
public class DefaultImpexFilesCreationService implements ImpexFilesCreationService {

    private static final String IMPEX_DELIMITER = ";";

    @Resource
    private MDUtils mdUtils;

    @Override
    public void createUpdateImpexWithPKs(final String folderName, final String sourceFileName, final String resultFileName, final String header) throws IOException {
        final Set<String> pkList = new HashSet<>(mdUtils.readResourceFile(sourceFileName, folderName));
        log.debug(String.format("Number of lines read from file [%s] is [%s] elements.", sourceFileName, pkList.size()));

        final List<String> result = createUpdateImpexWithPKs(header, pkList, StringUtils.EMPTY);
        log.debug(String.format("Number of lines ready to write in the file [%s] is [%s] elements.", resultFileName, result.size()));

        mdUtils.writeInResourceFile(resultFileName, folderName, result);
    }

    @Override
    public void createUpdateImpexWithPKs(final String folderName, final String sourceFileName, final String resultFileName, final String header, final String lineAddition) throws IOException {
        final Set<String> pkList = new HashSet<>(mdUtils.readResourceFile(sourceFileName, folderName));
        log.debug(String.format("Number of lines read from file [%s] is [%s] elements.", sourceFileName, pkList.size()));

        final List<String> result = createUpdateImpexWithPKs(header, pkList, lineAddition);
        log.debug(String.format("Number of lines ready to write in the file [%s] is [%s] elements.", resultFileName, result.size()));

        mdUtils.writeInResourceFile(resultFileName, folderName, result);
    }

    private List<String> createUpdateImpexWithPKs(final String header, final Collection<String> pkList, final String lineAddition) {
        final List<String> impexFile = new ArrayList<>();
        impexFile.add(header);

        if(StringUtils.isNotBlank(lineAddition)) {
            for(String pk : pkList) {
                impexFile.add(IMPEX_DELIMITER + pk + IMPEX_DELIMITER + lineAddition);
            }
        } else {
            for(String pk : pkList) {
                impexFile.add(IMPEX_DELIMITER + pk);
            }
        }

        return impexFile;
    }
}
