import javax.sound.midi.SysexMessage;
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
        // store the user's menu choice
        String choice = getMenuChoice();

        // until the user selects '4' (the exit option), the program will
        // remain in this loop. (completing any function will return to menu)
        while (!choice.equals("4")) {
            switch (choice) {
                case "1" -> enrol();
                case "2" -> showEnrolments();
                case "3" -> withdraw();
                default -> System.out.println("Please select a valid "
                        + "menu option.");
            }
            choice = getMenuChoice();  // get next menu choice
        }
        System.out.println("Quitting...");
    }


    private String getMenuChoice() {
        // return the user input according to displayed menu
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
        // get a keyword from the user
        String keyword = tb.getString("Enter a keyword to search: ");

        // if a keyword match is found, output all the keyword matches before
        // allowing the user to select from the matches
        if (tb.checkMatch(coursesArray, keyword)) {
            outputMatches(keyword);
        } else {  // no initial match found
            System.out.println("No courses were found matching that "
                    + "keyword...");
        }
    }


    private void outputMatches(String keyword) {

        // iterate through coursesArray and add any courses with a name
        // matching 'keyword' to the ArrayList 'matches'
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

        try {
            // get course index as int
            response = getCourseChoice(matches);

            // throw a ReturnToMenuException if the user chose to exit
            // to menu (1 higher than last array element)
            if (response==(matches.size()+1)) {
                throw new ReturnToMenuException("Returning to menu...");
            }

            // retrieve the course according to 'response'
            selectedCourse = matches.get(response-1);
            // add course to 'coursesEnrolled' if it isn't already in there
            if (!tb.checkDuplicates(coursesEnrolled, selectedCourse)) {
                System.out.println("You have enrolled in the course '"
                        + selectedCourse.getName() + "'!");
                coursesEnrolled.add(selectedCourse);
            } else {  // the course was already in 'coursesEnrolled'
                System.out.println("You are already enrolled in the course '"
                        + selectedCourse.getName() + "'.");
            }

        // return to menu and output message to console
        } catch (ReturnToMenuException e) {
            System.out.println(e.getMessage());

        // call this function recursively if the user input was not
        // a valid menu option
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Please select a valid option.");
            outputMatches(keyword);
        }
    }


    private int getCourseChoice(List<Course> array) {
        // start the counter at 1 because it is presented to users
        int i=1;

        // iterate through 'array' and output the name of each course
        for (Course c : array) {
            System.out.println(i+") "+c.getName());
            i++;
        }
        // last option is 'return to menu'
        System.out.println(i+") Return to menu");

        // return the user input as an integer
        return tb.getInteger("Please select: ");
    }


    private void showEnrolments() {
        if (!coursesEnrolled.isEmpty()) {
            // start the counter at 1 because it is presented to users
            int i=1;

            System.out.println("""
                    --------------------------------------------------------------------------------
                    You have enrolled into the following course(s):
                    --------------------------------------------------------------------------------""");

            // format each course in 'array'
            for (Course c : coursesEnrolled) {
                // pass Course and index to format and print
                System.out.print(tb.formatCourseDetails(c, i));
                i++;
            }

        } else {  // coursesEnrolled is empty
            System.out.println("You are not enrolled in any classes.");
        }
    }


    private void withdraw() {
        // continue if the user is enrolled in at least one course
        if (!coursesEnrolled.isEmpty()) {

            System.out.println("""
                    --------------------------------------------------------------------------------
                    Please choose a course to withdraw:
                    --------------------------------------------------------------------------------""");

            int response;
            Course selectedCourse;

            try {
                // get course index as int
                response = getWithdrawalChoice(coursesEnrolled);

                // throw a ReturnToMenuException if the user chose to exit
                // to menu (1 higher than last array element)
                if (response==(coursesEnrolled.size()+1)) {
                    throw new ReturnToMenuException("Returning to menu...");
                }

                // retrieve the course according to 'response' and remove
                // it from coursesEnrolled
                selectedCourse = coursesEnrolled.get(response - 1);
                System.out.println("You have withdrawn from '"
                        + selectedCourse.getName() + "'!");
                coursesEnrolled.remove(selectedCourse);

            // return to menu and output message to console
            } catch (ReturnToMenuException e) {
                System.out.println(e.getMessage());

            // call this function recursively if the user input was not
            // a valid menu option
            } catch (IndexOutOfBoundsException e) {
                System.out.println("Please select a valid option.");
                withdraw();
            }

        } else {  // if coursesEnrolled is empty
            System.out.println("You must be enrolled in at least one class "
                    + "to withdraw.");
        }
    }


    private int getWithdrawalChoice(List<Course> array) {
        // start the counter at 1 because it is presented to users
        int i=1;

        // format each course in 'array'
        for (Course c : array) {
            // pass Course and index to format and print
            System.out.print(tb.formatCourseDetails(c, i));
            i++;
        }
        // last option is 'return to menu'
        System.out.println(i+") Return to menu");

        // return the user input as an integer
        return tb.getInteger("Please select: ");
    }


    void readFromFile(String filename) throws FileNotFoundException {

        try {
            // create a Scanner object around the file inputted
            Scanner fileData = new Scanner(new FileReader(filename));

            fileData.nextLine();  // skips the headings

            // loop through the file until there are no more lines to read
            while (fileData.hasNextLine()) {
                // take current line and split into an array of values
                String line = fileData.nextLine();
                String[] values = line.split(",");

                // assign each value to their respective variable
                String name = values[0];
                int capacity = Integer.parseInt(values[1]);
                String year = values[2];
                String delivery = values[3];
                String ltlDay = values[4];
                LocalTime ltlTime = LocalTime.parse(values[5], tb.formatter);
                double duration = Double.parseDouble(values[6]);

                // create a new Course object and add to the coursesArray ArrayList
                Course course = new Course(name, capacity, year,
                        delivery, ltlDay, ltlTime, duration);
                coursesArray.add(course);
            }

        // catches FileNotFoundExceptions and its children
        } catch (FileNotFoundException e) {
            // throws InvalidFileNameException if the filename is not
            // a valid CSV file
            if (!tb.isValidCSVFile(filename)) {
                throw new InvalidFileNameException("'" + filename
                        + "' is an invalid .csv filename");

            // throws a FileNotFoundException for any unaccounted for
            // file exception cases
            } else {
                throw new FileNotFoundException("'" + filename
                        + "' could not be found");
            }
        }

    }

}