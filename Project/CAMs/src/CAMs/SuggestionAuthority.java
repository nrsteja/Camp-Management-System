package CAMs;

import java.util.List;
/**
Manages the authority actions related to suggestions, including submitting, viewing, editing,
and deleting suggestions. It also handles viewing and replying to camp-related inquiries.
@author Jeevika, Mathangi, Teja, Vikaas
@version 3.0
@since 2023-11-25
*/
public class SuggestionAuthority implements Authority {
	@Override
	/**
     * Displays the suggestion authority page, allowing a camp committee member to perform actions
     * related to suggestions and inquiries for a selected camp.
     *
     * @param selectedCamp The camp for which authority actions are performed.
     * @param user         The user (camp committee member) interacting with the authority page.
     */
	public void authorityPage(Camp selectedCamp, User user) {
		HandleSuggestions handler = new HandleSuggestions();
		if(user instanceof CampCommitteeMember) {
			CampCommitteeMember comMem = (CampCommitteeMember) user;
			List<Suggestion> suggestionsSubmitted = comMem.getSuggestions();
	    	this.enquiryMenu();
	    	int inquiryActionChoice = InputErrorChecking.getUserChoice("Enter your choice: ", 1, 7);
	    	switch (inquiryActionChoice) {
		    	case 1: String prompt = "Enter your Suggestion for the camp: ";
		        	String suggestText = InputErrorChecking.getUserInput(prompt, "string");
		        	Suggestion suggest = new Suggestion(suggestText, comMem);
		        	suggest.setCamp(selectedCamp);
		        	handler.submit(selectedCamp, suggest, comMem);
		    		break;
		    	case 2:
		        	if(suggestionsSubmitted.isEmpty())
		        		System.out.println("Sorry no suggestions given to display");
		        	else
		        		handler.view(comMem);
		            break;
		    	case 3:
					if (suggestionsSubmitted.isEmpty()) {
					    System.out.println("No suggestions available to edit.");
					} 
					else {
					    System.out.println("Available suggestions:");
						for (int i = 0; i < suggestionsSubmitted.size(); i++) {
						    System.out.println((i + 1) + ". " + suggestionsSubmitted.get(i).getSuggestion());
						}
						prompt = "Enter the number of the suggestion you want to edit: ";
						int editChoice = InputErrorChecking.getUserChoice(prompt, 1, suggestionsSubmitted.size());
						prompt = "Enter the new text for the suggestion: ";
						String newInquiryText = InputErrorChecking.getUserInput(prompt, "string");
						//suggestionsSubmitted.get(editChoice).setSuggestion(newInquiryText);
						handler.edit(suggestionsSubmitted.get(editChoice - 1), newInquiryText, comMem);
					}
					break;
		    	case 4:
		    	    if (suggestionsSubmitted.isEmpty()) {
		    	        System.out.println("No suggestions available to delete.");
		    	    } else {
		    	        System.out.println("Available suggestions:");
		    	        for (int i = 0; i < suggestionsSubmitted.size(); i++) {
		    	            System.out.println((i + 1) + ". " + suggestionsSubmitted.get(i).getSuggestion());
		    	        }
		    	        prompt = "Enter the number of the suggestion you want to delete: ";
		    	        int deleteChoice = InputErrorChecking.getUserChoice(prompt, 1, suggestionsSubmitted.size());
		    	        handler.delete(suggestionsSubmitted.get(deleteChoice - 1), comMem);
		    	    }
		    	    break;
		    	case 5: List<Enquiry> campEnquiries = selectedCamp.getEnquiries();
		    		int count = 1;
		    		boolean found = false;
		    		for(Enquiry enquiry: campEnquiries) {
		    			if(enquiry.getProccessStatus() == false) {
		    				found = true;
		    				System.out.println("(" + (count++) + ")" + enquiry.getQuestion());
		    			}
		    		}
		    		if(!found)
		    			System.out.println("No enquirires pending to reply!");
		    		break;
		    	case 6: this.viewAndReplyToEnquiries(selectedCamp, comMem);
		    		
		    		break;
		    	case 7:
		          // Return to the main menu
		          break;
	
	    	}
		}
	}
	/**
     * Displays the menu for suggestion-related actions.
     */
	public void enquiryMenu() {
    	System.out.println("1. Add Suggestions");
        System.out.println("2. View Suggestions");
        System.out.println("3. Edit Suggestions");
        System.out.println("4. Delete Suggestions");
        System.out.println("5. View Enquiries");
        System.out.println("6. Reply Enquiries");
        System.out.println("7. Back to Main Menu");
    }
	/**
     * Views and replies to pending camp-related inquiries for a selected camp.
     *
     * @param camp   The camp with pending inquiries.
     * @param comMem The camp committee member handling inquiries.
     */
	public void viewAndReplyToEnquiries(Camp camp, CampCommitteeMember comMem) {
		RegistrationCamps campRegistration = new CampCommitteeMemberRegistrationCamps();
        if (campRegistration.isCampCommitteeMember(camp, comMem)) {
            List<Enquiry> enquiries = camp.getEnquiries();
            
            System.out.println("List of Enquiries");
            int count = 1;
            for(Enquiry enquiry: enquiries) {
    			if(enquiry.getProccessStatus() == false) {
    				System.out.println("(" + (count++) + ")" + enquiry.getQuestion());
    			}
    		}
            String prompt = "Enter your choice";
            int selection = InputErrorChecking.getUserChoice(prompt, 1, enquiries.size());
            count = 0;
            for (Enquiry enquiry : enquiries) {
                // View the inquiry
            	count++;
            	if(count == selection) {
	            	if(enquiry.getProccessStatus() == false) {
		                System.out.println("Enquiry: " + enquiry.getQuestion());
		                // Reply to the inquiry
		                prompt = "Enter your response: ";
		                String response = InputErrorChecking.getUserInput(prompt, "string");
		                enquiry.setResponse(response);
		                String reply = enquiry.getResponse();
		                enquiry.setProccessStatus();
		                System.out.println("Reply: " + reply);
		                comMem.earnPointsForActivity(); // Award one point for replying to an inquiry
		                camp.removeStudentEnquiry(enquiry);
	            	}
	            	else {
	            		System.out.println("No enquiries pending!!!");
	            	}
            	}
            }
        } else {
            System.out.println("You are not a committee member for this camp.");
        }
    }
	

}
