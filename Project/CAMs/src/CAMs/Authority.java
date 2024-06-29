package CAMs;
/**
Defines the contract for classes that handle authority-related functionalities, such as accessing authority pages or performing actions based on user roles.
@author Jeevika, Mathangi, Teja, Vikaas
@version 3.0
@since 2023-11-25
*/
public interface Authority {
	/**
     * Redirects the user to an authority-specific page based on their role and
     * the context of the camp.
     *
     * @param camp The camp for which the authority page is accessed.
     * @param user The user for whom the authority page is accessed.
     */
	void authorityPage(Camp camp, User user);
}
