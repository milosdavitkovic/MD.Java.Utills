package sapmarketing.docstore.core.util.file;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import sapmarketing.docstore.core.configuration.annotation.UtilClass;

import java.io.File;
import java.io.IOException;

@UtilClass
@Slf4j
public class JsonUtil {

    @Autowired
    private FileUtils fileUtils;

    public File createJsonFile(final Object object, @NonNull final String filePath) {
        try {
            final ObjectMapper objectMapper = new ObjectMapper();
            final ObjectNode objectNode = objectMapper.valueToTree(object);
            final File newJsonFile = fileUtils.createNewJsonFile(filePath, objectNode.toPrettyString());
            if (newJsonFile == null) {
                log.error("[Sap Marketing DocStore] JSON file {} can't be created.", filePath);
                return null;
            }

            log.debug("[Sap Marketing DocStore] JSON file {}, on location {} has been created.", newJsonFile.getName(), newJsonFile.getPath());
            log.debug("[Sap Marketing DocStore] JSON file {} has been created.", newJsonFile.getName());
            return newJsonFile;
        } catch (Exception e) {
            log.error("[Sap Marketing DocStore] Error while creating a new JSON file: {}", e.getMessage(), e);
        }

        return null;
    }

    public <T> T readJsonFile(final String jsonFilePath, Class<T> object) {
        try {
            final ObjectMapper objectMapper = new ObjectMapper();
            final File jsonFile = fileUtils.getJsonFile(jsonFilePath);
            return objectMapper.readValue(jsonFile, object);
        } catch (IOException e) {
            log.error("[Sap Marketing DocStore] Error while reading JSON file: {}", e.getMessage(), e);
        }
        return null;
    }
}
