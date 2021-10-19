package milos.davitkovic.utills.services.investment;

import milos.davitkovic.utills.annotations.Interface;

@Interface
public interface InvestmentService {

    double getNetMonthlyOperatingIncome(int grossMonthlyIncome, int propertyMonthlyExpenses);

    double getNetAnnualOperationIncome(int grossMonthlyIncome, int propertyMonthlyExpenses);

    double getThresholdValue(int grossMonthlyIncome, int propertyMonthlyExpenses);

    double getThresholdValue(int annualNetIncome);

    double getMonthlyCashFlow(int monthlyNetIncome, int monthlyMortgagePayment);

    double getMonthlyCashFlow(int grossMonthlyIncome, int propertyMonthlyExpenses, int monthlyMortgagePayment);

    double getAnnualCashFlow(int grossMonthlyIncome, int propertyMonthlyExpenses, int monthlyMortgagePayment);

    double getCashOnCashReturn(int annualCashFlow, int downPayment);

    double getCashOnCashReturn(int grossMonthlyIncome, int propertyMonthlyExpenses, int monthlyMortgagePayment, int downPayment);

    double getCapitalizationRate(int annualNetIncome, int propertyValue);

    double getCapitalizationRate(int grossMonthlyIncome, int propertyMonthlyExpenses, int propertyValue);

    double getInvestmentReturnEstimation(int annualNetIncome, int propertyValue);

    double getInvestmentReturnEstimation(int grossMonthlyIncome, int propertyMonthlyExpenses, int propertyPrice);
}
