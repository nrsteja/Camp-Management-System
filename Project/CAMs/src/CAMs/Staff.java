package CAMs;

import java.util.ArrayList;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.IOException;
import java.util.List;
import java.time.LocalDateTime;
import java.time.Duration;
import java.time.format.DateTimeFormatter;
/**
The class represents a staff member in a system or organization.
It extends the `User` class, inheriting basic user information and functionality.
Staff members typically have additional responsibilities and permissions in the system.
@author Jeevika, Mathangi, Teja, Vikaas
@version 3.0
@since 2023-11-25
*/
public class Staff extends User{
	/**
	 * Integer value representing the number of camps that has been initialised to zero in the start.
	 */
	private static int numberOfCamps = 0;
	private int points = 0;
	private String name;
	private String email;
	private String faculty;
	/**
	 * Instance of CampDataManager used for managing camp-related data.
	 */
	public CampDataManager campDataManager = new CampDataManager();
	private List<Camp> campsCreated = new ArrayList<>();
	private List<Query> suggestionInbox = new ArrayList<>();
	/**
     * Constructs a new `Staff` instance with the specified name, email, and faculty.
     * 
     * @param name The name of the staff member.
     * @param email The email address of the staff member.
     * @param faculty The faculty to which the staff member belongs, represented by an instance of the `Schools` class.
     */
	public Staff(String name, String email, Schools faculty) {
		super(name, email, faculty);
	}
	/**
	 * Returns the user type associated with this object.
	 *
	 * @return The user type, which is always {@code UserType.STAFF}.
	 */
	public UserType getUserType() {return UserType.STAFF;}
	/**
     * Prints the menu options for staff members.
     */
	public void printMenu() {
		System.out.println("**********STAFF LOGIN MENU**********");
		System.out.println("(1) Change Password");
		System.out.println("(2) Camp Settings");
		System.out.println("(3) Suggestions Inbox");
		System.out.println("(4) Ongoing Camps");
		System.out.println("(5) Logout");
	}
	/**
     * Displays the camp settings menu options.
     */
	public void CampSettings() {
		System.out.println("**********CAMP SETTINGS*********");
		System.out.println("(1) Create Camp");
		System.out.println("(2) Edit Camp");
		System.out.println("(3) Delete Camp");
		System.out.println("(4) Camp Report Generation");
		System.out.println("(5) Back");
	}
	/**
     * Gets the camp at the specified index from the list of created camps.
     *
     * @param index The index of the camp to retrieve.
     * @return The camp at the specified index.
     */
	public Camp getCamp(int index) {
		Camp camp = campsCreated.get(index);
		return camp;
	}
	/**
     * Gets the list of camps created by the staff member.
     *
     * @return The list of camps created.
     */
	public List<Camp> getCreatedCamps() {
		return campsCreated;
	}
	/**
     * Gets the number of camps
     *
     * @return The number of camps created.
     */
	public int getNumberOfCamps() {
		return numberOfCamps;
	}
	/**
     * Deletes the specified camp from the list of created camps.
     *
     * @param camp            The camp to be deleted.
     * @param campDataManager The camp data manager to handle data operations.
     */
	public void deleteCamp(Camp camp, CampDataManager campDataManager) {
	    if (campsCreated.contains(camp)) {
	        campsCreated.remove(camp);
	        numberOfCamps--;
	        campDataManager.deleteCamp(camp, this);
	    }
	}
	/**
     * Creates a new camp and adds it to the list of created camps.
     *
     * @param camp            The camp to be created.
     * @param campDataManager The camp data manager to handle data operations.
     */
	public void createCamp(Camp camp, CampDataManager campDataManager) {
        this.campsCreated.add(camp);
        campDataManager.addCamp(camp, this);
        numberOfCamps++;
    }
	/**
	 * Removes the specified camp from the list of camps created by the staff.
	 * Decrements the count of created camps.
	 *
	 * @param camp The camp to be removed.
	 */
	public void removeFromCampsCreated(Camp camp) {
        campsCreated.remove(camp);
        numberOfCamps--;
    }
	/**
     * Retrieves the list of suggestions in the staff member's inbox.
     *
     * @return The list of suggestions in the inbox.
     */
    public List<Query> getSuggestionInbox() {
        return suggestionInbox;
    }
    /**
     * Adds a suggestion to the staff member's inbox.
     *
     * @param suggestion The suggestion to be added to the inbox.
     */
    public void addSuggestionToInbox(Query suggestion) {
        suggestionInbox.add(suggestion);
    }
    /**
     * Removes a suggestion from the staff member's inbox.
     *
     * @param query The suggestion to be removed from the inbox.
     */
    public void removeSuggestionFromInbox(Query query) {
    	suggestionInbox.remove(query);
    }
    
}
