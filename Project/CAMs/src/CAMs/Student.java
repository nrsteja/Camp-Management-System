package CAMs;
import java.util.ArrayList;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
/**
The class represents a user in a system or organization who is a student.
It extends the `User` class, inheriting basic user information and functionality.
Studdents have additional attributes and functionality specific to their role.
@author Jeevika, Mathangi, Teja, Vikaas
@version 3.0
@since 2023-11-25
*/
public class Student extends User {
	private String name;
	private String email;
	private Schools faculty;
	private String password;
	private List<Camp> registeredCamps = new ArrayList<>(); // To store camps the student is registered for
	private List<Camp> availableCamps;
	private List<Enquiry> studentEnquiries = new ArrayList<>();
	private List<Camp> withdrawnCamps = new ArrayList<>();
	private int numberOfRegisteredCamps = 0;
	/**
	 * Indicates whether a student is logging in for the first time.
	 */
	public boolean firstTimeLoginStudent = true;
	/**
	 * Boolean value indicating whether the user is a camp committee member.
	 */
	private boolean isCampCommitteeMember;
	/**
	 * The camp to be converted into a committee by the user.
	 */
	private Camp campToConvertAsCommittee;
	private boolean enteringFirstTime = true;
	/**
     * Constructs a new `Student` instance with the specified name, email, and faculty.
     * 
     * @param name The name of the student.
     * @param email The email address of the student.
     * @param faculty The faculty to which the student belongs, represented by an instance of the `Schools` class.
     */
	public Student(String name, String email, Schools faculty) {
		super(name, email, faculty);
		availableCamps = new ArrayList<>();
		isCampCommitteeMember = false;
	}
	/**
     * Returns the user type associated with this object.
     *
     * @return The user type, which is always {@code UserType.STUDENT}.
     */
	public UserType getUserType() {return UserType.STUDENT;}
	/**
     * Prints the menu options for student members.
     */
	public void printMenu() {
		System.out.println("**********STUDENT LOGIN MENU**********");
		System.out.println("(1) Change Password");
		System.out.println("(2) Camps Availability");
		System.out.println("(3) Camps to Register");
		System.out.println("(4) Submit  Enquiries about Camps");
		System.out.println("(5) My Camp Registration Details");
		System.out.println("(6) Replies to enquiries submitted");
		System.out.println("(7) Request to withdraw from Camps");
		System.out.println("(8) View Camp Committee Status");
		System.out.println("(9) Log Out");
	}
	/**
     * Gets a specific camp based on its index in the registered camps list.
     *
     * @param index The index of the camp in the registered camps list.
     * @return The camp at the specified index.
     */
	public Camp getCamp(int index) {
		Camp camp = registeredCamps.get(index);
		return camp;
	}
	/**
     * Gets the list of withdrawn camps.
     *
     * @return The list of withdrawn camps.
     */
	public List<Camp> getWithdrawnCamps() {
		return withdrawnCamps;
	}
	/**
	 * Adds the specified camp to the list of withdrawn camps.
	 *
	 * @param selectedCamp The {@code Camp} to be added to the list of withdrawn camps.
	 */
	public void setWithdrawnCamps(Camp selectedCamp) {
		withdrawnCamps.add(selectedCamp);
	}
	/**
     * Displays the camp committee status for the student.
     */
	public void viewCampCommitteeStatus() {
	    if (isCampCommitteeMember) {
	        System.out.println("You are a camp committee member.");
	    } else {
	        System.out.println("You are not a camp committee member.");
	    }
	}
	/**
	 * It checks and returns the camp committee membership status for the student.
	 *
	 * @return {@code true} if the student is a camp committee member, {@code false} otherwise.
	 */
	public boolean getIsCampCommitteeMember() {
		return this.isCampCommitteeMember;
	}
	/**
	 * The `setIsCampCommitteeMember` method sets the value as true.
	 */
	public void setIsCampCommitteeMember() {
		isCampCommitteeMember = true;
	}
	/**
	 * The `setCampCommitteeStatus` method sets the camp committee membership status for the student.
	 *
	 * @param selectedCamp The camp to be associated with the camp committee membership.
	 */
	public void setCampCommitteeStatus(Camp selectedCamp) {
		this.isCampCommitteeMember = true;
		campToConvertAsCommittee = selectedCamp;
	}
	/**
	 * It returns the camp associated with the camp committee membership.
	 *
	 * @return The camp associated with the camp committee membership.
	 */
	public Camp getCampToConvertAsCommittee() {
		return this.campToConvertAsCommittee;
	}
	/**
	 * Retrieves the boolean value indicating whether the user is entering the system for the first time.
	 *
	 * @return true if the user is entering the system for the first time, false otherwise.
	 */
	public boolean getEnteringFirstTime() {
		return enteringFirstTime;
	}
	/**
	 * Sets the flag indicating whether the user is entering the system for the first time.
	 */
	public void setEnteringFirstTime() {
		enteringFirstTime = false;
	}
	/**
	 * The `addRegisteredCamps` method adds a camp to the list of camps registered by the student.
	 *
	 * @param selectedCamp The camp to be added to the list of registered camps.
	 */
	public void addRegisteredCamps(Camp selectedCamp) {
		registeredCamps.add(selectedCamp);
	}
	/**
	 * This method checks if the student has withdrawn from a specific camp.
	 *
	 * @param camp The camp to be checked for withdrawal.
	 * @return {@code true} if the student has withdrawn from the camp, {@code false} otherwise.
	 */
	public boolean hasWithdrawnFromCamp(Camp camp) {
	    return withdrawnCamps.contains(camp);
	}
	/**
	 * Returns the number of camps the student is registered for.
	 *
	 * @return The number of registered camps.
	 */
	public int getNumberOfRegisteredCamps() {return this.numberOfRegisteredCamps;}
	/**
     * Checks if the student is registered for a specific camp.
     *
     * @param camp The camp to be checked for registration.
     * @return {@code true} if the student is registered for the camp, {@code false} otherwise.
     */
    public boolean isRegisteredForCamp(Camp camp) {
        return registeredCamps.contains(camp);
    }
    /**
     * Removes the specified camp from the list of registered camps for the current user.
     *
     * @param selectedCamp The Camp object to be removed from the list of registered camps.
     */
    public void removeRegisteredCamps(Camp selectedCamp) {
    	registeredCamps.remove(selectedCamp);
    }
    /**
     * Retrieves the list of camps that the student is registered for.
     *
     * @return A {@code List} of {@code Camp} objects representing the camps that the student is registered for.
     */
    public List<Camp> getRegisteredCamps() {
        return registeredCamps;
    }
    /**
     * Adds a new enquiry to the student's list of enquiries.
     *
     * @param enquiry The enquiry to be added.
     */
    public void addStudentEnquiry(Enquiry enquiry) {
        studentEnquiries.add(enquiry);
    }
    /**
     * Returns the list of enquiries submitted by the student.
     *
     * @return The list of student enquiries.
     */
    public List<Enquiry> getStudentEnquiries() {
        return studentEnquiries;
    }
    /**
     * Removes an enquiry from the student's list and marks it as processed.
     *
     * @param enquiry The enquiry to be removed.
     */
    public void removeStudentEnquiry(Enquiry enquiry) {
    	for(Enquiry checkEnquiry: studentEnquiries) {
    		if(checkEnquiry.equals(enquiry)) {
    			checkEnquiry.setProccessStatus();
    			studentEnquiries.remove(enquiry);
    		}
    	}
    }
    
}
