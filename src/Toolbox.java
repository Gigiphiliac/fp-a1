import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;

public class Toolbox {

    Scanner sc = new Scanner(System.in);
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("H:mm");


    String formatCourseDetails(Course c, int i) {
        String day, dayAndTime;
        LocalTime timeStart, timeEnd;

        // retrieve the course day and time details in required format
        day = c.getLtlDay().substring(0,3);
        timeStart = c.getLtlTime();
        timeEnd = addDoubleToTime(timeStart, c.getDuration());

        // format day and time details
        dayAndTime = String.format("%s %s-%s", day, timeStart, timeEnd);

        // return the formatted line
        return String.format("%d) %-30s%-15s%-18s\n",
                i, c.getName(), c.getDelivery(), dayAndTime);
    }


    LocalTime addDoubleToTime(LocalTime time, double value) {
        // convert 'value' from boolean into int representations
        // of hours and minutes
        int hours = (int) value;
        int minutes = (int) ((value - hours) * 60);

        // return the LocalTime value after hours and minutes have been added
        return time.plusHours(hours).plusMinutes(minutes);
    }


    <T> boolean checkDuplicates(List<T> array, T element) {
        // iterate through passed 'array' and return true if passed 'element'
        // matches any element within 'array'
        for (T t : array) {
            if (t.equals(element)) {
                return true;
            }
        }
        return false;  // no matches found
    }


     boolean checkMatch(List<Course> array, String keyword) {
        // iterate through passed 'array' and return true if any element's
        // name matches 'keyword'
        for (Course c : array) {
            if (c.getName().toLowerCase().contains(keyword.toLowerCase())) {
                return true;
            }
        }
        return false;  // no match found
    }


    boolean isValidCSVFile(String filename) {
        // return true if the filename matches the regex pattern '[text].csv'
        Pattern pattern = Pattern.compile("^.+\\.csv$");
        return pattern.matcher(filename).matches();
    }


    String getString(String prompt) {
        String value;  // stores user input

        try {
            System.out.print(prompt);
            // take value from user input and throw exception if empty
            value = sc.nextLine();
            if (value.equals("")) {
                throw new EmptyInputException("Invalid input! You must enter "
                        + "a value: ");
            }
        // if an empty input is caught, recursively call this method with
        // error message as the text prompt
        } catch (EmptyInputException e) {
            value = getString(e.getMessage());
        }

        return value;  // return the expected value
    }


    int getInteger(String prompt) {
        int value = 0;  // initialises user input variable to 0
        boolean proceed = true;  // keeps do-while running while true

        // as long as user input is not an integer, do-while will repeat
        do {
            System.out.print(prompt);
            // try parsing input as Integer, and catches any failure to do so
            try {
                value = Integer.parseInt(sc.nextLine());
                proceed = false;  // desired value obtained, allow loop to end
            } catch (NumberFormatException e) {  // non-int inputted
                // change prompt to provide direction to user
                prompt = "Invalid input! You must enter an integer value: ";
            }
        } while (proceed);

        return value;  // return the expected value
    }
}
