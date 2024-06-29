package CAMs;
/**
Implements the {@code ReportSelector} interface for generating and formatting reports related to enquiries for a specific camp.
@author Jeevika, Mathangi, Teja, Vikaas
@version 3.0
@since 2023-11-25
*/
public class EnquiryReport implements StaffViewableReportSelector, CommitteeMemberViewableReportSelector  {
    @Override
    /**
     * Generates and formats an enquiry report for a specific camp based on user input.
     *
     * @param camp The camp for which the enquiry report is generated.
     */
    public void generateReport(Camp camp) {
        // Implementation for generating Enquiry report
        System.out.println("Generating Enquiry report...");
        EnquiryReportFormatSelector reportFormatSelector = null;
        String format = ReportTypeChoice.getReportFormatChoice();
        switch(format) {
	        case "TXT": reportFormatSelector = new EnquiryTextReportFormatSelector();
	        	break;
	        case "CSV": reportFormatSelector = new EnquiryCsvReportFormatSelector();
	        	break;
        }
        reportFormatSelector.generateReport(camp);
    }
}