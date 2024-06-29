package CAMs;
/**
 Interface for handling suggestions, providing a method to manage the suggestion inbox.
 @author Jeevika, Mathangi, Teja, Vikaas
 @version 3.0
 @since 2023-11-25
 */
public interface SuggestionHandler {
	/**
     * Displays the suggestion inbox for a staff member, allowing them to view and manage suggestions.
     *
     * @param staff The staff member whose suggestion inbox is being managed.
     */
	void suggestionInbox(Staff staff);
}
