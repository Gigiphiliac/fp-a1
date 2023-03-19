import java.io.FileNotFoundException;
import java.io.FileReader;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MyTimetable {

    Toolbox tb = new Toolbox();
    List<Course> coursesArray = new ArrayList<>();
    List<Course> coursesEnrolled = new ArrayList<>();


    void displayMenu() {

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
        return tb.getString("""
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
        String keyword = tb.getString("Enter a keyword to search: ");

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
            if (!tb.checkDuplicates(coursesEnrolled, selectedCourse)) {
                System.out.println("You have enrolled in the course '" + selectedCourse.getName() + "'!");
                coursesEnrolled.add(selectedCourse);
            } else {
                System.out.println("You are already enrolled in the course '" + selectedCourse.getName() + "'.");
            }

        } catch (IndexOutOfBoundsException ignored) {}

    }


    private int getCourseChoice(List<Course> array) {
        int i=1;
        for (Course c : array) {
            System.out.println(i+") "+c.getName());
            i++;
        }
        System.out.println(i+") Return to menu");

        return tb.getInteger("Please select: ");
    }


    private void showEnrolments() {
        if (!coursesEnrolled.isEmpty()) {
            int i=1;

            System.out.println("""
                    --------------------------------------------------------------------------------
                    You have enrolled into the following course(s):
                    --------------------------------------------------------------------------------""");

            for (Course c : coursesEnrolled) {
                tb.formatCourseDetails(c, i);
                i++;
            }

        } else {
            System.out.println("You are not enrolled in any classes.");
        }
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
            tb.formatCourseDetails(c, i);
            i++;
        }
        System.out.println(i+") Return to menu");

        return tb.getInteger("Please select: ");
    }


    void readFromFile(String filename) throws FileNotFoundException {
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
            LocalTime ltlTime = LocalTime.parse(values[5], tb.formatter);
            double duration = Double.parseDouble(values[6]);

            Course course = new Course(name, capacity, year,
                    delivery, ltlDay, ltlTime, duration);

            coursesArray.add(course);
        }
    }

}