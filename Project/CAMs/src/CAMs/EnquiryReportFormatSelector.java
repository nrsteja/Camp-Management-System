package CAMs;
/**
Defines the contract for classes that select the format and generate reports related to enquiries for a specific camp.
@author Jeevika, Mathangi, Teja, Vikaas
@version 3.0
@since 2023-11-25
*/
public interface EnquiryReportFormatSelector {
    //ReportGenerator selectReportGenerator();
	/**
     * Generates a report in a specific format for the given camp.
     *
     * @param camp The camp for which the enquiry report is generated.
     */
	void generateReport(Camp camp);
}