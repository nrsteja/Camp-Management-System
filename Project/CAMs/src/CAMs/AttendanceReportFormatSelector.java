package CAMs;
/**
Interface defines the contract for classes that are responsible for generating attendance reports in various formats.
@author Jeevika, Mathangi, Teja, Vikaas
@version 3.0
@since 2023-11-25
 */
public interface AttendanceReportFormatSelector {
	/**
     * Generates an attendance report for the given camp.
     *
     * @param camp The camp for which the attendance report is generated.
     */
	void generateReport(Camp camp);
}
