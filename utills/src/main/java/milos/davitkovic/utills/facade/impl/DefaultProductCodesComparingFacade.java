package milos.davitkovic.utills.facade.impl;

import milos.davitkovic.utills.facade.ProductCodesComparingFacade;
import milos.davitkovic.utills.services.ProductCodesComparingService;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.io.IOException;

@Component
public class DefaultProductCodesComparingFacade implements ProductCodesComparingFacade {

    @Resource
    private ProductCodesComparingService productCodesComparingService;

    @Override
    public void compare2Files(final String folderName, final String sourceFileName1, final String sourceFileName2, final String resultFileName) throws IOException {
        productCodesComparingService.compare2Files(folderName, sourceFileName1, sourceFileName2, resultFileName);
    }
}
