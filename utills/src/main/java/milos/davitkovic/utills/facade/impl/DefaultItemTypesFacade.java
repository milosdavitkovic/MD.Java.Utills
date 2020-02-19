package milos.davitkovic.utills.facade.impl;

import milos.davitkovic.utills.annotations.Facade;
import milos.davitkovic.utills.facade.ItemTypesFacade;
import milos.davitkovic.utills.services.ProductCodesComparingService;
import milos.davitkovic.utills.services.UniqueIndexesService;

import javax.annotation.Resource;
import java.io.IOException;

@Facade
public class DefaultItemTypesFacade implements ItemTypesFacade {

    @Resource
    private ProductCodesComparingService productCodesComparingService;
    @Resource
    private UniqueIndexesService uniqueIndexesService;

    @Override
    public void compare2Files(final String folderName, final String sourceFileName1, final String sourceFileName2, final String resultFileName) throws IOException {
        productCodesComparingService.compare2Files(folderName, sourceFileName1, sourceFileName2, resultFileName);
    }

    @Override
    public void getDuplicates(final String folderName, final String sourceFileName, final String resultFileName) throws IOException {
        uniqueIndexesService.getDuplicates(folderName, sourceFileName, resultFileName);
    }
}
