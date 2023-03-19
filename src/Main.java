import java.io.FileNotFoundException;

public class Main {

    // static final reference variables as they remain unchanged
    static final MyTimetable myTimetable = new MyTimetable();
    static final String FILENAME = "course.csv";


    public static void main(String[] args) {

        // try to read the data from FILENAME, if the file is not found, output an error
        try {
            myTimetable.readFromFile(FILENAME);
            System.out.println("\nWelcome to MyTimetable!");
            myTimetable.displayMenu();
        } catch (FileNotFoundException e) {
            System.err.println("File not found: " + e);
        }

    }
}
