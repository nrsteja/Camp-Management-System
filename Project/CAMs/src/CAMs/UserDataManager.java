package CAMs;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
/**
The `UserDataManager` class is responsible for managing user data within the application.
@author Jeevika, Mathangi, Teja, Vikaas
@version 3.0
@since 2023-11-25
*/
public class UserDataManager {
	/**
	 * Two integer variables i and j.
	 */
	public static int i,j;
	/**
	 * Creates a list of Users.
	 */
	public static List<User> users = new ArrayList<>();
	//public static User[] users = new User[];
	/**
	 * Generates a list of User objects based on given lists of student and staff details.
	 *
	 * @param studentlist A string containing student details separated by tabs.
	 * @param stafflist A string containing staff details separated by tabs.
	 * 
	 */
	public void generateData(String studentlist, String stafflist)  {
		String[] staffName = ReadCol(0, stafflist, "	");
		String[] staffEmail = ReadCol(1, stafflist, "	");
		String[] staffFaculty = ReadCol(2, stafflist, "	");
		
		String[] studentName = ReadCol(0, studentlist, "	");
		String[] studentEmail = ReadCol(1, studentlist, "	");
		String[] studentFaculty = ReadCol(2, studentlist, "	");
		
		//Create the camp members
		//User[] users = new User[staffName.length + studentName.length];
		for(i=0; i<studentEmail.length; i++) {
			Schools studentFaculties = Schools.valueOf(studentFaculty[i]);
			users.add(new Student(studentName[i], studentEmail[i], studentFaculties));
		}
		for(j=0; j<staffEmail.length; j++) {
			Schools staffFaculties = Schools.valueOf(staffFaculty[j]);
			users.add(new Staff(staffName[j], staffEmail[j], staffFaculties));
		}
	}
	/**
	 * Retrieves the list of User objects representing user data.
	 *
	 * @return The List of User objects containing user data.
	 */
	public List<User> getUserData() {
		return users;
	}
	/**
	 * Reads the data from a specified column in a CSV file and returns array containing the column values.
	 *
	 * @param col         The index of the column to read.
	 * @param filepath    The path to the CSV file.
	 * @param delimiter   The delimiter used in the CSV file to separate values.
	 * @return An array of strings containing the data from the specified column, excluding the header.
	 *         Returns null if any error occurs during the file reading process.
	 */
	public static String[] ReadCol(int col, String filepath, String delimiter) {
		String data[];
		String currentLine;
		ArrayList<String> colData = new ArrayList<String>();
		int count = 0;
		
		try {
			FileReader fr = new FileReader(filepath);
			BufferedReader br = new BufferedReader(fr);
			//Scanner staffList = new Scanner(new File("Staff_List.txt"));
			
			while((currentLine = br.readLine()) != null) {
				data = currentLine.split(delimiter);
				if(count != 0)				// To avoid the heading getting stored
					colData.add(data[col]);
				count++;
			}
		}
		catch(Exception e)
		{
			System.out.println(e);
			return null;
		} 
		
		return colData.toArray(new String[0]);
	}

}
