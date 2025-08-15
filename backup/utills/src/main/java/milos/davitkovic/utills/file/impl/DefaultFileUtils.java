package sapmarketing.docstore.core.util.file.impl;

import lombok.NonNull;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import sapmarketing.docstore.core.configuration.annotation.UtilClass;
import sapmarketing.docstore.core.util.LoggingUtil;
import sapmarketing.docstore.core.util.file.FileUtils;

import java.io.*;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;


@Log4j2
@UtilClass
public class DefaultFileUtils implements FileUtils {

    private static final String SLASH = "/";
    private static final String PDF_EXTENSION = ".pdf";
    private static final String DATE_TIME_PATTERN = "dd-MM-yyyy_HH-mm-ss";
    private static final String BACK_SLASH = "\\";
    private static final String ALREADY_SENT_SUFIX = "_sent_";
    private static final String JSON_EXTENSION = ".json";
    private static final String COOKIES_FILE_NAME = "cookies";
    private static final String HTML_EXTENSION = ".html";
    private static final String XML_EXTENSION = ".xml";

    @Value("${sbb.sap.marketing.docstore.folder.search.level}")
    private String defaultMaxDeepSearch;
    @Value("${sbb.sap.marketing.docstore.pdf.file.name.prefix:SapMarketing}")
    private String defaultPdfFileName;
    @Value("${sbb.sap.marketing.docstore.pdf.resource.folder}")
    private String PDF_RESOURCE_FOLDER;
    @Value("${sbb.sap.marketing.docstore.pdf.resource.folder.windows}")
    private String PDF_RESOURCE_FOLDER_WINDOWS;
    @Value("${sbb.sap.marketing.docstore.pdf.resource.folder}")
    private String RESOURCE_FOLDER;
    @Value("${sbb.sap.marketing.docstore.fonts.resource.folder}")
    private String FONT_FOLDER;

    @Override
    public File savePdfFile(@NonNull final String fileName, @NonNull final ByteArrayOutputStream outputStream) {
        if (outputStream.size() == 0) {
            log.error("[FileUtil] [{}] Save a file as not been successful, output stream is empty.",
                    LoggingUtil.getLogTimeStamp());
            return null;
        }

        final String pdfFileName = StringUtils.isBlank(fileName) ? defaultPdfFileName : fileName + StringUtils.SPACE + new Date().toInstant();

        try {
            final File file = createNewPdfFile(pdfFileName);
            if (file == null) {
                log.error("[FileUtil] [{}] Save PDF File, File with file name {} is null.",
                        LoggingUtil.getLogTimeStamp(), pdfFileName);
                return null;
            }
            final FileOutputStream fileOutputStream = new FileOutputStream(file);
            outputStream.writeTo(fileOutputStream);
            fileOutputStream.close();
            return file;
        } catch (IOException ioe) {
            log.error("[FileUtil] [{}] Save a PDF file {} as not been successful, exception {} ",
                    LoggingUtil.getLogTimeStamp(), pdfFileName, ioe);
        } catch (Exception ioe) {
            log.error("[FileUtil] [{}] Save a PDF file {} as not been successful, exception {}",
                    LoggingUtil.getLogTimeStamp(), pdfFileName, ioe);
        }
        return null;
    }

    @Override
    public File saveFile(@NonNull final String fileName, @NonNull final String fileExtension, @NonNull final String text) {
        try {
            final File file = getFile(fileName, fileExtension);
            if (file == null) {
                log.error("[FileUtil] [{}] File with file name {} is null.",
                        LoggingUtil.getLogTimeStamp(), fileName);
                return null;
            }

            final FileOutputStream fileOutputStream = new FileOutputStream(file);
            final DataOutputStream outStream = new DataOutputStream(new BufferedOutputStream(fileOutputStream));
            outStream.writeUTF(text);
            outStream.close();

            return file;
        } catch (IOException ioe) {
            log.error("[FileUtil] [{}] Save file as not been successful, exception {} ",
                    LoggingUtil.getLogTimeStamp(), fileName, ioe);
        } catch (Exception ioe) {
            log.error("[FileUtil] [{}] Save file as not been successful, exception {}",
                    LoggingUtil.getLogTimeStamp(), fileName, ioe);
        }
        return null;
    }

