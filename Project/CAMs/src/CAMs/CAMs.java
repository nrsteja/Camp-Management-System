package CAMs;
import java.util.Scanner;
import java.io.FileReader;  // Import the File class
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.io.FileNotFoundException;  // Import this class to handle errors
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.io.BufferedReader;
import java.io.*;

/**
Centralized hub for all staff and students. 
@author Jeevika, Mathangi, Teja, Vikaas
@version 3.0
@since 2023-11-25
*/
public class CAMs {
	/**
	 * Static Scanner object for reading input from the standard input (System.in).
	 */
	public static Scanner input = new Scanner(System.in);
	/**
	 * Static instance used to perform validation and error checking on user inputs.
	 */
	public static InputErrorChecking inputErrorChecking = new InputErrorChecking();
	/**
	 * Boolean value to check if a user is found, password is matched and to logout
	 */
	public static boolean userFound, passwordMatch, logout;
	/**
	 * Integer representing a user's choice.
	 */
	public static int choice = 0;
	/**
	 * Boolean value to check if camp committee member.
	 */
	public static boolean isCampCommitteeMember = false;
	/**
	 * Boolean value for first time users.
	 */
	public static boolean enteringFirstTime = true;
	/**
	 * Static reference to a Camp Committee Member associated with the Camp
	 */
	public static CampCommitteeMember comMem;
	/**
	 * A static list that contains instances of the Camp class.
	 * It is used to store all Camp objects and you can add, remove, or access Camp objects from this list.
	 * 
	 * @see Camp
	 * @see java.util.ArrayList
	 */
	public static List<Camp> allCamps = new ArrayList<>();
	/**
	 * A static list of Camp objects used to store instances of the Camp class.
     * It is initialized as an empty ArrayList.
	 */
	public static List<Camp> store = new ArrayList<>();
	/**
	 * A static list of User objects used to store instances of the User class.
     * It is initialized as an empty ArrayList.
	 */
	public static List<User> users = new ArrayList<>();
	/**
	 * A static instance of CampDataManager class for managing Camp objects.
	 * It provides methods to perform operations on Camp objects, such as adding, removing, updating, and retrieving Camp information.
	 * 
	 * @see CampDataManager
	 */
	public static CampDataManager campDataManager = new CampDataManager();
	/**
	 * A static list of Camp objects used to store instances of the Camp class.
     * It is initialized as an empty ArrayList.
	 */
	public static UserDataManager userDataManager = new UserDataManager();
	/**
	 * The main entry point of the NTU CAMs application.
     * It presents a menu for users to log in or exit, and handles corresponding actions based on user input.
     * The application continues to run until the user chooses to exit.
     *
     * @param args Command-line arguments (not used in this application).
     * @throws FileNotFoundException If there is an issue with reading user data from files.
	 */
	public static void main(String[] args) throws FileNotFoundException {
		userDataManager.generateData("Student_List.txt", "Staff_List.txt");
		//Check UserID & Password
		while(true) {
			System.out.println("Welcome to NTU CAMs!!!");
            System.out.println("1. Login");
            System.out.println("2. Exit");
            int choiceToOpen = inputErrorChecking.getUserChoice("Enter your choice: ", 1, 2);
            switch (choiceToOpen) {
            case 1:
                login(users);
                choice = 0;
                break;
            case 2:
            	System.out.println("Goodbye!");
                input.close();
                System.exit(0);
                break;
            default:
                System.out.println("Invalid choice. Try again.");
                break;
            }	
		}
	}
	/**
	 * Logs in user and initiates the user interface based on their role (Student or Staff).
     * Upon successful login, the user is presented with a menu corresponding to their user type,
     * and the appropriate actions are taken based on the user's choices.
	 * @param users A list of User objects representing potential login candidates.
	 */
	public static void login(List<User> users) {
		List<Camp> allCamps = CampDataManager.getAllCamps();
		System.out.println("LOGIN CREDENTIALS");
		User loginUser = User.getUsernamePassword(userDataManager);
		logout = false;
		if(loginUser.getFirstTimeLogin()) {
			System.out.println("Please change your default password!!!");
			String prompt = "Would you like to change it now?";
			String selection = inputErrorChecking.getUserInput(prompt, "yesNoInput");
			if(selection.equals("Y")) {
				loginUser.changePassword();
				logout = true;
			}
			loginUser.setFirstTimeLogin();
		}
		while(!logout) {
			if(loginUser instanceof Student && ((Student) loginUser).getIsCampCommitteeMember()) {
				Student student = ((Student) loginUser);
				if(isCampCommitteeMember) {
					while (!logout) {
	                    campCommitteeMenu(student);
	                }
				}
			}
			else
			{
				loginUser.printMenu();
				if(loginUser.getUserType() == UserType.STUDENT) {
					choice = inputErrorChecking.getUserChoice("Enter your choice: ", 1, 9);
				}
				else {
					choice = inputErrorChecking.getUserChoice("Enter your choice: ", 1, 5);
				}
				if(loginUser instanceof Student) {
					Student student = ((Student) loginUser);
					switch(choice) {
					case 1: student.changePassword();
						logout = true;
						break;
					case 2: AvailableCamps availCamps = new StudentAvailableCamps();
						availCamps.viewAvailableCamps(allCamps, student);	
						break;
					case 3: isCampCommitteeMember = registerForCamps(student);
						break;
					case 4: Camp selectedCamp = EnquiryPage.enquiryPage(allCamps, student);
						if(selectedCamp != null) {
							Authority authority = new EnquiryAuthority();
							authority.authorityPage(selectedCamp, student);
						}
						break;
					case 5: RegistrationCamps registrationStudent = new StudentRegistrationCamps();
						registrationStudent.viewCampRegistrationDetails(student);
						break;
					case 6: ReplyHandler handle = new EnquiryReply();
						handle.viewRepliesToEnquiries(student, allCamps);
		                break;
					case 7: WithdrawFromCamps withdraw = new StudentWithdrawal();
						withdraw.withdrawFromCamps(student);
						break;
					case 8:
					    student.viewCampCommitteeStatus();
					    break;	
					case 9: System.out.println("Thank you!!!");
						logout = true;
					}
				}
				else if(loginUser instanceof Staff) {
					Staff staff = ((Staff) loginUser);
					switch(choice) {
					case 1: staff.changePassword();
						logout = true;
						break;
					case 2: CampSettings(staff);
						break;
					case 3: SuggestionHandler handler = new DisplaySuggestionBox();
						handler.suggestionInbox(staff);
						break;
					case 4: ViewAllCamps.viewAllCamps(allCamps);
						break;
					case 5: System.out.println("Thank you!!!");
						logout = true;
					}
				}
			}
			
		}
	}
	/**
	 * Creates a new CampCommitteeMember object based on the provided Student information.
	 * 
	 * @param student The Student object from which to create a CampCommitteeMember.
	 * @return A CampCommitteeMember object initialized with the student's name, email, faculty,
	 *         registered camps, student enquiries, and withdrawn camps.
	 */
	public static CampCommitteeMember declareCommitteeMember(Student student) {
		CampCommitteeMember comMem = new CampCommitteeMember(student.getName(), student.getEmail(), student.getFaculty(), student.getRegisteredCamps(), student.getStudentEnquiries(), student.getWithdrawnCamps());
		return comMem;
	}
	/**
	 * Displays a menu for the camp committee, allowing them to perform various actions related to a given student.
	 *
	 * @param student The Student object for which the camp committee actions are performed.
	 *                This student is the subject of the committee's actions.
	 */
	public static void campCommitteeMenu(Student student) {
		List<Camp> allCamps = CampDataManager.getAllCamps();
		if(student.getEnteringFirstTime()) {
			comMem = declareCommitteeMember(student);
			comMem.setCampCommitteeStatus(student);
			comMem.setCommitteeCamp(student.getCampToConvertAsCommittee());
			student.setEnteringFirstTime();
		}
		comMem.printMenu();
		choice = inputErrorChecking.getUserChoice("Enter your choice: ", 1, 10);

		switch(choice) {
		case 1: student.changePassword();
			logout = true;
			break;
		case 2: AvailableCamps availCamps = new StudentAvailableCamps();
			availCamps.viewAvailableCamps(allCamps, comMem);	
			break;
		case 3: isCampCommitteeMember = registerForCamps(comMem);
			break;
		case 4: Camp selectedCamp = EnquiryPage.enquiryPage(allCamps, comMem);
			RegistrationCamps campRegistration = new CampCommitteeMemberRegistrationCamps();
			if(campRegistration.isCampCommitteeMember(selectedCamp, comMem)) {
				System.out.println("Suggestion");
				Authority authority = new SuggestionAuthority();
				authority.authorityPage(selectedCamp, comMem);
			}
			else {
				System.out.println("Enquiry");
				Authority authority = new EnquiryAuthority();
				authority.authorityPage(selectedCamp, comMem);
			}
			break;
		case 5: RegistrationCamps registrationComMem = new CampCommitteeMemberRegistrationCamps();
			registrationComMem.viewCampRegistrationDetails(comMem);
			break;
		case 6: ReplyHandler handle = new EnquiryReply();
			handle.viewRepliesToEnquiries(comMem, allCamps);
            break;
		case 7: WithdrawFromCamps withdraw = new StudentWithdrawal();
			withdraw.withdrawFromCamps(comMem);
			break;
		case 8:
			comMem.viewCampCommitteeStatus();
		    break;	
		case 9: Camp camp = comMem.getCommitteeCamp();
			ReportGenerator reportGenerator = new CampCommitteeMemberReportGeneration();
			reportGenerator.generateReport(camp);
			break;
		case 10: System.out.println("Thank you!!!");
			logout = true;
		}
	}
	/**
	* Displays a menu using info provided by a staff member where a camp can be created, edited or deleted
	*
	* @param staff The Staff member whose information is used to configure camp settings.
	*/
	public static void CampSettings(Staff staff) {
		staff.CampSettings();
		choice =  inputErrorChecking.getUserChoice("Enter your choice: ", 1, 5);
		switch(choice) {
		case 1: createCamp(staff);
			break;
		case 2:editCamp(staff);
			break;
		case 3: deleteCamp(staff);
			break;
		case 4: Camp selectedCamp = CampListGenerate.campListGenerate(staff);
			ReportGenerator reportGenerator = new StaffReportGeneration();
			reportGenerator.generateReport(selectedCamp);
			break;
		}
	}
	/**
	 * Creates a new Camp object with the specified staff and adds it to the storage.
	 *
	 * @param staff The Staff object representing the staff assigned to the camp.
	 */
	public static void createCamp(Staff staff) {
		//input.nextLine();
		
		String prompt = "Enter the name: ";
		String campName = inputErrorChecking.getUserInput(prompt, "stringWithoutSplChar");
		
		prompt = "Enter the Opening Date (yyyy-mm-dd): ";
		String openDate = inputErrorChecking.getUserInput(prompt, "date");
		
		prompt = "Enter the Closing Date (yyyy-mm-dd): ";
		String closeDate = inputErrorChecking.getUserInput(prompt, "date");
		
		while(!DateConfiguration.checkCorrectDate(openDate, closeDate)) {
			System.out.println("Sorry the closing date cannot be be before opeing date!!!");
			prompt = "Enter the Closing Date (yyyy-mm-dd): ";
			closeDate = inputErrorChecking.getUserInput(prompt, "date");
		}
		
		prompt = "Enter the Registration Closing Date (yyyy-mm-dd): ";
		String regCloseDate = inputErrorChecking.getUserInput(prompt, "date");
		
		while(!DateConfiguration.checkCorrectDate(regCloseDate, closeDate)) {
			System.out.println("Sorry the registration cannot be open after the camp ends!!!");
			prompt = "Enter the Registration Closing Date (yyyy-mm-dd):";
			regCloseDate = inputErrorChecking.getUserInput(prompt, "date");
		}
		
		boolean found = false;
		CampAvailability campAccessible = CampAvailability.NTU;;
		Schools campSchool = null;
		while(!found) {
			prompt = "Is the camp available to entire NTU(Y/N)";
			String inp  = inputErrorChecking.getUserInput(prompt, "yesNoInput");
			if(inp.equals("Y")) {
				found = true;
			}
			else if(inp.equals("N")) {
				campAccessible = CampAvailability.SCHOOL;
				campSchool = staff.getFaculty();
				found = true;
			}
//			else {
//				System.out.println("Sorry invalid input. Try again!!!");
//			}
		}

		prompt = "Enter the Location: ";
		String location = inputErrorChecking.getUserInput(prompt, "stringWithoutSplChar");
		
		prompt = "Enter the Total Slots in this camp: ";
		int totalSlots = inputErrorChecking.getUserInput(prompt, "int");
		
		boolean checkInput = false;
		boolean visibility = true;
		while(!checkInput) {
			prompt = "Do you want the camp to be visible to students(Y/N): ";
			String visbilityChoice = inputErrorChecking.getUserInput(prompt, "yesNoInput");
			if(visbilityChoice.equals("Y")) {
				visibility = true;
				checkInput = true;
			}
			else if(visbilityChoice.equals("N")) {
				visibility = false;
				checkInput = true;
			}	
		}
		
		prompt = "Description: ";
		String description = inputErrorChecking.getUserInput(prompt, "string");
		
		Staff staffIncharge = staff;
		Camp camp = new Camp(campName, openDate, closeDate, regCloseDate, campAccessible, campSchool, location, totalSlots, description, staffIncharge, visibility);
		staff.createCamp(camp, campDataManager);
			
	}
	/**
	 * Deletes a Camp object associated with a specified staff.
	 *
	 * @param staff The Staff object representing the staff assigned to the camp.
	 */
	public static void deleteCamp(Staff staff) {
		ViewingCamps viewCamps = new StaffViewCamps();
		List<String> campNames = viewCamps.listCreatedCamps(staff);
		viewCamps.viewCreatedCamps(staff);
		if(campNames != null && campNames.size() != 0) {
			System.out.println("Which camp you want to delete:");
			for(int i=0; i<staff.getNumberOfCamps(); i++) {
				System.out.println("("+ (i+1) +") "+campNames.get(i));
			}
			choice = inputErrorChecking.getUserChoice("Enter your choice: ", 1, staff.getNumberOfCamps());
			// Get camp to be deleted
			Camp camp = staff.getCamp(choice - 1);
			if(camp.getTotalSlots() == camp.getRemainingSlots()) {
				staff.deleteCamp(camp, campDataManager);
			}
			else {
				System.out.println("Sorry students have enrolled, so cannot delete the camp!!!");
			}	
		}
	}
	/**
	 * Allows a staff member to edit the details of a camp they have created.
	 *
	 * @param staff The staff member initiating the camp editing.
	 */
	 public static void editCamp(Staff staff) {
		ViewingCamps viewCamps = new StaffViewCamps();
		String prompt;
	 	List<String> campNames = viewCamps.listCreatedCamps(staff);
	 	if(campNames != null) {
	 		viewCamps.viewCreatedCamps(staff);
		 	if(campNames.size() != 0) {
				for(int i=0; i<campNames.size(); i++) {
					System.out.println("("+ (i+1) +") "+campNames.get(i));
				}
				prompt = "Which camp you want to Edit: ";
				choice = inputErrorChecking.getUserChoice(prompt, 1, campNames.size());

				int choice2 = 0;
			 	while(choice2 != 10) {
				 	Camp camp = staff.getCamp(choice-1);
				 	camp.campMenu();
					choice2 = inputErrorChecking.getUserChoice("Enter your choice: ", 1, 10);

					switch(choice2) {
						case 1: prompt = "Enter the new camp Name: ";
							String newCampName = inputErrorChecking.getUserInput(prompt, "stringWithoutSplChar");
							camp.setCampName(newCampName);
							break;
						case 2: if(camp.getTotalSlots() != camp.getRemainingSlots())
							System.out.println("Sorry you cannot change the CampSchool as people started registering...");
						      else {
									prompt = "Enter the new Opening Date (yyyy-mm-dd): ";
									String newOpenDate = inputErrorChecking.getUserInput(prompt, "date");
									camp.setOpenDate(newOpenDate);
									String newClosingDate = camp.getCloseDate();
									String newRegClosingDate = camp.getCloseDate();
									while(!DateConfiguration.checkCorrectDate(newOpenDate, newClosingDate)) {
										System.out.println("Sorry the opening date cannot be after closing date, so change the close date as well!!!");								
										prompt = "Enter the Closing Date (yyyy-mm-dd):";
										newClosingDate = inputErrorChecking.getUserInput(prompt, "date");
									}
									camp.setCloseDate(newClosingDate);
						      }
							break;
						case 3: if(camp.getTotalSlots() != camp.getRemainingSlots())
							System.out.println("Sorry you cannot change the CampSchool as people started registering...");
							else { prompt = "Enter the new Closing Date (yyyy-mm-dd): ";
									String newCloseDate = inputErrorChecking.getUserInput(prompt, "date");
									camp.setCloseDate(newCloseDate);
									String newOpeningDate = camp.getOpenDate();
									while(!DateConfiguration.checkCorrectDate(newOpeningDate, newCloseDate)) {
										System.out.println("Sorry the closing date cannot be be before opeing date!!!");
										prompt = "Enter the new Opening Date (yyyy-mm-dd): ";
										newOpeningDate = inputErrorChecking.getUserInput(prompt, "date");
									}
									camp.setOpenDate(newOpeningDate);
						       }
							break;
						case 4: prompt = "Enter the new Registration closing Date: ";
							String newRegCloseDate = inputErrorChecking.getUserInput(prompt, "date");
							while(!DateConfiguration.checkCorrectDate(newRegCloseDate, camp.getCloseDate())) {
								System.out.println("Sorry the registration cannot be open after the camp ends!!!");
								prompt = "Enter the Registration Closing Date (yyyy-mm-dd):";
								newRegCloseDate = inputErrorChecking.getUserInput(prompt, "date");
							}
							camp.setRegCloseDate(newRegCloseDate);
							break;
						case 5: if(camp.getTotalSlots() != camp.getRemainingSlots())
									System.out.println("Sorry you cannot change the CampSchool as people started registering...");
							else {
								if(camp.getCampAccessible() == CampAvailability.NTU) {
									camp.setCampAccessible(CampAvailability.SCHOOL);
									camp.setCampSchool(staff.getFaculty());
									System.out.println("Camp availability is changed to "+ staff.getFaculty() + " school students only!!!");
								}
								else {
									camp.setCampAccessible(CampAvailability.NTU);
									System.out.println("Camp is now available to entire NTU");
								}
							}
							break;
						case 6: prompt = "Enter the new Total number of Slots: ";
							int newTotalSlots = inputErrorChecking.getUserInput(prompt, "int");
							while(newTotalSlots < (camp.getTotalSlots() - camp.getRemainingSlots())) {
								System.out.println("Sorry there are already " + (camp.getTotalSlots() - camp.getRemainingSlots()) + " students enrolled, so you can only increase beyond it not less!!!");
								newTotalSlots = inputErrorChecking.getUserInput(prompt, "int");
							}
							camp.setTotalSlots(newTotalSlots);
							break;
						case 7: prompt = "Enter the new Location: ";
							String newLocation = inputErrorChecking.getUserInput(prompt, "stringWithoutSplChar");
							camp.setlocation(newLocation);
							break;
						case 8: prompt = "Enter the new Description: ";
							String newDescription = inputErrorChecking.getUserInput(prompt, "string");
							camp.setDescription(newDescription);
							break;
						case 9: boolean checkInput = false;
							while(!checkInput) {
								
								if(camp.getTotalSlots() == camp.getRemainingSlots()) {
									prompt = "Do you want the camp to be visible to students(Y/N): ";
									String visbilityChoice = inputErrorChecking.getUserInput(prompt, "yesNoInput");
									if(visbilityChoice.equals("Y")) {
										camp.setVisiblity(true);
										checkInput = true;
										System.out.println("Visibility changed succesfully!!!");
										System.out.println("Camps are visibile to students now!");
									}
									else if(visbilityChoice.equals("N")) {
										camp.setVisiblity(false);
										checkInput = true;
										System.out.println("Visibility changed succesfully!!!");
										System.out.println("Camps are not visibile to students now");
									}	
								}
								else {
									System.out.println("Sorry students have enrolled, so visibility of the camp cannot be changed!!!");
									checkInput = true;
								}
							}
							break;
						case 10:
							break;
					}
			 	}
		 	}
	 	}
	}
	 /**
	  * Allows user to register for camps by checking the user's type and delegates the registration process to the appropriate user type-specific method
	  *
	  * @param user The User object for whom camp registration should be performed.
      * @return `true` if the registration is successful; otherwise, `false`.
	  */
	 public static boolean registerForCamps(User user) {
		 RegistrationCamps registrationStudent = new StudentRegistrationCamps();
		 RegistrationCamps registrationComMem = new CampCommitteeMemberRegistrationCamps();
		    if (user instanceof Student) {
		        return  registrationStudent.registerForCamp(((Student) user));
		    } else if (user instanceof CampCommitteeMember) {
		        return  registrationComMem.registerForCamp(((CampCommitteeMember) user));
		    }
		    return false;
		}
	 
