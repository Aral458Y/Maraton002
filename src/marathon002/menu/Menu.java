package marathon002.menu;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import marathon002.statics.Statics;
import marathon002.persons.*;
import marathon002.compare.ByName;
import marathon002.fileOperations.FileOp;
import marathon002.list.EmployeeList;

public class Menu implements Selections{
	private static Scanner input = new Scanner(System.in);
	private EmployeeList company;

	
	public Menu() {
		
		company = getCompanyDatas();
		menuLoop();
	}
	
	private EmployeeList getCompanyDatas() {
		
		EmployeeList list1 = FileOp.readSerializedObject(Statics.EMPLOYEE_PATH);
		
		if (list1 != null) {
			return list1;
		} else
			return new EmployeeList();
	}
	
	private void writeSchoolDatas(EmployeeList company) {
		
		FileOp.writeSerializedObject(company, Statics.EMPLOYEE_PATH);
		
	}
	
	@Override
	public void menuLoop() {
		do {
			showMenu();
			menuSelection(getInput(""));
		}while(true);
	}

    @Override
    public void showMenu() {
    	System.out.println("");
	    System.out.println("################## Personel Yönetim Sistemi #################");
	    System.out.println("# 1-  Personel Listesi Yarat" );
	    System.out.println("# 2-  Personelleri Listele");
	    System.out.println("# 3-  Eski Personelleri Listele");
	    System.out.println("# 4-  Bugüne Kadar Verilen Toplam Personel Maaşlarını Listele");
	    System.out.println("# 5-  Personel Çalışma Sürelerini Listele");
	    System.out.println("# 6-  Çalışan Tipine Göre Maaş Miktarları(Kadrolu/Saatlik)");
	    System.out.println("# 7-  Çıkış");
	    System.out.println("##############################################################");
}
    @Override
    public void menuSelection(int choice) {
	    switch (choice) {
	    case 1:
		    newEmployees();
		    break;
	    case 2:
		    listEmployees(company.getEmployees());
		    break;
	    case 3:
	    	oldEmployees(company.getEmployees());
	    	break;
	    case 4:
		    calculateSalary();
		    break;
	    case 5:
		    calculateWorkTime();
		    break;
	    case 6:
	    	salaryAmounts();
	    	break;
	    case Statics.MENU_EXIT:
		    endLoop();
		    break;
	
	    default:
		    System.out.println("Hatalı giriş yaptınız, yeniden deneyin.");
	}
}

    private void oldEmployees(List<Employee> employees) {
    	employees.stream().filter(i -> i.getResignDate() != null).forEach(i -> System.out.println(i));
		
	}

	private void salaryAmounts() {
		System.out.println("Saatlik çalışanlar için saatlik ücret 25TL+KDV dir ve günlük 5 saat çalışabilirler ");
		System.out.println("Kadrolu çalışanlar için saatlik ücret 21TL+ %25 primdir ve günlük 8 saat çalışırlar ve ayın 30 günü çalışıyor gibi maaş alırlar");
		
	}

	private void calculateWorkTime() {
    	List<Employee> employees = company.getEmployees();
    	for (Employee employee : employees) {
    		if(employee.getWorkStyle()== WorkStyle.STAFFED) {
    			System.out.println(employee.getWorkStyle()+"--"+employee.getName() + " : " + employee.calculateWorkTime()+" GÜN");
    		}else
  			System.out.println(employee.getWorkStyle()+"--"+employee.getName() + " : " + (employee.calculateWorkTime()*5)+" SAAT" );
  		}
	}

	private void calculateSalary() {

    	  List<Employee> employees = company.getEmployees();
    	  for (Employee employee : employees) {
  			System.out.println(employee.getWorkStyle()+"--"+employee.getName() + " : " + employee.calculateCurrentSalary()+"TL");
  		}
    	
		
	}

	private void listEmployees(List<Employee> employees) {
		Collections.sort(employees, new ByName());
	    employees.stream().forEach(s -> System.out.println(s));
}

    private void newEmployees() {
	
	    Employee employee1 = new Employee(new Name("Yiğit","Aral","Yücepur"), Gender.MALE, LocalDate.parse("10.08.2018",Statics.dateTimeFormatter),
	    		null, WorkStyle.STAFFED);
	    Employee employee2 = new Employee(new Name("Kağan","","Şahbaz"), Gender.MALE, LocalDate.parse("15.09.2022",Statics.dateTimeFormatter),
	    		LocalDate.parse("28.09.2022",Statics.dateTimeFormatter), WorkStyle.HOURLY);
	    Employee employee3 = new Employee(new Name("Pelin","Naz","Kartal"), Gender.MALE, LocalDate.parse("10.08.2021",Statics.dateTimeFormatter),
	    		LocalDate.parse("10.09.2022",Statics.dateTimeFormatter), WorkStyle.STAFFED);
	    Employee employee4 = new Employee(new Name("Utku","Berk","Aker"), Gender.MALE, LocalDate.parse("22.04.2020",Statics.dateTimeFormatter),
	    		null, WorkStyle.STAFFED);
	    Employee employee5 = new Employee(new Name("Eren","","Erol"), Gender.MALE, LocalDate.parse("15.09.2022",Statics.dateTimeFormatter),
	    		null, WorkStyle.HOURLY);
	    
	    System.out.println("Çalışan listesi oluşturuldu");
	
	    company.addEmployee(employee1);
	    company.addEmployee(employee2);
	    company.addEmployee(employee3);
	    company.addEmployee(employee4);
	    company.addEmployee(employee5);
	    
	    writeSchoolDatas(company);
	
}
	@Override
	public int getInput(String message) {
		System.out.println(message + " ");
		try {
			int userInput = input.nextInt();
			if (userInput >= Statics.MENU_MIN && userInput <= Statics.MENU_MAX) {
				return userInput;
			}
		} catch (Exception e) {
			System.out.println("Hatalı girşi yaptınız lütfen tekrar deneyin");
			return -1;
		}
		return -1;
	}
	@Override
	public void endLoop() {
		System.out.println("Çıkmak istediğinize emin misiniz?(evet/hayır)");
		if (input.next().equalsIgnoreCase("evet")) {
			System.out.println("Tekrar görüşmek dileğiyle.");
			input.close();
			System.exit(0);
		}	
	}
}
