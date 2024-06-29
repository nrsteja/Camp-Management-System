package CAMs;
/**
Interface that defines a contract for classes responsible for generating performance reports in various formats for a given camp.
@author Jeevika, Mathangi, Teja, Vikaas
@version 3.0
@since 2023-11-25
 */
public interface PerformanceReportFormatSelector {
	/**
     * Generates a performance report in a specific format for the provided camp.
     *
     * @param camp The camp for which the performance report is generated.
     */
	void generateReport(Camp camp);
}
