package CAMs;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
/**
The class represents a student who serves on a camp committee. It extends the `Student` class, inheriting basic student information and behavior.
@author Jeevika, Mathangi, Teja, Vikaas
@version 3.0
@since 2023-11-25
*/
public class CampCommitteeMember extends Student {
	/**
     * Total points accumulated by the camp committee member.
     */
    private int points;
    /**
     * List of camps the committee member is currently registered for.
     */
    private List<Camp> registeredCamps = new ArrayList<>();
    /**
     * List of student enquiries associated with the committee member.
     */
    private List<Enquiry> studentEnquiries = new ArrayList<>();
    /**
     * List of camps the committee member has withdrawn from.
     */
    private List<Camp> withdrawnCamps = new ArrayList<>();
    /**
     * List of available camps for the committee member.
     */
    private List<Camp> availableCamps;
    /**
     * List of suggestions submitted by the committee member.
     */
    private List<Suggestion> suggestionsSubmitted = new ArrayList<>();
    /**
     * The camp assigned to the committee member.
     */
    private Camp committeeCamp;
    /**
     * Constructs a new CampCommitteeMember with the specified details.
     *
     * @param name               The name of the camp committee member.
     * @param email              The email address of the camp committee member.
     * @param faculty            The school faculty to which the camp committee member belongs.
     * @param getRegisteredCamps The list of camps the committee member is registered for.
     * @param getStudentEnquiries The list of student enquiries associated with the committee member.
     * @param getwithdrawnCamps  The list of camps the committee member has withdrawn from.
     */
    public CampCommitteeMember(String name, String email, Schools faculty, List<Camp> getRegisteredCamps, List<Enquiry> getStudentEnquiries, List<Camp> getwithdrawnCamps) {
        super(name, email, faculty);
        this.registeredCamps = getRegisteredCamps;
        this.studentEnquiries = getStudentEnquiries;
        this.withdrawnCamps = getwithdrawnCamps;
        points = 0; // Initialize points to 0 for a new committee member
    }
    /**
     * Gets the camp assigned to the committee member.
     *
     * @return The camp assigned to the committee member.
     */
    public Camp getCommitteeCamp() { return this.committeeCamp;}
    /**
     * Sets the camp assigned to the committee member.
     *
     * @param camp The camp to be assigned to the committee member.
     */
    public void setCommitteeCamp(Camp camp) { this.committeeCamp = camp;}
    /**
     * Gets the total points accumulated by the committee member.
     *
     * @return The total points accumulated by the committee member.
     */
    public int getPoints() { return this.points;}
    /**
     * Checks if there is a date clash with the selected camp.
     *
     * @param selectedCamp The camp to check for date clash.
     * @return {@code true} if there is a date clash; {@code false} otherwise.
     */
    public boolean hasDateClash(Camp selectedCamp) {
    	return DateConfiguration.hasDateClash(selectedCamp, this);
    }
    /**
     * Gets list of camps the committee member is registered for.
     *
     * @return The list of camps the committee member is registered for.
     */
    public List<Camp> getRegisteredCamps() {
        return registeredCamps;
    }
    /**
     * Sets the registered camps of the camp committee member.
     * Prints "Empty" if the list of old camps is empty; otherwise, prints "Not Empty".
     */
    public void setRegisteredCamps() {
    	List<Camp> oldCamps = super.getRegisteredCamps();
    	if (oldCamps.isEmpty()) {
    		System.out.println("Empty");
    	}
    	else
    		System.out.println("Not Empty");
        this.registeredCamps = super.getRegisteredCamps();
    }
    /**
     * Sets the camp committee status for the given student.
     *
     * @param student The student to convert to a camp committee member.
     */
	public void setCampCommitteeStatus(Student student) {
		this.setIsCampCommitteeMember();
		Camp selectedCamp = student.getCampToConvertAsCommittee();
		selectedCamp.addCommitteeMember(this);
		selectedCamp.removeAttendee(student);
		
	}
	/**
	 * Gets list of student enquiries associated with camp committee member.
	 *
	 * @return The list of student enquiries.
	 */
    public List<Enquiry> getStudentEnquiries() {
        return studentEnquiries;
    }
    /**
     * Sets student enquiries of the camp committee member.
     */
    public void setStudentEnquiries() {
        this.studentEnquiries = super.getStudentEnquiries();
    }
    /**
     * Gets list of withdrawn camps by the camp committee member.
     *
     * @return The list of withdrawn camps.
     */
    public List<Camp> getWithdrawnCamps() {
        return withdrawnCamps;
    }
    /**
     * Sets withdrawn camps of the camp committee member.
     */
    public void setWithdrawnCamps() {
        this.withdrawnCamps = super.getWithdrawnCamps();
    }
    /**
     * Checks if camp committee member is registered for a specific camp.
     *
     * @param camp The camp to check registration status.
     * @return {@code true} if registered; {@code false} otherwise.
     */
    public boolean isRegisteredForCamp(Camp camp) {
        return registeredCamps.contains(camp);
    }
    /**
     * Checks if the current user is a member of the specified camp.
     *
     * @param camp The Camp object to check for membership.
     * @return true if the user is a registered attendee or camp committee member of the specified camp, false otherwise.
     */
    public boolean isCampMember(Camp camp) {
		List<Student> registeredStudents = camp.getAttendees();
		List<CampCommitteeMember> registeredMember = camp.getCommitteeMemberes();
		for(CampCommitteeMember comMember: registeredMember) {
			if(comMember.equals(this)) {
				return true;
			}
		}
		for(Student students: registeredStudents) {
			if(students.equals(this)) {
				return true;
			}
		}
		return false;
	}
    /**
     * Prints the menu options for the camp committee member.
     */
    public void printMenu() {
		System.out.println("**********COMMITTEE MEMBER LOGIN MENU**********");
		System.out.println("(1) Change Password");
		System.out.println("(2) Camps Availability");
		System.out.println("(3) Camps to Register");
		System.out.println("(4) Enquiries & Suggestions Page");
		System.out.println("(5) My Camp Registration Details");
		System.out.println("(6) Replies to enquiries submitted");
		System.out.println("(7) Request to withdraw from Camps");
		System.out.println("(8) View Camp Committee Status");
		System.out.println("(9) Report Generation");
		System.out.println("(10) Log Out");
	}

