package milos.davitkovic.utills.services.impl;

import milos.davitkovic.utills.services.MDUtils;
import milos.davitkovic.utills.services.ProductCodesComparingService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class DefaultProductCodesComparingService implements ProductCodesComparingService {

    @Resource
    private MDUtils mdUtils;

    public void compare2Files(final String folderName, final String sourceFileName1, final String sourceFileName2, final String resultFileName) {
        final Set<String> set1 = new HashSet<>(mdUtils.readFile(sourceFileName1, folderName));
        final Set<String> set2 = new HashSet<>(mdUtils.readFile(sourceFileName2, folderName));

        final List<String> result = new ArrayList<>();
        result.add("1. ********************************");
        result.add("INTERSECTION 1 and 2");
        result.addAll(mdUtils.getIntersection(set1, set2));

        result.add("2. ********************************");
        result.add("DIFFERENCE in 1, not in 2");
        result.addAll(mdUtils.getDifference(set1, set2));

        result.add("3. ********************************");
        result.add("DIFFERENCE in 2 not in 1");
        result.addAll(mdUtils.getDifference(set2, set1));

        mdUtils.writeInFile(resultFileName, folderName, result);
    }
}