    @Override
    public File savePdfFile(@NonNull final String fileName, @NonNull final String text) {
        try {
            final File file = createNewPdfFile(fileName);
            if (file == null) {
                log.error("[FileUtil] [{}] File with file name {} is null.",
                        LoggingUtil.getLogTimeStamp(), fileName);
                return null;
            }

            final FileOutputStream outputStream = new FileOutputStream(file);
            final DataOutputStream outStream = new DataOutputStream(new BufferedOutputStream(outputStream));
            outStream.writeUTF(text);

            outStream.close();
            outputStream.close();

            return file;
        } catch (IOException ioe) {
            log.error("[FileUtil] [{}] Save a file {} as not been successful, exception {} ",
                    LoggingUtil.getLogTimeStamp(), fileName, ioe);
        } catch (Exception ioe) {
            log.error("[FileUtil] [{}] Save a file {} as not been successful, exception {}",
                    LoggingUtil.getLogTimeStamp(), fileName, ioe);
        }

        return null;
    }

    @Override
    public File createNewPdfFile(@NonNull final String fileName) {
        if (StringUtils.isEmpty(fileName)) {
            log.error("[FileUtil] [{}] File name is null or empty.",
                    LoggingUtil.getLogTimeStamp());
            return null;
        }

        try {
            final File file = createNewResourceFile(fileName, PDF_EXTENSION);
            if (file == null) {
                log.error("[FileUtil] [{}] File is null.",
                        LoggingUtil.getLogTimeStamp());
                return null;
            }
            return createNewFile(file);
        } catch (IOException ioException) {
            log.error("[FileUtil] [{}] IOException during file creation {}.",
                    LoggingUtil.getLogTimeStamp(), fileName, ioException);
        } catch (Exception exception) {
            log.error("[FileUtil] [{}] Exception during file creation {}",
                    LoggingUtil.getLogTimeStamp(), fileName, exception);
        }

        return null;
    }

