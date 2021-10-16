package edu.theCanay.webApp.Models;

public class Bank {

    private int id;
    private String name;
    private int interestRate;
    private int maximumLoan;
    private int minimumDownPayment;
    private int loanTermMonths;

    public Bank(int id, String name, int interestRate, int maximumLoan, int minimumDownPayment) {
        //Name, interest Rate, maximum Loan, minimum Down Payment
        this.id = id;
        this.name = name;
        this.interestRate = interestRate;
        this.maximumLoan = maximumLoan;
        this.minimumDownPayment = minimumDownPayment;
        this.loanTermMonths = 48;
    }

    public Bank() {
        this.loanTermMonths = 48;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(int interestRate) {
        this.interestRate = interestRate;
    }

    public int getMaximumLoan() {
        return maximumLoan;
    }

    public void setMaximumLoan(int maximumLoan) {
        this.maximumLoan = maximumLoan;
    }

    public int getMinimumDownPayment() {
        return minimumDownPayment;
    }

    public void setMinimumDownPayment(int minimumDownPayment) {
        this.minimumDownPayment = minimumDownPayment;
    }

    public int getLoanTermMonths() {
        return loanTermMonths;
    }
}
