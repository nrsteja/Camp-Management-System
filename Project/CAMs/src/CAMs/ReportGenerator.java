package CAMs;
/**
Defines a method for generating reports related to a camp.
@author Jeevika, Mathangi, Teja, Vikaas
@version 3.0
@since 2023-11-25
*/
public interface ReportGenerator {
	/**
     * Generates a report for the specified camp.
     *
     * @param camp The camp for which the report is to be generated.
     */
    void generateReport(Camp camp);
}
