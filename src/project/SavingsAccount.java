package project;

public class SavingsAccount extends Account
	                 implements BankKKU {
public SavingsAccount(String accountName, double balance) {
		             super(accountName, balance);
	                 }
@Override
public double calculateInterest() {
    return balance * 0.02 ;
 }
@Override
public void withdraw(double amount) {
	 if (amount > balance) {
	        throw new IllegalArgumentException("ยอดเงินไม่พอ!!");
	    }
	    balance -= amount;
}

@Override
public void deposit(double amount) {
	balance += amount;
}
@Override
public double checkBalance() {
    return balance;
}
}