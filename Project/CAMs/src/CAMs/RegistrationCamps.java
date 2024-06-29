package CAMs;
/**
Defines methods related to camp registration for students.
@author Jeevika, Mathangi, Teja, Vikaas
@version 3.0
@since 2023-11-25
*/
public interface RegistrationCamps {
	/**
     * Checks whether a student is eligible to register for the specified camp.
     *
     * @param selectedCamp The camp for which eligibility is checked.
     * @param student      The student for whom the eligibility is checked.
     * @return {@code true} if the student is eligible to register, {@code false} otherwise.
     */
	public boolean checkRegisterForCamp(Camp selectedCamp, Student student);
	/**
     * Registers a student for a camp.
     *
     * @param student The student to be registered.
     * @return {@code true} if the registration is successful, {@code false} otherwise.
     */
	public boolean registerForCamp(Student student);
	/**
     * Displays the camps for which the student is registered.
     *
     * @param student The student whose registered camps are to be displayed.
     */
	public void viewRegisteredCamps(Student student);
	/**
     * Displays detailed information about a student's registration for a specific camp.
     *
     * @param student The student for whom registration details are to be displayed.
     * @return {@code true} if details are successfully displayed, {@code false} otherwise.
     */
	public boolean viewCampRegistrationDetails(Student student);
	/**
     * Checks if a student is a camp committee member for the specified camp.
     *
     * @param camp    The camp for which committee membership is checked.
     * @param student The student for whom committee membership is checked.
     * @return {@code true} if the student is a committee member, {@code false} otherwise.
     */
	public boolean isCampCommitteeMember(Camp camp, Student student);
}
