package CAMs;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
/**
Provides utility methods for date-related operations and validations.
@author Jeevika, Mathangi, Teja, Vikaas
@version 3.0
@since 2023-11-25
*/
public class DateConfiguration {
	/**
     * Checks if there is a date clash between a new camp and a student's registered camps.
     *
     * @param newCamp The new camp to be checked for date clash.
     * @param student The student for whom the date clash is checked.
     * @return {@code true} if there is a date clash, {@code false} otherwise.
     */
	public static boolean hasDateClash(Camp newCamp, Student student) {
	    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd"); // Adjust the date format as per your date representation
	    List<Camp> registeredCamps = student.getRegisteredCamps();
	    for (Camp camp : registeredCamps) {
	        try {
	            Date newCampOpenDate = dateFormat.parse(newCamp.getOpenDate());
	            Date newCampCloseDate = dateFormat.parse(newCamp.getCloseDate());
	            Date campOpenDate = dateFormat.parse(camp.getOpenDate());
	            Date campCloseDate = dateFormat.parse(camp.getCloseDate());

	            // Check if the new camp's open date falls within the date range of an already registered camp
	            if ((newCampOpenDate.after(campOpenDate) || newCampOpenDate.equals(campOpenDate)) &&
	                newCampOpenDate.before(campCloseDate)) {
	                return true; // Date clash found
	            }

	            // Check if the new camp's close date falls within the date range of an already registered camp
	            if (newCampCloseDate.after(campOpenDate) && (newCampCloseDate.before(campCloseDate) || newCampCloseDate.equals(campCloseDate))) {
	                return true; // Date clash found
	            }

	            // Check if the new camp's date range entirely includes an already registered camp's date range
	            if (newCampOpenDate.before(campOpenDate) && newCampCloseDate.after(campCloseDate)) {
	                return true; // Date clash found
	            }
	        } catch (ParseException e) {
	            e.printStackTrace();
	        }
	    }
	    return false; // No date clash
	}
	/**
     * Checks if the given start and end dates are in the correct order.
     *
     * @param startDate The start date to be checked.
     * @param endDate   The end date to be checked.
     * @return {@code true} if the dates are in the correct order, {@code false} otherwise.
     */
	public static boolean checkCorrectDate(String startDate, String endDate) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		try {
			Date startingDate = dateFormat.parse(startDate);
			Date closingDate = dateFormat.parse(endDate);
			
			if(closingDate.before(startingDate))
				return false;	
				
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return true;
	}
	/**
     * Checks if two date ranges overlap.
     *
     * @param start1 The start date of the first date range.
     * @param end1   The end date of the first date range.
     * @param start2 The start date of the second date range.
     * @param end2   The end date of the second date range.
     * @return {@code true} if the date ranges overlap, {@code false} otherwise.
     */
	public static boolean doDatesOverlap(Date start1, Date end1, Date start2, Date end2) {
        return start1.before(end2) && start2.before(end1);
    }
}
