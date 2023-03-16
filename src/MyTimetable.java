import java.text.ParseException;
import java.time.LocalTime;
import java.util.Scanner;

public class MyTimetable {

    Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        MyTimetable myTimetable = new MyTimetable();
        System.out.println("Welcome to MyTimetable!");
        System.out.println(myTimetable.getMenuChoice());
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


    private void displayMenu() {

        String choice = getMenuChoice();

        while(!choice.equals("4")) {
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


    private void enrol() {
    }


    private void showEnrolments() {
    }


    private void withdraw() {
    }


    private void readCoursesFromFile(String filename) {
        String name, year, delivery, ltlDay;
        int capacity;
        LocalTime ltlTime;
        double duration;

        // open and read file contents line by line
        // save each line of content as a new Course object

        value = line;
        name = value[0];
        capacity = value[1];
        year = value[2];
        delivery = value[3];
        ltlDay = value[4];
        ltlTime = value[5];
        duration = value[6];

        Course course = new Course(name, capacity, year, delivery,
                ltlDay, ltlTime, duration);
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