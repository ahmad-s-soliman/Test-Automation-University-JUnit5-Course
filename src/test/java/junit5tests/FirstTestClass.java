package junit5tests;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class FirstTestClass {

    @Test //annotation: labels the following function as test function and hence makes this
        // a test class because it has a test function
    void firstTest(){  // return type is always void followed by function name
        System.out.println("This is the first test"); //simple print statement, nothing too fancy here
    }

    // another test function but with a display name to show user story number, test case number and function name
    // for better readability in the console
    @Test
    @DisplayName("US no. - TC no. - sample method")
    void secondTest(){ //same as above
        System.out.println("This is the second test"); // same as above
    }
}
