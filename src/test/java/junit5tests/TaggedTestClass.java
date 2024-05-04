package junit5tests;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

//adding "!" before tag name will choose all test that don't have this tag
//Also the following symbols can be used: "&" and "|"
//from execute maven goals, the following can be used to run specific tags: mvn test -Dgroups=sanity
public class TaggedTestClass {
    @Tag("sanity")
    @Test //annotation: labels the following function as test function and hence makes this
        // a test class because it has a test function
    void firstTest(){  // return type is always void followed by function name
        System.out.println("This is the first test"); //simple print statement, nothing too fancy here
    }

    // another test function but with a display name to show user story number, test case number and function name
    // for better readability in the console
    @Tag("sanity")
    @Tag("acceptance")
    @Test
    @DisplayName("US no. - TC no. - sample method")
    void secondTest(){ //same as above
        System.out.println("This is the second test"); // same as above
    }


    @Tag("acceptance")
    @ParameterizedTest(name = "run count: {index} - current value: {arguments}") //annotation denoting this test function accepts
    // parameters. The text between () is for better readability in the console.
    @ValueSource(ints = {1, 3, 10}) // The values to be passed to the test function of type int one by one in the written order.
    void intTest(int param){ // return type is always void followed by function name and parameters.
        System.out.println("param = " + param); //simple print statement.
    }

}
