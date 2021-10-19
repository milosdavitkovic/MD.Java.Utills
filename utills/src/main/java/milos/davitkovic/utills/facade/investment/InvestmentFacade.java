package milos.davitkovic.utills.facade.investment;

import milos.davitkovic.utills.annotations.Facade;
import milos.davitkovic.utills.annotations.Interface;
import milos.davitkovic.utills.facade.dto.investment.PropertyInvestDTO;
import milos.davitkovic.utills.facade.dto.investment.GarageInvestDTO;

@Interface
public interface InvestmentFacade {

    /**
     * Calculation with buying in cash
     *
     * @param totalPrice price of the property, payed out in cash
     * @return {@link GarageInvestDTO}
     */
    GarageInvestDTO getGarageAnalyze(int propertyPrice, boolean electricCharger, int grossMonthlyIncome, int propertyMonthlyExpenses, int monthlyMortgagePayment, int downPayment);

    PropertyInvestDTO getPropertyAnalyze();
}
