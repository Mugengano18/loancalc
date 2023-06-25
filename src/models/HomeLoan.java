package models;

import models.Borrower;

// Class representing a home loan
public class HomeLoan extends Loan {


    public HomeLoan(double amount, float interestRate, int term, Borrower borrower) {
        super(amount, interestRate, term, borrower);
    }


    @Override
    public int calculateNumberOfPayments() {
        return term * 12;
    }



    @Override
    public double calculateMonthlyInterestRate() {
        return (interestRate / 100) / 12;
    }


    @Override
    public double calculateMonthlyPayment() {
        double monthlyPay = amount * (calculateMonthlyInterestRate() * Math.pow(1 + calculateMonthlyInterestRate(), calculateNumberOfPayments())) / (Math.pow(1 + calculateMonthlyInterestRate(), calculateNumberOfPayments()) - 1);
        return monthlyPay;
    }

    @Override
    public double TotalInterest() {
        double totalInterest = calculateMonthlyPayment() * calculateNumberOfPayments() - amount;
        return totalInterest;
    }



}
