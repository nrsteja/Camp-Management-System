package CAMs;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
/**
Implements the {@code EnquiryReportFormatSelector} interface for generating and writing an enquiries report in CSV format. 
@author Jeevika, Mathangi, Teja, Vikaas
@version 3.0
@since 2023-11-25
*/
public class EnquiryCsvReportFormatSelector implements EnquiryReportFormatSelector {
    @Override  
    /**
     * Generates and saves a CSV report containing student enquiries for a specific camp.
     *
     * @param camp The camp for which the enquiries report is generated.
     */
    public void generateReport(Camp camp) {
    	System.out.println("Generating CSV report...");
    	try {
			writeEnquiryReportToCSV(camp);
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
    /**
     * Writes the student enquiries report to a CSV file for the specified camp.
     *
     * @param camp The camp for which the enquiries report is generated.
     * @throws IOException If an I/O error occurs while writing the CSV file.
     */
    public void writeEnquiryReportToCSV(Camp camp) throws IOException {
        List<Student> attendees = camp.getAttendees();
        List<Enquiry> studentEnquiries = camp.getStudentEnquiries();

        // Create a FileWriter to write the CSV content to a file
        FileWriter csvFileWriter = new FileWriter(camp.getCampName() + "_enquiries_report.csv");

        // Create a PrintWriter to simplify writing text
        PrintWriter csvContent = new PrintWriter(csvFileWriter);

        try {
            // Write the header row
        	csvContent.println("Camp Name,Start Date,End Date,Student submitted,Enquiry,Response");

            // Write camp information
            csvContent.print("\"" + camp.getCampName() + "\",");
            csvContent.print("\"" + camp.getOpenDate() + "\",");
            csvContent.print("\"" + camp.getCloseDate() + "\",");

            // Write committee members
            for (Enquiry enquirySubmitted : studentEnquiries) {
                csvContent.print("\"" + enquirySubmitted.getPostedBy().getName() + "\",");
                csvContent.print("\"" + enquirySubmitted.getQuestion() + "\",");
                csvContent.println("\"" + enquirySubmitted.getResponse() + "\",");
                for (int j = 0; j < 3; j++) {
                    csvContent.print(",");
                }
            }
            System.out.println("CSV report saved to " + camp.getCampName() + "_enquiries_report.csv");

        } finally {
            // Close the PrintWriter and FileWriter
            csvContent.close();
            csvFileWriter.close();
        }
    }
}
