package milos.davitkovic.utills.services.impl.utils.File;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.tomcat.util.http.fileupload.FileUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.nio.charset.StandardCharsets.UTF_8;
import static java.nio.file.StandardOpenOption.APPEND;
import static java.nio.file.StandardOpenOption.CREATE;

/**
 * 
 * @author Milos Davitkovic
 *
 */
@Service
@Slf4j
public class FileIOUtils {

	private static final int DEFAULT_MAX_DEPTH = 30;
	private static final Path START_PATH = Paths.get(StringUtils.EMPTY);

	/**
	 * 
	 * @return
	 * @throws IOException
	 */
	public String listFilesInDefaultFolder() throws IOException {
		try (Stream<Path> stream = Files.list(Paths.get(""))) {
			return stream
					.map(String::valueOf)
					.filter(path -> !path.startsWith("."))
					.sorted()
					.collect(Collectors.joining(" "));
		}
	}

	/**
	 * 
	 * @param folderName
	 * @return
	 * @throws IOException
	 */
	public String listFilesInSpecificFolder(final String folderName) throws IOException {
		try (Stream<Path> stream = Files.list(Paths.get(folderName))) {
			return stream
					.map(String::valueOf)
					.filter(path -> !path.startsWith("."))
					.sorted()
					.collect(Collectors.joining(" "));
		}
	}

	/**
	 * Find List of Paths of founded files.
	 *
	 * @param fileName
	 * @return List of Paths of founded files
	 *
	 * @throws IOException
	 */
	public List<Path> findFilesInWholeSystem(final String fileName) throws IOException {
		final Stream<Path> stream = Files.find(START_PATH, DEFAULT_MAX_DEPTH, (path, attr) -> String.valueOf(path).endsWith(fileName));
		return stream
				.sorted()
				.collect(Collectors.toList());
	}

	public List<Path> findFilesInWholeSystem(final String fileName, final String folderName) throws IOException {
		final Path folderPath = Paths.get(folderName);
		final Stream<Path> stream = Files.find(folderPath, DEFAULT_MAX_DEPTH, (path, attr) -> String.valueOf(path).endsWith(fileName));
		return stream
				.sorted()
				.collect(Collectors.toList());
	}

	/**
	 * Return String representation of List, of Files with specified name
	 *
	 * @param fileName
	 * @return
	 * @throws IOException
	 */
	public String findSpecificFilesInWholeSystem(final String fileName) throws IOException {
		final Stream<Path> stream = Files.find(START_PATH, DEFAULT_MAX_DEPTH, (path, attr) -> String.valueOf(path).endsWith(fileName));
		return stream
				.sorted()
				.map(String::valueOf)
				.collect(Collectors.joining(StringUtils.EMPTY));
	}

	public String[] findSpecificFilesArrayInWholeSystem(String fileName) throws IOException {
		Path start = Paths.get("");
		int maxDepth = DEFAULT_MAX_DEPTH;
		try (Stream<Path> stream = Files.find(start, maxDepth, (path, attr) ->
		String.valueOf(path).endsWith(fileName))) {
			String joined = stream
					.sorted()
					.map(String::valueOf)
					.collect(Collectors.joining(" "));
			System.out.println("Found: " + joined);
			return joined.split(" ");
		}
	}

	public List<Path> findSpecificFilesInWholeSystemBefore(String folderName, String fileExtension, long unixTime) throws IOException {
		//		long cut = LocalDateTime.now().minusWeeks(1).toEpochSecond(ZoneOffset.UTC);
		long cut = unixTime;
		List<Path> outputFiles = new ArrayList<>();
		List<Path> filteredFiles = new ArrayList<>();
		//		Path path = findFolderPath("CSV");
		Path path = findFolderPath(folderName);
		Files.list(path)
		.filter(n -> {
			try {
				return Files.getLastModifiedTime(n)
						.to(TimeUnit.SECONDS) < cut;
			} catch (IOException ex) {
				//handle exception
				return false;
			}
		})
		.filter(n -> String.valueOf(n).endsWith(fileExtension)
				)
		.forEach(n -> outputFiles.add(n));
		System.out.println("List of Paths: " + outputFiles);
		System.out.println("Number: " + outputFiles.size());
		return outputFiles;
	}

