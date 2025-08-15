package milos.davitkovic.javautil.utills.controllers;

import milos.davitkovic.javautil.utills.facade.ItemTypesFacade;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import jakarta.annotation.Resource;
import java.io.IOException;

@RestController
@RequestMapping(value = "itemTypes/")
public class ItemTypesController {

    @Autowired
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

    @PostMapping(value = "/createUpdateImpex")
    @ResponseStatus(value = HttpStatus.OK)
    public void createUpdateImpex(
            @RequestParam final String inputFileName,
            @RequestParam final String folderName,
            @RequestParam final String resultFileName,
            @RequestParam final String header,
            @RequestParam final String additionalParameter
    ) throws IOException {
        itemTypesFacade.createUpdateImpexWithPKs(folderName, inputFileName, resultFileName, header, additionalParameter);
    }
}
