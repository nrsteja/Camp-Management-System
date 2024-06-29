package CAMs;
/**
Defines the contract for handling the submission, viewing, editing, and deletion of enquiries and suggestions by a student.
@author Jeevika, Mathangi, Teja, Vikaas
@version 3.0
@since 2023-11-25
*/
public interface EnquirySuggestionHandler {
	/**
     * Submits an enquiry or suggestion to a specific camp on behalf of a student.
     *
     * @param camp   The camp to which the enquiry or suggestion is submitted.
     * @param query  The enquiry or suggestion to be submitted.
     * @param student The student submitting the enquiry or suggestion.
     */
	public void submit(Camp camp, Query query, Student student);
	/**
     * Displays the enquiries and suggestions submitted by a student.
     *
     * @param student The student for whom the enquiries and suggestions are displayed.
     */
	public void view(Student student);
	/**
     * Edits the content of an existing enquiry or suggestion submitted by a student.
     *
     * @param oldQuery        The original enquiry or suggestion to be edited.
     * @param newEnquiryText  The new text for the enquiry or suggestion.
     * @param student         The student who submitted the original enquiry or suggestion.
     */
	public void edit(Query oldQuery, String newEnquiryText, Student student);
	/**
     * Deletes an existing enquiry or suggestion submitted by a student.
     *
     * @param query   The enquiry or suggestion to be deleted.
     * @param student The student who submitted the enquiry or suggestion.
     */
    public void delete(Query query, Student student);
}
