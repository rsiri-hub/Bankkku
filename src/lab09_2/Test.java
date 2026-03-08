package lab09_2;

public class Test {

	public static void main(String[] args) {
		Customer cs = new Customer("Isaac Newton","Member");
	    Invoice  se = new Invoice("00001",cs,150);
	    System.out.println(cs);
	    System.out.println(se);
	}

}