	public String[] findFolder(String folderName) throws IOException {
		Path start = Paths.get("");
		int maxDepth = DEFAULT_MAX_DEPTH;
		try (Stream<Path> stream = Files.find(start, maxDepth, (path, attr) ->
		String.valueOf(path).endsWith(folderName))) {
			String joined = stream
					.sorted()
					.map(String::valueOf)
					.collect(Collectors.joining(" "));
			if (!joined.isEmpty()) {
				System.out.println("Folder: " + joined + " has found");
			}
			else {
				System.out.println("Folder: " + folderName + " doesn't exist!");
			}
			return joined.split(" ");
		}
	}

	/**
	 * 
	 * @param folderName
	 * @return Path of specified folder
	 * @throws IOException
	 */
	public Path findFolderPath(String folderName) throws IOException {
		final String[] inputList = findFolder(folderName);
		final Path outputPath = Paths.get(inputList[0]);
		return outputPath;
	}

	public Path findFilePath(String fileName) throws IOException {
		final Path outputPath = findSpecificFilePathInWholeSystem(fileName, 0);
		return outputPath;
	}

	/**
	 * 
	 * @param folderName
	 * @return 
	 * @throws IOException
	 */
	public String findFolderPathStr(String folderName) throws IOException {
		String[] inputList = findFolder(folderName);
		String outputPath = inputList[0];
		return outputPath;
	}



	public String findSpecificFileInWholeSystem(String fileName, Integer ordinalNumberOfItem) throws IOException {
		//		System.out.println("findSpecificFileInWholeSystem ");
		String input = findSpecificFilesInWholeSystem(fileName);
		//		System.out.println("Input: " + input);
		String[] output = input.split(" ");
		//		System.out.println("Output: " + output);
		return output[ordinalNumberOfItem];
	}

	/**
	 * Find List of Specified Files in Whole System and select one specified from the list
	 * @param fileName
	 * @param ordinalNumberOfItem
	 * @return Path of desirable file
	 * @throws IOException
	 */
	public Path findSpecificFilePathInWholeSystem(String fileName, Integer ordinalNumberOfItem) throws IOException {
		Path fileNamePath = null;
		String input = findSpecificFilesInWholeSystem(fileName);
		String[] output = input.split(" ");
		fileNamePath = Paths.get(output[ordinalNumberOfItem]);
		return fileNamePath;
	}

	public Path findSpecificFilePathInSpecificFolder(String fileName, String folderName, Integer ordinalNumberOfItem) throws IOException {
		Path fileNamePath = null;
		String input = findSpecificFilesInSpecificFolder(fileName, folderName);
		String[] output = input.split(" ");
		fileNamePath = Paths.get(output[ordinalNumberOfItem]);
		return fileNamePath;
	}

	public String findSpecificFileInSpecificFolder(String fileName, String folderName) throws IOException {
		String fileNamePath = null;
		String input = findSpecificFilesInSpecificFolder(fileName, folderName);
		String[] output = input.split(" ");
		fileNamePath = output[0];
		return fileNamePath;
	}

	/**
	 * Function find all files in specified folder and subfolders (30 is max depth) and filter specified file names in string as space as delimiter between.
	 * @param fileName
	 * @param folderName
	 * @return String of founded files which is collection of items separated by space
	 * @throws IOException
	 */
	public String findSpecificFilesInSpecificFolder(String fileName, String folderName) throws IOException {
		//		Path start = Paths.get(folderName);
		//		int maxDepth = 30;
		//		try (Stream<Path> stream = Files.find(start, maxDepth, (path, attr) ->
		//		String.valueOf(path).endsWith(fileName))) {
		//			String joined = stream
		//					.sorted()
		//					.map(String::valueOf)
		//					.collect(Collectors.joining(" "));
		//			System.out.println("Found: " + joined);
		//			return findSpecificFilessInSpecificFolder(fileName, folderName, " ");
		//		}
		return findSpecificFilessInSpecificFolder(fileName, folderName, " ");
	}

