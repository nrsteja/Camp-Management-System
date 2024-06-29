package CAMs;
/**
Provides a method to interactively get the user's choice for the output format of a report.
@author Jeevika, Mathangi, Teja, Vikaas
@version 3.0
@since 2023-11-25
*/
public class ReportTypeChoice {
	/**
     * Displays options for choosing the output format of a report and returns the selected format.
     *
     * @return A {@code String} representing the chosen output format (e.g., "TXT" or "CSV").
     */
	public static String getReportFormatChoice() {
		System.out.println("Choose the output format:");
        System.out.println("1. TXT");
        System.out.println("2. CSV");
        int outputFormat = InputErrorChecking.getUserChoice("Enter your choice: ", 1, 2);
        if(outputFormat == 1)
        	return "TXT";
        else
        	return "CSV";
	}
}
