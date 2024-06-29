package CAMs;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
/**
Implements the {@code PerformanceReportFormatSelector} interface, providing methods to generate and write a performance report for a Camp in CSV format.
@author Jeevika, Mathangi, Teja, Vikaas
@version 3.0
@since 2023-11-25
*/
public class PerformanceCsvReportFormatSelector implements PerformanceReportFormatSelector {
	/**
     * Generates a CSV performance report for the given camp.
     *
     * @param camp The camp for which the performance report is generated.
     */
	public void generateReport(Camp camp) {
		System.out.println("Generating CSV report...");
		try {
			writePerformanceReportToCSV(camp);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	/**
     * Writes the performance report to a CSV file for the given camp.
     *
     * @param camp The camp for which the performance report is generated.
     * @throws IOException If an I/O error occurs while writing the CSV file.
     */
	public void writePerformanceReportToCSV(Camp camp) throws IOException {
        List<Student> attendees = camp.getAttendees();
        List<CampCommitteeMember> comMembers = camp.getCommitteeMemberes();

        // Create a FileWriter to write the CSV content to a file
        FileWriter csvFileWriter = new FileWriter(camp.getCampName() + "_performance_report.csv");

        // Create a PrintWriter to simplify writing text
        PrintWriter csvContent = new PrintWriter(csvFileWriter);

        try {
            // Write the header row
        	csvContent.println("Camp Name,Start Date,End Date,Committee Members, Points");

            // Write camp information
            csvContent.print("\"" + camp.getCampName() + "\",");
            csvContent.print("\"" + camp.getOpenDate() + "\",");
            csvContent.print("\"" + camp.getCloseDate() + "\",");

            // Write committee members

            for (CampCommitteeMember comMems : comMembers) {
                csvContent.print("\"" + comMems.getName() + "\",");
                csvContent.println("\"" + comMems.getPoints() + "\",");
                for (int j = 0; j < 3; j++) {
                    csvContent.print(",");
                }
            }
            System.out.println("CSV report saved to " + camp.getCampName() + "_performance_report.csv");

        } finally {
            // Close the PrintWriter and FileWriter
            csvContent.close();
            csvFileWriter.close();
        }
    }
}