	/**
	 *  
	 * @param fileName
	 * @param folderName
	 * @param delimiter
	 * @return String of founded files which is collection of items separated by specified delimiter
	 * @throws IOException
	 */
	public String findSpecificFilessInSpecificFolder(String fileName, String folderName, String delimiter) throws IOException {
		final Path start = Paths.get(folderName);
		int maxDepth = DEFAULT_MAX_DEPTH;
		try (Stream<Path> stream = Files.find(start, maxDepth, (path, attr) ->
		String.valueOf(path).endsWith(fileName))) {
			String joined = stream
					.sorted()
					.map(String::valueOf)
					.collect(Collectors.joining(delimiter));
			System.out.println("Found: " + joined);
			return joined;
		}
	}

	/**
	 * Return String of founded files which is collection of items separated by specified delimiter
	 * @param fileName
	 * @param folderName
	 * @return
	 * @throws IOException
	 */
	public String findSpecificFilesInSpecificFolder(String fileName, String folderName, String delimiter) throws IOException {
		Path start = Paths.get(folderName);
		int maxDepth = DEFAULT_MAX_DEPTH;
		try (Stream<Path> stream = Files.find(start, maxDepth, (path, attr) ->
		String.valueOf(path).endsWith(fileName))) {
			String joined = stream
					.sorted()
					.map(String::valueOf)
					.collect(Collectors.joining(delimiter));
			System.out.println("Found: " + joined);
			return joined;
		}
	}

	/**
	 * Return String of founded files which is collection of items separated by space
	 * @param fileName
	 * @param folderName
	 * @param maximalDepth
	 * @return
	 * @throws IOException
	 */
	public String findSpecificFilesInSpecificFolder(String fileName, String folderName, Integer maximalDepth) throws IOException {
		Path start = Paths.get(folderName);
		int maxDepth = maximalDepth;
		try (Stream<Path> stream = Files.find(start, maxDepth, (path, attr) ->
		String.valueOf(path).endsWith(fileName))) {
			String joined = stream
					.sorted()
					.map(String::valueOf)
					.collect(Collectors.joining(" "));
			System.out.println("Found: " + joined);
			return joined;
		}
	}

	/**
	 * 
	 * @param fileName
	 * @return List of String with all lines of specified file
	 * @throws IOException
	 */
	public List<String> readAllLinesOfFile(String fileName) throws IOException {
		List<String> outputLines = Files.readAllLines(findSpecificFilePathInWholeSystem(fileName, 0));
		return outputLines;
	}

	/**
	 * 
	 * @param fileName
	 * @return String with whole content of specified file
	 * @throws IOException
	 */
	public String readResourceFile(String fileName) throws IOException {
		String outputString = "";
		List<String> outputLines = Files.readAllLines(findSpecificFilePathInWholeSystem(fileName, 0));
		for (String it : outputLines) {
			outputString += it;
		}
		//		System.out.println("File Content: " + outputString);
		return outputString;
	}

