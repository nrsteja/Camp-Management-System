package CAMs;

import java.util.List;
/**
Provides a method to generate and display a list of camps created by a staff member.
@author Jeevika, Mathangi, Teja, Vikaas
@version 3.0
@since 2023-11-25
*/
public class CampListGenerate {
	/**
     * Generates and displays a list of camps created by a staff member,
     * allowing the user to select a camp from the list.
     *
     * @param staff The staff member for whom the camp list is generated.
     * @return The selected camp from the list, or null if no camps are created.
     */
	public static Camp campListGenerate(Staff staff) {
		// TODO Auto-generated method stub
		ViewingCamps viewCamps = new StaffViewCamps();
		List<Camp> createdCamps = staff.getCreatedCamps();
		viewCamps.viewCreatedCamps(staff);
		if(createdCamps.isEmpty()) {
			System.out.println("Sorry havent created any camps!!!");
			return null;
		}
		else {
			System.out.println("Created Camps:");
            for (int i = 0; i < createdCamps.size(); i++) {
                System.out.println((i + 1) + ". " + createdCamps.get(i).getCampName());
            }
			Camp selectedCamp = null;
            boolean correctChoice = false;
            while(!correctChoice) {
            	int campChoice = InputErrorChecking.getUserChoice("Enter the camp number: ", 1, createdCamps.size());
	        	correctChoice = true;
	            selectedCamp = createdCamps.get(campChoice - 1);        
        	}
            return selectedCamp;
		}
	}
}
