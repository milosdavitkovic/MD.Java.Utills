package milos.davitkovic.utills.services.impl;

import milos.davitkovic.utills.services.ProductCodesComparingService;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.nio.charset.StandardCharsets.UTF_8;
import static java.nio.file.StandardOpenOption.APPEND;
import static java.nio.file.StandardOpenOption.CREATE;

@Service
public class DefaultProductCodesComparingService implements ProductCodesComparingService {

    @Override
    public void compare2Files(final String folderName, final String fileName1, final String fileName2) throws IOException {
//        final String folderName = "/Users/d444061/Programming/cyberport/ws30/hybris/bin/custom/cp/cpfacades/src/de/cyberport/ws30/facades/customer/impl/";
        final Set<String> set1 = new HashSet<>(readAllLinesOfFileStr(fileName1, folderName));
        final Set<String> set2 = new HashSet<>(readAllLinesOfFileStr(fileName2, folderName));

        final List<String> needChange = new ArrayList<>();
        needChange.add("1. ********************************");
        needChange.add("INTERSECTION Sortiment vs. Inactiv or Deleted 2");
        needChange.addAll(getIntersectionS1S2(set1, set2));
        needChange.add("2. ********************************");
        needChange.add("INTERSECTION Inactiv or Deleted 2 vs. Sortiment");
        needChange.addAll(getIntersectionS2S1(set1, set2));
        needChange.add("3. ********************************");
        needChange.add("DIFFERENCE Sortiment vs. Inactiv or Deleted 2");
        needChange.addAll(getDifferenceS1S2(set1, set2));
        needChange.add("4. ********************************");
        needChange.add("DIFFERENCE Inactiv or Deleted 2 vs. Sortiment");
        needChange.addAll(getDifferenceS2S1(set1, set2));

        final String header = "********************************";
        final String fileName = "Result.txt";
        writeInFile(fileName, folderName, header);
        needChange.forEach(str -> {
            try {
                writeInFile(fileName, folderName, ";" + str);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    private Set<String> getDifferenceS1S2(final Set<String> set1, final Set<String> set2) {
        return set1.stream()
                .filter(code -> !set2.contains(code))
                .collect(Collectors.toSet());
    }

    private Set<String> getDifferenceS2S1(final Set<String> set1, final Set<String> set2) {
        return set2.stream()
                .filter(code -> !set1.contains(code))
                .collect(Collectors.toSet());
    }

    private Set<String> getIntersectionS1S2(final Set<String> set1, final Set<String> set2) {
        return set1.stream()
                .filter(set2::contains)
                .collect(Collectors.toSet());
    }

    private Set<String> getIntersectionS2S1(final Set<String> set1, final Set<String> set2) {
        return set2.stream()
                .filter(set1::contains)
                .collect(Collectors.toSet());
    }


    /**
     *
     * @param fileName
     * @return String with whole content of specified file
     * @throws IOException
     */
    public List<String> readAllLinesOfFileStr(final String fileName, final String folderName) throws IOException {
        List<String> outputLines = Files.readAllLines(findSpecificFilePathInSpecificFolder(fileName, folderName, 0));
        return outputLines;
    }

    public Path findSpecificFilePathInSpecificFolder(String fileName, String folderName, Integer ordinalNumberOfItem) throws IOException {
        Path fileNamePath = null;
        String input = findSpecificFilesInSpecificFolder(fileName, folderName, " ");
        String[] output = input.split(" ");
        fileNamePath = Paths.get(output[ordinalNumberOfItem]);
        return fileNamePath;
    }

    public String findSpecificFilesInSpecificFolder(String fileName, String folderName, String delimiter) throws IOException {
        Path start = Paths.get(folderName);
        int maxDepth = 30;
        try (Stream<Path> stream = Files.find(start, maxDepth, (path, attr) -> String.valueOf(path).endsWith(fileName))) {
            String joined = stream.sorted().map(String::valueOf).collect(Collectors.joining(delimiter));
            System.out.println("Found: " + joined);
            return joined;
        }
    }

    public void writeInFile(String fileName, String folderName, String inputText) throws IOException {
        List<String> lines = new ArrayList<String>();
        lines.add(inputText);

        System.out.println("Current directory" + System.getProperty("user.dir"));
        File theDir = new File(folderName);
        // if the directory does not exist, create it
        if (!theDir.exists()) {
            System.out.println("creating directory: " + folderName);
            boolean result = false;

            try {
                theDir.mkdir();
                result = true;
            } catch (SecurityException se) {
                //handle it
            }
            if (result) {
                System.out.println("DIR created");
            }
        }
        File outputFile = new File(theDir, fileName);
        System.out.println(outputFile.toString());

        Files.write(outputFile.toPath(), lines, UTF_8, APPEND, CREATE);
    }
}
