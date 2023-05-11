package milos.davitkovic.utills.facade.impl;

import lombok.extern.slf4j.Slf4j;
import milos.davitkovic.utills.annotations.Facade;
import milos.davitkovic.utills.facade.ItemTypesFacade;
import milos.davitkovic.utills.services.impex.ImpexFilesCreationService;
import milos.davitkovic.utills.services.ProductCodesComparingService;
import milos.davitkovic.utills.services.UniqueIndexesService;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;


@Facade
@Slf4j
public class DefaultItemTypesFacade implements ItemTypesFacade {

    @Autowired
    private ProductCodesComparingService productCodesComparingService;
    @Autowired
    private UniqueIndexesService uniqueIndexesService;
    @Autowired
    private ImpexFilesCreationService impexFilesCreationService;

    @Override
    public void compare2Files(final String folderName, final String sourceFileName1, final String sourceFileName2, final String resultFileName) throws IOException {
        productCodesComparingService.compare2Files(folderName, sourceFileName1, sourceFileName2, resultFileName);
    }

    @Override
    public void getDuplicates(final String folderName, final String sourceFileName, final String resultFileName) throws IOException {
        uniqueIndexesService.getDuplicates(folderName, sourceFileName, resultFileName);
    }

    @Override
    public void createUpdateImpexWithPKs(final String folderName, final String sourceFileName, final String resultFileName, final String header) throws IOException {
        impexFilesCreationService.createUpdateImpexWithPKs(folderName, sourceFileName, resultFileName, header);
    }

    @Override
    public void createUpdateImpexWithPKs(final String folderName, final String sourceFileName, final String resultFileName, final String header, final String lineAddition) throws IOException {
        impexFilesCreationService.createUpdateImpexWithPKs(folderName, sourceFileName, resultFileName, header, lineAddition);
    }
}
