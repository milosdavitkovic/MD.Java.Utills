package milos.davitkovic.javautil.utills.file.impl;

import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import milos.davitkovic.javautil.utills.annotations.UtilClass;
import milos.davitkovic.javautil.utills.file.FileUtils;
import milos.davitkovic.javautil.utills.file.PropertiesUtils;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.File;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.List;

@Slf4j
@UtilClass
public class DefaultPropertiesUtils implements PropertiesUtils {

    @Autowired
    private FileUtils fileUtils;

    @Override
    public void convertApplicationPropertiesToHashiCorpJSON(@NonNull final String propertiesFileName) {
        final File propertiesFile = fileUtils.getPropertiesFile(propertiesFileName);
        if (!propertiesFile.canRead()) {
            throw new IllegalArgumentException("The file " + propertiesFileName + " cannot be read.");
        }

        try {
            final String hashiCorpJSON = StringUtils.replace(propertiesFileName, ".properties", "_hashicorp");
            final List<String> lines = Files.readAllLines(propertiesFile.toPath(), StandardCharsets.UTF_8);
            fileUtils.saveFile(hashiCorpJSON, ".json", getJsonProperties(lines));
        } catch (Exception e) {
            log.error("Error converting application properties to HashiCorp JSON", e);
        }
    }

    private String getJsonProperties(final List<String> applicationProperties) {
        if (CollectionUtils.isEmpty(applicationProperties)) {
            log.error("The application properties are empty.");
            return StringUtils.EMPTY;
        }

        final StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("{").append("\n");
        for (String line : applicationProperties) {
            if (StringUtils.containsNone(line, "=")) {
                log.error("The line {} is not a key-value pair.", line);
                continue;
            }
            stringBuilder.append(getLine(line)).append(",").append("\n");
        }
        stringBuilder.append("\n").append("}");

        return stringBuilder.toString();
    }

    @Override
    public String convertApplicationStringPropertiesToHashiCorpJSON(@NonNull final String applicationProperties) {
        final List<String> lines = Arrays.asList(StringUtils.split(applicationProperties, "\n"));
        if (CollectionUtils.isEmpty(lines)) {
            throw new IllegalArgumentException("The application properties are empty.");
        }

        final StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("{").append("\n");
        lines.stream().forEach(line -> addLine(line, stringBuilder));
        stringBuilder.append("\n").append("}");

        return stringBuilder.toString();
    }

    private void addLine(String line, StringBuilder stringBuilder) {
        if(StringUtils.isNotEmpty(line)) {
            stringBuilder.append(getLine(line)).append(",");
        }
    }

    private String getLine(final String line) {
        final List<String> keyValue = Arrays.asList(StringUtils.split(line, "="));
        if (CollectionUtils.size(keyValue) != 2) {
            log.warn("The line " + line + " is not a key-value pair.");
            return StringUtils.EMPTY;
        }

        final String key = "\"" + keyValue.get(0) + "\"";
        final String value = "\"" + keyValue.get(1) + "\"";

        return key + ": " + value;
    }
}
