package lab09_2;

public class Invoice {
    private String id;
	private Customer customer;
    private double totalPrice;
	public Invoice(String id, Customer customer, double totalPrice) {
		this.id = id;
		this.customer = customer;
		this.totalPrice = totalPrice;
	}
	@Override
	public String toString() {
		return "=======INVOICE======= " + 
	             "\nID:" + id 
	             + "\nCustomer Name:" + customer.getFullname() 
	             + "\nCustomer type:"+customer.getType()
	             +" \nTotal Price:$" + totalPrice ;
	}
    
}
