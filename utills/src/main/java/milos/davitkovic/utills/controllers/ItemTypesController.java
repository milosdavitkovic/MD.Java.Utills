package milos.davitkovic.utills.controllers;

import milos.davitkovic.utills.facade.ItemTypesFacade;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.io.IOException;

@RestController
@RequestMapping(value = "itemTypes/")
public class ItemTypesController {

    @Resource
    private ItemTypesFacade itemTypesFacade;

    @GetMapping(value = "/productCodesComparing")
    @ResponseStatus(value = HttpStatus.OK)
    public void productCodesComparing() throws IOException {
        final String fileName1 = "File1.txt";
        final String fileName2 = "File2.txt";
        final String folderName = "files/compare";
        final String resultFileName = "Result.txt";
        itemTypesFacade.compare2Files(folderName, fileName1, fileName2, resultFileName);
    }

    @GetMapping(value = "/uniqueIndexes")
    @ResponseStatus(value = HttpStatus.OK)
    public void uniqueIndexes() throws IOException {
        final String inputFileName = "InputFile.txt";
        final String folderName = "files/uniqueIndexes";
        final String resultFileName = "Result.txt";
        itemTypesFacade.getDuplicates(folderName, inputFileName, resultFileName);
    }

    @GetMapping(value = "/createUpdateImpex")
    @ResponseStatus(value = HttpStatus.OK)
    public void createUpdateImpex() throws IOException {
        final String inputFileName = "InputFile.txt";
        final String folderName = "files/impex";
        final String resultFileName = "update.impex";
//        final String header = "UPDATE NewsletterProspect;pk[unique=true];emailStatus(code)";
//        final String header = "UPDATE PriceRow;pk[unique = true];basePriceUnit";
//        final String header = "REMOVE NearestPointOfService; pk[unique = true]";
//        final String header = "UPDATE Product; pk[unique = true];deleted";
        final String header = "UPDATE Commission; pk[unique = true];deleted";
//        final String header = "UPDATE ObjectNumber; pk[unique = true];deleted";
//        final String header = "UPDATE ProductReference;pk[unique=true];active";
//        final String header = "UPDATE NewsletterProspect;pk[unique=true];domain";
//        final String additionalParameter = "at";
//        final String additionalParameter = "SENT_OK";
//        final String additionalParameter = "kg";
        final String additionalParameter = "true";
        itemTypesFacade.createUpdateImpexWithPKs(folderName, inputFileName, resultFileName, header, additionalParameter);
    }

    @GetMapping(value = "/createCleanLogs")
    @ResponseStatus(value = HttpStatus.OK)
    public void createCleanLogs() {
        final String inputFileName = "inputLogs.txt";
        final String folderName = "files/logging/checkout";
        final String resultFileName = "ClearLogs.txt";
        final String keyMessage = "ClearLogs.txt";
        itemTypesFacade.createClearLogsFile(folderName, inputFileName, resultFileName, keyMessage);
    }
}