	/**
	 * Write specified inputText into specified fileName if thats file exist, 
	 * if that isn't a case, create new file with that file name in root and write into it
	 * @param fileName
	 * @param inputText
	 * @throws IOException
	 */
	public void writeInFile(String fileName, String inputText) throws IOException {
		String listOfFiles = findSpecificFilesInWholeSystem(fileName);
		System.out.println("listOfFiles: " + listOfFiles);
		List<String> lines = new ArrayList<String>();
		lines.add(inputText);
		if (listOfFiles.isEmpty()) {
			try {   // this is for monitoring runtime Exception within the block 

				String content = inputText; // content to write into the file

				File file = new  File(fileName); // here file not created here

				// if file doesnt exists, then create it
				if (!file.exists()) {   // checks whether the file is Exist or not
					file.createNewFile();   // here if file not exist new file created 
				}

				FileWriter fw = new FileWriter(file.getAbsoluteFile(), true); // creating fileWriter object with the file
				BufferedWriter bw = new BufferedWriter(fw); // creating bufferWriter which is used to write the content into the file
				bw.write(content); // write method is used to write the given content into the file
				bw.close(); // Closes the stream, flushing it first. Once the stream has been closed, further write() or flush() invocations will cause an IOException to be thrown. Closing a previously closed stream has no effect. 

				System.out.println("Writing has Done");

			} catch (IOException e) { // if any exception occurs it will catch
				e.printStackTrace();
			}
		}else {
			Path outputFile = findSpecificFilePathInWholeSystem(fileName, 0);
			Files.write(outputFile, lines, UTF_8, APPEND, CREATE);
		}
		System.out.println("Text put in file: " + fileName);
	}

	/**
	 * Write specified inputText into specified fileName in specific folder if thats file exist, 
	 * if that isn't a case, create new file with that file name in root and write into it
	 * @param fileName
	 * @param folderName
	 * @param inputText
	 * @throws IOException
	 */
	public void writeInFile(String fileName, String folderName, String inputText) throws IOException {
		//		String listOfFiles = findSpecificFilePathInSpecificFolder(fileName, folderName, 0);
		//		String filderPath = "./" + folderName + "/";
		//		Path filderPath = findFolderPath(folderName);
		List<String> lines = new ArrayList<String>();
		lines.add(inputText);

		System.out.println("Current directory" +  System.getProperty("user.dir"));
		File theDir = new File(folderName);
		// if the directory does not exist, create it
		if (!theDir.exists()) {
			System.out.println("creating directory: " + folderName);
			boolean result = false;

			try{
				theDir.mkdir();
				result = true;
			} 
			catch(SecurityException se){
				//handle it
			}        
			if(result) {    
				System.out.println("DIR created");  
			}
		}
		File outputFile = new File (theDir, fileName);
		System.out.println(outputFile.toString());
		//		FileWriter writeOutputFile = new FileWriter(outputFile, true);
		//		System.out.println(writeOutputFile);
		//		String filePath = "\\" + theDir.toPath() + "\\" + fileName;

		Files.write(outputFile.toPath(), lines, UTF_8, APPEND, CREATE);

		//		try {
		//			String filePath = findSpecificFileInSpecificFolder(fileName, folderName);
		//			System.out.println("filePath: " + filePath);
		//			if (filePath.isEmpty()) {
		//				String folderPath = findFolderPathStr(folderName);
		//				Path outputFilePath = Paths.get(folderPath);
		//				System.out.println("folderPath: " + folderPath);
		//				Files.write(outputFilePath, lines, UTF_8, APPEND, CREATE);
		//			}else {
		//				Path outputFilePath = Paths.get(filePath);
		//				System.out.println("filePath: " + filePath);
		//				Files.write(outputFilePath, lines, UTF_8, APPEND, CREATE);
		//			}
		//		}catch (Exception e) {
		//			// TODO: handle exception
		//			Files.createDirectories(Paths.get("/" + folderName));
		//			String folderPath = findFolderPathStr(folderName);
		//			Path outputFilePath = Paths.get(folderPath);
		//			System.out.println("folderPath: " + folderPath);
		//			Files.write(outputFilePath, lines, UTF_8, APPEND, CREATE);
		//		}
		//		

		//		String outputFile = filderPath.toString() + fileName;
		//		System.out.println("outputFile: " + outputFile);
		//		Files.write(filderPath, lines, UTF_8, APPEND, CREATE);
		//		
		//		
		//		if (listOfFiles.isEmpty()) {
		//			try {   // this is for monitoring runtime Exception within the block 
		//
		//		        String content = inputText; // content to write into the file
		//
		//		        File file = new File(fileName); // here file not created here
		//
		//		        // if file doesnt exists, then create it
		//		        if (!file.exists()) {   // checks whether the file is Exist or not
		//		            file.createNewFile();   // here if file not exist new file created 
		//		        }
		//
		//		        FileWriter fw = new FileWriter(file.getAbsoluteFile(), true); // creating fileWriter object with the file
		//		        BufferedWriter bw = new BufferedWriter(fw); // creating bufferWriter which is used to write the content into the file
		//		        bw.write(content); // write method is used to write the given content into the file
		//		        bw.close(); // Closes the stream, flushing it first. Once the stream has been closed, further write() or flush() invocations will cause an IOException to be thrown. Closing a previously closed stream has no effect. 
		//
		//		        System.out.println("Writing has Done");
		//
		//		    } catch (IOException e) { // if any exception occurs it will catch
		//		        e.printStackTrace();
		//		    }
		//		}else {
		//			Path outputFile = findSpecificFilePathInWholeSystem(fileName, 0);
		//			Files.write(outputFile, lines, UTF_8, APPEND, CREATE);
		//		}
		//		System.out.println("Text put in file: " + fileName);
	}



