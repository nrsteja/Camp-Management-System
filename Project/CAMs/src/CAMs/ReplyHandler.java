package CAMs;

import java.util.List;
/**
Defines methods for handling replies to enquiries and viewing replies.
@author Jeevika, Mathangi, Teja, Vikaas
@version 3.0
@since 2023-11-25
*/
public interface ReplyHandler {
	/**
     * Submits a reply to a specific enquiry.
     *
     * @param enquiry The enquiry to which the reply is submitted.
     */
	void reply(Enquiry enquiry);
	/**
     * Displays replies to enquiries submitted by a student across all camps.
     *
     * @param student  The student for whom replies are to be displayed.
     * @param allCamps The list of all camps to consider for displaying replies.
     */
	void viewRepliesToEnquiries(Student student, List<Camp> allCamps);
}
