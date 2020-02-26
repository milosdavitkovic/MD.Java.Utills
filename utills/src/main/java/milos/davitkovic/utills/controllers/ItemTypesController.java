package milos.davitkovic.utills.controllers;

import milos.davitkovic.utills.facade.ItemTypesFacade;
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
    @ResponseStatus(value = HttpStatus.ACCEPTED)
    public void productCodesComparing() throws IOException {
        final String fileName1 = "File1.txt";
        final String fileName2 = "File2.txt";
        final String folderName = "files/compare";
        final String resultFileName = "Result.txt";
        itemTypesFacade.compare2Files(folderName, fileName1, fileName2, resultFileName);
    }

    @GetMapping(value = "/uniqueIndexes")
    @ResponseStatus(value = HttpStatus.ACCEPTED)
    public void uniqueIndexes() throws IOException {
        final String inputFileName = "InputFile.txt";
        final String folderName = "files/uniqueIndexes";
        final String resultFileName = "Result.txt";
        itemTypesFacade.getDuplicates(folderName, inputFileName, resultFileName);
    }

    @GetMapping(value = "/createUpdateImpex")
    @ResponseStatus(value = HttpStatus.ACCEPTED)
    public void createUpdateImpex() throws IOException {
        final String inputFileName = "InputFile.txt";
        final String folderName = "files/impex";
        final String resultFileName = "update.impex";
        final String header = "UPDATE ProductReference;pk[unique=true];active";
        itemTypesFacade.createUpdateImpexWithPKs(folderName, inputFileName, resultFileName, header);
    }
}