	/**
	 * 
	 * @param fileName
	 * @param folderName
	 */
	public void deleteFileInSpecificFolder(String fileName, String folderName) {
		System.out.println("This is non-recovery operation!!! ");
		File theDir = new File(folderName);
		if (!theDir.exists()) {
			System.out.println("I can't find directory: " + folderName);
			boolean result = false;
		}else {
			File outputFile = new File (theDir, fileName);
			if (!outputFile.isDirectory()) {
				outputFile.delete();
				System.out.println("I deleted file: " + fileName + " in directory: " + folderName);
			}
			//			System.out.println(outputFile.toString());
			//			Path outputFilePath = outputFile.toPath();	
			//			Arrays.stream(new File(outputFilePath.toString()).listFiles()).forEach(file->file.delete());
		}
	}

	public void deleteFile(Path filePath) {
		System.out.println("This is non-recovery operation!!! ");
		File outputFile = new File (filePath.toString());
		if (!outputFile.isDirectory()) {
			outputFile.delete();
			System.out.println("I deleted file with path: " + filePath);
		}
	}

	/**
	 * 
	 * @param fileName
	 * @param folderName
	 * @throws IOException 
	 */
	public void deleteFileInSpecificFolderWholeSystemSearch(String fileName, String folderName) throws IOException {
		System.out.println("Delete is non-recovery operation!!! ");
		Path dirPath = findFolderPath(folderName);
		System.out.println("dirPath: " + dirPath);
		File theDir = new File(dirPath.toString());
		if (!theDir.exists()) {
			System.out.println("I can't find directory: " + folderName);
			boolean result = false;
		}else {
			File outputFile = new File (theDir, fileName);
			if (!outputFile.isDirectory()) {
				outputFile.delete();
				System.out.println("I deleted file: " + fileName + " in directory: " + folderName);
			}
			//			System.out.println(outputFile.toString());
			//			Path outputFilePath = outputFile.toPath();	
			//			Arrays.stream(new File(outputFilePath.toString()).listFiles()).forEach(file->file.delete());
		}
	}

	/**
	 * This deletes only files from specified folder (sub-directories are untouched)

	 * @param folderName
	 */
	public void deleteAllFilesInSpeceficFolder(String folderName) {
		File theDir = new File(folderName);
		// if the directory does not exist, create it
		if (!theDir.exists()) {
			System.out.println("I can't find directory: " + folderName);
			boolean result = false;
		}else {
			//			File outputFile = new File (theDir, fileName);
			//			System.out.println(outputFile.toString());
			//			Path outputFilePath = outputFile.toPath();	
			Arrays.stream(new File(theDir.toString()).listFiles()).forEach(file->file.delete());
			System.out.println("I deleted directory: " + folderName);
		}
	}


