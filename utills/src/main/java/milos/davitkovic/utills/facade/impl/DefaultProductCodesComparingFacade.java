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
    public void compare2Files(final String folderName, final String fileName1, final String fileName2) throws IOException {
        productCodesComparingService.compare2Files(folderName, fileName1, fileName2);
    }
}
