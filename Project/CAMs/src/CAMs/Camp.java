package CAMs;

import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
/**
Includes information of all the camps including list of students registered as attendees and camp committee
@author Jeevika, Mathangi, Teja, Vikaas
@version 3.0
@since 2023-11-25
*/
public class Camp {
	/**
	 * Integer value that holds the total number of camps. Initially this is zero.
	 */
	public int totalCamps = 0;
	private String campName;
	private String openDate;
	private String closeDate;
	private String regCloseDate;
	/**
	 * Represents the availability status of the camp.
	 * This field is used to track the accessibility of the camp.
	 *
	 * @see CampAvailability
	 */
	private CampAvailability campAccessible;
	private String location;
	/**
	 * Integer value that holds the available slots.
	 */
	public int slotsAvailable;
	/**
	 * Integer value that holds the total number of slots.
	 */
	public int totalSlots;
	/**
	 * Represents the staff that is incharge.
	 */
	public Staff staffIncharge;
	private String description;
	private List<Student> students;
	private boolean visibility;
	/**
	 * Represents the school associated with a camp.
	 * This field holds an instance of the {@link Schools} class.
	 */
	private Schools campSchool;
	private List<Suggestion> approvedSuggestions = new ArrayList<>();
	private List<Student> attendees;
	private List<CampCommitteeMember> comMems = new ArrayList<>();
    //private List<String> studentEnquiries;
	private List<Enquiry> studentEnquiries;
    private List<Suggestion> suggestions;
    private List<String> replies;
    private List<Student> studentsWhoWithdrawn = new ArrayList<>();

    /**
	 * Static Scanner object for reading input from the standard input (System.in).
	 */
	public static Scanner input = new Scanner(System.in);
	/**
	 * Static instance used to perform validation and error checking on user inputs.
	 */
	public static InputErrorChecking inputErrorChecking = new InputErrorChecking();
	/**
     * Constructs new Camp with the specified parameters.
     *
     * @param campName        The name of the camp.
     * @param openDate        The opening date of the camp in the format "yyyy-mm-dd".
     * @param closeDate       The closing date of the camp in the format "yyyy-mm-dd".
     * @param regCloseDate    The registration closing date of the camp in the format "yyyy-mm-dd".
     * @param campAccessible  The availability status of the camp.
     * @param campSchool      The school associated with the camp.
     * @param location        The location of the camp.
     * @param totalSlots      The total number of slots available in the camp.
     * @param description     The description of the camp.
     * @param staffIncharge   The staff member in charge of the camp.
     * @param visibility      The visibility status of the camp for students.
     */
	public Camp(String campName, String openDate, String closeDate, String regCloseDate, CampAvailability campAccessible, Schools campSchool, String location, int totalSlots, String description, Staff staffIncharge, boolean visibility) {
		this.campName = campName;
		this.openDate = openDate;
		this.closeDate = closeDate;
		this.regCloseDate = regCloseDate;
		this.campAccessible = campAccessible;
		this.campSchool = campSchool;
		this.location = location;
		this.totalSlots = totalSlots;
		this.description = description;
		this.staffIncharge = staffIncharge;
		this.totalCamps+=1;
		this.visibility = visibility;
		
		replies = new ArrayList<>();
		attendees = new ArrayList<>();
        studentEnquiries = new ArrayList<>();
        suggestions = new ArrayList<>();
        students = new ArrayList<>();
	}
	/**
	 * Adds a suggestion to the collection of approved suggestions.
	 *
	 * @param suggestion The suggestion to be added to the approved suggestions.
	 */
	public void setApprovedSuggestions(Suggestion suggestion) {
		approvedSuggestions.add(suggestion);
	}
	/**
	 * Adds an enquiry to the list of student enquiries for the camp.
	 *
	 * @param enquiry The enquiry to add.
	 */
	public void addEnquiry(Enquiry enquiry) { studentEnquiries.add(enquiry);}
	/**
	 * Retrieves the list of enquiries for the camp.
	 *
	 * @return The list of enquiries.
	 */
    public List<Enquiry> getEnquiries() { return studentEnquiries;}
    /**
     * Retrieves remaining available slots for the camp.
     *
     * @return The number of remaining slots.
     */
	public int getRemainingSlots() { return totalSlots - attendees.size()-comMems.size();}
	/**
	 * Adds a camp committee member to the list of committee members.
	 *
	 * @param comMem The committee member to add.
	 */
	public void addCommitteeMember(CampCommitteeMember comMem) {this.comMems.add(comMem);}
	/**
	 * Retrieves the list of committee members for the camp.
	 *
	 * @return The list of committee members.
	 */
	public List<CampCommitteeMember> getCommitteeMemberes() { return this.comMems;}
	/**
	 * Retrieves the list of committee members for the camp.
	 *
	 * @return The list of committee members.
	 */
	public List<CampCommitteeMember> getCommitteeMembers() { return comMems;}
	/**
	 * Retrieves the list of replies for the camp.
	 *
	 * @return The list of replies.
	 */
	public List<String> getReplies() { return replies;}
	/**
	 * Stores a reply in the list of replies for the camp.
	 *
	 * @param reply The reply to store.
	 */
    public void storeReply(String reply) { replies.add(reply);}	
    /**
     * Retrieves the list of attendees for the camp.
     *
     * @return The list of attendees.
     */
	public List<Student> getAttendees() { return attendees;}
	/**
	 * Adds a student as an attendee to the camp.
	 *
	 * @param student The student to add as an attendee.
	 */
    public void addAttendee(Student student) { attendees.add(student);}
    /**
     * Removes a student from the list of attendees for the camp.
     *
     * @param student The student to remove.
     */
    public void removeAttendee(Student student) { attendees.remove(student);}
    /**
     * Retrieves the list of student enquiries for the camp.
     *
     * @return The list of student enquiries.
     */
    public List<Enquiry> getStudentEnquiries() { return studentEnquiries;}
    /**
     * Retrieves the list of suggestions for the camp.
     *
     * @return The list of suggestions.
     */
    public List<Suggestion> getSuggestions() { return suggestions;}
    /**
     * Removes a student enquiry from the list of enquiries for the camp.
     *
     * @param enquiry The enquiry to remove.
     */
    public void removeStudentEnquiry(Enquiry enquiry) { staffIncharge.removeSuggestionFromInbox(enquiry);}
    /**
     * Adds a student enquiry to the list of enquiries for the camp.
     *
     * @param enquiry The enquiry to add.
     */
    public void addStudentEnquiry(Enquiry enquiry) {
        studentEnquiries.add(enquiry);
        staffIncharge.addSuggestionToInbox(enquiry);
    }
    /**
     * Adds a suggestion to the list of suggestions for the camp.
     *
     * @param suggestion The suggestion to add.
     */
    public void addSuggestion(Suggestion suggestion) {
        suggestions.add(suggestion);
        staffIncharge.addSuggestionToInbox(suggestion);
    }
	