	/**
	 * This deletes specified file from specified folder and sub-directories
	 * @param fileName
	 * @param folderName
	 * @throws IOException 
	 */
	public void deleteAllFilesInSpeceficFolderAndSubFolders(String fileName, String folderName) throws IOException {
		File theDir = new File(folderName);
		// if the directory does not exist, create it
		if (!theDir.exists()) {
			System.out.println("I can't find directory: " + folderName);
			boolean result = false;
		}else {
			//			File outputFile = new File (theDir, fileName);
			//			System.out.println(outputFile.toString());
			Path outputFilePath = theDir.toPath();
			Files.walk(outputFilePath)
			.filter(Files::isRegularFile)
			.map(path->path.toFile())
			.forEach(file->file.delete());
			System.out.println("I deleted directory: " + folderName + " and it's subdirectoryes");
		}
	}

	/**
	 * Java 8
	 * Recursively deletes all sub-folders and files under specified folder
	 * @param fileName
	 * @param folderName
	 * @throws IOException
	 */
	public void deleteAllFilesAndFolderInSpeceficFolderAndSubFolders(String fileName, String folderName) throws IOException {
		File theDir = new File(folderName);
		// if the directory does not exist, create it
		if (!theDir.exists()) {
			System.out.println("I can't find directory: " + folderName);
			boolean result = false;
		}else {
			File outputFile = new File (theDir, fileName);
			System.out.println(outputFile.toString());
			Path outputFilePath = outputFile.toPath();
			FileUtils.cleanDirectory(outputFile);
		}
	}

	/**
	 * Java 7
	 * Java byte streams are used to perform input and output of 8-bit bytes. The most frequently used classes are, FileInputStream and FileOutputStream.
	 * @param inputFileName
	 * @param outputFileName
	 * @throws IOException 
	 */
	public void copyFile1(String inputFileName, String outputFileName) throws IOException {
		FileInputStream in = null;
		FileOutputStream out = null;

		try {
			in = new FileInputStream(inputFileName);
			out = new FileOutputStream(outputFileName);

			int c;
			while ((c = in.read()) != -1) {
				out.write(c);
			}
		}finally {
			if (in != null) {
				in.close();
			}
			if (out != null) {
				out.close();
			}
		}
	}

	/**
	 * Java 7
	 * FileReader reads two bytes at a time and FileWriter writes two bytes at a time.
	 * @param inputFileName
	 * @param outputFileName
	 * @throws IOException 
	 */
	public void copyFile2(String inputFileName, String outputFileName) throws IOException {
		FileReader in = null;
		FileWriter out = null;

		try {
			in = new FileReader(inputFileName);
			out = new FileWriter(outputFileName);

			int c;
			while ((c = in.read()) != -1) {
				out.write(c);
			}
		}finally {
			if (in != null) {
				in.close();
			}
			if (out != null) {
				out.close();
			}
		}
	}

	/**
	 * Java 7
	 * @param path - /tmp/user/java/bin
	 * @param folderName MyFolder 
	 * Complet path to the new folder will be: /tmp/user/java/bin/MyFolder
	 */
	public void createFolder(String path, String folderName) {
		String dirname = path + "/" + folderName;
		File d = new File(dirname);

		// Create directory now.
		d.mkdirs();
	}

	/**
	 * 
	 * @param folderName - "/tmp", "/tmp/test" 
	 * @return
	 */
	public String[] ReadFolder(String folderName) {
		File file = null;
		String[] paths = null;

		try {      
			// create new file object
			file = new File(folderName);

			// array of files and directory
			paths = file.list();

			// for each name in the path array
			for(String path:paths) {
				// prints filename and directory name
				System.out.println(path);
			}
		}catch(Exception e) {
			// if any error occurs
			e.printStackTrace();
		}

		return paths;
	}

