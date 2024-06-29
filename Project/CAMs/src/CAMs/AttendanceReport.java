package CAMs;
/**
Implements the {@code ReportSelector} interface to generate student attendance reports in various formats. 
It overrides the {@code generateReport} method to initiate the generation of attendance reports based on the selected report format (TXT or CSV).
@author Jeevika, Mathangi, Teja, Vikaas
@version 3.0
@since 2023-11-25
*/
public class AttendanceReport implements StaffViewableReportSelector, CommitteeMemberViewableReportSelector {
    @Override
    /**
     * Overrides the {@code generateReport} method to generate student attendance reports for the given camp based on the selected report format (TXT or CSV).
     *
     * @param camp The camp for which the attendance report is generated.
     */
    public void generateReport(Camp camp) {
        // Implementation for generating Student Attendance report
        System.out.println("Generating Student Attendance report...");
        AttendanceReportFormatSelector reportFormatSelector = null;
        String format = ReportTypeChoice.getReportFormatChoice();
        switch(format) {
	        case "TXT": reportFormatSelector = new AttendanceTextReportFormatSelector();
	        	break;
	        case "CSV": reportFormatSelector = new AttendanceCsvReportFormatSelector();
	        	break;
        }
        reportFormatSelector.generateReport(camp);
    }
}