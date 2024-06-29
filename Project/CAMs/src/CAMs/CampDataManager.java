package CAMs;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
/**
The class is responsible for managing and organizing data related to a camp.
@author Jeevika, Mathangi, Teja, Vikaas
@version 3.0
@since 2023-11-25
*/
public class CampDataManager {
	/**
     * The list containing all the camps managed by the CampDataManager.
     */
	private static List<Camp> allCamps = new ArrayList<>();
	/**
     * A mapping of staff members to the list of camps they have created.
     */
    private HashMap<String, List<Camp>> staffCampLists = new HashMap<>();
    /**
     * Adds new camp to the CampDataManager and associates it with the staff member who created it.
     *
     * @param camp           The camp to be added.
     * @param createdByStaff The staff member who created the camp.
     */
    public void addCamp(Camp camp, Staff createdByStaff) {
        allCamps.add(camp);

        // Associate the camp with the staff member who created it
        staffCampLists
            .computeIfAbsent(createdByStaff.getName(), k -> new ArrayList<>())
            .add(camp);
    }
    /**
     * Retrieves a list of all camps managed by the CampDataManager.
     *
     * @return A list of all camps.
     */
    public static List<Camp> getAllCamps() {
        return allCamps;
    }
    /**
     * Deletes a camp from the CampDataManager and removes it from the list of camps created by the staff member.
     *
     * @param campToBeDeleted The camp to be deleted.
     * @param staff           The staff member requesting the deletion.
     */
    public void deleteCamp(Camp campToBeDeleted, Staff staff) {
        if (allCamps.remove(campToBeDeleted)) {
            System.out.println("Camp " + campToBeDeleted.getCampName() + " has been deleted.");
            staff.removeFromCampsCreated(campToBeDeleted);
        } else {
            System.out.println("Camp " + campToBeDeleted.getCampName() + " not found in the list.");
        }
    }

}
