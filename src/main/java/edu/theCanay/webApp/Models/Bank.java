package edu.theCanay.webApp.Models;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class Bank {

    private int id;

    //Annotations on variables are the rules of their validation

    @NotEmpty(message = "Name should not be empty")
    @Size(min = 2, max = 30, message = "Name should be between 2 and 30 characters")
    private String name;

    @Min(value = 1, message = "Interest rate should be greater than 0")
    private int interestRate;

    @Min(value = 1, message = "Maximum loan should be greater than 0")
    private int maximumLoan;

    @Min(value = 1, message = "Minimum down payment should be greater than 0")
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
