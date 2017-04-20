package test.junit.beans;

import java.math.BigDecimal;

import com.quakearts.tools.test.generator.primitives.configuration.GenerateWith;

public class Transaction {
	private BigDecimal amount;
	private boolean debit;

	@GenerateWith(min=0.0, max=1000.0)
	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public boolean isDebit() {
		return debit;
	}

	public void setDebit(boolean debit) {
		this.debit = debit;
	}

}
