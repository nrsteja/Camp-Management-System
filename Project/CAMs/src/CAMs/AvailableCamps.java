package CAMs;
/**
 Defines methods for retrieving available camps for a given student.
 @author Jeevika, Mathangi, Teja, Vikaas
 @version 3.0
 @since 2023-11-25
 */
import java.util.List;
/**
Defines methods for retrieving available camps for a given student.
@author Jeevika, Mathangi, Teja, Vikaas
@version 3.0
@since 2023-11-25
*/
public interface AvailableCamps {
	/**
     * Gets the available camps that a student can enquire about.
     *
     * @param allCamps The list of all available camps.
     * @param student  The student for whom the available camps are determined.
     * @return A list of camps available for enquiry by the student.
     */
	public List<Camp> getAvailableCampsToEnquire(List<Camp> allCamps, Student student);
	/**
     * Gets the available camps for a student.
     *
     * @param allCamps The list of all available camps.
     * @param student  The student for whom the available camps are determined.
     * @return A list of camps available for the student.
     */
	public List<Camp> getAvailableCamps(List<Camp> allCamps, Student student);
	/**
     * Views the available camps for a student without modifying the original list.
     *
     * @param camps   The list of camps to view.
     * @param student The student for whom the available camps are determined.
     * @return A list of camps available for the student.
     */
	public List<Camp> viewAvailableCamps(List<Camp> camps, Student student);
}
