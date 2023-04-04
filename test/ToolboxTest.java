import org.junit.*;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import static org.junit.Assert.*;

public class ToolboxTest {

    Toolbox tester;

    @Before
    public void setUp() {
        tester = new Toolbox(); // initialise a new Toolbox instance
    }

    @Test
    public void testFormatCourseDetails() {
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
    public void testAddDoubleToTime() {
        // create a starting time of 6:30, an increment of 1.5 hours,
        // and the time expected to be returned of 8:00
        LocalTime startTime = LocalTime.of(6,30);
        double addedTime = 1.5;
        LocalTime expectedTime = LocalTime.of(8,0);

        // return the value of addDoubleToTime method call
        LocalTime result = tester.addDoubleToTime(startTime,addedTime);

        // check that the returned value matches expectedTime
        assertEquals(expectedTime, result);
    }

    @Test
    public void testCheckDuplicates() {
        // create and add colours to an ArrayList
        List<String> testList = new ArrayList<>();
        testList.add("red");
        testList.add("green");
        testList.add("blue");

        // check that the checkDuplicates method successfully identifies
        // whether an element is already in a List
        assertTrue(tester.checkDuplicates(testList, "red"));
        assertFalse(tester.checkDuplicates(testList, "yellow"));
    }

    @Test
    public void testCheckMatch() {
        // create a new test course
        Course course = new Course("science",25,"Year 1",
                "Online","Friday", LocalTime.of(12, 0),2.5);

        // add that course into an arrayList
        List<Course> testList = new ArrayList<>();
        testList.add(course);

        // initialise a valid and invalid keyword
        String validKeyword = "SCI";
        String invalidKeyword = "SKI";

        // check that the checkMatch method successfully identifies
        // valid keyword matches
        assertTrue(tester.checkMatch(testList, validKeyword));
        assertFalse(tester.checkMatch(testList, invalidKeyword));
    }

    @Test
    public void testIsValidCSVFile() {
        // create two example strings, one of which fits the .csv format
        // while the other doesn't
        String shouldMatch = "asdf.csv";
        String shouldNotMatch = "asdf.cs";

        // check that the isValidCSVFile method successfully identifies
        // a String input matching the .csv file format
        assertTrue(tester.isValidCSVFile(shouldMatch));
        assertFalse(tester.isValidCSVFile(shouldNotMatch));
    }

    @Test
    public void testGetString() {

        // simulate user input
        String userInput = "testing";
        System.setIn(new ByteArrayInputStream(userInput.getBytes()));

        // call getString method
        String result = tester.getString("Enter a string: ");

        // check that the method returned the expected value
        assertEquals(userInput, result);
    }

    @Test
    public void testGetInteger() {
        // untestable
    }
}