	/**
	 * 
	 * @param fileName - country.csv
	 * @param cvsSplitBy - ",", "\t"
	 * @param columnNumber - 1
	 * @return
	 * @throws IOException
	 */
	public List<String> CSVReaderByColumn(String fileName, String cvsSplitBy, Integer columnNumber) throws IOException {

		String csvFile = findFilePath(fileName).toString();
		String line = "";
		List<String> header = new ArrayList<String>();;
		List<String> data = new ArrayList<String>();
		List<String> result = new ArrayList<String>();
		//        String cvsSplitBy = ",";

		try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {

			while ((line = br.readLine()) != null) {

				// use comma as separator
				String[] lines = line.split(cvsSplitBy);
				if (header.isEmpty()) {
					//                	header = new ArrayList<String>(); 
					for (String str : lines) {
						header.add(str);
					}
				}
				else {
					for (String str : lines) {
						data.add(str);
					}
				}


				//                System.out.println("Country [code= " + country[4] + " , name=" + country[5] + "]");

			}

		} catch (IOException e) {
			e.printStackTrace();
		}

		Integer indexOfColumn = columnNumber;
		if (indexOfColumn > header.size() || indexOfColumn < 0) {
			System.out.println("Number of columns is: " + header.size() + " but you wanted: " + indexOfColumn);
		}else {
			for (int i = 0; i < data.size() - header.size(); i += header.size()) {
				result.add(data.get(indexOfColumn));
				indexOfColumn += header.size();
			}
		}
		return result;
	}
	
	public List<String> CSVReaderByColumnName(String fileName, String cvsSplitBy, String columnName) throws IOException {

		String csvFile = findFilePath(fileName).toString();
		String line = "";
		List<String> header = new ArrayList<String>();;
		List<String> data = new ArrayList<String>();
		List<String> result = new ArrayList<String>();
		//        String cvsSplitBy = ",";

		try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {

			while ((line = br.readLine()) != null) {

				// use comma as separator
				String[] lines = line.split(cvsSplitBy);
				if (header.isEmpty()) {
					//                	header = new ArrayList<String>(); 
					for (String str : lines) {
						header.add(str);
					}
				}
				else {
					for (String str : lines) {
						data.add(str);
					}
				}


				//                System.out.println("Country [code= " + country[4] + " , name=" + country[5] + "]");

			}

		} catch (IOException e) {
			e.printStackTrace();
		}

//		Integer indexOfColumn;
//		
//		try {
//			indexOfColumn = header.indexOf(columnName);
//		}
		
		Integer indexOfColumn = header.indexOf(columnName);
		if (indexOfColumn > header.size() || indexOfColumn < 0) {
			System.out.println("Number of columns is: " + header.size() + " but you wanted: " + indexOfColumn);
		}else {
			for (int i = 0; i < data.size() - header.size(); i += header.size()) {
				result.add(data.get(indexOfColumn));
				indexOfColumn += header.size();
			}
		}
		return result;
	}
	
	/**
	 * 
	 * @param fileName
	 * @param cvsSplitBy
	 * @param rowNumber - First row is have zero index, row Number can be: 0, 1, 2...
	 * @return
	 * @throws IOException
	 */
	public List<String> CSVReaderByRow(String fileName, String cvsSplitBy, Integer rowNumber) throws IOException {

		String csvFile = findFilePath(fileName).toString();
		String line = "";
		List<String> header = new ArrayList<String>();;
		List<String> data = new ArrayList<String>();
		List<String> result = new ArrayList<String>();
		//        String cvsSplitBy = ",";

		try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {

			while ((line = br.readLine()) != null) {

				// use comma as separator
				String[] lines = line.split(cvsSplitBy);
				if (header.isEmpty()) {
					//                	header = new ArrayList<String>(); 
					for (String str : lines) {
						header.add(str);
					}
				}
				else {
					for (String str : lines) {
						data.add(str);
					}
				}
			}

		} catch (IOException e) {
			e.printStackTrace();
		}

		Integer indexOfRow = rowNumber * header.size();
		if (rowNumber > data.size() || rowNumber < 0) {
			System.out.println("Number of rows is: " + data.size() + " but you wanted: " + rowNumber);
		}else {
			for (int i = indexOfRow; i < indexOfRow + header.size(); i++) {
				result.add(data.get(i));
			}
		}
		return result;
	}

