package CAMs;

import java.util.List;
/**
Implements the {@code Authority} interface for managing student enquiries and interactions related to enquiries for a specific camp.
@author Jeevika, Mathangi, Teja, Vikaas
@version 3.0
@since 2023-11-25
*/
public class EnquiryAuthority implements Authority {
	/**
     * The handler to manage the submission, viewing, editing, and deletion of enquiries.
     */
	static EnquirySuggestionHandler handle = new HandleEnquiries();
	/**
     * Displays the authority page for managing student enquiries for a specific camp.
     *
     * @param selectedCamp The camp for which enquiries are managed.
     * @param user         The user accessing the authority page (expected to be a student).
     */
	public void authorityPage(Camp selectedCamp, User user) {
		Student student = (Student)user;
    	String prompt;
    	this.enquiryMenu();
    	int inquiryActionChoice = InputErrorChecking.getUserChoice("Enter your choice: ", 1, 5);
    	List<Enquiry> studentEnquiries = student.getStudentEnquiries();
    	switch (inquiryActionChoice) {
	    	case 1: prompt = "Enter your enquiry for the camp: ";
            	String enquiryText = InputErrorChecking.getUserInput(prompt, "string");
            	Enquiry enquiry = new Enquiry(enquiryText, student);
            	handle.submit(selectedCamp, enquiry, student);
	    		break;
	    	case 2:
            	if(studentEnquiries.isEmpty())
            		System.out.println("Sorry no enquiries done to display");
            	else
            		handle.view(student);
                break;
	    	case 3:
	           	if (studentEnquiries.isEmpty() || selectedCamp.getStudentEnquiries().isEmpty()) {
	                 System.out.println("No inquiries available to edit.");
	          	} 
	           	else {
	      	       	System.out.println("Submitted Enquiries:");
	       	        for (int i = 0; i < studentEnquiries.size(); i++) {
	       	            System.out.println((i + 1) + ". " + studentEnquiries.get(i).getQuestion());
	       	        }
	       	        prompt = "Enter the number of the inquiry you want to edit: ";
	       	        int editChoice = InputErrorChecking.getUserChoice(prompt, 1, studentEnquiries.size());
       	            Enquiry oldEnquiry = studentEnquiries.get(editChoice - 1);
       	            if(oldEnquiry.getProccessStatus())
       	            	System.out.println("This inquiry has already been processed and cannot be edited.");
       	            else {
	       	            prompt = "Enter the new text for the inquiry: ";
	       	            String newInquiryText = InputErrorChecking.getUserInput(prompt, "string");
	       	            // Edit the selected inquiry
	       	            handle.edit(oldEnquiry, newInquiryText, student);
       	            }
	       	    }
	       	    break;
	    	case 4:
        	    if (studentEnquiries.isEmpty() || selectedCamp.getStudentEnquiries().isEmpty()) {
        	        System.out.println("No inquiries available to delete.");
        	    } else {
        	        System.out.println("Available Inquiries:");
        	        for (int i = 0; i < studentEnquiries.size(); i++) {
        	            System.out.println((i + 1) + ". " + studentEnquiries.get(i).getQuestion());
        	        }
        	        prompt = "Enter the number of the inquiry you want to delete: ";
        	        int deleteChoice = InputErrorChecking.getUserChoice(prompt, 1, studentEnquiries.size());
        	            // Delete the selected inquiry
        	        handle.delete(studentEnquiries.get(deleteChoice - 1), student);
        	    }
        	    break;
	    	case 5:
	    		break;
    	}
    }
	/**
     * Displays the menu options for managing student enquiries.
     */
    public void enquiryMenu() {
    	System.out.println("1. Add Enquiries");
        System.out.println("2. View Enquiries");
        System.out.println("3. Edit Enquiry");
        System.out.println("4. Delete Enquiry");
        System.out.println("5. Back to Main Menu");
    }
}
