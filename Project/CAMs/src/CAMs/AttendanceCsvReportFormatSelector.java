package CAMs;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
/**
Implements the {@code AttendanceReportFormatSelector} interface to generate attendance reports in CSV format.
@author Jeevika, Mathangi, Teja, Vikaas
@version 3.0
@since 2023-11-25
*/
public class AttendanceCsvReportFormatSelector implements AttendanceReportFormatSelector {
	/**
     * Generates a CSV attendance report for the given camp based on user input.
     *
     * @param camp The camp for which the attendance report is generated.
     */
	public void generateReport(Camp camp) {
		System.out.println("Generating CSV report...");
		System.out.println("Choose the content format:");
        System.out.println("1. ATTENDEES");
        System.out.println("2. COMMITTEE MEMBERS");
        System.out.println("3. PRINT BOTH");
        int outputFormat2 = InputErrorChecking.getUserChoice("Enter your choice: ", 1, 3);
        try {
			writeStudentAttendanceReportToCSV(outputFormat2, camp);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/**
     * Writes the student attendance report to a CSV file based on the selected content format.
     *
     * @param outputFormat2 The selected content format (1 for attendees, 2 for committee members, 3 for both).
     * @param camp         The camp for which the attendance report is generated.
     * @throws IOException If an error occurs while writing to the CSV file.
     */
	public void writeStudentAttendanceReportToCSV(int outputFormat2, Camp camp) throws IOException {
        List<Student> attendees = camp.getAttendees();
        List<CampCommitteeMember> comMembers = camp.getCommitteeMembers();

        // Create a FileWriter to write the CSV content to a file
        FileWriter csvFileWriter = new FileWriter(camp.getCampName() + "_attendance_report.csv");

        // Create a PrintWriter to simplify writing text
        PrintWriter csvContent = new PrintWriter(csvFileWriter);

        try {
            // Write the header row
            if (outputFormat2 == 3)
                csvContent.println("Camp Name,Start Date,End Date,Attendees,Committee Members");
            else if (outputFormat2 == 2)
                csvContent.println("Camp Name,Start Date,End Date,Committee Members");
            else
                csvContent.println("Camp Name,Start Date,End Date,Attendees");

            // Write camp information
            csvContent.print(camp.getCampName() + ",");
            csvContent.print(camp.getOpenDate() + ",");
            csvContent.print(camp.getCloseDate() + ",");

            // Write attendees
            if(outputFormat2 == 1) {
                for (Student attendee : attendees) {
                    csvContent.println(attendee.getName());
                	for(int i=0; i<3; i++)
                		csvContent.print(",");   	
                }
            }
            

            // Write committee members
            else if(outputFormat2 == 2) {
                for (CampCommitteeMember comMems : comMembers) {
                    csvContent.println(comMems.getName() + ",");
                    for(int i=0; i<3; i++)
                		csvContent.print(",");
                }
            }
            if (outputFormat2 == 3) {
                int maxSize = Math.max(attendees.size(), comMembers.size());

                for (int i = 0; i < maxSize; i++) {
                    String attendeeName = (i < attendees.size()) ? attendees.get(i).getName() : "";
                    String comMemberName = (i < comMembers.size()) ? comMembers.get(i).getName() : "";

                    csvContent.print(attendeeName + ",");
                    csvContent.println(comMemberName + ",");

                    // Add extra columns if needed
                    for (int j = 0; j < 3; j++) {
                        csvContent.print(",");
                    }
                }
            }

            // Print a newline after writing attendees and/or committee members
            csvContent.println();

            System.out.println("CSV report saved to " + camp.getCampName() + "_attendance_report.csv");
        } finally {
            // Close the PrintWriter and FileWriter
            csvContent.close();
            csvFileWriter.close();
        }
    }

}
