package marathon002.persons;

import java.io.Serializable;


public abstract class Person implements Serializable{	
	private static final long serialVersionUID = 883855160249485455L;
	protected Name name;
	protected Gender gender;
	public Person(Name name, Gender gender) {
		super();
		this.name = name;
		this.gender = gender;
	}
	public void setName(Name name) {
		this.name = name;
	}
	
	public Name getName() {
		return name;
	}
	public Gender getGender() {
		return gender;
	}
	
	@Override
	public String toString() {
		return "Person [name=" + name + ", gender=" + gender + "]";
	}

}