	/**
	 * 
	 * @param fileName
	 * @param cvsSplitBy
	 * @param wantedString
	 * @return
	 * @throws IOException
	 */
	public Boolean CSVReaderIsStringExist(String fileName, String cvsSplitBy, String wantedString) throws IOException {

		String csvFile = findFilePath(fileName).toString();
		String line = "";
		List<String> header = new ArrayList<String>();;
		//		List<String> data = new ArrayList<String>();
		//		List<String> result = new ArrayList<String>();
		//        String cvsSplitBy = ",";

		try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {

			while ((line = br.readLine()) != null) {

				// use comma as separator
				String[] lines = line.split(cvsSplitBy);
				if (header.isEmpty()) {
					//                	header = new ArrayList<String>(); 
					for (String str : lines) {
						header.add(str);
					}
				}
				else {
					for (String str : lines) {
						if (str.equals(wantedString))
							return true;
						//                		data.add(str);
					}
				}
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
		return false;
	}

	/**
	 *
	 * @param fileName
	 * @return String with whole content of specified file
	 * @throws IOException
	 */
	public List<String> readResourceFile(final String fileName, final String folderName) throws IOException {
		final Path path = getResourceFile(folderName, fileName);
		if(StringUtils.isBlank(path.toString())) {
			return Collections.emptyList();
		}
		log.debug("Resource File for reading, path: " + path.toAbsolutePath());
		return Files.isReadable(path) ? Files.readAllLines(path) : Collections.emptyList();
	}

	public List<String> readFile(final String fileName, final String folderName) throws IOException {
		final Path path = getFile(folderName, fileName);
		if(StringUtils.isBlank(path.toString())) {
			return Collections.emptyList();
		}
		log.debug("File for reading, path: " + path.toAbsolutePath());
		return Files.isReadable(path) ? Files.readAllLines(path) : Collections.emptyList();
	}

	/**
	 *
	 * @param fileName
	 * @param folderName
	 * @param inputText
	 * @throws IOException
	 */
	public void writeInResourceFile(final String fileName, final String folderName, final List<String> inputText) throws IOException {
		final Path path = getResourceFile(folderName, fileName);
		log.debug("Resource File for writing, path: " + path.toAbsolutePath());
		writeInFile(path, inputText);
	}

	/**
	 *
	 * @param filePath
	 * @param inputText
	 * @throws IOException
	 */
	public void writeInFile(final Path filePath, final List<String> inputText) throws IOException {
		if(Files.isWritable(filePath)) {
			Files.write(filePath, inputText, UTF_8, CREATE);
		}
	}

	public void writeInFile(final String fileName, final String folderName, final List<String> inputText) throws IOException {
		final Path path = getFile(folderName, fileName);
		writeInFile(path, inputText);
	}

	/**
	 * Get a specified File from specified Folder of resource
	 *
	 * @param folderName
	 * @param fileName
	 * @return Path of specified File from specified Folder of resource
	 * @throws FileNotFoundException
	 */
	private Path getResourceFile(final String folderName, final String fileName) throws FileNotFoundException {
		try {
			final File file = ResourceUtils.getFile("classpath:" + folderName + "/" + fileName);
			return Paths.get(file.getAbsolutePath());
		} catch (FileNotFoundException e) {
			throw new FileNotFoundException(String.format("Cannot find file [%s] in the folder [%s].", fileName, folderName));
		}
	}

	private Path getFile(final String folderName, final String fileName) throws IOException {
		try {
			final Path currentWorkingDir = Paths.get("").toAbsolutePath();
			final Optional<Path> filePath = findFilesInWholeSystem(fileName, folderName).stream().findFirst();
//			return Paths.get(file.getAbsolutePath());
			return filePath.orElseThrow(() -> new IOException(String.format("Cannot find file [%s] in the folder [%s].", fileName, folderName)));
		} catch (IOException e) {
			throw new IOException(String.format("Cannot find file [%s] in the folder [%s].", fileName, folderName));
		}
	}
}



