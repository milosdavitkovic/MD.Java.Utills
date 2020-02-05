package milos.davitkovic.utills.services.impl;

import milos.davitkovic.utills.services.MDUtills;
import milos.davitkovic.utills.services.ProductCodesComparingService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class DefaultProductCodesComparingService implements ProductCodesComparingService {

    @Resource
    private MDUtills mdUtills;

    @Override
    public void compare2Files(final String folderName, final String fileName1, final String fileName2) throws IOException {
//        final String folderName = "/Users/d444061/Programming/cyberport/ws30/hybris/bin/custom/cp/cpfacades/src/de/cyberport/ws30/facades/customer/impl/";
        final Set<String> set1 = new HashSet<>(mdUtills.readFile(fileName1, folderName));
        final Set<String> set2 = new HashSet<>(mdUtills.readFile(fileName2, folderName));

        final List<String> needChange = new ArrayList<>();
        needChange.add("1. ********************************");
        needChange.add("INTERSECTION 1 and 2");
        needChange.addAll(mdUtills.getIntersection(set1, set2));

        needChange.add("2. ********************************");
        needChange.add("DIFFERENCE in 1, not in 2");
        needChange.addAll(mdUtills.getDifference(set1, set2));

        needChange.add("3. ********************************");
        needChange.add("DIFFERENCE in 2 not in 1");
        needChange.addAll(mdUtills.getDifference(set2, set1));

        final String header = "********************************";
        final String fileName = "Result.txt";
        mdUtills.writeInFile(fileName, folderName, header);
        needChange.forEach(str -> mdUtills.writeInFile(fileName, folderName, ";" + str));
    }
}
