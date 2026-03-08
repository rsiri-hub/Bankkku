package project;

public abstract class Account {
       protected  String accountName;
	   protected  double balance;
	   public Account(String accountName, double balance) {
		   this.accountName = accountName;
		   this.balance = balance;
	   }
	   public abstract double calculateInterest();
	   @Override
	   public String toString() {
		return "Account [accountName=" + accountName + ", balance=" + balance + "]";
	   }
       
}
