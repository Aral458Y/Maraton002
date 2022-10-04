package marathon002.list;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.List;

import marathon002.persons.Employee;

public class EmployeeList implements Serializable {
	private static final long serialVersionUID = 5327021754629413199L;
	
	private List<Employee> employees;
	
	public EmployeeList() {
		this.employees = new ArrayList<Employee>();
		
	}


	public List<Employee> getEmployees() {
		return employees;
	}


	public void addEmployee(Employee employee) {
		this.employees.add(employee);

}
}

