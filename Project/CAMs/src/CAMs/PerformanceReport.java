package CAMs;
/**
Implements the {@code ReportSelector} interface, providing a method to generate a performance report for a Camp based on the selected report format.
@author Jeevika, Mathangi, Teja, Vikaas
@version 3.0
@since 2023-11-25
*/
public class PerformanceReport implements StaffViewableReportSelector {
    @Override
    /**
     * Generates a performance report for the given camp based on the selected report format.
     *
     * @param camp The camp for which the performance report is generated.
     */
    public void generateReport(Camp camp) {
        // Implementation for generating Performance report
        System.out.println("Generating Performance report...");
        PerformanceReportFormatSelector reportFormatSelector = null;
        String format = ReportTypeChoice.getReportFormatChoice();
        switch(format) {
	        case "TXT": reportFormatSelector = new PerformanceTextReportFormatSelector();
	        	break;
	        case "CSV": reportFormatSelector = new PerformanceCsvReportFormatSelector();
	        	break;
        }
        reportFormatSelector.generateReport(camp);
    }
}