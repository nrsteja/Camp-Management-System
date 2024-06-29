package CAMs;
/**
An interface for classes that provide methods to withdraw from camps.
@author Jeevika, Mathangi, Teja, Vikaas
@version 3.0
@since 2023-11-25
*/
public interface WithdrawFromCamps {
	/**
     * Checks if a student can withdraw from the selected camp.
     *
     * @param selectedCamp The camp from which the student wants to withdraw.
     * @param student      The student requesting the withdrawal.
     * @return true if the withdrawal is allowed, false otherwise.
     */
	public boolean checkWithdrawFromCamp(Camp selectedCamp, Student student);
	/**
     * Withdraws a student from camps based on user input or other criteria.
     *
     * @param student The student requesting the withdrawal.
     */
	public void withdrawFromCamps(Student student);
}
