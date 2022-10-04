package marathon002.fileOperations;


import java.io.BufferedReader;

import java.io.BufferedWriter;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import marathon002.list.*;
public class FileOp {

	public static void writeSerializedObject(EmployeeList member, File file) {
		try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file));) {
			oos.writeObject(member);
		} catch (FileNotFoundException ex) {
			
		} catch (IOException ex) {
			
		}
        catch (NumberFormatException ex) {
        	
		}
		
	}
	
	public static EmployeeList readSerializedObject(File file) {
		EmployeeList member = null;
		try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file));) {
			member = (EmployeeList) ois.readObject();
		} catch (FileNotFoundException ex) {
			System.out.println("HATA:Personel Listesi Mevcut Değil!!!");
		} catch (EOFException ex) {
			
		}catch (IOException ex) {
			
		} catch (ClassNotFoundException ex) {
			
		} 
        catch (NumberFormatException ex) {
        	
		}
		return member;
	}

	public static void writeInt(File file, int number) {
		try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file))) {
			bufferedWriter.write(Integer.toString(number));
			bufferedWriter.flush();
		} catch (Exception e) {
			
		}
	}

	public static int readInt(File file) {
		int read = -1;
		try (BufferedReader bufferedReader = new BufferedReader(new FileReader(file))) {
			read = Integer.parseInt(bufferedReader.readLine());
		} catch (Exception e) {
			
		}
		return read;
	}

}
