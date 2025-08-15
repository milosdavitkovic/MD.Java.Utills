package milos.davitkovic.javautil.utills.file;

import lombok.NonNull;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.nio.file.Path;
import java.util.List;

/**
 * File Utils class for saving and getting files from the file system
 */
public interface FileUtils {

    boolean isEmptyDirectory(String directoryPath);
    boolean isEmptyDirectory(File directoryPath);

    /**
     * Save a PDF file with the given file name and output stream
     *
     * @param fileName     the file name
     * @param outputStream the output stream
     * @return the saved file
     */
    File savePdfFile(String fileName, ByteArrayOutputStream outputStream);

    /**
     * Save a PDF file with the given file name and text content
     *
     * @param fileName the file name
     * @param text     the text content
     * @return the saved file
     */
    File savePdfFile(String fileName, String text);

    /**
     * Save a file with the given file name, extension, and text content
     *
     * @param fileName      the file name
     * @param fileExtension the file extension
     * @param text          the text content
     * @return the saved file
     */
    File saveFile(String fileName, String fileExtension, String text);

    /**
     * Create a new PDF file with the given file name
     *
     * @param fileName the file name
     * @return the created PDF file
     */
    File createNewPdfFile(String fileName);

    /**
     * Get the properties file with the given file name
     *
     * @param fileName the file name
     * @return the properties file
     */
    File getPropertiesFile(String fileName);

    /**
     * Get the resource file with the given file name
     *
     * @param resourceFileName the resource file name
     * @return the resource file
     */
    File getResourceFile(@NonNull final String resourceFileName);

    /**
     * Get the font file with the given font name
     *
     * @param fontName the font name
     * @return the font file
     */
    File getFontFile(String fontName);

    /**
     * Get the file with the given file name from the resources folder
     *
     * @param fileName the file name
     * @return the file
     */
    File getFile(String fileName);

    /**
     * Get the file with the given file path
     *
     * @param filePath the file path
     * @return the file
     */
    File getFileBasedOnPath(String filePath);

    /**
     * Get the file with the given file name and extension
     *
     * @param fileName         the file name
     * @param fileNameExtension the file name extension
     * @return the file
     */
    File getFile(@NonNull final String fileName, @NonNull final String fileNameExtension);

    /**
     * Create a new document file with the given JSON content
     *
     * @param documentFileName the document file name
     * @param json             the JSON content
     * @return the new document file
     */
    File createNewJsonFile(final String documentFileName, final String json);

    /**
     * Get the JSON file with the given file name
     *
     * @param jsonFileName the JSON file name
     * @return the JSON file
     */
    File getJsonFile(@NonNull final String jsonFileName);

    /**
     * Get the path of the JSON file with the given file name
     *
     * @param jsonFileName the JSON file name
     * @return the path of the JSON file
     */
    Path getJsonFilepath(@NonNull final String jsonFileName);

    /**
     * Get the HTML file with the given file name
     *
     * @param htmlFileName the HTML file name
     * @return the HTML file
     */
    Path getHtmlFilepath(@NonNull final String htmlFileName);

    /**
     * Get the content of the resource file with the given file name
     *
     * @param fileName the file name
     * @return the list of file content lines
     */
    List<String> getResourceFileContent(String fileName);

    /**
     * Create a new resource file with the given file name and extension
     *
     * @param fileName      the file name
     * @param fileExtension the file extension
     * @return the new resource file
     */
    File createNewResourceFile(String fileName, String fileExtension);

    /**
     * Create a new file with the given name, extension, and content
     *
     * @param name      the name of the file
     * @param extension the extension of the file
     * @param content   the content of the file
     * @return the new file
     */
    File createNewFile(String name, String extension, String content);

    /**
     * Get a clean file name from the given input file name
     *
     * @param inputFileName the input file name
     * @return the clean file name
     */
    String getCleanFileName(String inputFileName);

    /**
     * Get the path of the file with the given file name
     *
     * @param fileName the file name
     * @return the file path
     */
    Path getFilePath(String fileName);

    /**
     * Get the string representation of the file path with the given file name
     *
     * @param fileName the file name
     * @return the string representation of the file path
     */
    String getStringFilePath(String fileName);

    /**
     * Get the string representation of the font folder path
     *
     * @return the font folder path string
     */
    String getFontFolderPathString();

    /**
     * Get the path of the resource folder
     *
     * @return the resource folder path
     */
    String getResourceFolderPath();

    /**
     * Create a new file with the given file path
     *
     * @param filePath the file path
     * @return the new file
     */
    File createNewFile(String filePath);

    /**
     * Create a new image file with the given image name
     *
     * @param imageName the image name
     * @return the new image file
     */
    File createNewImageFile(String imageName);

    /**
     * Get or create a file with the given image name
     *
     * @param imageName the image name
     * @return the file
     */
    File getOrCreateFile(String imageName);

    /**
     * Get or create a resource file with the given file name and extension
     *
     * @param fileName  the file name
     * @param extension the file extension
     * @return the resource file
     */
    File getOrCreateResourceFile(@NonNull final String fileName, final String extension);

    /**
     * Write the given content to the file at the specified output file path
     *
     * @param content       the content to write
     * @param outputFilePath the output file path
     */
    void writeToFile(String content, String outputFilePath);

    /**
     * Write the given content to the specified output file
     *
     * @param content    the content to write
     * @param outputFile the output file
     */
    void writeToFile(String content, File outputFile);

    /**
     * Get all files in the folder with the specified file extension
     *
     * @param folderPath    the folder path
     * @param fileExtension the file extension
     * @return the list of files
     */
    List<File> getFilesWithExtension(String folderPath, String fileExtension);

    /**
     * Rename the given file to the new file name
     *
     * @param pdfFile     the file to rename
     * @param newFileName the new file name
     */
    void renameFile(File pdfFile, String newFileName);

    /**
     * Check if the file is a PDF file based on the file name and file
     *
     * @param fileName the file name
     * @param file     the file
     * @return true if the file is a PDF file, false otherwise
     */
    boolean isPdfDFile(final String fileName, final File file);

    /**
     * Check if the file is a PDF file
     *
     * @param file the file
     * @return true if the file is a PDF file, false otherwise
     */
    boolean isPdfDFile(final File file);

    /**
     * Check if the file is ready for further processing
     *
     * @param file the file to check
     * @return true if the file is ready for processing, false otherwise
     */
    boolean isReadyForProcessing(final File file);

    Path getResourceFile(final String folderName, final String fileName);

    Path getOrCreateResourceFilePath(final String folderName, final String fileName);
}