	 /**
	  * Allows student to submit enquiry for a selected camp
	  *
	  * @param student The student submitting the inquiry.
      * @param selectedCamp The camp for which the inquiry is being submitted.
	  */
	 public static void submitEnquiry(Student student, Camp selectedCamp) {
		 		EnquirySuggestionHandler handle = new HandleEnquiries();
            	String prompt = "Enter your enquiry for the camp: ";
                String inquiryText = InputErrorChecking.getUserInput(prompt, "string");
                Enquiry enquiry = new Enquiry(inquiryText, student);
                handle.submit(selectedCamp, enquiry, student);
                enquiry.setPostedBy(student);
	   }
	 /**
	  * Allows camp committee member to submit suggestion for a selected camp
	  *
	  * @param comMem The camp committee member submitting the suggestion.
      * @param selectedCamp The camp for which the suggestion is being submitted.
	  */
	 public static void submitSuggestion(CampCommitteeMember comMem, Camp selectedCamp) {
         	String prompt = "Enter your Suggestion for the camp: ";
            String inputSuggestion = inputErrorChecking.getUserInput(prompt, "string");
            Suggestion suggestion = new Suggestion(inputSuggestion, comMem);
            selectedCamp.addSuggestion(suggestion);
	                
	   }
	 
	
	
}
