package CAMs;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;
import java.util.InputMismatchException;
/**
Utility class for error-checked user input, providing methods for various input types and validation.
@author Jeevika, Mathangi, Teja, Vikaas
@version 2.0
@since 2023-11-19
*/
public class InputErrorChecking {
	// Scanner object for reading user input
	static Scanner input = new Scanner(System.in);
	/**
     * Generic function for error-checked user input.
     *
     * @param prompt The prompt message to display to the user.
     * @param type   The type of input expected (e.g., "int", "double", "string", etc.).
     * @param <T>    The generic type of the returned input.
     * @return The user input of the specified type after validation.
     */
    public static <T> T getUserInput(String prompt, String type) {
        Scanner scanner = new Scanner(System.in);
        T userInput = null;
        boolean isValidInput = false;

        do {
            try {
                System.out.print(prompt);
                String input = scanner.nextLine();

                // Checking input type based on the provided type parameter
                switch (type) {
                    case "int":
                        userInput = (T) Integer.valueOf(input);
                        isValidInput = true;
                        break;
                    case "double":
                        userInput = (T) Double.valueOf(input);
                        isValidInput = true;
                        break;
                    case "string":
                        userInput = (T) input;
                        isValidInput = true;
                        break;
                    case "stringWithoutNumbers&SplChar":
                    	if (input.matches("^[a-zA-Z\\s]+$")) {
                            userInput = (T) input;
                            isValidInput = true;
                        } else {
                            throw new InputMismatchException("Invalid input. Please enter alphabets only.");
                        }
                        break;
                    case "stringWithoutSplChar":
                    	if (input.matches("^[a-zA-Z0-9\\s]*$")) {
                            userInput = (T) input;
                            isValidInput = true;
                        } else {
                            throw new InputMismatchException("Invalid input. Please enter alphabets and numbers only.");
                        }
                        break;
                    case "yesNoInput":
                        if (input.matches("^[YyNn]$")) {
                            userInput = (T) input.toUpperCase();
                            isValidInput = true;
                        } else {
                            throw new InputMismatchException("Invalid input. Please enter either Y or N.");
                        }
                        break;
                    case "date":
                        if (isValidDateFormat(input, "yyyy-MM-dd")) {
                            userInput = (T) input;
                            isValidInput = true;
                        } else {
                            throw new InputMismatchException("Invalid date format. Please enter in yyyy-MM-dd.");
                        }
                        break;
                    // Add more cases for other types as needed
                    default:
                        throw new InputMismatchException("Invalid input type");
                }

                //isValidInput = true;
                
            } catch (InputMismatchException | NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid " + type + ".");
                //scanner.nextLine(); // Consume the invalid input to prevent an infinite loop
            }
        } while (!isValidInput);

        return userInput;
    }
    /**
     * Placeholder method for obtaining and validating user choice within a specified range.
     * This method is currently not implemented and always returns null.
     *
     * @param prompt The prompt message to display to the user.
     * @param start  The start value of the allowed range (inclusive).
     * @param end    The end value of the allowed range (inclusive).
     * @return Always returns null as the implementation is not provided.
     */
    public static String getUserChoice(String prompt, String start, String end) {
    	return null;
    }
    /**
     * Retrieves and validates a user's choice within a specified range for menu selections.
     *
     * @param prompt The prompt message to display to the user.
     * @param start  The start value of the allowed range (inclusive).
     * @param end    The end value of the allowed range (inclusive).
     * @return The user's validated choice within the specified range.
     */
    public static int getUserChoice(String prompt, int start, int end) {
    	int choice = 0;
    	boolean check = false;
		while(!check) {
			choice = getUserInput(prompt, "int");
			if(choice >= start  && choice <= end) {
				check = true;
			}
			else {
            	System.out.println("Invalid Chocie! Try again");
            }
		}
		return choice;
    }
    /**
     * Private method to check if input string follows a specific date format.
     *
     * @param input      The input string representing a date.
     * @param dateFormat The expected date format (e.g., "yyyy-MM-dd").
     * @return true if the input string follows the specified date format, false otherwise.
     */
    private static boolean isValidDateFormat(String input, String dateFormat) {
        SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
        sdf.setLenient(false);

        try {
            Date date = sdf.parse(input);
            return true;
        } catch (ParseException e) {
            return false;
        }
    }
}
