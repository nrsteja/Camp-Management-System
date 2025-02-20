package CAMs;
/**
 * Represents a generic query submitted by a user.
 *
 * @author Jeevika, Mathangi, Teja, Vikaas
 * @version 3.0
 * @since 2023-11-25
 * 
 * @see Student
 */
public class Query {
	/**
     * The response to the query.
     */
	private String response;
	/**
     * The student who posted the query.
     */
	private Student postedBy;
	/**
     * A boolean flag indicating whether the query has been processed.
     */
	private boolean isProcessed;
	/**
     * Checks whether the query has been processed.
     *
     * @return {@code true} if the query has been processed; {@code false} otherwise.
     */
	public boolean isProcessed() {
	    return this.isProcessed;
	}
	/**
     * Sets the response to the query.
     *
     * @param response The response to the query.
     */
	public void setResponse(String response) {
		this.response = response;
	}
	/**
     * Gets the response to the query.
     *
     * @return The response to the query, or {@code null} if no response has been set.
     */

	public String getResponse() {
		if(this.response == null)
			return null;
		else
			return response;
	}
}
