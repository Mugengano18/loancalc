package interfaces;

// Interface representing an entity that can be repaid
public interface LoanCalculator {
    double calculateMonthlyPayment();
    double calculateMonthlyInterestRate();
    double calculateNewLoanAmount(double monthlyPayment);
    double calculateTotalLoanCost();
    int calculateNumberOfPayments();
    void displayMonthlyPaymentSchedule();

    double TotalInterest();
}
