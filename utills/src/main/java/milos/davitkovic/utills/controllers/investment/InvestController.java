package milos.davitkovic.utills.controllers.investment;

import milos.davitkovic.utills.facade.dto.investment.GarageInvestDTO;
import milos.davitkovic.utills.facade.investment.InvestmentFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "invest/")
public class InvestController {

    @Autowired
    private InvestmentFacade investmentFacade;

    @GetMapping(value = "/garage")
    @ResponseStatus(value = HttpStatus.OK)
    public GarageInvestDTO getGarageAnalyze(@RequestParam int propertyPrice,
                                            @RequestParam boolean electricCharger,
                                            @RequestParam int grossMonthlyIncome,
                                            @RequestParam int propertyMonthlyExpenses,
                                            @RequestParam int monthlyMortgagePayment,
                                            @RequestParam int downPayment) {
        return investmentFacade.getGarageAnalyze(propertyPrice, electricCharger, grossMonthlyIncome, propertyMonthlyExpenses, monthlyMortgagePayment, downPayment);
    }
}
