import java.util.ArrayList;
import java.util.Scanner;

class Account {
    private String accountHolderName;
    private String accountNumber;
    private double balance;
    private ArrayList<String> transactionHistory;

    public Account(String accountHolderName, String accountNumber) {
        this.accountHolderName = accountHolderName;
        this.accountNumber = accountNumber;
        this.balance = 0.0;
        this.transactionHistory = new ArrayList<>();
        transactionHistory.add("Account created with initial balance: $0.00");
    }

    public String getAccountHolderName() {
        return accountHolderName;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public double getBalance() {
        return balance;
    }

    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            transactionHistory.add("Deposited: $" + amount + " | New Balance: $" + balance);
            System.out.println("Deposit successful. New balance: $" + balance);
        } else {
            System.out.println("Invalid deposit amount.");
        }
    }

    public void withdraw(double amount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
            transactionHistory.add("Withdrew: $" + amount + " | New Balance: $" + balance);
            System.out.println("Withdrawal successful. New balance: $" + balance);
        } else {
            System.out.println("Invalid withdrawal amount or insufficient funds.");
        }
    }

    public void showTransactionHistory() {
        System.out.println("Transaction History for Account: " + accountNumber);
        for (String transaction : transactionHistory) {
            System.out.println("- " + transaction);
        }
    }
}

class Bank {
    private ArrayList<Account> accounts;
    private int accountCounter;

    public Bank() {
        accounts = new ArrayList<>();
        accountCounter = 1000; // Starting account number
    }

    public void createAccount(String accountHolderName) {
        String accountNumber = "ACC" + accountCounter++;
        Account newAccount = new Account(accountHolderName, accountNumber);
        accounts.add(newAccount);
        System.out.println("Account created successfully! Account Number: " + accountNumber);
    }

    public Account findAccount(String accountNumber) {
        for (Account acc : accounts) {
            if (acc.getAccountNumber().equals(accountNumber)) {
                return acc;
            }
        }
        return null;
    }

    public void showAllAccounts() {
        System.out.println("All Accounts:");
        for (Account acc : accounts) {
            System.out.println("Account Number: " + acc.getAccountNumber() + ", Holder: " + acc.getAccountHolderName() + ", Balance: $" + acc.getBalance());
        }
    }
}

public class BankingInformationSystem {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Bank bank = new Bank();

        while (true) {
            System.out.println("\n--- Banking Information System ---");
            System.out.println("1. Create Account");
            System.out.println("2. Deposit Money");
            System.out.println("3. Withdraw Money");
            System.out.println("4. Check Balance");
            System.out.println("5. View Transaction History");
            System.out.println("6. Show All Accounts");
            System.out.println("7. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    System.out.print("Enter account holder name: ");
                    String name = scanner.nextLine();
                    bank.createAccount(name);
                    break;
                case 2:
                    System.out.print("Enter account number: ");
                    String accNumDeposit = scanner.nextLine();
                    Account accDeposit = bank.findAccount(accNumDeposit);
                    if (accDeposit != null) {
                        System.out.print("Enter deposit amount: ");
                        double amountDeposit = scanner.nextDouble();
                        accDeposit.deposit(amountDeposit);
                    } else {
                        System.out.println("Account not found.");
                    }
                    break;
                case 3:
                    System.out.print("Enter account number: ");
                    String accNumWithdraw = scanner.nextLine();
                    Account accWithdraw = bank.findAccount(accNumWithdraw);
                    if (accWithdraw != null) {
                        System.out.print("Enter withdrawal amount: ");
                        double amountWithdraw = scanner.nextDouble();
                        accWithdraw.withdraw(amountWithdraw);
                    } else {
                        System.out.println("Account not found.");
                    }
                    break;
                case 4:
                    System.out.print("Enter account number: ");
                    String accNumBalance = scanner.nextLine();
                    Account accBalance = bank.findAccount(accNumBalance);
                    if (accBalance != null) {
                        System.out.println("Balance: $" + accBalance.getBalance());
                    } else {
                        System.out.println("Account not found.");
                    }
                    break;
                case 5:
                    System.out.print("Enter account number: ");
                    String accNumHistory = scanner.nextLine();
                    Account accHistory = bank.findAccount(accNumHistory);
                    if (accHistory != null) {
                        accHistory.showTransactionHistory();
                    } else {
                        System.out.println("Account not found.");
                    }
                    break;
                case 6:
                    bank.showAllAccounts();
                    break;
                case 7:
                    System.out.println("Exiting system. Goodbye!");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid option. Try again.");
            }
        }
    }
}