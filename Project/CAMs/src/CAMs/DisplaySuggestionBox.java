package CAMs;

import java.util.ArrayList;
import java.util.List;
/**
Provides utility methods for date-related operations and validations.
@author Jeevika, Mathangi, Teja, Vikaas
@version 3.0
@since 2023-11-25
*/
public class DisplaySuggestionBox implements SuggestionHandler {
	/**
     * Displays the suggestion inbox for a staff member, including enquiries and suggestions.
     * Allows staff to reply to enquiries and approve suggestions.
     *
     * @param staff The staff member for whom the suggestion inbox is displayed.
     */
	public void suggestionInbox(Staff staff) {
	    List<Query> queries = staff.getSuggestionInbox();
	    if(queries.isEmpty()) {
	    	System.out.println("Your suggestion inbox is empty.");
	    }
	    else {
	    	List<Enquiry> enquiries = new ArrayList<>();
	    	List<Suggestion> suggestionList = new ArrayList<>();
	    	for(Query query:queries) {
			    if(query instanceof Enquiry) {
			    	enquiries.add((Enquiry)query);
			    }
			    else if(query instanceof Suggestion) {
			    	suggestionList.add((Suggestion)query);
			    }
	    	}
	    	if(!enquiries.isEmpty()) {
		    	System.out.println("Enquiries Inbox:");
		    	for (int i = 0; i < enquiries.size(); i++) {
	        		System.out.println((i + 1) + ". " + enquiries.get(i).getQuestion());
		            if (enquiries.get(i).getResponse() != null) {
		                System.out.println("Response: " + enquiries.get(i).getResponse());
		            }
		        }
	    	}
	    	if(!suggestionList.isEmpty()) {
		    	System.out.println("Suggestion Inbox:");
		    	for (int i = 0; i < suggestionList.size(); i++) {
	        		System.out.println((i + 1) + ". " + suggestionList.get(i).getSuggestion());
		            if (suggestionList.get(i).getResponse() != null) {
		                System.out.println("Response: " + suggestionList.get(i).getResponse());
		            }
		        }
	    	}
	    	if(!enquiries.isEmpty()) {
		    	String prompt = "Do you want to reply to any Enquiry? (Enter the number or 0 to skip)";
		        int replyChoice =  InputErrorChecking.getUserInput(prompt, "int");
	
		        if (replyChoice > 0 && replyChoice <= enquiries.size()) {
		        	ReplyHandler replyHandler = new EnquiryReply();
		        	replyHandler.reply(enquiries.get(replyChoice - 1));
		        }
	    	}
	    	if(!suggestionList.isEmpty()) {
		        String prompt = "Do you want to approve any Suggestion? (Enter the number or 0 to skip)";
		        int approveChoice = InputErrorChecking.getUserInput(prompt, "int");
		        
		        if (approveChoice > 0 && approveChoice <= suggestionList.size()) {
		            this.approveSuggestion(suggestionList.get(approveChoice - 1), staff);
		        }
	    	}
	    }
	    	
	}
	/**
     * Approves a suggestion, updating related information such as removing it from the
     * staff member's inbox, updating the suggestion status, earning points for the user,
     * and changing the user's status.
     *
     * @param suggestion The suggestion to be approved.
     * @param staff      The staff member approving the suggestion.
     */
    public void approveSuggestion(Suggestion suggestion, Staff staff) {
    	
    	staff.removeSuggestionFromInbox(suggestion);
    	this.approveSuggestion(suggestion);
    	suggestion.getPostedBy().earnPointsForActivity();
    	suggestion.getPostedBy().removeSuggestion(suggestion);
    	suggestion.getPostedBy().changeStatus(suggestion);
    }
    /**
     * Approves a suggestion, updating related information such as removing it from the
     * suggestion list for a camp and marking it as approved.
     *
     * @param suggestion The suggestion to be approved.
     */
    public void approveSuggestion(Suggestion suggestion) {
    	List<Suggestion> suggestions = suggestion.getCamp().getSuggestions();
	    if (suggestions.contains(suggestion)) {
	        suggestions.remove(suggestion); // Remove from the list of suggestions
	        suggestion.setProccessStatus();
	        suggestion.getCamp().setApprovedSuggestions(suggestion);
	        System.out.println("Suggestion approved: " + suggestion.getSuggestion());
	    } else {
	        System.out.println("Suggestion not found in the list of suggestions.");
	    }
	}
    /**
     * Displays the suggestions for a specific camp.
     *
     * @param camp The camp for which suggestions are displayed.
     */
    public void viewSuggestions(Camp camp) {
        List<Suggestion> suggestions = camp.getSuggestions();

        if (suggestions.isEmpty()) {
            System.out.println("No suggestions available for this camp.");
        } else {
            System.out.println("Suggestions for " + camp.getCampName() + ":");
            for (int i = 0; i < suggestions.size(); i++) {
                System.out.println((i + 1) + ". " + suggestions.get(i));
            }
        }
    }
}
