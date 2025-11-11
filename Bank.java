import java.util.Scanner;
class Account {
    protected String customerName;
    protected String accountNumber;
    protected String accountType;
    protected double balance;

    public Account(String customerName, String accountNumber, String accountType, double initialBalance) {
        this.customerName = customerName;
        this.accountNumber = accountNumber;
        this.accountType = accountType;
        balance = initialBalance;
    }

    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            System.out.println("Deposited: ₹" + amount);
        } else {
            System.out.println("Invalid deposit amount.");
        }
    }

    public void displayBalance() {
        System.out.printf("Current Balance: ₹%.2f%n", balance);
    }
}
class SavAcct extends Account {
    private double interestRate;

    public SavAcct(String customerName, String accountNumber, double initialBalance, double interestRate) {
        super(customerName, accountNumber, "Savings", initialBalance);
        this.interestRate = interestRate;
    }

    public void computeAndDepositInterest(int years) {
        if (years > 0) {
            double interest = balance * Math.pow((1 + interestRate), years) - balance;
            balance += interest;
            System.out.printf("Interest added for %d year(s): ₹%.2f%n", years, interest);
        } else {
            System.out.println("Invalid number of years.");
        }
    }

    public void withdraw(double amount) {
        if (amount <= balance && amount > 0) {
            balance -= amount;
            System.out.println("Withdrawn: ₹" + amount);
        } else {
            System.out.println("Insufficient balance or invalid amount.");
        }
    }
}
class CurAcct extends Account {
    private final double minimumBalance = 1000;
    private final double serviceCharge = 100;

    public CurAcct(String customerName, String accountNumber, double initialBalance) {
        super(customerName, accountNumber, "Current", initialBalance);
    }

    public void withdraw(double amount) {
        if (amount <= balance && amount > 0) {
            balance -= amount;
            System.out.println("Withdrawn: ₹" + amount);
            checkMinimumBalance();
        } else {
            System.out.println("Insufficient balance or invalid amount.");
        }
    }

    private void checkMinimumBalance() {
        if (balance < minimumBalance) {
            balance -= serviceCharge;
            System.out.printf("Balance below ₹%.2f. Service charge of ₹%.2f imposed.%n", minimumBalance, serviceCharge);
        }
    }

    public void chequeBookFacility() {
        System.out.println("Cheque book facility is available.");
    }
}
public class Bank {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("=== Savings Account Setup ===");
        System.out.print("Enter name: ");
        String savName = sc.nextLine();
        System.out.print("Enter account number: ");
        String savAccNo = sc.nextLine();
        System.out.print("Enter initial balance: ");
        double savBalance = sc.nextDouble();
        System.out.print("Enter interest rate (%): ");
        double interestRate = sc.nextDouble() / 100;
        System.out.print("Enter number of years for interest calculation: ");
        int years = sc.nextInt();

        SavAcct sav = new SavAcct(savName, savAccNo, savBalance, interestRate);
        sav.displayBalance();
        sav.computeAndDepositInterest(years);

        System.out.print("Enter deposit amount: ");
        double depositAmt = sc.nextDouble();
        sav.deposit(depositAmt);

        System.out.print("Enter withdrawal amount: ");
        double withdrawAmt = sc.nextDouble();
        sav.withdraw(withdrawAmt);
        sav.displayBalance();

        sc.nextLine(); 
        System.out.println("\n=== Current Account Setup ===");
        System.out.print("Enter name: ");
        String curName = sc.nextLine();
        System.out.print("Enter account number: ");
        String curAccNo = sc.nextLine();
        System.out.print("Enter initial balance: ");
        double curBalance = sc.nextDouble();

        CurAcct cur = new CurAcct(curName, curAccNo, curBalance);
        cur.displayBalance();
        cur.chequeBookFacility();

        System.out.print("Enter first withdrawal amount: ");
        double curWithdraw1 = sc.nextDouble();
        cur.withdraw(curWithdraw1);
        cur.displayBalance();

        System.out.print("Enter second withdrawal amount: ");
        double curWithdraw2 = sc.nextDouble();
        cur.withdraw(curWithdraw2);
        cur.displayBalance();

        sc.close();
    }
}