    // Camp committee members can view the details of the camp they have registered for
//    public void viewCampDetails(Camp camp) {
//        if (this.isCampCommitteeMember(camp)) {
//            // Allow committee members to view camp details
//            camp.campInfo();
//        } else {
//            System.out.println("You are not a committee member for this camp.");
//        }
//    }
    /**
     * Awards one point for each suggestion and each inquiry replied by the camp committee member.
     */
    public void earnPointsForActivity() {
        points++;
    }
    /**
     * Gets the list of suggestions submitted by the camp committee member.
     *
     * @return The list of suggestions.
     */
    public List<Suggestion> getSuggestions() {
        return suggestionsSubmitted;
    }
    /**
     * Adds a suggestion to the list of suggestions submitted by the camp committee member.
     *
     * @param suggestion The suggestion to add.
     */
    public void addSuggestions(Suggestion suggestion) {
    	suggestionsSubmitted.add(suggestion);
    }
    /**
     * Removes a suggestion from the list of suggestions submitted by the camp committee member.
     *
     * @param suggestion The suggestion to remove.
     */
    public void removeSuggestion(Suggestion suggestion) {
    	suggestionsSubmitted.remove(suggestion);
    }
    /**
     * Changes the status of a suggestion to processed.
     *
     * @param suggestion The suggestion to update.
     */
    public void changeStatus(Suggestion suggestion) {
    	for(Suggestion suggest: suggestionsSubmitted) {
    		if(suggest.equals(suggestion)) {
    			suggest.setProccessStatus();
    		}
    	}
    }
    /**
     * Adds the specified camp to the list of registered camps for the current user.
     *
     * @param selectedCamp The Camp object to be added to the list of registered camps.
     */
    public void addRegisteredCamps(Camp selectedCamp) {
		registeredCamps.add(selectedCamp);
	}
    /**
     * Removes the specified camp from the list of registered camps for the current user.
     *
     * @param selectedCamp The Camp object to be removed from the list of registered camps.
     */
    public void removeRegisteredCamps(Camp selectedCamp) {
    	registeredCamps.remove(selectedCamp);
    }
  
    
}
