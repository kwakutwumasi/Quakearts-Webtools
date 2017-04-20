package test.junit.beans;

import java.util.List;

public class NonAnnotatedClass {
	private List<Customer> customers;
	private int anInt;
	private String aString;

	public List<Customer> getCustomers() {
		return customers;
	}

	public void setCustomers(List<Customer> customers) {
		this.customers = customers;
	}

	public int getAnInt() {
		return anInt;
	}

	public void setAnInt(int anInt) {
		this.anInt = anInt;
	}

	public String getaString() {
		return aString;
	}

	public void setaString(String aString) {
		this.aString = aString;
	}

}
