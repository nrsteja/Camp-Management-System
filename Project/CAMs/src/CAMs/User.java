package CAMs;

import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
/**
The class serves as a base class for representing users in a system.It defines common attributes and behavior that are shared by various types of users.
@author Jeevika, Mathangi, Teja, Vikaas
@version 3.0
@since 2023-11-25
*/
public abstract class User {
	/**
     * A constant representing the default password for users.
     */
	public static final String PASSWORD = "password";
	/**
     * Flag indicating whether the provided password matches the user's current password.
     */
	//public static boolean passwordMatch;
	/**
     * Name of user.
     */
	private String name;
	/**
     * Email address of user.
     */
	private String email;
	/**
	 * This field stores the information about the user's academic department or faculty.
	 */
	private Schools faculty;
	/**
     * The password associated with the user.
     */
    private String password;
    /**
     * Boolean value indicating a login for the first time.
     */
    private boolean firstTimeLogin = true;
    /**
     * Scanner object for user input.
     */
    public static Scanner input = new Scanner(System.in);
    /**
	 * Static instance used to perform validation and error checking on user inputs.
	 */
    public static InputErrorChecking inputErrorChecking = new InputErrorChecking();
    /**
     * Constructs new User with specified name, email, and faculty.
     *
     * @param name    The name of the user.
     * @param email   The email address of the user.
     * @param faculty The faculty to which the user belongs, represented by an instance of the `Schools` class.
     */
    public User(String name, String email, Schools faculty) {
        this.name = name;
        this.email = email;
        this.faculty = faculty;
        this.password = PASSWORD;
    }
    /**
     * Abstract method to be implemented by subclasses to print the user menu.
     */
    public abstract void printMenu();
    /**
     * Abstract method to be implemented by subclasses to get the user type.
     *
     * @return The user type.
     */
    public abstract UserType getUserType();
    /**
     * Retrieves the name of the user in uppercase.
     *
     * @return The uppercase name of the user.
     */
    public String getName() { return name.toUpperCase();}
    /**
     * Retrieves the email address of the user.
     *
     * @return The email address of the user.
     */
    public String getEmail() { return email;}
    /**
     * Retrieves the school faculty associated with this user.
     *
     * @return The school faculty of the user.
     */
    public Schools getFaculty() { return faculty;}
    /**
     * Retrieves the user ID based on the email address.
     *
     * @return The user ID.
     */
    public String getUserID() {
		return (getEmail().substring(0, getEmail().lastIndexOf("@")).toUpperCase());
	}
    /**
     * Gets first-time login status of the user.
     *
     * @return true if the user is logging in for the first time, false otherwise.
     */
    public boolean getFirstTimeLogin() {
    	return firstTimeLogin;
    }
    /**
     * Sets first-time login status to false, indicating that user has completed the initial login.
     */
    public void setFirstTimeLogin() {
    	firstTimeLogin = false;
    }
    /**
     * Prints information about available camps to the console.
     *
     * @param storedCamps The List of Camp objects to display.
     */
    public void printAvailableCamps(List <Camp> storedCamps) {
    	int count = 1;
    	for (Camp camp : storedCamps) {
			System.out.println("(" + (count++) + ") Camp Name: " + camp.getCampName());
			System.out.println("    Start Date: " + camp.getOpenDate());
			System.out.println("    End Date: " + camp.getCloseDate());
			System.out.println("    Registration Close Date: " + camp.getRegCloseDate());
			System.out.println("    Location: " + camp.getLocation());
			System.out.println("    Remaining Slots: " + camp.getRemainingSlots());
    	}
    }
    /**
     * Retrieves password associated with the user.
     *
     * @return The password of the user.
     */
    public String getPassword() { return password;}
    /**
     * Retrieves user credentials (username and password) and validates the login.
     *
     * @param userDataManager The UserDataManager object to fetch user data from.
     * @return The User object representing the logged-in user, or null if the login is unsuccessful.
     */
    public static User getUsernamePassword(UserDataManager userDataManager) {
    	List<User> users = userDataManager.getUserData();
		String prompt;
		boolean userFound = false;
		boolean passwordMatch = false;
		while(!userFound) {
			prompt = "UserID: ";    //Get username
			String userID = inputErrorChecking.getUserInput(prompt, "stringWithoutSplChar");
			userID = userID.toUpperCase();   //UserID not case sensitive
			for(User user: users) {
				if(user.getUserID().equals(userID)) {
					userFound = true;
					while(!passwordMatch) {
						String password = inputErrorChecking.getUserInput("Password: ", "string");
						if(user.getPassword().equals(password)) {
							System.out.println("Login Successful.");
							if (user instanceof Student) {
			                    System.out.println("Welcome student, " + user.getName()); // Handle student-specific functionality
			                } 
							else if (user instanceof Staff) {
			                    System.out.println("Welcome staff, "+ user.getName());  // Handle staff-specific functionality
			                }						
							return user;
						}
						else {
							System.out.println("Invalid Password!!! Try again");
						}
					}
				}
			}
			if(!userFound) {
				System.out.println("UserID invalid!!! Try again");
			}
		}
		return null;
	}
    /**
     * Sets a new password for the user.
     *
     * @param newPassword The new password to be set.
     */
    public void setPassword(String newPassword) {
    	this.password = newPassword;
    }
    /**
     * Changes the password for user. The user is prompted to enter the current password and a new password.
     * Password change is successful only if the current password is entered correctly.
     */
	public void changePassword() {
		boolean passwordMatch = false;
		while(!passwordMatch) {
			System.out.print("Enter your current password: ");
			String currentPassword = input.next();
			if(currentPassword.equals(this.getPassword())) {
				passwordMatch = true;
				System.out.print("Enter new Password: ");
				String newPassword = input.next();
				setPassword(newPassword);
			}
			else {
				System.out.println("Sorry Invalid Password!!! Try again");
			}
		}
		System.out.println("Password changed Successfully");
		System.out.println("Please relogin to verify");
	}
	
}