    @Override
    public void writeToFile(@NonNull final String content, @NonNull final String outputFilePath) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(outputFilePath))) {
            writer.write(content);
        } catch (IOException e) {
            log.error("[FileUtil] [{}] Error while writing content to file: {}. Exception has ocurred: {}",
                    LoggingUtil.getLogTimeStamp(), outputFilePath, e.getMessage());
            return;
        }
        log.debug("[FileUtil] [{}] Content has been written to file: {}",
                LoggingUtil.getLogTimeStamp(), outputFilePath);
    }

    @Override
    public void writeToFile(@NonNull final String content, @NonNull final File outputFile) {
        log.debug("[FileUtil] [{}] Writing content to file: {}",
                LoggingUtil.getLogTimeStamp(), outputFile.getPath());
        writeToFile(content, outputFile.getPath());
    }

    @Override
    public File getFile(@NonNull final String fileName, @NonNull final String fileNameExtension) {
        if (StringUtils.isEmpty(fileName) || StringUtils.isEmpty(fileNameExtension)) {
            log.error("[FileUtil] [{}] File name is null or empty.", LoggingUtil.getLogTimeStamp());
            return null;
        }

        try {
            final File file = createNewResourceFile(fileName, fileNameExtension);
            if (file == null) {
                log.error("[FileUtil] [{}] File is null.", LoggingUtil.getLogTimeStamp());
                return null;
            }
            return createNewFile(file);
        } catch (IOException ioException) {
            log.error("[FileUtil] [{}] IOException during getting a File {}.{} {}",
                    LoggingUtil.getLogTimeStamp(), fileName, fileNameExtension, ioException.getMessage());
        } catch (Exception exception) {
            log.error("[FileUtil] [{}] Exception during getting a File {}.{} {}",
                    LoggingUtil.getLogTimeStamp(), fileName, fileNameExtension, exception.getMessage());
        }

        return null;
    }

    @Override
    public List<File> getFilesWithExtension(@NonNull final String folderPath, @NonNull final String fileExtension) {
        try {
            final Path resourceFolder = Paths.get(folderPath).normalize();
            final File folder = resourceFolder.toFile();
            if (org.apache.commons.io.FileUtils.isEmptyDirectory(folder)) {
                log.debug("[Sap Marketing DocStore] [FileUtil] [{}] Folder {} is empty, no files with extension {}",
                        LoggingUtil.getLogTimeStamp(), folder, fileExtension);
                return new ArrayList<>();
            }

            final List<File> allFiles = getAllFiles(resourceFolder);
            if (CollectionUtils.isEmpty(allFiles)) {
                log.warn("[Sap Marketing DocStore] [FileUtil] [{}] No files are found in the folder: {}",
                        LoggingUtil.getLogTimeStamp(), resourceFolder.toString());
                return new ArrayList<>();
            }
            log.debug("[Sap Marketing DocStore] [FileUtil] [{}] Found {} files in the folder {}",
                    LoggingUtil.getLogTimeStamp(), CollectionUtils.size(allFiles), folderPath);

            final List<File> pdfFiles = allFiles.stream()
                    .filter(file -> StringUtils.endsWith(file.getName(), fileExtension)
                            && !StringUtils.containsIgnoreCase(file.getName(), ALREADY_SENT_SUFIX))
                    .toList();

            if (CollectionUtils.isEmpty(pdfFiles)) {
                log.debug("[Sap Marketing DocStore] [FileUtil] [{}] No files are found in the folder {} with extension {}",
                        LoggingUtil.getLogTimeStamp(), folderPath, fileExtension);
                return new ArrayList<>();
            }

            log.info("[Sap Marketing DocStore] [FileUtil] [{}] Found {} files in the folder {} with extension {}",
                    LoggingUtil.getLogTimeStamp(), CollectionUtils.size(pdfFiles), folderPath, fileExtension);
            return pdfFiles;
        } catch (NoSuchFileException exception) {
            log.error("[Sap Marketing DocStore] [FileUtil] [{}] Can't file a Files from a folder {}, NoSuchFileException has occurred {}",
                    LoggingUtil.getLogTimeStamp(), folderPath, exception);
        } catch (ArrayIndexOutOfBoundsException exception) {
            log.error("[Sap Marketing DocStore] [FileUtil] [{}] Can't file a Files from a folder {}, ArrayIndexOutOfBoundsException has occurred {}",
                    LoggingUtil.getLogTimeStamp(), folderPath, exception);
        } catch (Exception exception) {
            log.error("[Sap Marketing DocStore] [FileUtil] [{}] Getting Files from a folder {}, Exception has occurred {}",
                    LoggingUtil.getLogTimeStamp(), folderPath, exception);
        }

        log.debug("[Sap Marketing DocStore] [FileUtil] [{}] No files found in the folder {}",
                LoggingUtil.getLogTimeStamp(), folderPath);
        return new ArrayList<>();
    }

    private List<File> getAllFiles(final Path folder) {
        final List<File> files = new ArrayList<>();

        try (Stream<Path> pathStream = Files.list(folder)) {
            pathStream.forEach(path -> {
                if (!Files.isDirectory(path)) {
                    files.add(path.toFile());
                }
            });
        } catch (IOException ex) {
            log.error("[Sap Marketing DocStore] [FileUtil] [{}] Error reading directory. IOException: {}",
                    LoggingUtil.getLogTimeStamp(), ex.getMessage(), ex);
        } catch (Exception ex) {
            log.error("[Sap Marketing DocStore] [FileUtil] [{}] Error reading directory. Exception: {}",
                    LoggingUtil.getLogTimeStamp(), ex.getMessage(), ex);
        }

        return files;
    }

    private Path getFolder(final Path startingFolder, final String folderName) {
        try {
            return Files.walkFileTree(startingFolder, new SimpleFileVisitor<>() {
                @Override
                public FileVisitResult preVisitDirectory(@NonNull Path folderPath, BasicFileAttributes attributes) {
                    if (folderPath.getFileName().toString().equals(folderName)) {
                        log.debug("[Sap Marketing DocStore] [FileUtil] [{}] Found folder: {}",
                                LoggingUtil.getLogTimeStamp(), folderPath.toAbsolutePath());
                        return FileVisitResult.TERMINATE;
                    }
                    return FileVisitResult.CONTINUE;
                }
            });
        } catch (IOException exception) {
            log.error("[Sap Marketing DocStore] [FileUtil] [{}] Error searching for the folder: {}, starting from the folder {}.",
                    LoggingUtil.getLogTimeStamp(), folderName, startingFolder, exception);
        }
        return null;
    }

    @Override
    public void renameFile(final File pdfFile, final String newFileName) {
        try {
            final String newFilePath = StringUtils.replace(pdfFile.getPath(), pdfFile.getName(), newFileName);
            final File newFile = new File(newFilePath);

            if (pdfFile.renameTo(newFile)) {
                log.debug("[Sap Marketing DocStore] File {} renamed successfully to {}", pdfFile.getName(), newFile.getName());
            } else {
                log.error("[Sap Marketing DocStore] File {} cannot be renamed to {}", pdfFile.getName(), newFile.getName());
            }
        } catch (Exception exception) {
            log.error("[FileUtil] [{}] Renaming a file {}, Exception has occured {}",
                    LoggingUtil.getLogTimeStamp(), pdfFile.getName(), exception.getMessage());
        }
    }

    @Override
    public File getPropertiesFile(@NonNull final String resourceFileName) {
        if (StringUtils.endsWithAny(resourceFileName, ".properties", ".yaml")) {
            ClassLoader classLoader = getClass().getClassLoader();
            return new File(classLoader.getResource(resourceFileName).getFile());
        }
        return null;
    }

    @Override
    public File getResourceFile(@NonNull final String resourceFileName) {
        try {
            String filePath = findSpecificFilesInWholeSystem(resourceFileName);
            return getFile(filePath);
        } catch (Exception exception) {
            log.error("[FileUtil] [{}] Getting Resource File {}, Exception has occured {}",
                    LoggingUtil.getLogTimeStamp(), resourceFileName, exception.getMessage());
        }
        return null;
    }

    @Override
    public File getFontFile(@NonNull final String fontName) {
        if (!StringUtils.endsWithAny(fontName, ".ttf")) {
            return null;
        }

        return getFile(fontName);
    }

    @Override
    public File getFile(final String fileName) {
        if (StringUtils.isEmpty(fileName)) {
            log.debug("[Sap Marketing DocStore] [FileUtil] [{}] File can't be found, filename is null or empty.",
                    LoggingUtil.getLogTimeStamp());
            return null;
        }

        try {
            final Path filePath = getFilePath(fileName);
            if (filePath != null && StringUtils.isNoneEmpty(filePath.toString())) {
                Files.readAllLines(filePath);

                log.debug("[Sap Marketing DocStore] [FileUtil] [{}] File {} found in the system.",
                        LoggingUtil.getLogTimeStamp(), fileName);
                final File newFile = createNewFile(filePath.toString());
                log.debug("[Sap Marketing DocStore] [FileUtil] [{}] New File {} has been created.",
                        LoggingUtil.getLogTimeStamp(), newFile.getName());

                boolean readable = Files.isReadable(filePath);
                if (BooleanUtils.isFalse(readable)) {
                    log.error("[Sap Marketing DocStore] [FileUtil] [{}] File {} is not readable.",
                            LoggingUtil.getLogTimeStamp(), fileName);
                }

                boolean isWritable = Files.isWritable(filePath);
                if (BooleanUtils.isFalse(isWritable)) {
                    log.error("[Sap Marketing DocStore] [FileUtil] [{}] File {} is not writable.",
                            LoggingUtil.getLogTimeStamp(), fileName);
                }

                return newFile;
            }
        } catch (AccessDeniedException e) {
            log.error("[Sap Marketing DocStore] [FileUtil] [{}] Getting a File {}, AccessDeniedException has occurred {}",
                    LoggingUtil.getLogTimeStamp(), fileName, e.getMessage());
        } catch (IOException e) {
            log.error("[Sap Marketing DocStore] [FileUtil] [{}] Getting a File {}, IOException has occurred {}",
                    LoggingUtil.getLogTimeStamp(), fileName, e.getMessage());
        } catch (Exception exception) {
            log.error("[Sap Marketing DocStore] [FileUtil] [{}] Getting a File {}, Exception has occurred {}",
                    LoggingUtil.getLogTimeStamp(), fileName, exception.getMessage());
        }

        log.debug("[Sap Marketing DocStore] [FileUtil] [{}] File [{}] has not been found in the system.",
                LoggingUtil.getLogTimeStamp(), fileName);
        return null;
    }

    @Override
    public File getFileBasedOnPath(String filePath) {
        return null;
    }

    @Override
    public List<String> getResourceFileContent(String fileName) {
        try {
            return Files.readAllLines(Paths.get(fileName).normalize());
        } catch (IOException ioException) {
            log.error("[Sap Marketing DocStore] [FileUtil] [{}] Getting Resource File Content read File {}, IOException has occured {}",
                    LoggingUtil.getLogTimeStamp(), fileName, ioException.getMessage());
        } catch (Exception exception) {
            log.error("[Sap Marketing DocStore] [FileUtil] [{}] Getting Resource File Content read File {}, Exception has occured {}",
                    LoggingUtil.getLogTimeStamp(), fileName, exception.getMessage());
        }

        return new ArrayList<>();
    }

    private File createNewFile(@NonNull final File file) throws IOException {
        try {
            if (file.createNewFile()) {
                log.debug("[FileUtil] [{}] New PDF File '{}' has been created.",
                        LoggingUtil.getLogTimeStamp(), file.getName());
            } else {
                log.debug("[FileUtil] [{}] PDF File '{}' already exists.",
                        LoggingUtil.getLogTimeStamp(), file.getName());
            }
        } catch (Exception exception) {
            log.error("[FileUtil] [{}] New PDF File '{}' on location {} has not been created. Exception {}",
                    LoggingUtil.getLogTimeStamp(), file.getName(), file.getPath(), exception.getMessage());
        }
        return file;
    }

    @Override
    public File createNewResourceFile(@NonNull final String fileName, @NonNull final String fileExtension) {
        try {
            final String filePath = getResourceFolderPath();
            final Path path = Paths.get(filePath + File.separator + fileName + fileExtension).normalize();
            return new File(path.toString());
        } catch (Exception exception) {
            log.error("[Sap Marketing DocStore] [FileUtil] [{}] New PDF File '{}.{}' cannot been created. Exception {}",
                    LoggingUtil.getLogTimeStamp(), fileName, fileExtension, exception.getMessage());
        }

        return null;
    }

    @Override
    public File getJsonFile(@NonNull final String jsonFileName) {
        try {
            final String fullFilePath = getJsonFilepath(jsonFileName).toString();
            return new File(fullFilePath);
        } catch (Exception exception) {
            log.error("[FileUtil] [{}] New PDF File '{}' cannot been created. Exception {}",
                    LoggingUtil.getLogTimeStamp(), jsonFileName, exception.getMessage());
        }

        return null;
    }

    @Override
    public Path getJsonFilepath(@NonNull String jsonFileName) {
        try {
            final String resourceFolder = getResourceFolderPath();
            final Path resourcePath = Optional.ofNullable(Paths.get(resourceFolder + File.separator + jsonFileName))
                    .map(Path::normalize)
                    .orElse(null);
            return Optional.ofNullable(resourcePath)
                    .map(Path::toString)
                    .filter(path -> StringUtils.endsWithIgnoreCase(path, JSON_EXTENSION))
                    .map(path -> Paths.get(path).normalize())
                    .orElse(null);
        } catch (Exception exception) {
            log.error("[FileUtil] [{}] JSON File '{}' path cannot been found. Exception {}",
                    LoggingUtil.getLogTimeStamp(), jsonFileName, exception.getMessage());
        }

        return null;
    }

    @Override
    public Path getHtmlFilepath(@NonNull String htmlFileName) {
        try {
            final String resourceFolder = getResourceFolderPath();
            final Path resourcePath = Paths.get(resourceFolder + File.separator + htmlFileName).normalize();
            return Optional.of(resourcePath.toString())
                    .filter(path -> StringUtils.endsWithIgnoreCase(path, HTML_EXTENSION))
                    .map(path -> Paths.get(path).normalize())
                    .orElse(null);
        } catch (Exception exception) {
            log.error("[FileUtil] [{}] HTML File '{}' path cannot been found. Exception {}",
                    LoggingUtil.getLogTimeStamp(), htmlFileName, exception.getMessage());
        }

        return null;
    }

    @Override
    public File createNewFile(@NonNull final String name, @NonNull final String extension, final String content) {
        try {
            final String cleanFileName = getCleanFileName(name);
            final File file = createNewResourceFile(cleanFileName, extension);
            writeToFile(content, file);
            return file;
        } catch (Exception exception) {
            log.error("[FileUtil] [{}] New File '{}.{}' with content cannot been created. Exception {}",
                    LoggingUtil.getLogTimeStamp(), name, extension, exception.getMessage());
        }
        return null;
    }

    @Override
    public String getCleanFileName(final String inputFileName) {
        if (StringUtils.endsWithAny(inputFileName, PDF_EXTENSION, JSON_EXTENSION, HTML_EXTENSION, XML_EXTENSION)) {
            return StringUtils.substringBefore(inputFileName, ".");
        }

        log.debug("[FileUtil] [{}] Filename {} is already clean.",
                LoggingUtil.getLogTimeStamp(), inputFileName);
        return inputFileName;
    }

    @Override
    public File createNewFile(@NonNull final String filePath) {
        try {
            return new File(filePath);
        } catch (Exception exception) {
            log.error("[FileUtil] [{}] New File '{}' cannot be created. Exception {}",
                    LoggingUtil.getLogTimeStamp(), filePath, exception.getMessage());
        }
        return null;
    }

    @Override
    public File createNewJsonFile(@NonNull final String documentFileName, @NonNull final String json) {
        try {
            if (StringUtils.isEmpty(json)) {
                log.error("[Sap Marketing DocStore] Document JSON content can't be empty!");
                return null;
            }

            final File inputJSONFile = createNewFile(documentFileName, JSON_EXTENSION, json);
            if (inputJSONFile == null) {
                log.error("[Sap Marketing DocStore] [FileUtil] [{}] it is not possible to create a new JSON input File {}",
                        LoggingUtil.getLogTimeStamp(), documentFileName);
                return null;
            }

            log.debug("[Sap Marketing DocStore] [FileUtil] [{}] New document file with JSON content has been created: {}",
                    LoggingUtil.getLogTimeStamp(), inputJSONFile.getName());
            return inputJSONFile;
        } catch (final Exception e) {
            log.error("[Sap Marketing DocStore] [FileUtil] [{}] Error while creating a new document file with JSON content: {}",
                    LoggingUtil.getLogTimeStamp(), e.getMessage(), e);
            return null;
        }
    }

    @Override
    public Path getFilePath(@NonNull final String fileName) {
        try {
            final Path path = findSpecificFilePathInWholeSystem(fileName);
            if (StringUtils.isNoneEmpty(path.toString())) {
                log.debug("[Sap Marketing DocStore] [FileUtil] [{}] File {} found in the system.",
                        LoggingUtil.getLogTimeStamp(), fileName);
                return path;
            }

        } catch (AccessDeniedException ioException) {
            log.error("[Sap Marketing DocStore] [FileUtil] [{}] File [{}] has been not found in the system. AccessDeniedException has occurred: {}",
                    LoggingUtil.getLogTimeStamp(), fileName, ioException.getMessage(), ioException);
        } catch (IOException exception) {
            log.error("[Sap Marketing DocStore] [FileUtil] [{}] File [{}] has been not found in the system. IOException has occurred: {}",
                    LoggingUtil.getLogTimeStamp(), fileName, exception.getMessage(), exception);
        } catch (Exception exception) {
            if (StringUtils.equalsAnyIgnoreCase(fileName, "cookies")) {
                log.debug("[Sap Marketing DocStore] [FileUtil] [{}] File {} is a cookies file. It does not have a path.",
                        LoggingUtil.getLogTimeStamp(), fileName);
                return null;
            }

            log.error("[Sap Marketing DocStore] [FileUtil] [{}] File [{}] has been not found in the system. Exception has occurred: {}",
                    LoggingUtil.getLogTimeStamp(), fileName, exception.getMessage());
        }

        log.debug("[Sap Marketing DocStore] [FileUtil] [{}] File [{}] file system path has been not found in the system.",
                LoggingUtil.getLogTimeStamp(), fileName);
        return null;
    }

    @Override
    public String getStringFilePath(@NonNull final String fileName) {
        if (StringUtils.isEmpty(fileName)) {
            log.error("[Sap Marketing DocStore] [FileUtil] [{}] Filename is null or empty.",
                    LoggingUtil.getLogTimeStamp());
            return StringUtils.EMPTY;
        }

        try {
            final Path path = findSpecificFilePathInWholeSystem(fileName);
            if (path != null) {
                log.debug("[Sap Marketing DocStore] [FileUtil] [{}] A file path {} has been found in the system.",
                        LoggingUtil.getLogTimeStamp(), fileName);
                return path.toString();
            }

            final String filePath = getResourceFolderPath();
            if (StringUtils.isEmpty(filePath)) {
                log.error("[Sap Marketing DocStore] [FileUtil] [{}] A file path is empty. Please check your file name or FOLDER_SEARCH_LEVEL parameter.",
                        LoggingUtil.getLogTimeStamp());
                return StringUtils.EMPTY;
            }

            log.debug("[Sap Marketing DocStore] [FileUtil] [{}] A file path {} has been found in the system.",
                    LoggingUtil.getLogTimeStamp(), filePath);
            return filePath;
        } catch (IOException ioException) {
            log.error("[Sap Marketing DocStore] [FileUtil] [{}] IOException {}",
                    LoggingUtil.getLogTimeStamp(), ioException.getMessage());
        } catch (Exception exception) {
            log.error("[Sap Marketing DocStore] [FileUtil] [{}] Exception {}",
                    LoggingUtil.getLogTimeStamp(), exception.getMessage());
        }

        return StringUtils.EMPTY;
    }

    private Path findSpecificFilePathInWholeSystem(final String fileName) throws IOException {
        final String input = findSpecificFilesInWholeSystem(fileName);
        final String[] output = input.split(StringUtils.SPACE);
        return Paths.get(output[0]).normalize();
    }

    private String findSpecificFilesInWholeSystem(final String fileName) throws IOException {
        final Path start = Paths.get(StringUtils.EMPTY).normalize();
        try (Stream<Path> stream = Files.find(start, Integer.parseInt(defaultMaxDeepSearch), (path, attr) -> String.valueOf(path).endsWith(fileName))) {
            return stream.sorted().map(String::valueOf).collect(Collectors.joining(StringUtils.SPACE));
        }
    }

    @Override
    public String getResourceFolderPath() {
        final StringBuilder filePath = new StringBuilder();

        final String projectPath = getUsersProjectRootDirectory();
        if (StringUtils.isBlank(projectPath)) {
            log.error("[Sap Marketing DocStore] [FileUtil] [{}] Get PDF folder, a project path is null or empty.",
                    LoggingUtil.getLogTimeStamp());
        }

        final String resourceFolderPath = getResourceFolderPath(projectPath, filePath);
        if (StringUtils.isEmpty(resourceFolderPath)) {
            log.warn("[Sap Marketing DocStore] [FileUtil] [{}] A resource folder path is empty.",
                    LoggingUtil.getLogTimeStamp());
            return StringUtils.EMPTY;
        }

        final Path path = Paths.get(resourceFolderPath).normalize();
        final String pathString = path.toString();

        if (!isPathExists(pathString)) {
            log.error("[Sap Marketing DocStore] [FileUtil] [{}] a resource folder path {} doesn't exist.",
                    LoggingUtil.getLogTimeStamp(), resourceFolderPath);
            return StringUtils.EMPTY;
        }

        if (!isReadable(pathString)) {
            log.error("[Sap Marketing DocStore] [FileUtil] [{}] a resource folder path {} is not readable.",
                    LoggingUtil.getLogTimeStamp(), resourceFolderPath);
            return StringUtils.EMPTY;
        }

        if (!isWritable(pathString)) {
            log.error("[Sap Marketing DocStore] [FileUtil] [{}] a resource folder path {} is not writable.",
                    LoggingUtil.getLogTimeStamp(), resourceFolderPath);
            return StringUtils.EMPTY;
        }

        return pathString;
    }

    private String getFullPath(@NonNull final String pathString) {
        final StringBuilder fullFilePath = new StringBuilder(pathString);
        if (!StringUtils.endsWith(pathString, File.separator)) {
            fullFilePath.append(File.separator);
        }

        log.debug("[Sap Marketing DocStore] [FileUtil] [{}] Resource folder path {}",
                LoggingUtil.getLogTimeStamp(), fullFilePath);
        return fullFilePath.toString();
    }

    private String getResourceFolderPath(String projectPath, StringBuilder filePath) {
        if (StringUtils.contains(projectPath, SLASH)) {
            filePath.append(projectPath).append(SLASH);
            filePath.append(PDF_RESOURCE_FOLDER).append(SLASH);
            log.debug("[Sap Marketing DocStore] [FileUtil] [{}] Linux PDF folder path: {}",
                    LoggingUtil.getLogTimeStamp(), filePath.toString());
            return getPath(filePath);
        }

        if (StringUtils.contains(projectPath, BACK_SLASH)) {
            filePath.append(projectPath).append(BACK_SLASH);
            filePath.append(PDF_RESOURCE_FOLDER_WINDOWS).append(BACK_SLASH);
            log.debug("[Sap Marketing DocStore] [FileUtil] [{}] Windows PDF folder path: {}",
                    LoggingUtil.getLogTimeStamp(), filePath.toString());
            return getPath(filePath);
        }

        return StringUtils.EMPTY;
    }

    private String getPath(@NonNull final StringBuilder filePath) {
        return Paths.get(filePath.toString()).normalize().toString();
    }

    public boolean isPathExists(final String folderPath) {
        final Path path = Paths.get(folderPath).normalize();
        return Files.exists(path);
    }

    public boolean isReadable(final String folderPath) {
        final Path path = Paths.get(folderPath).normalize();
        return Files.isReadable(path);
    }

    public boolean isWritable(final String folderPath) {
        final Path path = Paths.get(folderPath);
        return Files.isWritable(path);
    }

    @Override
    public String getFontFolderPathString() {
        final StringBuilder filePath = new StringBuilder();

        final String projectPath = getUsersProjectRootDirectory();
        if (StringUtils.isBlank(projectPath)) {
            log.error("[FileUtil] [{}] Get Font folder, a project path is null or empty.", LoggingUtil.getLogTimeStamp());
        }

        filePath.append(projectPath);
        if (StringUtils.contains(projectPath, SLASH)) {
            return getUnixFilePath(filePath);
        }

        if (StringUtils.contains(projectPath, BACK_SLASH)) {
            return getWindowsFilePath(filePath);
        }

        return StringUtils.EMPTY;
    }

    @Override
    public File createNewImageFile(@NonNull final String imageName) {
        final StringBuilder filePath = new StringBuilder();

        final String projectPath = getUsersProjectRootDirectory();
        if (StringUtils.isBlank(projectPath)) {
            log.error("[FileUtil] [{}] A project path is null or empty.",
                    LoggingUtil.getLogTimeStamp());
        }

        filePath.append(projectPath);
        filePath.append(RESOURCE_FOLDER).append(SLASH);
        filePath.append(imageName);
        final String filePathString = filePath.toString();
        return createNewFile(filePathString);
    }

    @Override
    public File getOrCreateFile(@NonNull final String fileName) {
        final File file = getFile(fileName);
        if (file != null && file.exists()) {
            log.debug("[Sap Marketing DocStore] [FileUtil] [{}] File {} found in the system.",
                    LoggingUtil.getLogTimeStamp(), fileName);
            return file;
        }

        final String filePathString = getFilePathString(fileName);
        log.debug("[Sap Marketing DocStore] [FileUtil] [{}] File {} not found in the system. Creating a new file.",
                LoggingUtil.getLogTimeStamp(), fileName);
        return createNewFile(filePathString);
    }

    @Override
    public File getOrCreateResourceFile(@NonNull final String fileName, final String extension) {
        final File file = getFile(fileName);
        if (file != null && file.exists()) {
            log.debug("[Sap Marketing DocStore] File {}.{} found in the system.", fileName, extension);
            return file;
        }

        return createNewResourceFile(fileName, extension);
    }

    private String getFilePathString(@NonNull final String fileName) {
        final StringBuilder filePath = new StringBuilder();

        final String projectPath = getUsersProjectRootDirectory();
        if (StringUtils.isBlank(projectPath)) {
            log.error("[FileUtil] [{}] A project path is null or empty.",
                    LoggingUtil.getLogTimeStamp());
        }

        filePath.append(projectPath).append(SLASH);
        filePath.append(RESOURCE_FOLDER).append(SLASH);
        filePath.append(fileName);
        return filePath.toString();
    }

    private String getUnixFilePath(@NonNull final StringBuilder filePath) {
        if (!StringUtils.startsWith(FONT_FOLDER, SLASH)) {
            filePath.append(SLASH);
        }

        filePath.append(FONT_FOLDER).append(SLASH);
        return filePath.toString();
    }

    private String getWindowsFilePath(@NonNull final StringBuilder filePath) {
        if (!StringUtils.startsWith(FONT_FOLDER, BACK_SLASH)) {
            filePath.append(BACK_SLASH);
        }

        filePath.append(FONT_FOLDER).append(BACK_SLASH);
        return filePath.toString();
    }

    private String getUsersProjectRootDirectory() {
        String envRootDir = System.getProperty("user.dir");
        Path rootDir = Paths.get(".").normalize().toAbsolutePath();
        if (rootDir.startsWith(envRootDir)) {
            return rootDir.toString();
        } else {
            log.error("[Sap Marketing DocStore] Root dir is not found in the user directory.");
        }

        return Paths.get("src/main/resources").toString();
    }

    @Override
    public boolean isPdfDFile(final String fileName, final File file) {
        if (file == null) {
            return false;
        }

        if (StringUtils.isBlank(fileName)) {
            return false;
        }

        if (!StringUtils.containsIgnoreCase(fileName, ".pdf")) {
            return false;
        }

        if (!StringUtils.containsIgnoreCase(file.getName(), ".pdf")) {
            return false;
        }

        log.info("[Sap Marketing DocStore] [FileUtil] [{}] File {} is a PDF file.",
                LoggingUtil.getLogTimeStamp(), fileName);
        return true;
    }

    @Override
    public boolean isPdfDFile(final File file) {
        if (file == null) {
            return false;
        }

        final String fileName = file.getName();
        return isPdfDFile(fileName, file);
    }

    @Override
    public boolean isReadyForProcessing(final File file) {
        if (file == null) {
            log.debug("[Sap Marketing DocStore] [FileUtil] [{}] File is null, cannot check if it is ready for processing.",
                    LoggingUtil.getLogTimeStamp());
            return false;
        }

        final String fileName = file.getName();
        if (BooleanUtils.isFalse(file.isFile())) {
            log.debug("[Sap Marketing DocStore] [FileUtil] [{}] File {} is not a regular file, cannot check if it is ready for processing.",
                    LoggingUtil.getLogTimeStamp(), fileName);
            return false;
        }

        if (BooleanUtils.isFalse(file.exists())) {
            log.debug("[Sap Marketing DocStore] [FileUtil] [{}] File {} does not exist, cannot check if it is ready for processing.",
                    LoggingUtil.getLogTimeStamp(), fileName);
            return false;
        }

        if (BooleanUtils.isFalse(file.canRead())) {
            log.debug("[Sap Marketing DocStore] [FileUtil] [{}] File {} is not readable, cannot check if it is ready for processing.",
                    LoggingUtil.getLogTimeStamp(), fileName);
            return false;
        }

        return true;
    }
}
