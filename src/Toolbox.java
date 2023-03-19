import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

public class Toolbox {

    Scanner sc = new Scanner(System.in);
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("H:mm");


    void formatCourseDetails(Course c, int i) {
        String day = c.getLtlDay().substring(0,3);
        LocalTime timeStart = c.getLtlTime();
        LocalTime timeEnd = addDoubleToTime(timeStart, c.getDuration());

        String dayAndTime = String.format("%s %s-%s", day, timeStart, timeEnd);

        System.out.printf("%d) %-30s%-15s%-18s\n", i, c.getName(), c.getDelivery(), dayAndTime);
    }


    LocalTime addDoubleToTime(LocalTime time, double value) {
        int hours = (int) value;
        int minutes = (int) ((value - hours) * 60);

        return time.plusHours(hours).plusMinutes(minutes);
    }


    <T> boolean checkDuplicates(List<T> array, T element) {
        for (T t : array) {
            if (t.equals(element)) {
                return true;
            }
        }
        return false;
    }


    String getString(String prompt) {
        String value;  // stores user input

        // as long as user input isn't an empty String, do-while repeats
        do {
            System.out.print(prompt);
            value = sc.nextLine();
        } while (value.equals(""));

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
