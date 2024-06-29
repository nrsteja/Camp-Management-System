package CAMs;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class UserDataManager {
	public static int i,j;
	private static List<User> users = new ArrayList<>();
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
	
	public List<User> getUserData() {
		return users;
	}
	
	
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
