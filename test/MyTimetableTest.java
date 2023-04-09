import org.junit.*;

import java.io.ByteArrayInputStream;
import java.io.FileNotFoundException;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class MyTimetableTest {

    MyTimetable tester;

    @Before
    public void setUp() {
        tester = new MyTimetable();
    }

    @Test
    public void testDisplayMenu_1() {
        // untestable
    }

    @Test
    public void testDisplayMenu_2() {
        // untestable
    }

    @Test
    public void testGetMenuChoice_Success() {

        // simulate user input within getCourseChoice
        String userInput = "3"; // the 'withdraw' menu option
        System.setIn(new ByteArrayInputStream(userInput.getBytes()));

        // establish the lowest and highest valid menu options for the menu
        int lowestMenuVal = 1;
        int highestMenuVal = 4;

        // call getMenuChoice method
        int result = Integer.parseInt(tester.getMenuChoice());

        // check that the menu choice is within valid margins
        assertTrue(result>=lowestMenuVal && result<=highestMenuVal);
    }

    @Test(expected = NumberFormatException.class)
    public void testGetMenuChoice_NumberFormatException() {
        // simulate user input within getCourseChoice
        String userInput = "rmit"; // an invalid menu choice
        System.setIn(new ByteArrayInputStream(userInput.getBytes()));

        // call getMenuChoice method (however it will try parsing a String and
        // will call a NumberFormatException)
        int result = Integer.parseInt(tester.getMenuChoice());
    }

    @Test
    public void testEnrol_1() {
        // untestable
    }

    @Test
    public void testEnrol_2() {
        // untestable
    }

    @Test
    public void testOutputMatches_CourseSelected() {
        tester.coursesArray.add(new Course("science",25,"Year 1",
                "Online","Friday", LocalTime.of(12, 0),2.5));
        tester.coursesArray.add(new Course("geography",100,"Year 2",
                "Face-to-face","Monday", LocalTime.of(8, 0),2));

        // simulate user input within outputMatches
        String userInput = "2"; // enrol in the 2nd option
        System.setIn(new ByteArrayInputStream(userInput.getBytes()));

        String keyword = "e"; // present in both Courses

        // get 'coursesEnrolled' size before and after outputMatches call
        int initialEnrolledArrayLength = tester.coursesEnrolled.size();
        tester.outputMatches(keyword);
        int currentEnrolledArrayLength = tester.coursesEnrolled.size();

        // check that following outputMatches, the size of
        // 'tester.coursesEnrolled' (array) increased by one
        assertEquals(initialEnrolledArrayLength,
                currentEnrolledArrayLength - 1);
    }

    @Test
    public void testOutputMatches_ReturnToMenu() {
        tester.coursesArray.add(new Course("science",25,"Year 1",
                "Online","Friday", LocalTime.of(12, 0),2.5));
        tester.coursesArray.add(new Course("geography",100,"Year 2",
                "Face-to-face","Monday", LocalTime.of(8, 0),2));

        // simulate user input within outputMatches
        String userInput = "3"; // select the 3rd option (return to menu)
        System.setIn(new ByteArrayInputStream(userInput.getBytes()));

        String keyword = "e"; // present in both Courses

        // get 'coursesEnrolled' size before and after outputMatches call
        int initialEnrolledArrayLength = tester.coursesEnrolled.size();
        tester.outputMatches(keyword);
        int currentEnrolledArrayLength = tester.coursesEnrolled.size();

        // check that following outputMatches, the size of
        // 'tester.coursesEnrolled' (array) remained the same
        // i.e. no course was selected from menu
        assertEquals(initialEnrolledArrayLength,
                currentEnrolledArrayLength);
    }

    @Test
    public void testGetCourseChoice_EmptyArray() {
        List<Course> emptyList = new ArrayList<>();

        // simulate user input within getCourseChoice
        String userInput = "1"; // the expected 'exit' menu option
        System.setIn(new ByteArrayInputStream(userInput.getBytes()));

        // establish the lowest and highest valid menu options for the given array
        int lowestVal = 1;
        int highestVal = 1;

        // call getCourseChoice method
        int result = tester.getCourseChoice(emptyList);

        // check that the successful menu option of getCourseChoice was '1'
        // i.e. no elements were in the array (only 'exit')
        assertEquals(Integer.parseInt(userInput), result);
        assertTrue(result>=lowestVal && result<=highestVal);
    }

    @Test
    public void testGetCourseChoice_PopulatedArray() {
        List<Course> populatedList = new ArrayList<>();
        populatedList.add(new Course("science",25,"Year 1",
                "Online","Friday", LocalTime.of(12, 0),2.5));
        populatedList.add(new Course("geography",100,"Year 2",
                "Face-to-face","Monday", LocalTime.of(8, 0),2));

        // simulate user input within getCourseChoice
        String userInput = "2"; // the expected 2nd menu option
        System.setIn(new ByteArrayInputStream(userInput.getBytes()));

        // establish the lowest and highest valid menu options for the given array
        int lowestVal = 1;
        int highestVal = 3;

        // call getCourseChoice method
        int result = tester.getCourseChoice(populatedList);

        // check that the successful menu option of getCourseChoice was '2'
        // i.e. the second element in the array
        assertEquals(Integer.parseInt(userInput), result);
        assertTrue(result>=lowestVal && result<=highestVal);
    }

    @Test
    public void testShowEnrolments_1() {
        // untestable
    }

    @Test
    public void testShowEnrolments_2() {
        // untestable
    }

    @Test
    public void testWithdraw_CourseSelected() {
        tester.coursesEnrolled.add(new Course("science",25,"Year 1",
                "Online","Friday", LocalTime.of(12, 0),2.5));
        tester.coursesEnrolled.add(new Course("geography",100,"Year 2",
                "Face-to-face","Monday", LocalTime.of(8, 0),2));

        // simulate user input within withdraw
        String userInput = "1"; // withdraw from the 1st option
        System.setIn(new ByteArrayInputStream(userInput.getBytes()));

        // get 'coursesEnrolled' size before and after 'withdraw' call
        int initialEnrolledArrayLength = tester.coursesEnrolled.size();
        tester.withdraw();
        int currentEnrolledArrayLength = tester.coursesEnrolled.size();

        // check that following withdraw, the size of
        // 'tester.coursesEnrolled' (array) decreased by one
        assertEquals(initialEnrolledArrayLength,
                currentEnrolledArrayLength + 1);
    }

    @Test
    public void testWithdraw_ReturnToMenu() {
        tester.coursesEnrolled.add(new Course("science",25,"Year 1",
                "Online","Friday", LocalTime.of(12, 0),2.5));
        tester.coursesEnrolled.add(new Course("geography",100,"Year 2",
                "Face-to-face","Monday", LocalTime.of(8, 0),2));

        // simulate user input within withdraw
        String userInput = "3"; // select the 3rd option (return to menu)
        System.setIn(new ByteArrayInputStream(userInput.getBytes()));

        // get 'coursesEnrolled' size before and after 'withdraw' call
        int initialEnrolledArrayLength = tester.coursesEnrolled.size();
        tester.withdraw();
        int currentEnrolledArrayLength = tester.coursesEnrolled.size();

        // check that following withdraw, the size of
        // 'tester.coursesEnrolled' (array) remained the same
        // i.e. no course was selected from menu
        assertEquals(initialEnrolledArrayLength,
                currentEnrolledArrayLength);
    }

    @Test
    public void testGetWithdrawalChoice_EmptyArray() {
        List<Course> emptyList = new ArrayList<>();

        // simulate user input within getWithdrawalChoice
        String userInput = "1"; // the expected 'exit' menu option
        System.setIn(new ByteArrayInputStream(userInput.getBytes()));

        // establish the lowest and highest valid menu options for the given array
        int lowestVal = 1;
        int highestVal = 1;

        // call getWithdrawalChoice method
        int result = tester.getWithdrawalChoice(emptyList);

        // check that the successful menu option of getWithdrawalChoice was '1'
        // i.e. no elements were in the array (only 'exit')
        assertEquals(Integer.parseInt(userInput), result);
        assertTrue(result>=lowestVal && result<=highestVal);
    }

    @Test
    public void testGetWithdrawalChoice_PopulatedArray() {
        List<Course> populatedList = new ArrayList<>();
        populatedList.add(new Course("science",25,"Year 1",
                "Online","Friday", LocalTime.of(12, 0),2.5));
        populatedList.add(new Course("geography",100,"Year 2",
                "Face-to-face","Monday", LocalTime.of(8, 0),2));

        // simulate user input within getWithdrawalChoice
        String userInput = "2"; // the expected 2nd menu option
        System.setIn(new ByteArrayInputStream(userInput.getBytes()));

        // establish the lowest and highest valid menu options for the given array
        int lowestVal = 1;
        int highestVal = 3;

        // call getWithdrawalChoice method
        int result = tester.getWithdrawalChoice(populatedList);

        // check that the successful menu option of getWithdrawalChoice was '2'
        // i.e. the second element in the array
        assertEquals(Integer.parseInt(userInput), result);
        assertTrue(result>=lowestVal && result<=highestVal);
    }

    @Test(expected = InvalidFileNameException.class)
    public void testReadFromFile_InvalidFileNameException() throws FileNotFoundException {
        // create a filename that does not fit the requirements
        String invalidFileName = "something.blah";

        // call readFromFile with an invalid filename (and invalid csv format)
        // which should raise an exception
        tester.readFromFile(invalidFileName);
    }

    @Test(expected = FileNotFoundException.class)
    public void testReadFromFile_FileNotFoundException() throws FileNotFoundException {
        // create a filename that does not fit the requirements
        String invalidCSVFileName = "something.csv";

        // call readFromFile with an invalid filename (but valid csv format)
        // which should raise an exception
        tester.readFromFile(invalidCSVFileName);
    }
}