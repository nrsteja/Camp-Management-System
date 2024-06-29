package CAMs;

import java.util.List;
import java.util.Scanner;
/**
Implements the RegistrationCamps interface and provides methods specific to camp registration for Camp Committee Members.
@author Jeevika, Mathangi, Teja, Vikaas
@version 3.0
@since 2023-11-25
*/
public class CampCommitteeMemberRegistrationCamps implements RegistrationCamps{
	/**
	 * Static Scanner object for reading input from the standard input (System.in).
	 */
	public static Scanner input = new Scanner(System.in);
	/**
     * Checks if a Camp Committee Member can register for a specific camp.
     *
     * @param selectedCamp The camp to check registration for.
     * @param student      The Camp Committee Member attempting to register.
     * @return True if the registration is successful, false otherwise.
     */
	public boolean checkRegisterForCamp(Camp selectedCamp, Student student) {
		RegistrationCamps campRegistration = new StudentRegistrationCamps();
		CampCommitteeMember comMem = (CampCommitteeMember)student;
	    if (((StudentRegistrationCamps) campRegistration).isRegistrationOpen(selectedCamp) && !comMem.isRegisteredForCamp(selectedCamp)) {
	        if (selectedCamp.getRemainingSlots() > 0) {
	            // Check for date clashes with already registered camps
	            if (!DateConfiguration.hasDateClash(selectedCamp, comMem)) {
	                // Check if the camp is in the list of withdrawn camps
	                if (!comMem.hasWithdrawnFromCamp(selectedCamp)) {
	                    return true;
	                } else {
	                    System.out.println("You have previously withdrawn from this camp and cannot register again.");
	                }
	            } else {
	                System.out.println("You have a date clash with another registered camp.");
	            }
	        } else {
	            System.out.println("The camp is full, no more slots available for registration.");
	        }
	    } else if (comMem.isRegisteredForCamp(selectedCamp)) {
	        System.out.println("You are already registered for this camp.");
	    } else if (!((StudentRegistrationCamps) campRegistration).isRegistrationOpen(selectedCamp)) {
	        System.out.println("Registration for this camp is closed.");
	    }
	    return false;
	}
	/**
     * Registers a Camp Committee Member for a camp.
     *
     * @param student The Camp Committee Member registering.
     * @return True if the registration is successful, false otherwise.
     */
	public boolean registerForCamp(Student student) {
		AvailableCamps availCamps = new StudentAvailableCamps();
		CampCommitteeMember comMem = (CampCommitteeMember)student;
	    List<Camp> allCamps = CampDataManager.getAllCamps();
	    List<Camp> availableCamps = availCamps.getAvailableCamps(allCamps, comMem);

	    if (availableCamps.isEmpty()) {
	        System.out.println("No camps are available.");
	        return false;
	    }
	    
	    availableCamps = availCamps.viewAvailableCamps(allCamps, comMem);

	    System.out.println("Enter the number of the camp you want to register for: ");
	    int campChoice = InputErrorChecking.getUserChoice("Enter your choice: ", 1, availableCamps.size());

        Camp selectedCamp = availableCamps.get(campChoice - 1);
        if (this.checkRegisterForCamp(selectedCamp, comMem)) {
            if (!comMem.getIsCampCommitteeMember()) {
                boolean committeeChoice = inputConfirmation("Do you want to register as Camp committee Member (Y/N)?");
                if (committeeChoice) {
                	comMem.setCampCommitteeStatus(selectedCamp);
                }
            }
            selectedCamp.addAttendee(comMem);
            comMem.addRegisteredCamps(selectedCamp);
            System.out.println("You have successfully registered for the camp: " + selectedCamp.getCampName());
            return true;
        } else {
            System.out.println("Registration failed. Please try again.");
        }
	    return false;
	}
	/**
	 * Prompts the user for confirmation by displaying a message and reading a single character input.
	 * Accepts 'Y' or 'y' as confirmation, and any other character as rejection.
	 *
	 * @param message The message to display as a prompt for confirmation.
	 * @return True if the user confirms with 'Y' or 'y', false otherwise.
	 */
	public static boolean inputConfirmation(String message) {
	    System.out.print(message);
	    char choice = input.next().charAt(0);
	    return choice == 'Y' || choice == 'y';
	}
	/**
     * Views the camps registered by a Camp Committee Member.
     *
     * @param student The Camp Committee Member to view registered camps for.
     */
	public void viewRegisteredCamps(Student student) {
		CampCommitteeMember comMem = (CampCommitteeMember)student;
        System.out.println("Camps registered by " + comMem.getName() + ":");
        List<Camp> RegisteredCamps = comMem.getRegisteredCamps();
        int count = 1;
        for (Camp camp : RegisteredCamps) {
            System.out.println("(" + (count++) +") Camp Name: " + camp.getCampName());
            System.out.println("    Role: " + (this.isCampCommitteeMember(camp, comMem) ? "Camp Committee" : "Attendee"));
            // You can add more camp details here
        }
    }
	/**
     * Views the registration details for a Camp Committee Member.
     *
     * @param student The Camp Committee Member to view registration details for.
     * @return True if there are registered camps, false otherwise.
     */
	public boolean viewCampRegistrationDetails(Student student) {
		CampCommitteeMember comMem = (CampCommitteeMember)student;
    	if (comMem.getRegisteredCamps().isEmpty()) {
            System.out.println("You are not registered for any camps.");
            return false;
        } 
    	else {
    		this.viewRegisteredCamps(student);
        }
    	return true;
    }
	/**
     * Checks if a student is a Camp Committee Member for a specific camp.
     *
     * @param camp    The camp to check for committee membership.
     * @param student The student to check for committee membership.
     * @return True if the student is a committee member, false otherwise.
     */
	public boolean isCampCommitteeMember(Camp camp, Student student) {
		CampCommitteeMember comMem = (CampCommitteeMember)student;
		List<CampCommitteeMember> committeMembers = camp.getCommitteeMembers();
		if(committeMembers.isEmpty()) {
			return false;
		}
		else {
			for(CampCommitteeMember members: committeMembers) {
				if(members.equals(comMem))
					return true;
			}
		}
		return false;
	}
}
