package milos.davitkovic.utills.services.impl;

import milos.davitkovic.utills.services.MDUtills;
import milos.davitkovic.utills.services.impl.utils.Array.ArrayFn;
import milos.davitkovic.utills.services.impl.utils.File.FileFn;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.List;
import java.util.Set;

/**
 * Milos Davitkovic's Java Spring Utills interface implementation
 *
 * @author milos.davitkovic@gmail.com
 */
@Service
public class DefaultMDUtills implements MDUtills {

    @Resource
    private FileFn files;

    @Resource
    private ArrayFn arrays;

    @Override
    public String listFiles() {
        try {
            return files.listFilesInDefaultFolder();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return StringUtils.EMPTY;
    }

    @Override
    public String listFiles(final String folderName) {
        try {
            return files.listFilesInSpecificFolder(folderName);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return StringUtils.EMPTY;
    }

    @Override
    public List<String> readFile(final String fileName, final String folderName) {
        try {
            return files.readAllLinesOfFileStr(fileName, folderName);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return Collections.EMPTY_LIST;
    }

    @Override
    public Path findFile(final String fileName, final String folderName) {
        try {
            return files.findSpecificFilePathInSpecificFolder(fileName, folderName, 0);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return Paths.get(StringUtils.EMPTY);
    }

    @Override
    public void writeInFile(final String fileName, final String folderName, final String inputText) {
        try {
            files.writeInFile(fileName, folderName, inputText);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Get difference of elements which exists in Set 1 but not exists in S2.
     *
     * @param set1
     * @param set2
     * @return
     */
    @Override
    public Set<String> getDifference(final Set<String> set1, final Set<String> set2) {
        return arrays.getDifference(set1, set2);
    }

    /**
     * Get intersection of elements which exists in both Sets.
     *
     * @param set1
     * @param set2
     * @return
     */
    @Override
    public Set<String> getIntersection(final Set<String> set1, final Set<String> set2) {
        return arrays.getDifference(set1, set2);
    }
}
