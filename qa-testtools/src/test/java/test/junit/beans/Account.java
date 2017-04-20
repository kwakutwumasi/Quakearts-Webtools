package test.junit.beans;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import com.quakearts.tools.test.generator.annotation.CollectionType;
import com.quakearts.tools.test.generator.primitives.configuration.GenerateWith;

public class Account {
	private String accountNumber;
	private Date openingDate;
	private BigDecimal balance;
	private Set<Transaction> transactions;
	private List<Note> notes = new ArrayList<Note>();

	public String getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}

	public Date getOpeningDate() {
		return openingDate;
	}

	public void setOpeningDate(Date openingDate) {
		this.openingDate = openingDate;
	}

	@GenerateWith(min=0.0, max=20000.0)
	public BigDecimal getBalance() {
		return balance;
	}

	public void setBalance(BigDecimal balance) {
		this.balance = balance;
	}

	@CollectionType(Transaction.class)
	public Set<Transaction> getTransactions() {
		return transactions;
	}

	public void setTransactions(Set<Transaction> transactions) {
		this.transactions = transactions;
	}

	@CollectionType(Note.class)
	public List<Note> getNotes() {
		return notes;
	}

	public void setNotes(List<Note> notes) {
		this.notes = notes;
	}

}
