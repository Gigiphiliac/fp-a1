import java.io.FileNotFoundException;

public class Main {

    // static final reference variables as they remain unchanged
    static final MyTimetable MY_TIMETABLE = new MyTimetable();
    static final String FILENAME = "course.csv";


    public static void main(String[] args) {

        // try to read the data from FILENAME, if the file is not
        // found, output an error
        try {
            MY_TIMETABLE.readFromFile(FILENAME);
            System.out.println("\nWelcome to MyTimetable!");
            MY_TIMETABLE.displayMenu();  // begin the program

        // catch specific InvalidFileNameException and return error message
        } catch (InvalidFileNameException e) {
            System.err.println("InvalidFileNameException: " + e.getMessage());
        // catch general FileNotFoundException and return error message
        } catch (FileNotFoundException e) {
            System.err.println("FileNotFoundException: " + e.getMessage());
        }

    }
}
