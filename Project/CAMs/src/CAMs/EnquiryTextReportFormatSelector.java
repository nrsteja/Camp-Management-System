package CAMs;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
/**
Implements the {@code EnquiryReportFormatSelector} interface for generating and writing an enquiries report in TXT format.
@author Jeevika, Mathangi, Teja, Vikaas
@version 3.0
@since 2023-11-25
*/
public class EnquiryTextReportFormatSelector implements EnquiryReportFormatSelector {
    @Override
    /**
     * Generates and saves a TXT report containing student enquiries for a specific camp.
     *
     * @param camp The camp for which the enquiries report is generated.
     */
    public void generateReport(Camp camp) {
    	System.out.println("Generating TXT report...");
    	String reportTxt = generateEnquiryReportForTxt(camp);
    	writeReportToTXT(reportTxt, camp.getCampName() + "_enquiries_report.txt");
    }
    /**
     * Generates a string representation of the enquiry report in TXT format for a specific camp.
     *
     * @param camp The camp for which the enquiry report is generated.
     * @return A string representation of the TXT-formatted enquiry report.
     */
    public String generateEnquiryReportForTxt(Camp camp) {
    	List<Enquiry> studentEnquiries = camp.getStudentEnquiries();
        List<CampCommitteeMember> comMembers = camp.getCommitteeMemberes();

        // Generate the report as a string (customize based on your needs)
        StringBuilder report = new StringBuilder();
        report.append("Camp Name: " + camp.getCampName() + "\n");
        report.append("Date: " + camp.getOpenDate() + " to " + camp.getCloseDate() + "\n");

        report.append("Students - Enquiries - Response:\n");
        for (Enquiry enquirySubmitted : studentEnquiries) {
            report.append(enquirySubmitted.getPostedBy().getName()+ " - " + enquirySubmitted.getQuestion() + " - " + enquirySubmitted.getResponse() + "\n"); //+ " (" + attendee.getStudentID() + ")\n"
        }
        return report.toString();
    }
    /**
     * Writes the generated TXT report to a file.
     *
     * @param report   The TXT-formatted report content.
     * @param fileName The name of the file to which the report is saved.
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