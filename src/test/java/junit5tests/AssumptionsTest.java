package junit5tests;

import org.junit.jupiter.api.Assumptions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assumptions.*;

public class AssumptionsTest {
    @ParameterizedTest(name = "run count: {index} - current value: {arguments}") //annotation denoting this test function accepts
    // parameters. The text between () is for better readability in the console.
    @ValueSource(ints = {1, 3, 10}) // The values to be passed to the test function of type int one by one in the written order.
    void intTest(int param){ // return type is always void followed by function name and parameters.
        assumeTrue(param > 2); //only run if condition is true.
        System.out.println("param = " + param); //simple print statement.
    }

    @ParameterizedTest(name = "run count: {index} - current value: {arguments}") //annotation denoting this test function accepts
    // parameters. The text between () is for better readability in the console.
    @ValueSource(ints = {1, 3, 10}) // The values to be passed to the test function of type int one by one in the written order.
    void intTest2(int param){ // return type is always void followed by function name and
        // parameters.
        assumeFalse(param < 2, "test failed because current value is: " + param); // block the
        // test and print message if condition is true.
        System.out.println("param = " + param); //simple print statement.
    }

    @ParameterizedTest(name = "run count: {index} - current value: {arguments}") //annotation denoting this test function accepts
    // parameters. The text between () is for better readability in the console.
    @ValueSource(ints = {1, 3, 10}) // The values to be passed to the test function of type int one by one in the written order.
    void intTest3(int param){ // return type is always void followed by function name and
        // parameters.

        //message only prints when condition is true.
        assumingThat(param > 2, () -> System.out.println("this run executed"));

        System.out.println("param = " + param); //simple print statement.
    }
}
