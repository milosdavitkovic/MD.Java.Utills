package milos.davitkovic.javautil.utills.facade.impl;

import lombok.extern.slf4j.Slf4j;
import milos.davitkovic.javautil.utills.annotations.Facade;
import milos.davitkovic.javautil.utills.facade.ItemTypesFacade;
import milos.davitkovic.javautil.utills.services.impex.ImpexFilesCreationService;
import milos.davitkovic.javautil.utills.services.ProductCodesComparingService;
import milos.davitkovic.javautil.utills.services.UniqueIndexesService;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;


@Slf4j
@Facade
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
