package marathon002.compare;

import java.util.Comparator;

import marathon002.persons.Employee;

public class ByName implements Comparator<Employee> {
	
	@Override
	public int compare(Employee o1, Employee o2) {
		int x = o1.getEmployeeNumber().compareTo(o2.getEmployeeNumber());
		return x;
	}
}
