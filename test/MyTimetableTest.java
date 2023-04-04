import org.junit.Before;
import org.junit.Test;

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
    public void testDisplayMenu() {
        // untestable
    }

    @Test
    public void testGetMenuChoice() {
        // untestable
    }

    @Test
    public void testEnrol() {
        // untestable
    }

    @Test
    public void testOutputMatches() {
        // untestable
    }

    @Test
    public void testGetCourseChoice() {
        // untestable
    }

    @Test
    public void testShowEnrolments() {
        // untestable
    }

    @Test
    public void testWithdraw() {
        // untestable
    }

    @Test
    public void testGetWithdrawalChoice() {
        // untestable
    }

    @Test(expected = FileNotFoundException.class)
    public void testReadFromFile() throws FileNotFoundException {
        // create a filename that does not fit the requirements
        String invalidFileName = "something.blah";

        // call readFromFile with an invalid filename
        // which should raise an exception
        tester.readFromFile(invalidFileName);
    }
}