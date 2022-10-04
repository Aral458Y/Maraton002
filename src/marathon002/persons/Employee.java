package marathon002.persons;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

import marathon002.fileOperations.FileOp;
import marathon002.salary.Accounting;
import marathon002.statics.Statics;



public class Employee extends Person implements EmployeeNumber, Accounting, Serializable{
	private static final long serialVersionUID = -7014847655109070725L;
	
	private LocalDate startToWorkDate;
	private LocalDate resignDate;
	private WorkStyle workStyle;
	private Long workTime;
	private String employeeNumber;
	private double salaryRates;
	
	
	public Employee(Name name, Gender gender, LocalDate starToWorkDate, LocalDate resignDate, WorkStyle workStyle) {
		super(name, gender);
		this.startToWorkDate = starToWorkDate;
		this.resignDate = resignDate;
		this.workStyle = workStyle;
		this.employeeNumber = assignEmployeeNumber();
		this.salaryRates = assignSalaryRate();
	}
	@Override
	public double calculateWorkTime(){
		if(resignDate==null) {
			long days = this.startToWorkDate.until(LocalDate.now(), ChronoUnit.DAYS);
			double workTime = days;
			return workTime;
			
		}else {
		long days = this.startToWorkDate.until(resignDate, ChronoUnit.DAYS);
		    double workTime = days;
		    return workTime;
		}
	}
	@Override
	public double assignSalaryRate() {
		if (this.workStyle == WorkStyle.HOURLY)
			return 25;
		else
			return 21;
	}
	@Override
	// 5 Hours for each day(HOURLY Employees)
	public double calculateCurrentSalary() {
		if(resignDate==null) {
			long days = this.startToWorkDate.until(LocalDate.now(), ChronoUnit.DAYS);
			double currentSalary = this.salaryRates;
			if (this.workStyle == WorkStyle.STAFFED) {
				currentSalary = currentSalary*24*days*1.25;	
			}else {
				currentSalary = currentSalary*5*(days-2)*1.08;
			}
			return currentSalary;
			
		}else {
		long days = this.startToWorkDate.until(resignDate, ChronoUnit.DAYS);
		double currentSalary = this.salaryRates;
		if (this.workStyle == WorkStyle.STAFFED) {
			currentSalary = currentSalary*8*days*1.25;	
		}else {
			currentSalary = currentSalary*5*(days-2)*1.08;
		}
		return currentSalary;
		}
	}
	
	public String assignEmployeeNumber() {
		int number = FileOp.readInt(Statics.EMPLOYEE_PATH);
		if(number>9999) {
			number=1;
		}
		String employeeNumber = String.format("%C-%03d", this.workStyle.toString().charAt(0), (number+2));
		FileOp.writeInt(Statics.EMPLOYEE_PATH, ++number);
		
		return employeeNumber;
	}
	@Override
	public String toString() {
		return  this.workStyle +", name=" + name +" [starToWorkDate=" + startToWorkDate + ", resingDate=" + resignDate + 
				 ", employeeNumber=" + employeeNumber + ", salaryRates=" + salaryRates
				+ "]";
	}
	public LocalDate getStarToWorkDate() {
		return startToWorkDate;
	}
	public void setStarToWorkDate(LocalDate starToWorkDate) {
		this.startToWorkDate = starToWorkDate;
	}
	public LocalDate getResignDate() {
		return resignDate;
	}
	public void setResingDate(LocalDate resingDate) {
		this.resignDate = resingDate;
	}
	public WorkStyle getWorkStyle() {
		return workStyle;
	}
	public void setWorkStyle(WorkStyle workStyle) {
		this.workStyle = workStyle;
	}
	public String getEmployeeNumber() {
		return employeeNumber;
	}
	public void setEmployeeNumber(String employeeNumber) {
		this.employeeNumber = employeeNumber;
	}
	
	public double getSalaryRates() {
		return salaryRates;
	}
	public void setSalaryRates(double salaryRates) {
		this.salaryRates = salaryRates;
	}
	
	
	

}