	//Accessor or Getter
    /**
     * Retrieves the name of the camp.
     *
     * @return The name of the camp.
     */
	public String getCampName() { return this.campName;}
	
	/**
	 * Retrieves the opening date of the camp.
	 *
	 * @return The opening date of the camp.
	 */
	public String getOpenDate() {
	    return this.openDate;
	}

	/**
	 * Retrieves the closing date of the camp.
	 *
	 * @return The closing date of the camp.
	 */
	public String getCloseDate() {
	    return this.closeDate;
	}

	/**
	 * Retrieves the school associated with the camp.
	 *
	 * @return The school associated with the camp.
	 */
	public Schools getCampSchool() {
	    return this.campSchool;
	}

	/**
	 * Retrieves the registration closing date of the camp.
	 *
	 * @return The registration closing date of the camp.
	 */
	public String getRegCloseDate() {
	    return this.regCloseDate;
	}

	/**
	 * Retrieves the accessibility status of the camp.
	 *
	 * @return The accessibility status of the camp.
	 */
	public CampAvailability getCampAccessible() {
	    return this.campAccessible;
	}

	/**
	 * Retrieves the location of the camp.
	 *
	 * @return The location of the camp.
	 */
	public String getLocation() {
	    return this.location;
	}

	/**
	 * Retrieves the total number of slots for the camp.
	 *
	 * @return The total number of slots for the camp.
	 */
	public int getTotalSlots() {
	    return this.totalSlots;
	}

	/**
	 * Retrieves the staff member in charge of the camp.
	 *
	 * @return The staff member in charge of the camp.
	 */
	public Staff getStaffIncharge() {
	    return this.staffIncharge;
	}

	/**
	 * Retrieves the description of the camp.
	 *
	 * @return The description of the camp.
	 */
	public String getDescription() {
	    return this.description;
	}

