package CAMs;

import java.util.List;
/**
Implements the {@code EnquirySuggestionHandler} interface, providing methods to submit, view, edit, and delete enquiries for a student.
@author Jeevika, Mathangi, Teja, Vikaas
@version 3.0
@since 2023-11-25
*/

public class HandleEnquiries implements EnquirySuggestionHandler {
	/**
     * Submits an enquiry for a given camp and student.
     *
     * @param camp    The camp for which the enquiry is submitted.
     * @param query   The enquiry to be submitted.
     * @param student The student submitting the enquiry.
     */
	public void submit(Camp camp, Query query, Student student) {
		Enquiry enquiry = (Enquiry)query;
        camp.addStudentEnquiry(enquiry);
        student.addStudentEnquiry(enquiry);
        enquiry.setCampIncharge(camp);
        System.out.println("Enquiry submitted successfully.");
    }
	/**
     * Views and displays a student's submitted enquiries.
     *
     * @param student The student whose enquiries are to be viewed.
     */
    public void view(Student student) {
    	List<Enquiry>studentEnquiries = student.getStudentEnquiries();
        System.out.println("Your inquiries:");
        for (Enquiry enquiry : studentEnquiries) {
            System.out.println("Question: " + enquiry.getQuestion());
            if (enquiry.getResponse() != null) {
                System.out.println("Response: " + enquiry.getResponse());
            }
            System.out.println("Processed: " + (enquiry.getProccessStatus() ? "Yes" : "No"));
            System.out.println(); // Separate each inquiry
        }
    }
    /**
     * Edits an existing enquiry for a student.
     *
     * @param oldQuery        The original enquiry to be edited.
     * @param newEnquiryText  The new text for the edited enquiry.
     * @param student         The student who submitted the enquiry.
     */
    public void edit(Query oldQuery, String newEnquiryText, Student student) {
    	Enquiry oldEnquiry = (Enquiry)oldQuery;
    	List<Enquiry>studentEnquiries = student.getStudentEnquiries();
        for (Enquiry enquiry : studentEnquiries) {
            if (enquiry.getQuestion().equals(oldEnquiry.getQuestion())) {
                enquiry.setQuestion(newEnquiryText);
                System.out.println("Enquiry edited successfully.");
                return;
            }
        }
        System.out.println("Enquiry not found.");
    }
    /**
     * Deletes an existing enquiry for a student, if it has not been processed.
     *
     * @param query   The enquiry to be deleted.
     * @param student The student who submitted the enquiry.
     */
    public void delete(Query query, Student student) {
    	Enquiry oldEnquiry = (Enquiry)query;
    	List<Enquiry>studentEnquiries = student.getStudentEnquiries();
        for (Enquiry enquiry : studentEnquiries) {
            if (enquiry.getQuestion().equals(oldEnquiry.getQuestion())) {
                if (!enquiry.isProcessed()) {
                    studentEnquiries.remove(enquiry);
                    oldEnquiry.getCampIncharge().deleteStudentEnquiry(oldEnquiry);
                    //oldEnquiry.getCampIncharge().getStaffIncharge().removeSuggestionFromInbox(oldEnquiry);
                    System.out.println("Enquiry deleted successfully.");
                } else {
                    System.out.println("This inquiry has already been processed and cannot be deleted.");
                }
                return;
            }
        }
        System.out.println("Enquiry not found.");
    }
}
