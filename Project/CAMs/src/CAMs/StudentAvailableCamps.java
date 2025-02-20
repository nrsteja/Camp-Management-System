package CAMs;

import java.util.ArrayList;
import java.util.List;
/**
Implementation of the AvailableCamps interface for students to view available camps.
@author Jeevika, Mathangi, Teja, Vikaas
@version 3.0
@since 2023-11-25
*/
public class StudentAvailableCamps implements AvailableCamps {
	static RegistrationCamps campRegistration = new StudentRegistrationCamps();
	/**
     * Retrieves a list of available camps based on specific criteria for student enrollment.
     *
     * @param camps   The list of all camps.
     * @param student The student for whom available camps are to be determined.
     * @return A filtered list of available camps for the given student.
     */
	public List<Camp> viewAvailableCamps(List<Camp> camps, Student student) {
        // Iterate through camps and check if they are open to the student's user group
    	List<Camp> storeCamps = new ArrayList<>(); 
		boolean found = false;
		
        for (Camp camp : camps) {
        	if((camp.getCampAccessible() == CampAvailability.NTU  
        			|| camp.getCampSchool() == student.getFaculty()) 
	    			&& camp.getRemainingSlots() > 0
	    			&& ((StudentRegistrationCamps) campRegistration).isRegistrationOpen(camp)
	    			&& camp.getVisibility() == true
	    			&& this.isCampMember(camp, student) == false) {
        			storeCamps.add(camp);
        			found = true;
            }
        }
        if(!found) {
    		System.out.println("No camps Available");
    	}
        else {
        	storeCamps = FilterVisibility.filterVisibility(storeCamps);
        	student.printAvailableCamps(storeCamps);
        }
        return storeCamps;
    }
	/**
     * Retrieves a list of available camps for a given student.
     *
     * @param allCamps The list of all available camps.
     * @param student  The student for whom available camps are to be determined.
     * @return A list of available camps for the given student.
     */
	public List<Camp> getAvailableCamps(List<Camp> allCamps, Student student) {
	    List<Camp> availableCamps = new ArrayList<>();
	    for (Camp camp : allCamps) { // Assuming allCamps is a list of all available camps
	    	if((camp.getCampAccessible() == CampAvailability.NTU  
        			|| camp.getCampSchool() == student.getFaculty()) 
	    			&& camp.getRemainingSlots() > 0
	    			&& ((StudentRegistrationCamps) campRegistration).isRegistrationOpen(camp)
	    			&& camp.getVisibility() == true
	    			&& this.isCampMember(camp, student) == false) {
	    				availableCamps.add(camp);
	    	}
	    }
	    return availableCamps;
	}
	/**
     * Checks if a student is a member of a specific camp.
     *
     * @param camp    The camp to check membership for.
     * @param student The student to check for membership.
     * @return {@code true} if the student is a member of the camp, {@code false} otherwise.
     */
    public boolean isCampMember(Camp camp, Student student) {
		List<Student> registeredStudents = camp.getAttendees();
		for(Student students: registeredStudents) {
			if(students.equals(student)) {
				return true;
			}
		}
		return false;
	}
    /**
     * Retrieves a list of available camps for a student to inquire, including both available and registered camps.
     *
     * @param allCamps The list of all available camps.
     * @param student  The student making the inquiry.
     * @return A list of available camps for the student to inquire about.
     */
	public List<Camp> getAvailableCampsToEnquire(List<Camp> allCamps, Student student) {
		AvailableCamps availCamps = new StudentAvailableCamps();
	    List<Camp> availableCampsToEnquire = new ArrayList<>();
	    List<Camp> availableCamps = availCamps.getAvailableCamps(allCamps, student);
	    List<Camp> registeredCamps = student.getRegisteredCamps();
	    for (Camp camp : allCamps) { // Assuming allCamps is a list of all available camps
	    	if(availableCamps.contains(camp)) {
	    		availableCampsToEnquire.add(camp);
	    	}
	    }
	    for(int i=0; i<registeredCamps.size(); i++) {
	    	if (!availableCampsToEnquire.contains(registeredCamps.get(i))){
	    		availableCampsToEnquire.add(registeredCamps.get(i));
	    	}
	    }
	    return availableCampsToEnquire;
	}
}
