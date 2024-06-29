package CAMs;

import java.util.List;
/**
Manages the withdrawal process for students, including displaying registered camps,withdrawing from a selected camp, and checking withdrawal eligibility.
@author Jeevika, Mathangi, Teja, Vikaas
@version 3.0
@since 2023-11-25
*/
public class StudentWithdrawal implements WithdrawFromCamps {
	/**
     * Allows a student to withdraw from a registered camp.
     *
     * @param student The student withdrawing from the camp.
     */
	public void withdrawFromCamps(Student student) {
		RegistrationCamps campRegistration = new StudentRegistrationCamps();
    	RegistrationCamps registrationStudent = new StudentRegistrationCamps();
	    List<Camp> registeredCamps = student.getRegisteredCamps();
	    boolean checkRegisteredCamps = registrationStudent.viewCampRegistrationDetails(student); // Show registered camps
	    if(checkRegisteredCamps) {
		    System.out.println("Enter the number of the camp you want to withdraw from: ");
		    int choice = InputErrorChecking.getUserChoice("Enter your choice: ", 1, registeredCamps.size());

	        Camp selectedCamp = registeredCamps.get(choice - 1);
	        if(campRegistration.isCampCommitteeMember(selectedCamp, student))
	        	System.out.println("Sorry you cannot withdraw from the camp as you are a committee Member");
	        else {
	        	if (this.checkWithdrawFromCamp(selectedCamp, student)) {
	            System.out.println("You have successfully withdrawn from the camp: " + selectedCamp.getCampName());
	        	} 
	        	else {
	        		System.out.println("Withdrawal failed. Please try again.");
	        	}
	        }
	    }
    }
	/**
     * Checks if a student is eligible to withdraw from a specific camp.
     *
     * @param selectedCamp The camp from which withdrawal eligibility is checked.
     * @param student      The student attempting to withdraw.
     * @return {@code true} if the student is eligible to withdraw, {@code false} otherwise.
     */
	public boolean checkWithdrawFromCamp(Camp selectedCamp, Student student) {
        if (student.isRegisteredForCamp(selectedCamp)) {
        	student.removeRegisteredCamps(selectedCamp); // Assuming registeredCamps is a list in the Student class
            selectedCamp.removeAttendee(student); // Assuming removeAttendee method in Camp class removes the student from the camp's attendee list
            student.setWithdrawnCamps(selectedCamp);
            return true;
        }
        return false;
    }
}
