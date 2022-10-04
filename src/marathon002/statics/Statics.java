package marathon002.statics;

import java.io.File;
import java.time.format.DateTimeFormatter;

public class Statics {
	public static final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
	public static final int MENU_MIN = 0;
	public static final int MENU_MAX = 7;
	public static final int MENU_EXIT = 7;
	public static final File EMPLOYEE_PATH = new File("C:\\Users\\90553\\OneDrive\\Masaüstü\\school\\Employee.txt");

}
