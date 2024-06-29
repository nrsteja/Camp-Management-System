package CAMs;

import java.util.ArrayList;
import java.util.List;
/**
Implements the {@link ViewingCamps} interface and provides methods
for listing and viewing camps created by a staff member.
@author Jeevika, Mathangi, Teja, Vikaas
@version 3.0
@since 2023-11-25
*/
public class StaffViewCamps implements ViewingCamps{
	/**
     * Lists the names of camps created by the specified staff member.
     *
     * @param staff The {@code Staff} member whose created camps are listed.
     * @return A list of camp names created by the staff member, or {@code null} if no camps are created.
     */
    public List<String> listCreatedCamps(Staff staff) {
    	List<Camp>campsCreated = staff.getCreatedCamps();
    	if (campsCreated.size() > 0) {
    		List<String> campNames = new ArrayList<>();
        	int count = 0;
        	for(Camp camp : campsCreated) {
    			campNames.add(camp.getCampName());
    		}
        	return campNames;
    	    // The rest of your code for editing camps
    	} else {
    	    System.out.println("You haven't created any camps.");
    	}
    	return null;
    }
    /**
     * Displays information about the camps created by the specified staff member.
     *
     * @param staff The {@code Staff} member whose created camps are viewed.
     */
    public void viewCreatedCamps(Staff staff) {
        // View the camps created by this staff
    	List<Camp>campsCreated = staff.getCreatedCamps();
    	if(staff.getNumberOfCamps() == 0)
			System.out.println("Not created any Camps");
		else {
			int count = 1;
			for(Camp camp : campsCreated) {
				System.out.println("CAMP "+ (count++) + ":");
				this.campInfo(camp);
			}
		}
    }
    /**
     * Displays detailed information about a specific camp.
     *
     * @param camp The {@code Camp} for which information is displayed.
     */
    public void campInfo(Camp camp) {
		System.out.println("\tCampName: " + camp.getCampName());
		System.out.println("\tDates:\n\tFrom: " + camp.getOpenDate() + " To: " + camp.getCloseDate());
		System.out.println("\tRegistration Closing Date: " + camp.getRegCloseDate());
		System.out.println("\tCamp Availability: " + camp.getCampAccessible());
		if(camp.getCampAccessible() == CampAvailability.SCHOOL)
			System.out.println("\tCamp School: " + camp.getCampSchool());
		System.out.println("\tLocation: " + camp.getLocation());
		System.out.println("\tTotal Slots: " + camp.getTotalSlots());
		System.out.println("\tSlots Available: " + camp.getRemainingSlots());
		System.out.println("\tDescription: " + camp.getDescription());
		System.out.println("\tStaff Incharge: " + camp.getStaffIncharge().getName());
	}
}
