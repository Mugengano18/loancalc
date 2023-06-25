import models.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

//Main class
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<Loan> loans = new ArrayList<>();
        LoanManager loanManager = new LoanManager("loan_details.json");
        final int PRINCIPAL_MIN = 1000;
        final int PRINCIPAL_MAX = 50_000_000;

        boolean repeatProcess = true;

        while (repeatProcess) {
            System.out.print("Enter borrower's name: ");
            String borrowerName = scanner.nextLine();
            System.out.print("Enter borrower's address: ");
            String borrowerAddress = scanner.nextLine();
            Borrower borrower = new Borrower(borrowerName, borrowerAddress);




            System.out.println("Choose the type of loan:");
            System.out.println("1. Personal Loan");
            System.out.println("2. Home Loan");
            System.out.println("3. Car Loan");
            System.out.print("Enter your choice: ");
            int loanChoice = scanner.nextInt();

            switch (loanChoice) {
                case 1:
                    double personalLoanAmount = (int) readNumber("Enter personal loan amount: ", PRINCIPAL_MIN, PRINCIPAL_MAX);
                    float personalLoanInterestRate = (float) readNumber("Enter personal loan interest rate: ", 1, 30);
                    int personalLoanTerm = (int) readNumber("Enter loan term (in years): ", 1, 30);
                    PersonalLoan personalLoan = new PersonalLoan(personalLoanAmount, personalLoanInterestRate, personalLoanTerm, borrower);
                    loans.add(personalLoan);
                    break;
                case 2:
                    double homeLoanAmount = (int) readNumber("Enter home loan amount: ", PRINCIPAL_MIN, PRINCIPAL_MAX);
                    float homeLoanInterestRate = (float) readNumber("Enter home loan interest rate: ", 1, 30);
                    int homeLoanTerm = (int) readNumber("Enter loan term (in years): ", 1, 30);
                    HomeLoan homeLoan = new HomeLoan(homeLoanAmount, homeLoanInterestRate, homeLoanTerm, borrower);
                    loans.add(homeLoan);
                    break;
                case 3:
                    double carLoanAmount = readNumber("Enter car loan amount:", PRINCIPAL_MIN, PRINCIPAL_MAX);
                    float carLoanInterestRate = (float) readNumber("Enter car loan interest rate: ",1,30);
                    int carLoanTerm = (int) readNumber("Enter car loan term (in years): ",1,30);
                    CarLoan carLoan = new CarLoan(carLoanAmount, carLoanInterestRate, carLoanTerm, borrower);
                    loans.add(carLoan);
                    break;
                default:
                    System.out.println("Invalid choice");
                    return;
            }



            // Calculate and display loan details
            for (Loan loan : loans) {

                System.out.println();
                System.out.println("HERE'S YOUR DETAILS");
                System.out.println("---------------------------------");
                System.out.println();
                System.out.println("\tmodels.Borrower: " + loan.getBorrower().getName());
                System.out.println("\tAddress: " + loan.getBorrower().getAddress());
                System.out.println("\tmodels.Loan Type: " + loan.getClass().getSimpleName());
                System.out.printf("\tmodels.Loan amount:\t Rwf %.2f%n " , loan.getAmount());
                System.out.printf("\tAnnual interest rate: \t %.2f%n" ,loan.getInterestRate());
                System.out.printf("\tmodels.Loan period in years: \t" + loan.getTerm() + " years");
                System.out.println();

                loan.displayMonthlyPaymentSchedule();
            }
            System.out.print("Do you want to enter details for another borrower? (yes/no): ");
            String choice = scanner.nextLine().trim().toLowerCase();
            repeatProcess = choice.equals("yes");
        }
        scanner.close();

    }
    private static double readNumber(String prompt, double min, double max) {
        Scanner scanner = new Scanner(System.in);
        double value;
        while (true) {
            System.out.print(prompt);
            value = scanner.nextFloat();
            if(value >= min && value <= max) {
                break;
            }
            System.out.println("Enter a value between " + min + " and " + max);
        }
        return value;
    }
}
