package CAMs;

import java.util.Comparator;
import java.util.List;
/**
Provides functionality to filter and sort a list of camps based on user preferences.
@author Jeevika, Mathangi, Teja, Vikaas
@version 3.0
@since 2023-11-25
*/
public class FilterVisibility {
	/**
     * Filters and sorts a list of camps based on user preferences.
     *
     * @param storeCamps The list of camps to be filtered and sorted.
     * @return The filtered and sorted list of camps.
     */
	public static List<Camp> filterVisibility(List<Camp> storeCamps) {
    	if(storeCamps.size() ==  1)
    		return storeCamps;
    	String prompt = "Do you want to filter the display(Y/N)?";
    	String selection = InputErrorChecking.getUserInput(prompt, "yesNoInput");
    	int choice;
    	if(selection.equals("N")) {
    		choice = 1;
    	}
    	else {
	    	System.out.println("FILTER OPTIONS:");
	    	System.out.println("(1) Camp Name");
	    	System.out.println("(2) Date");
	    	System.out.println("(3) Location");
	    	System.out.println("(4) Remaining Slots");
	    	prompt = "Enter your choice: ";
	    	choice = InputErrorChecking.getUserChoice(prompt, 1, 4);
    	}
    	switch(choice) {
	    	case 1: storeCamps.sort(Comparator.comparing(Camp::getCampName));
	    		break;
	    	case 2: System.out.println("FILTER OPTIONS PAGE 2:");
		    	System.out.println("(1) Start Date");
		    	System.out.println("(2) End Date");
		    	System.out.println("(3) Registration Close Date");
		    	prompt = "Enter your choice: ";
		    	int choice2 = InputErrorChecking.getUserChoice(prompt, 1, 3);
		    	switch(choice2) {
			    	case 1: storeCamps.sort(Comparator.comparing(Camp::getOpenDate));
			    		break;
			    	case 2: storeCamps.sort(Comparator.comparing(Camp::getCloseDate));
			    		break;
			    	case 3: storeCamps.sort(Comparator.comparing(Camp::getRegCloseDate));
			    		break;
		    	}
	    		break;
	    	case 3: storeCamps.sort(Comparator.comparing(Camp::getLocation));
	    		break;
	    	case 4: storeCamps.sort(Comparator.comparingInt(Camp::getRemainingSlots));
	    		break;
    	}
    	return storeCamps;
    }
}
