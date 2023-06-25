package models;

import com.google.gson.Gson;
import interfaces.LoanCalculator;

import java.text.DecimalFormat;
import java.text.NumberFormat;

// Abstract class representing a loan
public abstract class Loan implements LoanCalculator {
    protected double amount;
    protected float interestRate;
    protected int term;
    protected Borrower borrower;

    public Loan(double amount, float interestRate, int term, Borrower borrower) {
        this.amount = amount;
        this.interestRate = interestRate;
        this.term = term;
        this.borrower = borrower;
    }

    // Abstract method to calculate the monthly payment
    public abstract double calculateMonthlyPayment();

    // Calculate the monthly interest
    public abstract double calculateMonthlyInterestRate();


    public abstract int calculateNumberOfPayments();


    public double calculateMonthlyInterest() {
        return (amount * (interestRate/100)) / 12.0;
    }
    // Calculate the new loan amount after deducting monthly payment
    public double calculateNewLoanAmount(double monthlyPayment) {
        double monthlyInterest = calculateMonthlyInterest();
        double principalPayment = monthlyPayment - monthlyInterest;
        return amount - principalPayment;
    }

    // Calculate the total loan cost
    public double calculateTotalLoanCost() {
        return calculateMonthlyPayment() * term * 12.0;
    }

    // Getter and setter methods

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public float getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(float interestRate) {
        this.interestRate = interestRate;
    }

    public int getTerm() {
        return term;
    }

    public void setTerm(int term) {
        this.term = term;
    }

    public Borrower getBorrower() {
        return borrower;
    }

    public void setBorrower(Borrower borrower) {
        this.borrower = borrower;
    }
    public void displayMonthlyPaymentSchedule() {


        double monthlyPayment = calculateMonthlyPayment();
        int nbrOfPayments = calculateNumberOfPayments();
        double monthlyInterest = 0;
        double Total_interest = TotalInterest();
        double newLoanAmount;

        NumberFormat currencyFormatter = new DecimalFormat("#,##0.00");
        String formattedMonthlyPay = currencyFormatter.format(monthlyPayment);
        String formattedTotalInterest = currencyFormatter.format(Total_interest);
        String formattedTotalLoan = currencyFormatter.format(calculateTotalLoanCost());

        System.out.println();
        System.out.printf("LOAN SUMMARY%n");
        System.out.println("---------------------------------");
        System.out.println();
        System.out.printf("\tMonthly payment:\tRwf%s%n ", formattedMonthlyPay);
        System.out.printf("\tNumber of payments:\t%d%n ", nbrOfPayments);
        System.out.printf("\tTotal interest:\tRwf%s%n ", formattedTotalInterest);
        System.out.printf("\tTotal loan cost:\tRwf%s%n " , formattedTotalLoan);
        System.out.println();

        System.out.println("Monthly Payment Schedule:");
        System.out.println("-----------------------------------------------------------------------------------------------------------------------------------------------------------");
        System.out.printf("%-10s %-15s %-20s %-25s %-30s %-35s\n", "Month","Initial_amount(Rwf)", "Payment(Rwf)", "Monthly Interest(Rwf)", "Principal Payment(Rwf)", "New models.Loan Amount(Rwf)");
        System.out.println("-----------------------------------------------------------------------------------------------------------------------------------------------------------");
        for (int month = 1; month <= getTerm() * 12; month++) {
            newLoanAmount = calculateNewLoanAmount(monthlyPayment);
            monthlyInterest = (amount * (interestRate/100)) / 12;
            double principalPayment = monthlyPayment - monthlyInterest;

            System.out.printf("%-10d %-14.2f %-14.2f %-14.2f %-14.2f %-14.2f\n", month, amount, monthlyPayment, monthlyInterest, principalPayment, newLoanAmount);

            amount -= principalPayment;
        }


    }

    public String toJson() {
        Gson gson = new Gson();
        return gson.toJson(this);
    }

    public static Loan fromJson(String json) {
        Gson gson = new Gson();
        return gson.fromJson(json, Loan.class);
    }
}
