package CAMs;
/**
An interface for classes that provide methods to view camps.
@author Jeevika, Mathangi, Teja, Vikaas
@version 3.0
@since 2023-11-25
*/
import java.util.List;
/**
An interface for classes that provide methods to view camps.
@author Jeevika, Mathangi, Teja, Vikaas
@version 3.0
@since 2023-11-25
*/
public interface ViewingCamps {
	/**
     * Lists the names of the camps created by the staff member.
     *
     * @param staff The staff member whose created camps are to be listed.
     * @return A list of camp names created by the staff member.
     */
	List<String> listCreatedCamps(Staff staff);
	/**
     * Displays information about the camps created by the staff member.
     *
     * @param staff The staff member whose created camps are to be viewed.
     */
	void viewCreatedCamps(Staff staff);
}
