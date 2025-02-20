package CAMs;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
/**
Manages the registration process for students, including checking eligibility, registering for camps, and providing information about registered camps.
@author Jeevika, Mathangi, Teja, Vikaas
@version 3.0
@since 2023-11-25
*/
public class StudentRegistrationCamps implements RegistrationCamps {
	/**
	 * Static Scanner object for reading input from the standard input (System.in).
	 */
	public static Scanner input = new Scanner(System.in);
	/**
     * Checks if a student is eligible to register for a specific camp.
     *
     * @param selectedCamp The camp for which registration eligibility is checked.
     * @param student      The student attempting to register.
     * @return {@code true} if the student is eligible to register, {@code false} otherwise.
     */
	public boolean checkRegisterForCamp(Camp selectedCamp, Student student) {
	    if (this.isRegistrationOpen(selectedCamp) && !student.isRegisteredForCamp(selectedCamp)) {
	        if (selectedCamp.getRemainingSlots() > 0) {
	            // Check for date clashes with already registered camps
	            if (!DateConfiguration.hasDateClash(selectedCamp, student)) {
	                // Check if the camp is in the list of withdrawn camps
	                if (!student.hasWithdrawnFromCamp(selectedCamp)) {
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
	    } else if (student.isRegisteredForCamp(selectedCamp)) {
	        System.out.println("You are already registered for this camp.");
	    } else if (!this.isRegistrationOpen(selectedCamp)) {
	        System.out.println("Registration for this camp is closed.");
	    }
	    return false;
	}
	/**
     * Allows a student to register for an available camp.
     *
     * @param student The student registering for the camp.
     * @return {@code true} if the registration is successful, {@code false} otherwise.
     */
	public boolean registerForCamp(Student student) {
		AvailableCamps availCamps = new StudentAvailableCamps();
	    List<Camp> allCamps = CampDataManager.getAllCamps();
	    List<Camp> availableCamps = availCamps.getAvailableCamps(allCamps, student);

	    if (availableCamps.isEmpty()) {
	        System.out.println("No camps are available.");
	        return false;
	    }

	    availableCamps = availCamps.viewAvailableCamps(allCamps, student);

	    System.out.println("Enter the number of the camp you want to register for: ");
	    int campChoice = InputErrorChecking.getUserChoice("Enter your choice: ", 1, availableCamps.size());

        Camp selectedCamp = availableCamps.get(campChoice - 1);
        if (this.checkRegisterForCamp(selectedCamp, student)) {
            if (!student.getIsCampCommitteeMember()) {
                boolean committeeChoice = inputConfirmation("Do you want to register as Camp committee Member (Y/N)?");
                if (committeeChoice) {
                	student.setCampCommitteeStatus(selectedCamp);
                }
            }
            selectedCamp.addAttendee(student);
            student.addRegisteredCamps(selectedCamp);
            System.out.println("You have successfully registered for the camp: " + selectedCamp.getCampName());
            return true;
        } else {
            System.out.println("Registration failed. Please try again.");
        }
	    return false;
	}
	 /**
     * Takes user input and returns a boolean based on the confirmation choice.
     *
     * @param message The message to display for user confirmation.
     * @return {@code true} if the user confirms (Y or y), {@code false} otherwise.
     */
	public static boolean inputConfirmation(String message) {
	    System.out.print(message);
	    char choice = input.next().charAt(0);
	    return choice == 'Y' || choice == 'y';
	}
	/**
     * Displays the camps registered by a specific student.
     *
     * @param student The student for whom registered camps are displayed.
     */
	public void viewRegisteredCamps(Student student) {
        System.out.println("Camps registered by " + student.getName() + ":");
        List<Camp> RegisteredCamps = student.getRegisteredCamps();
        int count = 1;
        for (Camp camp : RegisteredCamps) {
            System.out.println("("+(count++)+") " + "Camp Name: " + camp.getCampName());
            System.out.println("    Role: " + (this.isCampCommitteeMember(camp, student) ? "Camp Committee" : "Attendee"));
            // You can add more camp details here
        }
    }
	/**
     * Displays detailed registration information about camps registered by a student.
     *
     * @param student The student for whom registration details are displayed.
     * @return {@code true} if the student is registered for any camps, {@code false} otherwise.
     */
	public boolean viewCampRegistrationDetails(Student student) {
    	if (student.getRegisteredCamps().isEmpty()) {
            System.out.println("You are not registered for any camps.");
            return false;
        } 
    	else {
    		this.viewRegisteredCamps(student);
        }
    	return true;
    }
	/**
     * Checks if the registration for a specific camp is open.
     *
     * @param camp The camp for which registration status is checked.
     * @return {@code true} if registration is open, {@code false} otherwise.
     */
	public boolean isRegistrationOpen(Camp camp) {
	    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	    try {
	        Date currentDate = new Date();  // Get the current date
	        Date registrationClosingDate = dateFormat.parse(camp.getRegCloseDate());

	        // Compare the current date with the registration closing date
	        if (currentDate.before(registrationClosingDate)) {
	            return true;  // Registration is open
	        }
	    } catch (ParseException e) {
	        e.printStackTrace(); // Handle the parse exception
	    }
	    return false;  // Registration is closed
	}
	/**
     * Checks if a student is a committee member for a specific camp.
     *
     * @param camp    The camp for which committee membership is checked.
     * @param student The student for whom committee membership is checked.
     * @return {@code true} if the student is a committee member, {@code false} otherwise.
     */
	public boolean isCampCommitteeMember(Camp camp, Student student) {
		List<CampCommitteeMember> committeMembers = camp.getCommitteeMembers();
		if(committeMembers.isEmpty()) {
			return false;
		}
		else {
			for(CampCommitteeMember members: committeMembers) {
				if(members.equals(student))
					return true;
			}
		}
		return false;
	}
}
