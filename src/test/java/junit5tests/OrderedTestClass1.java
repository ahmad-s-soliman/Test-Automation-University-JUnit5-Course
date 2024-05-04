package junit5tests;

import org.junit.jupiter.api.*;

//@TestMethodOrder(MethodOrderer.MethodName.class) //alphabetic order based on method name
//@TestMethodOrder(MethodOrderer.DisplayName.class) //alphabetic order based on display name. Test
// method without display name runs last
@TestMethodOrder(MethodOrderer.OrderAnnotation.class) //user @Order(intHere) to specify order.
// Low number runs first, and no number means max int/2 is used.
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class OrderedTestClass1 {

    @Test //annotation: labels the following function as test function and hence makes this
        // a test class because it has a test function
    @Order(2)
    void firstTest(){  // return type is always void followed by function name
        System.out.println("This is the first test"); //simple print statement, nothing too fancy here
    }

    // another test function but with a display name to show user story number, test case number and function name
    // for better readability in the console
    @Test
    @DisplayName("US no. - TC no. - sample method")
    @Order(1)
    void secondTest(){ //same as above
        System.out.println("This is the second test"); // same as above
    }
}
