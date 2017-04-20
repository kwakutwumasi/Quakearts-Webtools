package test.junit.beans;

import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;
import com.quakearts.tools.test.generator.annotation.UseGeneratorProperty;
import com.quakearts.tools.test.generator.primitives.configuration.GenerateWith;

public class Entity {
	private String name;
	private int id;
	private String accountNumber;
	@JsonFormat(shape=Shape.NUMBER_FLOAT, pattern="#.00")
	private BigDecimal balance;
	@JsonFormat(shape=Shape.STRING, pattern="dd/MM/yyyy")
	private Date accountCreateDate;

	@UseGeneratorProperty("names")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@GenerateWith(max=20, min=11)
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@GenerateWith(strings={"8983189012","2534523454","452463546345","53453673467","64562352455","52345236257"})
	public String getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}

	@GenerateWith(max=10000, min=2000)
	public BigDecimal getBalance() {
		return balance;
	}

	public void setBalance(BigDecimal balance) {
		this.balance = balance;
	}

	@GenerateWith(maxYear=2017, minYear=2014)
	public Date getAccountCreateDate() {
		return accountCreateDate;
	}

	public void setAccountCreateDate(Date accountCreateDate) {
		this.accountCreateDate = accountCreateDate;
	}

}
