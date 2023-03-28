import org.junit.*;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class ToolboxTest {

    Toolbox tester;

    @Before
    public void setUpClass() {
        tester = new Toolbox(); // initialise a new Toolbox instance
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
    public void testGetString() {
        // question to be 'asked'
        String prompt = "Enter a string: ";

        // simulate user input
        String input = "test";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        // return the value of getString method call
        String result = tester.getString(prompt);

        // check that the returned value matches userInput
        assertEquals(input, result);
    }

    @Test
    public void testGetInteger() {
        // question to be 'asked'
        String prompt = "Enter an integer: ";

        // simulate user input
        String input = "6";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        // return the value of getInteger method call
        int result = tester.getInteger(prompt);

        // check that the returned value matches userInput
        assertEquals(Integer.parseInt(input), result);
    }
}