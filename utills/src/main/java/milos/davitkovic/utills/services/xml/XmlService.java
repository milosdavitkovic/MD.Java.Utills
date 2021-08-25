package milos.davitkovic.utills.services.xml;

import java.nio.file.Path;

public interface XmlService {

    Path getCleanXML(String rawXmlFileName, String cleanXmlFileName, String folderName);

    String getCleanXML(String rawXmlFileName, String folderName);
}
