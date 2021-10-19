package milos.davitkovic.utills.facade.investment.impl;

import milos.davitkovic.utills.annotations.Facade;
import milos.davitkovic.utills.facade.dto.investment.GarageInvestDTO;
import milos.davitkovic.utills.facade.dto.investment.PropertyInvestDTO;
import milos.davitkovic.utills.facade.investment.InvestmentFacade;
import milos.davitkovic.utills.services.impl.utils.Number.NumberUtils;
import milos.davitkovic.utills.services.investment.InvestmentService;
import org.springframework.beans.factory.annotation.Autowired;

@Facade
public class DefaultInvestmentFacade implements InvestmentFacade {

    private static final String CURRENCY_EUR = "EUR";
    private static final String SPACE = " ";

    @Autowired
    private InvestmentService investmentService;
    @Autowired
    private NumberUtils numberUtils;

    @Override
    public GarageInvestDTO getGarageAnalyze(int propertyPrice, boolean electricCharger, int grossMonthlyIncome, int propertyMonthlyExpenses, int monthlyMortgagePayment, int downPayment) {
        final GarageInvestDTO garageInvest = new GarageInvestDTO();

        garageInvest.setElectricCharger(electricCharger);

        setNetMonthlyOperationIncome(grossMonthlyIncome, propertyMonthlyExpenses, garageInvest);
        setThresholdValue(grossMonthlyIncome, propertyMonthlyExpenses, garageInvest);
        setCashFlow(grossMonthlyIncome, propertyMonthlyExpenses, monthlyMortgagePayment, garageInvest);
        setCashOnCashReturn(grossMonthlyIncome, propertyMonthlyExpenses, monthlyMortgagePayment, downPayment, garageInvest);
        setCapitalisationRate(propertyPrice, grossMonthlyIncome, propertyMonthlyExpenses, garageInvest);
        setInvestmentReturnEstimation(propertyPrice, grossMonthlyIncome, propertyMonthlyExpenses, garageInvest);

        return garageInvest;
    }

    private void setInvestmentReturnEstimation(int propertyPrice, int grossMonthlyIncome, int propertyMonthlyExpenses, GarageInvestDTO garageInvest) {
        final double investmentReturnEstimationValue = investmentService.getInvestmentReturnEstimation(grossMonthlyIncome, propertyMonthlyExpenses, propertyPrice);
        garageInvest.setInvestmentReturnEstimationValue(investmentReturnEstimationValue);

        garageInvest.setInvestmentReturnEstimation(investmentReturnEstimationValue + SPACE + CURRENCY_EUR);

        garageInvest.setInvestmentReturnEstimationPositive(investmentReturnEstimationValue <= 8);
    }

    private void setCapitalisationRate(int propertyPrice, int grossMonthlyIncome, int propertyMonthlyExpenses, GarageInvestDTO garageInvest) {
        final double capitalizationRateValue = investmentService.getCapitalizationRate(grossMonthlyIncome, propertyMonthlyExpenses, propertyPrice);
        garageInvest.setCapitalizationRateValue(capitalizationRateValue);

        final String capitalizationRate = numberUtils.getAmount(capitalizationRateValue);
        garageInvest.setCapitalizationRate(capitalizationRate + SPACE + CURRENCY_EUR);

        garageInvest.setCapitalizationRatePositive(capitalizationRateValue >= 8);
    }

    private void setCashOnCashReturn(int grossMonthlyIncome, int propertyMonthlyExpenses, int monthlyMortgagePayment, int downPayment, GarageInvestDTO garageInvest) {
        final double cashOnCashReturnValue = investmentService.getCashOnCashReturn(grossMonthlyIncome, propertyMonthlyExpenses, monthlyMortgagePayment, downPayment);
        garageInvest.setCashOnCashReturnValue(cashOnCashReturnValue);

        final String cashOnCashReturn = numberUtils.getAmount(cashOnCashReturnValue);
        garageInvest.setCashOnCashReturn(cashOnCashReturn + SPACE + CURRENCY_EUR);

        garageInvest.setCashOnCashReturnPositive(cashOnCashReturnValue >= 10);
    }

    private void setCashFlow(int grossMonthlyIncome, int propertyMonthlyExpenses, int monthlyMortgagePayment, GarageInvestDTO garageInvest) {
        final double cashFlowValue = investmentService.getMonthlyCashFlow(grossMonthlyIncome, propertyMonthlyExpenses, monthlyMortgagePayment);
        garageInvest.setCashFlowValue(cashFlowValue);

        final String roundOnLess = numberUtils.getAmount(cashFlowValue);
        garageInvest.setCashFlow(roundOnLess + SPACE + CURRENCY_EUR);

        garageInvest.setCashFlowPositive(cashFlowValue > 0);
    }

    private void setThresholdValue(int grossMonthlyIncome, int propertyMonthlyExpenses, GarageInvestDTO garageInvest) {
        final double thresholdValue = investmentService.getThresholdValue(grossMonthlyIncome, propertyMonthlyExpenses);
        garageInvest.setThresholdValue(thresholdValue);

        final String threshold = numberUtils.getAmount(thresholdValue);
        garageInvest.setThreshold(threshold + SPACE + CURRENCY_EUR);
    }

    private void setNetMonthlyOperationIncome(int grossMonthlyIncome, int propertyMonthlyExpenses, GarageInvestDTO garageInvest) {
        final double netMonthlyOperatingIncome = investmentService.getNetMonthlyOperatingIncome(grossMonthlyIncome, propertyMonthlyExpenses);
        garageInvest.setNetOperatingIncomeValue(netMonthlyOperatingIncome);

        final String netMonthlyOpIncome = numberUtils.getAmount(netMonthlyOperatingIncome);
        garageInvest.setNetOperatingIncome(netMonthlyOpIncome + SPACE + CURRENCY_EUR);
    }

    @Override
    public PropertyInvestDTO getPropertyAnalyze() {
        return null;
    }
}
