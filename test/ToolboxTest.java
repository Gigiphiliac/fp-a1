import org.junit.*;

import java.io.ByteArrayInputStream;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class ToolboxTest {

    Toolbox tester;

    @Before
    public void setUp() {
        tester = new Toolbox(); // initialise a new Toolbox instance
    }

    @Test
    public void testFormatCourseDetails_success() {
        // create a new test course
        Course course = new Course("science",25,"Year 1",
                "Online","Friday", LocalTime.of(12, 0),2.5);

        // create a formatted expectedOutput and an actual String formatted
        // using the formatCourseDetails method
        String expectedOutput = String.format("%d) %-30s%-15s%-18s\n",
                1, "science", "Online", "Fri 12:00-14:30");
        String formattedCourseDetails = tester.formatCourseDetails(course, 1);

        // verify that the formatted result matches the expected formatting
        assertEquals(expectedOutput, formattedCourseDetails);
    }

    @Test
    public void testFormatCourseDetails_failure() {
        // create a new test course
        Course course = new Course("science",25,"Year 1",
                "Online","Friday", LocalTime.of(12, 0),2.5);

        // create an incorrectly formatted output and an actual String formatted
        // using the formatCourseDetails method
        String incorrectOutput = "This isn't even a formatted String hehe";
        String formattedCourseDetails = tester.formatCourseDetails(course, 1);

        // verify that the formatted result fails to match the incorrect formatting
        assertNotEquals(incorrectOutput, formattedCourseDetails);
    }

    @Test
    public void testAddDoubleToTime_success() {
        // create a starting time of 6:30, an increment of 1.5 hours,
        // and the time expected to be returned of 8:00
        LocalTime startTime = LocalTime.of(6,30);
        double addedTime = 1.5;
        LocalTime expectedTime = LocalTime.of(8,0);

        // return the value of addDoubleToTime method call
        LocalTime result = tester.addDoubleToTime(startTime, addedTime);

        // check that the returned value matches expectedTime
        assertEquals(expectedTime, result);
    }

    @Test
    public void testAddDoubleToTime_failure() {
        // create a starting time of 12:00, an increment of 2.5 hours,
        // and the time expected to be returned of 8:00
        LocalTime startTime = LocalTime.of(12,0);
        double addedTime = 2.5;

        // create a LocalTime that should be incorrect
        LocalTime incorrectTime = LocalTime.of(15,30);
        // expected result is 14:30

        // return the value of addDoubleToTime method call
        LocalTime result = tester.addDoubleToTime(startTime, addedTime);

        // check that the returned value matches expectedTime
        assertNotEquals(incorrectTime, result);
    }

    @Test
    public void testCheckDuplicates_success() {
        // create and add colours to an ArrayList
        List<String> testList = new ArrayList<>();
        testList.add("red");
        testList.add("green");
        testList.add("blue");

        // validate that checkDuplicates successfully locates 'red' in the testList
        assertTrue(tester.checkDuplicates(testList, "red"));
    }

    @Test
    public void testCheckDuplicates_failure() {
        // create and add colours to an ArrayList
        List<String> testList = new ArrayList<>();
        testList.add("red");
        testList.add("green");
        testList.add("blue");

        // validate that checkDuplicates fails to locate 'yellow' in the testList
        assertFalse(tester.checkDuplicates(testList, "yellow"));
    }

    @Test
    public void testCheckMatch_matchFound() {
        // create a new test course
        Course course = new Course("science",25,"Year 1",
                "Online","Friday", LocalTime.of(12, 0),2.5);

        // add that course into an arrayList
        List<Course> testList = new ArrayList<>();
        testList.add(course);

        // initialise a valid keyword
        String validKeyword = "SCI";

        // check that the checkMatch method successfully identifies
        // valid keyword matches
        assertTrue(tester.checkMatch(testList, validKeyword));
    }

    @Test
    public void testCheckMatch_matchNotFound() {
        // create a new test course
        Course course = new Course("science",25,"Year 1",
                "Online","Friday", LocalTime.of(12, 0),2.5);

        // add that course into an arrayList
        List<Course> testList = new ArrayList<>();
        testList.add(course);

        // initialise an invalid keyword
        String invalidKeyword = "SKI";

        // check that the checkMatch method successfully identifies
        // invalid keyword matches
        assertFalse(tester.checkMatch(testList, invalidKeyword));
    }

    @Test
    public void testIsValidCSVFile_validMatch() {
        // create an example string which matches the .csv format
        String shouldMatch = "asdf.csv";

        // check that the isValidCSVFile method successfully identifies
        // a String input matching the .csv file format
        assertTrue(tester.isValidCSVFile(shouldMatch));
    }

    @Test
    public void testIsValidCSVFile_invalidMatch() {
        // create a string that does not match the .csv format
        String shouldNotMatch = "asdf.cs";

        // check that the isValidCSVFile method successfully identifies
        // a String input not matching the .csv file format
        assertFalse(tester.isValidCSVFile(shouldNotMatch));
    }

    @Test
    public void testGetString_success() {

        // simulate user input
        String userInput = "testing";
        System.setIn(new ByteArrayInputStream(userInput.getBytes()));

        // call getString method
        String result = tester.getString("Enter a string: ");

        // check that the method returned the expected value
        assertEquals(userInput, result);
    }

    @Test
    public void testGetString_failure() {
        // cannot test due to the need to queue simulated user input
    }

    @Test
    public void testGetInteger_success() {

        // simulate user input
        String userInput = "2";
        System.setIn(new ByteArrayInputStream(userInput.getBytes()));

        // call getInteger method
        int result = tester.getInteger("Enter an integer: ");

        // check that the method returned the expected value
        assertEquals(Integer.parseInt(userInput), result);
    }

    @Test
    public void testGetInteger_failure() {
        // cannot test due to the need to queue simulated user input
    }
}