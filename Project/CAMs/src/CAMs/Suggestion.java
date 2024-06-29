package CAMs;
/**
 * Represents a suggestion submitted by a Camp Committee Member.
 * Extends the base class Query.
 *
 * @author Jeevika, Mathangi, Teja, Vikaas
 * @version 2.0
 * @since 2023-11-19
 * 
 * @see Query
 * @see CampCommitteeMember
 * @see Camp
 */
public class Suggestion extends Query {
	/**
     * The text content of the suggestion.
     */
	private String suggestion;
	//private String response;
	/**
     * The Camp Committee Member who posted the suggestion.
     */
	private CampCommitteeMember postedBy;
	/**
     * A boolean flag indicating whether the suggestion has been processed.
     */
	private boolean isProcessed = false;
	/**
     * The camp associated with this suggestion.
     */
	private Camp camp;
	/**
     * Constructs a new Suggestion object with the specified suggestion and the Camp Committee Member who posted it.
     *
     * @param suggestion The text content of the suggestion.
     * @param comMem The Camp Committee Member who posted the suggestion.
     */
	public Suggestion(String suggestion, CampCommitteeMember comMem) {
		this.suggestion = suggestion;
		this.postedBy = comMem;
	}
	/**
     * Gets the Camp Committee Member who posted the suggestion.
     *
     * @return The Camp Committee Member who posted the suggestion.
     */
	public CampCommitteeMember getPostedBy() {
		return this.postedBy;
	}
	/**
     * Gets the text content of the suggestion.
     *
     * @return The text content of the suggestion.
     */
	// Getter for 'suggestion'
	public String getSuggestion() {
	    return suggestion;
	}
	/**
     * Sets the camp associated with this suggestion.
     *
     * @param selectedCamp The camp to be associated with this suggestion.
     */
	public void setCamp(Camp selectedCamp) {
		this.camp = selectedCamp;
	}
	/**
     * Gets the camp associated with this suggestion.
     *
     * @return The camp associated with this suggestion.
     */
	public Camp getCamp() {
		return this.camp;
	}
	/**
     * Sets the text content of the suggestion.
     *
     * @param suggestion The new text content of the suggestion.
     */
   // Setter for 'suggestion'
	public void setSuggestion(String suggestion) {
	    this.suggestion = suggestion;
	}
	/**
     * Mark the suggestion as processed.
     */
	public void setProccessStatus() {
		this.isProcessed = true;
	}
	
}
