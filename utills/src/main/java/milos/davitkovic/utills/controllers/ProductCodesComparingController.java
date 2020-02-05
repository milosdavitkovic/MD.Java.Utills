package milos.davitkovic.utills.controllers;

import milos.davitkovic.utills.facade.ProductCodesComparingFacade;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.io.IOException;

@RestController
@RequestMapping(value = "comparing/")
public class ProductCodesComparingController {

    @Resource
    private ProductCodesComparingFacade productCodesComparingFacade;

    @RequestMapping(value = "/productCodesComparing", method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.ACCEPTED)
    @ResponseBody
    public void productCodesComparing() throws IOException {
        final String fileName1 = "File1";
        final String fileName2 = "File2";
        final String folderName = "src/main/resources/files/compare/";
        productCodesComparingFacade.compare2Files(folderName, fileName1, fileName2);
    }
}
