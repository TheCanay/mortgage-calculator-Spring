package edu.theCanay.webApp.Services;

import edu.theCanay.webApp.Models.Bank;

public class mortgageCalculatorService {
    public static boolean isValuesValid(int initLoan,
                                 int downPayment,
                                 Bank selectedBank) {

        return initLoan <= selectedBank.getMaximumLoan() && downPayment >= selectedBank.getMinimumDownPayment();

    }

    public static double calculateMonthlyMortgagePayment(int amountBorrowed,
                                                         Bank selectedBank) {

        return amountBorrowed * (selectedBank.getInterestRate() /(double) 12) * Math.pow((1 + (selectedBank.getInterestRate() /(double) 12)), selectedBank.getLoanTermMonths())
                /
                Math.pow((1 + (selectedBank.getInterestRate() /(double) 12)), selectedBank.getLoanTermMonths()) - 1;
    }
}
