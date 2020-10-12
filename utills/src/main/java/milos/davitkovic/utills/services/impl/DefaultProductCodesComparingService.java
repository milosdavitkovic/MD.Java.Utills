package milos.davitkovic.utills.services.impl;

import lombok.extern.slf4j.Slf4j;
import milos.davitkovic.utills.services.MDUtils;
import milos.davitkovic.utills.services.ProductCodesComparingService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@Slf4j
public class DefaultProductCodesComparingService implements ProductCodesComparingService {

    @Resource
    private MDUtils mdUtils;

    public void compare2Files(final String folderName, final String sourceFileName1, final String sourceFileName2, final String resultFileName) throws IOException {
        final Set<String> set1 = new HashSet<>(mdUtils.readResourceFile(sourceFileName1, folderName));
        log.info("Number of lines read from file {} is {} elements.", sourceFileName1, set1.size());
        final Set<String> set2 = new HashSet<>(mdUtils.readResourceFile(sourceFileName2, folderName));
        log.info("Number of lines read from file {} is {} elements.", sourceFileName2, set2.size());

        final List<String> result = new ArrayList<>();
        result.add("***************************************************");
        result.add("1. ********************************");
        result.add("INTERSECTION: List 1 and List 2");
        result.addAll(mdUtils.getIntersection(set1, set2));

        result.add("2. ********************************");
        result.add("DIFFERENCE: Elements from List 1, but not in List 2");
        result.addAll(mdUtils.getDifference(set1, set2));

        result.add("3. ********************************");
        result.add("DIFFERENCE: Elements from List 2, but not in List 1");
        result.addAll(mdUtils.getDifference(set2, set1));

        log.info("Number of lines ready to write in the file {} is {} elements.", resultFileName, result.size());
        mdUtils.writeInResourceFile(resultFileName, folderName, result);
    }
}
