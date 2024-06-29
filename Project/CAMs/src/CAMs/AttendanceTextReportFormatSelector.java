package CAMs;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
/**
Implements the {@code AttendanceReportFormatSelector} interface to generate attendance reports in text format.
@author Jeevika, Mathangi, Teja, Vikaas
@version 3.0
@since 2023-11-25
*/
public class AttendanceTextReportFormatSelector implements AttendanceReportFormatSelector{
	/**
     * Generates a TXT attendance report for the given camp based on user input.
     *
     * @param camp The camp for which the attendance report is generated.
     */
	public void generateReport(Camp camp) {
		System.out.println("Generating TXT report...");
		System.out.println("Choose the content format:");
        System.out.println("1. ATTENDEES");
        System.out.println("2. COMMITTEE MEMBERS");
        System.out.println("3. PRINT BOTH");
        int outputFormat2 = InputErrorChecking.getUserChoice("Enter your choice: ", 1, 3);
        String reportTxt = generateStudentAttendanceReportForTxt(outputFormat2, camp);
        writeReportToTXT(reportTxt, camp.getCampName() + "_attendance_report.txt");

	}
	/**
     * Writes the student attendance report to a txt file based on the selected content format.
     *
     * @param outputFormat2 The selected content format (1 for attendees, 2 for committee members, 3 for both).
     * @param camp         The camp for which the attendance report is generated.
     * @return A string representing the generated attendance report.
     */
	public String generateStudentAttendanceReportForTxt(int outputFormat2, Camp camp) {
        List<Student> attendees = camp.getAttendees();
        List<CampCommitteeMember> comMembers = camp.getCommitteeMemberes();

        // Generate the report as a string (customize based on your needs)
        StringBuilder report = new StringBuilder();
        report.append("Camp Name: " + camp.getCampName() + "\n");
        report.append("Date: " + camp.getOpenDate() + " to " + camp.getCloseDate() + "\n");
        if(outputFormat2 == 1 || outputFormat2 == 3) {
	        report.append("Attendees:\n");
	        for (Student attendee : attendees) {
	            report.append(attendee.getName() + "\n"); //+ " (" + attendee.getStudentID() + ")\n"
	        }
        }
	    if(outputFormat2 == 2 || outputFormat2 == 3) {
	        report.append("\n\nCommittee Members:\n");
	        if(comMembers.isEmpty())
	    		report.append("No Committee Members for this camp yet");
	        else {
		        for (CampCommitteeMember comMems : comMembers) {
		            report.append(comMems.getName() + "\n"); //+ " (" + attendee.getStudentID() + ")\n"
		        }
	        }
	    }
        return report.toString();
    }
	/**
     * Writes the provided report to a TXT file.
     *
     * @param report   The attendance report content in string format.
     * @param fileName The name of the file to which the report is written.
     */
	public void writeReportToTXT(String report, String fileName) {
        try (PrintWriter writer = new PrintWriter(fileName)) {
            writer.println(report);
            System.out.println("TXT report saved to " + fileName);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
