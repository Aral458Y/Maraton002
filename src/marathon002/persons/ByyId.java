package marathon002.persons;

import java.util.Comparator;

public class ByyId implements Comparator<Employee>{


	

	@Override
	public int compare(Employee o1, Employee o2) {
		int x = o1.getEmployeeNumber().compareTo(o2.getEmployeeNumber());
		return x;
	}

}
