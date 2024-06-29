package CAMs;

import java.util.List;
/**
A utility class for viewing all camps and applying visibility filters.
@author Jeevika, Mathangi, Teja, Vikaas
@version 3.0
@since 2023-11-25
*/
public class ViewAllCamps {
	/**
     * Displays the names of all camps after applying a visibility filter.
     *
     * @param allCamps The list of all camps to be displayed.
     */
	public static void viewAllCamps(List<Camp> allCamps) {
	  // View all camps
		allCamps = FilterVisibility.filterVisibility(allCamps);
		System.out.println("List of All Camps:");
		  if (allCamps.isEmpty()) {
		      System.out.println("No camps available.");
		  } 
		  else {
		  	int count = 1;
		    for (Camp camp : allCamps)
		        System.out.println("(" + (count++) + ") " + camp.getCampName());
		  }
	}
}
