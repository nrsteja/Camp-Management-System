package CAMs;

import java.util.List;
/**
Provides a static method to handle the submission of inquiries for a student, allowing them to select a camp from the available camps list.
@author Jeevika, Mathangi, Teja, Vikaas
@version 3.0
@since 2023-11-25
*/
public class EnquiryPage {
	/**
     * Displays available camps and allows a student to select a camp to submit an inquiry.
     *
     * @param allCamps The list of all camps available.
     * @param student  The student submitting the inquiry.
     * @return The selected camp for submitting an inquiry, or null if no available camps.
     */
	public static Camp enquiryPage(List<Camp> allCamps, Student student) {
		Camp selectedCamp = null;
		AvailableCamps availCamps = new StudentAvailableCamps();
		List<Camp> availableCampsToEnquire = availCamps.getAvailableCampsToEnquire(allCamps, student);
	    if (availableCampsToEnquire.isEmpty()) {
	        System.out.println("No available camps to submit inquiries.");
	        return null;
	    } 
	    else {
	        System.out.println("Available Camps:");
	        for (int i = 0; i < availableCampsToEnquire.size(); i++) {
	            System.out.println((i + 1) + ". " + availableCampsToEnquire.get(i).getCampName());
	        }
	        boolean found = false;
	       
	        while(!found) {
		        String prompt = "Enter the number of the camp to submit an inquiry: ";
		        int campChoice = InputErrorChecking.getUserChoice(prompt, 1, availableCampsToEnquire.size());
	            selectedCamp = availableCampsToEnquire.get(campChoice - 1);
	            found = true;
	        }
	    }
	    return selectedCamp;
	}
}
