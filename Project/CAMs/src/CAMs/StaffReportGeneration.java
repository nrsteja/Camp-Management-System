package CAMs;

import java.util.List;
/**
Implements the {@link ReportGenerator} interface and provides methods
for generating various reports related to a selected camp, such as Camp Attendance Report, Committee Members
Performance Report, and Students Enquiry Report.
@author Jeevika, Mathangi, Teja, Vikaas
@version 3.0
@since 2023-11-25
*/
public class StaffReportGeneration implements ReportGenerator {
	StaffViewableReportSelector reportSelector = null;
	/**
     * Generates a report based on the selected camp. The user is prompted to choose the type of report to generate.
     * The available report types include Camp Attendance Report, Committee Members Performance Report, and
     * Students Enquiry Report.
     *
     * @param selectedCamp The {@code Camp} for which the report is generated.
     */
	public void generateReport(Camp selectedCamp) {
		if(selectedCamp != null) {       
			System.out.println("Choose the Report:");
	        System.out.println("1. Camp Attendance Report");
	        System.out.println("2. Committee Memberes Performance Report");
	        System.out.println("3. Students Enquiry Report");
	        int reportChoice = InputErrorChecking.getUserChoice("Enter your choice: ", 1, 3);
	        switch (reportChoice) {
	            case 1: reportSelector = new AttendanceReport();
	                break;
	            case 2: reportSelector = new PerformanceReport();
	                break;
	            case 3: reportSelector = new EnquiryReport();
	                break;
	        }
	        reportSelector.generateReport(selectedCamp);
		}
	}
}