	/**
	 * Retrieves the visibility status of the camp for students.
	 *
	 * @return The visibility status of the camp for students.
	 */
	public boolean getVisibility() {
	    return this.visibility;
	}
	
	//Mutator or Setters
	/**
	 * Sets the name of the camp.
	 *
	 * @param campName The new name for the camp.
	 */
	public void setCampName(String campName) {
	    this.campName = campName;
	}

	/**
	 * Sets the opening date of the camp.
	 *
	 * @param openDate The new opening date for the camp.
	 */
	public void setOpenDate(String openDate) {
	    this.openDate = openDate;
	}

	/**
	 * Sets the closing date of the camp.
	 *
	 * @param closeDate The new closing date for the camp.
	 */
	public void setCloseDate(String closeDate) {
	    this.closeDate = closeDate;
	}

	/**
	 * Sets the school associated with the camp.
	 *
	 * @param campSchool The new school associated with the camp.
	 */
	public void setCampSchool(Schools campSchool) {
	    this.campSchool = campSchool;
	}

	/**
	 * Sets the registration closing date of the camp.
	 *
	 * @param regCloseDate The new registration closing date for the camp.
	 */
	public void setRegCloseDate(String regCloseDate) {
	    this.regCloseDate = regCloseDate;
	}

	/**
	 * Sets the accessibility status of the camp.
	 *
	 * @param campAccessible The new accessibility status for the camp.
	 */
	public void setCampAccessible(CampAvailability campAccessible) {
	    this.campAccessible = campAccessible;
	}

	/**
	 * Sets the location of the camp.
	 *
	 * @param location The new location for the camp.
	 */
	public void setlocation(String location) {
	    this.location = location;
	}

	/**
	 * Sets the total number of slots for the camp.
	 *
	 * @param totalSlots The new total number of slots for the camp.
	 */
	public void setTotalSlots(int totalSlots) {
	    this.totalSlots = totalSlots;
	}

	/**
	 * Sets the staff member in charge of the camp.
	 *
	 * @param staffIncharge The new staff member in charge of the camp.
	 */
	public void setStaffIncharge(Staff staffIncharge) {
	    this.staffIncharge = staffIncharge;
	}

	/**
	 * Sets the description of the camp.
	 *
	 * @param description The new description for the camp.
	 */
	public void setDescription(String description) {
	    this.description = description;
	}

	/**
	 * Sets the visibility status of the camp for students.
	 *
	 * @param visibility The new visibility status for the camp.
	 */
	public void setVisiblity(boolean visibility) {
	    this.visibility = visibility;
	}
	/**
	 * Retrieves the total number of camps.
	 *
	 * @return The total number of camps.
	 */
	public int noOfCamps() { return totalCamps;}
	/**
	 * Displays the camp editing menu.
	 */
	 public void campMenu() {
		System.out.println("Edit Menu");
		System.out.println("(1) Camp Name");
		System.out.println("(2) Opening Date(yyyy-mm-dd)");
		System.out.println("(3) Closing Date(yyyy-mm-dd)");
		System.out.println("(4) Registration closing Date(yyyy-mm-dd)");
		System.out.println("(5) Camp Availability");
		System.out.println("(6) Total Slots");
		System.out.println("(7) Location");
		System.out.println("(8) Description");
		System.out.println("(9) Visibility for Students");
		System.out.println("(10) Exit");
	}
	 /**
	  * Deletes a student enquiry from the collection of student enquiries and
	  * removes the associated suggestion from the staff in charge's inbox.
	  *
	  * @param enquiry The student enquiry to be deleted.
	  */
	public void deleteStudentEnquiry(Enquiry enquiry) {
		studentEnquiries.remove(enquiry);
		staffIncharge.removeSuggestionFromInbox(enquiry);
	}
	/**
	 * Deletes a suggestion from the collection of suggestions and removes the
	 * associated suggestion from the staff in charge's inbox.
	 *
	 * @param suggestion The suggestion to be deleted.
	 */
	public void deleteSuggestion(Suggestion suggestion) {
        suggestions.remove(suggestion);
        staffIncharge.removeSuggestionFromInbox(suggestion);
    }
	/**
	 * Sets a new suggestion for a suggestion at the specified index in the collection
	 * of suggestions.
	 *
	 * @param newSuggestion The new suggestion to be set.
	 * @param index         The index of the suggestion in the collection.
	 */
	public void setSuggestion (String newSuggestion, int index) {
		suggestions.get(index).setSuggestion(newSuggestion);
	}
	
	
}
