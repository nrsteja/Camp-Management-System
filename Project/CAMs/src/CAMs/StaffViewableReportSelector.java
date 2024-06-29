package CAMs;
/**
It extends the {@link ReportSelector} interface and is intended for report selectors that can be viewed by staff members.
@author Jeevika, Mathangi, Teja, Vikaas
@version 3.0
@since 2023-11-25
*/
public interface StaffViewableReportSelector extends ReportSelector{
	/**
     * Generates a report for the specified camp.
     *
     * @param camp The camp for which the report is to be generated.
     */
    void generateReport(Camp camp);

}
