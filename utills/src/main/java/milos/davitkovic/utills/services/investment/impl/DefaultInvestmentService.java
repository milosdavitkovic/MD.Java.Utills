package milos.davitkovic.utills.services.investment.impl;

import milos.davitkovic.utills.services.investment.InvestmentService;
import org.springframework.stereotype.Service;

@Service
public class DefaultInvestmentService implements InvestmentService {

    @Override
    public double getNetMonthlyOperatingIncome(int grossMonthlyIncome, int propertyMonthlyExpenses) {
        return grossMonthlyIncome - propertyMonthlyExpenses;
    }

    @Override
    public double getNetAnnualOperationIncome(int grossMonthlyIncome, int propertyMonthlyExpenses) {
        final int netMonthlyOperationIncome = grossMonthlyIncome - propertyMonthlyExpenses;
        return netMonthlyOperationIncome * 12;
    }

    @Override
    public double getThresholdValue(int annualNetIncome) {
        return annualNetIncome * 18;
    }

    @Override
    public double getThresholdValue(int grossMonthlyIncome, int propertyMonthlyExpenses) {
        final double netAnnualOperationIncome = getNetAnnualOperationIncome(grossMonthlyIncome, propertyMonthlyExpenses);
        return netAnnualOperationIncome * 18;
    }

    @Override
    public double getMonthlyCashFlow(int monthlyNetIncome, int monthlyMortgagePayment) {
        return monthlyNetIncome - monthlyMortgagePayment;
    }

    @Override
    public double getMonthlyCashFlow(int grossMonthlyIncome, int propertyMonthlyExpenses, int monthlyMortgagePayment) {
        final double netMonthlyOperatingIncome = getNetMonthlyOperatingIncome(grossMonthlyIncome, propertyMonthlyExpenses);
        return netMonthlyOperatingIncome - monthlyMortgagePayment;
    }

    @Override
    public double getAnnualCashFlow(int grossMonthlyIncome, int propertyMonthlyExpenses, int monthlyMortgagePayment) {
        final double monthlyCashFlow = getMonthlyCashFlow(grossMonthlyIncome, propertyMonthlyExpenses, monthlyMortgagePayment);
        return monthlyCashFlow * 12;
    }

    @Override
    public double getCashOnCashReturn(int annualCashFlow, int downPayment) {
        return annualCashFlow / downPayment;
    }

    @Override
    public double getCashOnCashReturn(int grossMonthlyIncome, int propertyMonthlyExpenses, int monthlyMortgagePayment, int downPayment) {
        final double annualCashFlow = getAnnualCashFlow(grossMonthlyIncome, propertyMonthlyExpenses, monthlyMortgagePayment);
        return annualCashFlow / downPayment;
    }

    @Override
    public double getCapitalizationRate(int annualNetIncome, int propertyValue) {
        return (annualNetIncome / propertyValue) * 100;
    }

    @Override
    public double getCapitalizationRate(int grossMonthlyIncome, int propertyMonthlyExpenses, int propertyValue) {
        final double netAnnualOperationIncome = getNetAnnualOperationIncome(grossMonthlyIncome, propertyMonthlyExpenses);
        return (netAnnualOperationIncome / propertyValue) * 100;
    }

    @Override
    public double getInvestmentReturnEstimation(int annualNetIncome, int propertyPrice) {
        return propertyPrice / annualNetIncome;
    }

    @Override
    public double getInvestmentReturnEstimation(int grossMonthlyIncome, int propertyMonthlyExpenses, int propertyPrice) {
        final double netAnnualOperationIncome = getNetAnnualOperationIncome(grossMonthlyIncome, propertyMonthlyExpenses);
        return propertyPrice / netAnnualOperationIncome;
    }
}
