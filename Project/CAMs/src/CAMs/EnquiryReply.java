package CAMs;

import java.util.List;
/**
Implements the {@code ReplyHandler} interface for replying to enquiries and viewing replies submitted by a student for their submitted enquiries.
@author Jeevika, Mathangi, Teja, Vikaas
@version 3.0
@since 2023-11-25
*/
public class EnquiryReply implements ReplyHandler{

	@Override
	/**
     * Allows the user (student) to reply to a specific enquiry and sets the response.
     *
     * @param enquiry The enquiry to which the user is replying.
     */
	public void reply(Enquiry enquiry) {
		String reply =  InputErrorChecking.getUserInput("Enter your reply: ", "string");
        enquiry.setResponse(reply);
        System.out.println("Reply added successfully.");
        enquiry.setProccessStatus();
        enquiry.getCampIncharge().getStaffIncharge().removeSuggestionFromInbox(enquiry);
	}
	/**
     * Displays the enquiries submitted by a student, along with any replies given.
     *
     * @param student   The student for whom the replies to enquiries are displayed.
     * @param allCamps  The list of all available camps.
     */
	public void viewRepliesToEnquiries(Student student, List<Camp> allCamps) {
	 	AvailableCamps availCamps = new StudentAvailableCamps();
	    List<Camp> availableCamps = availCamps.getAvailableCamps(allCamps, student);
	    List<Enquiry> submittedEnquiries = student.getStudentEnquiries();

	    // Check if there are available camps to inquire about
	    if (submittedEnquiries.isEmpty()) {
	        System.out.println("You havent submitted any enquiry");
	        return;
	    }

	    // Display available camps
	    boolean replyGiven = false;
	    System.out.println("Enquiries Submitted:");
	    for (int i = 0; i < submittedEnquiries.size(); i++) {
	    	System.out.println((i + 1) + ". " + submittedEnquiries.get(i).getQuestion());
	    	if(submittedEnquiries.get(i).getResponse() != null) {
	    		replyGiven = true;
	    		System.out.println("Reply: " + submittedEnquiries.get(i).getResponse());
	    	}
	    }
	    if(!replyGiven) {
	    	System.out.println("No replies given yet!!!");
	    }

}
	
}
