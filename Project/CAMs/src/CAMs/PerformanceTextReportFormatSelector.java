package CAMs;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
/**
Responsible for generating performance reports in TXT format for a given camp.
@author Jeevika, Mathangi, Teja, Vikaas
@version 3.0
@since 2023-11-25
*/
public class PerformanceTextReportFormatSelector implements PerformanceReportFormatSelector {
	/**
     * Generates a performance report in TXT format for the provided camp.
     *
     * @param camp The camp for which the performance report is generated.
     */
	public void generateReport(Camp camp) {
		System.out.println("Generating TXT report...");
		String reportTxt = generatePerformanceReportForTxt(camp);
		writeReportToTXT(reportTxt, camp.getCampName() + "_perfomance_report.txt");
	}
	/**
     * Generates the content of a performance report in TXT format for the provided camp.
     *
     * @param camp The camp for which the performance report is generated.
     * @return The content of the performance report as a string.
     */
	public String generatePerformanceReportForTxt(Camp camp) {
        List<CampCommitteeMember> comMembers = camp.getCommitteeMemberes();

        // Generate the report as a string (customize based on your needs)
        StringBuilder report = new StringBuilder();
        report.append("Camp Name: " + camp.getCampName() + "\n");
        report.append("Date: " + camp.getOpenDate() + " to " + camp.getCloseDate() + "\n");

        report.append("Committee Members (Points):\n");
        for (CampCommitteeMember comMems : comMembers) {
            report.append(comMems.getName()+ " (" + comMems.getPoints() + ")\n"); //+ " (" + attendee.getStudentID() + ")\n"
        }
        return report.toString();
    }
	/**
     * Writes the given report content to a TXT file with the specified file name.
     *
     * @param report   The content of the report to be written.
     * @param fileName The name of the TXT file to which the report will be saved.
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
