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
    List<Course> coursesEnrolled = new ArrayList<>();

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


    private void enrol() {
        String keyword = getString("Enter a keyword to search: ");

        if (checkMatch(keyword)) {
            outputMatches(keyword);
        } else {
            System.out.println("No courses were found matching that keyword...");
        }
    }


    private boolean checkMatch(String keyword) {
        for (Course c : coursesArray) {
            if (c.getName().toLowerCase().contains(keyword.toLowerCase())) {
                return true;
            }
        }
        return false;
    }


    private void outputMatches(String keyword) {

        List<Course> matches = new ArrayList<>();
        for (Course c : coursesArray) {
            if (c.getName().toLowerCase().contains(keyword.toLowerCase())) {
                matches.add(c);
            }
        }

        System.out.println("""
                --------------------------------------------------------------------------------
                > Select from matching list
                --------------------------------------------------------------------------------""");

        int response;
        Course selectedCourse;
        do {
            response = getCourseChoice(matches);
        } while (response<1||response>matches.size()+1);

        try {
            selectedCourse = matches.get(response-1);
            if (!checkDuplicates(coursesEnrolled, selectedCourse)) {
                System.out.println("You have enrolled in the course '" + selectedCourse.getName() + "'!");
                coursesEnrolled.add(selectedCourse);
            } else {
                System.out.println("You are already enrolled in the course '" + selectedCourse.getName() + "'.");
            }

        } catch (IndexOutOfBoundsException ignored) {}

    }


    private <T> boolean checkDuplicates(List<T> array, T element) {
        for (T t : array) {
            if (t.equals(element)) {
                return true;
            }
        }
        return false;
    }


    private int getCourseChoice(List<Course> array) {
        int i=1;
        for (Course c : array) {
            System.out.println(i+") "+c.getName());
            i++;
        }
        System.out.println(i+") Return to menu");

        return getInteger("Please select: ");
    }


    private void showEnrolments() {
        if (!coursesEnrolled.isEmpty()) {
            int i=1;

            System.out.println("""
                    --------------------------------------------------------------------------------
                    You have enrolled into the following course(s):
                    --------------------------------------------------------------------------------""");

            for (Course c : coursesEnrolled) {

                String day = c.getLtlDay().substring(0,3);
                LocalTime timeStart = c.getLtlTime();
                LocalTime timeEnd = addDoubleToTime(timeStart, c.getDuration());

                String dayAndTime = String.format("%s %s-%s", day, timeStart, timeEnd);

                System.out.printf("%d) %-30s%-15s%-18s\n", i, c.getName(), c.getDelivery(), dayAndTime);
                i++;
            }

        } else {
            System.out.println("You are not enrolled in any classes.");
        }
    }


    private LocalTime addDoubleToTime(LocalTime time, double value) {
        int hours = (int) value;
        int minutes = (int) ((value - hours) * 60);

        return time.plusHours(hours).plusMinutes(minutes);
    }


    private void withdraw() {
        if (!coursesEnrolled.isEmpty()) {

            System.out.println("""
                    --------------------------------------------------------------------------------
                    Please choose a course to withdraw:
                    --------------------------------------------------------------------------------""");

            int response;
            Course selectedCourse;
            do {
                response = getWithdrawalChoice(coursesEnrolled);
            } while (response<1||response>coursesEnrolled.size()+1);

            try {
                selectedCourse = coursesEnrolled.get(response-1);
                System.out.println("You have withdrawn from '" + selectedCourse.getName() + "'!");
                coursesEnrolled.remove(selectedCourse);
            } catch (IndexOutOfBoundsException ignored) {}
        } else {
            System.out.println("You must be enrolled in at least one class to withdraw.");
        }
    }


    private int getWithdrawalChoice(List<Course> array) {
        int i=1;
        for (Course c : array) {
            String day = c.getLtlDay().substring(0,3);
            LocalTime timeStart = c.getLtlTime();
            LocalTime timeEnd = addDoubleToTime(timeStart, c.getDuration());

            String dayAndTime = String.format("%s %s-%s", day, timeStart, timeEnd);

            System.out.printf("%d) %-30s%-15s%-18s\n", i, c.getName(), c.getDelivery(), dayAndTime);
            i++;
        }
        System.out.println(i+") Return to menu");

        return getInteger("Please select: ");
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


    public String getString(String prompt) {
        String value;  // stores user input

        // as long as user input isn't an empty String, do-while repeats
        do {
            System.out.print(prompt);
            value = sc.nextLine();
        } while (value.equals(""));

        return value;  // return the expected value
    }


    public int getInteger(String prompt) {
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