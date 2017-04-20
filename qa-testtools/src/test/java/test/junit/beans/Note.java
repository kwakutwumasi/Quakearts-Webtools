package test.junit.beans;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;
import com.quakearts.tools.test.generator.primitives.configuration.GenerateWith;

public class Note {
	@JsonFormat(shape=Shape.STRING, pattern="dd/MM/yyyy")
	private Date createDate;
	private String value;

	@GenerateWith(maxYear=2017, minYear=2016)
	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	@GenerateWith(strings={"Withdrawal","Deposit", "Transfer","Bill Payment","Mobile Money"})
	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

}
