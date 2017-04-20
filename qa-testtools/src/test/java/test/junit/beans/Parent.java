package test.junit.beans;

import java.util.List;

import com.quakearts.tools.test.generator.annotation.CollectionType;

public class Parent {
	private Person person;
	private List<Parent> children;

	public Person getPerson() {
		return person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}

	@CollectionType(Parent.class)
	public List<Parent> getChildren() {
		return children;
	}

	public void setChildren(List<Parent> children) {
		this.children = children;
	}

}
