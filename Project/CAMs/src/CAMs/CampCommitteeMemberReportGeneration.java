package CAMs;
/**
Implements the {@code ReportGenerator} interface to generate various reports for a camp committee member.
@author Jeevika, Mathangi, Teja, Vikaas
@version 3.0
@since 2023-11-25
*/
public class CampCommitteeMemberReportGeneration implements ReportGenerator {
	CommitteeMemberViewableReportSelector reportSelector = null;
	@Override
	/**
     * Generates a report for the camp committee member based on their choice.
     *
     * @param camp The camp for which the report is generated.
     */
	public void generateReport(Camp camp) {
		if(camp == null)
			System.out.println("Sorry, Havent registered for any camps as committee Member");
		else {
			System.out.println("1. Student Attendance Report");
			System.out.println("2. Enquiries Submitted List");
			String prompt = "Enter your choice:";
			int selection = InputErrorChecking.getUserChoice(prompt, 1, 2);
			if(selection == 1)
				reportSelector = new AttendanceReport();
			else
				reportSelector = new EnquiryReport();
			reportSelector.generateReport(camp);
		}
		
	}

}
