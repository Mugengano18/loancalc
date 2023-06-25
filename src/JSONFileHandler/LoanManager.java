package JSONFileHandler;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import models.Loan;

import java.io.*;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class LoanManager {
    private List<Loan> loans;
    private String fileName;
    private Gson gson;

    public LoanManager(String fileName) {
        this.loans = new ArrayList<>();
        this.fileName = fileName;
        this.gson = new Gson();
    }

    public void addLoan(Loan loan) {
        loans.add(loan);
    }

    public void saveLoanDetails() {
        try (Writer writer = new FileWriter(fileName)) {
            gson.toJson(loans, writer);
            System.out.println("Loan details saved successfully.");
        } catch (IOException e) {
            System.out.println("Error occurred while saving loan details: " + e.getMessage());
        }
    }

    public void loadLoanDetails() {
        try (Reader reader = new FileReader(fileName)) {
            Type loanListType = new TypeToken<List<Loan>>(){}.getType();
            loans = gson.fromJson(reader, loanListType);
            if (loans == null) {
                loans = new ArrayList<>();
            }
            System.out.println("Loan details loaded successfully.");
        } catch (IOException e) {
            System.out.println("Error occurred while loading loan details: " + e.getMessage());
        }
    }

    public List<Loan> getLoans() {
        return loans;
    }
}
