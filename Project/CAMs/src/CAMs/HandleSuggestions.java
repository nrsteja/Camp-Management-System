package CAMs;

import java.util.List;
/**
Implements the {@code EnquirySuggestionHandler} interface, providing methods to submit, view, edit, and delete suggestions for a Camp Committee Member.
@author Jeevika, Mathangi, Teja, Vikaas
@version 3.0
@since 2023-11-25
*/
public class HandleSuggestions implements EnquirySuggestionHandler{
	/**
     * Submits a suggestion for a given camp and Camp Committee Member.
     *
     * @param camp    The camp for which the suggestion is submitted.
     * @param query   The suggestion to be submitted.
     * @param student The Camp Committee Member submitting the suggestion.
     */
	public void submit(Camp camp, Query query, Student student) {
		Suggestion suggestion = (Suggestion)query;
		CampCommitteeMember comMem = (CampCommitteeMember)student;
    	camp.addSuggestion(suggestion);
    	comMem.addSuggestions(suggestion);
        System.out.println("Suggestion submitted successfully.");
        comMem.earnPointsForActivity(); // Award one point for submitting a suggestion
    }
	/**
     * Edits an existing suggestion for a Camp Committee Member.
     *
     * @param oldQuery        The original suggestion to be edited.
     * @param newSuggestion   The new text for the edited suggestion.
     * @param student         The Camp Committee Member who submitted the suggestion.
     */
    public void edit(Query oldQuery, String newSuggestion, Student student) {
    	Suggestion oldSuggestion = (Suggestion)oldQuery;
    	CampCommitteeMember comMem = (CampCommitteeMember)student;
    	List<Suggestion>suggestionsSubmitted = comMem.getSuggestions();
        for (Suggestion suggestion : suggestionsSubmitted) {
            if (suggestion.getSuggestion().equals(oldSuggestion.getSuggestion())) {
            	suggestion.setSuggestion(newSuggestion);
                System.out.println("Suggestion edited successfully.");
                return;
            }
        }
        System.out.println("Suggestion not found.");
    }
    /**
     * Deletes an existing suggestion for a Camp Committee Member, if it has not been processed.
     *
     * @param query   The suggestion to be deleted.
     * @param student The Camp Committee Member who submitted the suggestion.
     */
    public void delete(Query query, Student student) {
    	Suggestion oldSuggestion = (Suggestion)query;
    	CampCommitteeMember comMem = (CampCommitteeMember)student;
    	List<Suggestion>suggestionsSubmitted = comMem.getSuggestions();
        for (Suggestion suggestion : suggestionsSubmitted) {
            if (suggestion.getSuggestion().equals(oldSuggestion.getSuggestion())) {
                if (!suggestion.isProcessed()) {
                	comMem.removeSuggestion(suggestion);
                	suggestion.getCamp().deleteSuggestion(oldSuggestion);
                    //oldEnquiry.getCampIncharge().getStaffIncharge().removeSuggestionFromInbox(oldEnquiry);
                    System.out.println("Suggestion deleted successfully.");
                } else {
                    System.out.println("This suggestion has already been processed and cannot be deleted.");
                }
                return;
            }
        }
        System.out.println("Suggestion not found.");
    }
    /**
     * Views and displays a Camp Committee Member's submitted suggestions.
     *
     * @param student The Camp Committee Member whose suggestions are to be viewed.
     */
    public void view(Student student) {
    	CampCommitteeMember comMem = (CampCommitteeMember)student;
    	List<Suggestion>suggestionsSubmitted = comMem.getSuggestions();
        System.out.println("Your Suggestions:");
        for (Suggestion suggestion : suggestionsSubmitted) {
            System.out.println("Suggestions: " + suggestion.getSuggestion());
            System.out.println("Processed: " + (suggestion.isProcessed() ? "Yes" : "No"));
            System.out.println(); // Separate each inquiry
        }
    }
}
