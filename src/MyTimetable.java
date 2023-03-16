import java.io.FileNotFoundException;
import java.io.FileReader;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MyTimetable {

    Scanner sc = new Scanner(System.in);

    List<Course> coursesArray = new ArrayList<>();
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("H:mm");

    public static void main(String[] args) {
        MyTimetable myTimetable = new MyTimetable();

        String FILENAME = "course.csv";

        try {
            myTimetable.readFromFile(FILENAME);
            System.out.println("\nWelcome to MyTimetable!");
            myTimetable.displayMenu();
        } catch (FileNotFoundException e) {
            System.err.println("File not found: " + e);
        }

    }


    private void displayMenu() {

        String choice = getMenuChoice();

        while (!choice.equals("4")) {
            switch (choice) {
                case "1" -> enrol();
                case "2" -> showEnrolments();
                case "3" -> withdraw();
                default -> System.out.println("Please select a valid menu option.");
            }
            choice = getMenuChoice();
        }
        System.out.println("Quitting...");
    }


    private String getMenuChoice() {
        return getString("""
                --------------------------------------------------------------------------------
                > Select from main menu
                --------------------------------------------------------------------------------
                   1) Search by keyword to enrol
                   2) Show my enrolled courses
                   3) Withdraw from a course
                   4) Exit
                Please select:\s""");
    }

//    private String getCourseChoice() {
//        int i=0;
//        System.out.println("""
//                --------------------------------------------------------------------------------
//                > Select from main menu
//                --------------------------------------------------------------------------------""");
//        for (Course c : coursesArray) {
//            System.out.println(i + ") " + c.getName());
//        }
//        return getString("Please select: ");
//    }

    private void enrol() {
        String keyword = getString("Enter a keyword to search: ");

        if (searchForMatch(keyword)) {
            System.out.println("Match");
        } else {
            System.out.println("No match");
        }
    }


    private void showEnrolments() {

    }


    private void withdraw() {

    }


    private void readFromFile(String filename) throws FileNotFoundException {
        Scanner fileData = new Scanner(new FileReader(filename));

        fileData.nextLine();  // skips the headings

        while (fileData.hasNextLine()) {
            String line = fileData.nextLine();

            String[] values = line.split(",");

            // error handling probably required here...
            String name = values[0];
            int capacity = Integer.parseInt(values[1]);
            String year = values[2];
            String delivery = values[3];
            String ltlDay = values[4];
            LocalTime ltlTime = LocalTime.parse(values[5], formatter);
            double duration = Double.parseDouble(values[6]);

            Course course = new Course(name, capacity, year,
                    delivery, ltlDay, ltlTime, duration);

            coursesArray.add(course);
        }
    }


    private boolean searchForMatch(String keyword) {
        for (Course c : coursesArray) {
            if (c.getName().toLowerCase().contains(keyword.toLowerCase())) {
                return true;
            }
        }
        return false;
    }


    public String getString(String prompt) {
        String value;  // stores user input

        // as long as user input isn't an empty String, do-while repeats
        do {
            System.out.print(prompt);
            value = sc.nextLine();
        } while (value.equals(""));

        return value;  // return the expected value
    }
}