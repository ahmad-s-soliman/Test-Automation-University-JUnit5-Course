// purpose of this class is to introduce test functions that accept parameters through different ways as follows:
// 1. Direct value by using @ValueSource annotation follow by values between (inputTypeHere = {})
// 2. Csv (comma separated value) source using @CsvSource annotation followed by (values = {"", "", ""})
// 3. Csv source file using @CsvFileSource (files = {"file1PathHere", "file2PathHere"}). Note that {} and double quote are to be
    //removed if only 1 file is used.
// 4.Method source using @MethodSource (value = "MethodName") if in the same class or (value = "PackageName.ClassName#MethodName")
package junit5tests;

import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.params.provider.*;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.params.provider.Arguments.arguments;

/*
Setting the test instance lifecycle mode to PER_CLASS enables the following features:

1. Shared test instance state between test methods in a given test class as well as between non-static @BeforeAll and @AfterAll methods in the test class.
2. Declaration of @BeforeAll and @AfterAll methods in @Nested test classes.
3. Declaration of @BeforeAll and @AfterAll on interface default methods.
4. Simplified declaration of @BeforeAll and @AfterAll methods in test classes implemented with the Kotlin programming language.
 */

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class ParameterizedTest {

    @org.junit.jupiter.params.ParameterizedTest(name = "run count: {index} - current value: {arguments}") //annotation denoting this test function accepts
    // parameters. The text between () is for better readability in the console.
    @ValueSource(ints = {1, 3, 10}) // The values to be passed to the test function of type int one by one in the written order.
    void intTest(int param){ // return type is always void followed by function name and parameters.
        System.out.println("param = " + param); //simple print statement.
    }

    /////////////////////////////////////////////////////////////////////////////////////////////

    @org.junit.jupiter.params.ParameterizedTest(name = "run count: {index} - current value: {arguments}")
    @NullAndEmptySource //add null and empty values to the input. Shouldn't be used with primitive data types (8):
                                                    //byte, short, int, long
                                                    //float, double
                                                    //boolean
                                                    //char
    @ValueSource( strings = {"ahmed", "testName"})  // the values to be passed into the function of type string
                                                    // one by one in written order.
    void stringTest(String param){
        System.out.println("param = " + param);
    }


    /////////////////////////////////////////////////////////////////////////////////////////


    @org.junit.jupiter.params.ParameterizedTest
    @CsvSource(value = {"ahmed, soliman", "abdullah, amer", "islam, amer"}) //pairs of input values to be passed
                                            // into the test function, one pair at a time, separated by a comma
                                            // hence the term comma separated values (CSV)
    void csvSource_stringString (String param1, String param2){ //the input is pairs of string
        System.out.println("param1 " + param1 + "param2 " +param2);
    }


    ///////////////////////////////////////////////////////////////////////////////////////////

    @org.junit.jupiter.params.ParameterizedTest
    @CsvSource(value = {"ahmed, 0, false", "abdullah, 1, true", "islam, 2, true"})
    void csvSource_stringIntBoolean (String param1, int param2, boolean param3){ // the input is
        // string, int, boolean
        System.out.println("param1 = " + param1 + ", param2 = " + param2 + ", param3 = " + param3);
    }

    ///////////////////////////////////////////////////////////////////////////////////////////


    @org.junit.jupiter.params.ParameterizedTest
    // the input below is pairs of only 2 values. The second value is separated by a comma so a single quote had to be used.
    @CsvSource(value = {"ahmed, 'soliman, soliman'", "abdullah, 'amer, amer'", "islam, 'amer, amer'"})
    void csvSource_stringWithComma(String param1, String param2){
        System.out.println("param1 " + param1 + "param2 " +param2);
    }

    ///////////////////////////////////////////////////////////////////////////////////////////

    @org.junit.jupiter.params.ParameterizedTest
    @CsvSource(value = {"1, 1", "2, 2", "3, 3"}) //input is pairs of 2 int at a time
    void csvSource_intInt (int param1, int param2){
        System.out.println("param1 " + param1 + "param2 " +param2);
    }

    ///////////////////////////////////////////////////////////////////////////////////////////

    @org.junit.jupiter.params.ParameterizedTest
    @CsvSource(value = {"ahmed?soliman", "abdullah?amer", "islam?amer"}, delimiter = '?') // delimited char of ? is used instead
        //of the regular comma.
    void csvSource_stringStringWithDelimiter (String param1, String param2){
        System.out.println("param1 " + param1 + "param2 " +param2);
    }


    ///////////////////////////////////////////////////////////////////////////////////////////

    // Here the input is 2 files of csv values with skipping the first line because it contains titles of the values and not
    // actual values.
    @org.junit.jupiter.params.ParameterizedTest
    @CsvFileSource(files = {"src/test/java/resources/shoppingList.csv", "src/test/java/resources/shoppingList2.csv"},
            numLinesToSkip = 1)
    void csvFileSource_StringDoubleIntStringString(String name, double price, int qty, String uom, String prov){
        System.out.println("name " +name + "price ="+ price + "qty =" + qty + "uom =" + uom + "prov= " + prov);
    }


    ///////////////////////////////////////////////////////////////////////////////////////////


    @org.junit.jupiter.params.ParameterizedTest
    @CsvFileSource(files = "src/test/java/resources/shoppingList3.csv",
            numLinesToSkip = 1, delimiterString = "___") // here a delimiter string of three underscores is used.
    //please note the difference between a delimited (char) and a delimiterString (string).
    void csvFileSource_StringDoubleIntStringStringWithDelimiter(String name, double price, int qty, String uom, String prov){
        System.out.println("name " +name + "price ="+ price + "qty =" + qty + "uom =" + uom + "prov= " + prov);
    }

    /////////////////////////////////////////////////////////////////////////////////
    // test method using method source
    @org.junit.jupiter.params.ParameterizedTest
    @MethodSource(value = "sourceString") // name of the method that will provide the parameters.
    void methodSource_sourceString (String param1){
        System.out.println("param1 =" + param1);
    }

    List<String> sourceString(){
        //processing here
        return Arrays.asList("tomato", "carrot", "cabbage"); //returns a list to the test method above.
    }

    /////////////////////////////////////////////////////////////////////////////

    @org.junit.jupiter.params.ParameterizedTest
    @MethodSource(value = "sourceStringAsStream")
    void methodSource_sourceStringAsStream(String param1){
        System.out.println("param1 =" +param1);
    }


    Stream<String> sourceStringAsStream() {
        //processing
        return Stream.of("beetroot", "apple", "pear"); //return a stream to be used in the above test method.
    }

    //////////////////////////////////////////////////////////


    //This function accepts 2 params from a method source
    @org.junit.jupiter.params.ParameterizedTest
    @MethodSource(value = "sourceList_StringDouble")
    void methodSource_ListStringDouble(String param1, double param2){
        System.out.println("param1 = " + param1 + "param2 ="  + param2);
    }

    //method source that returns 2 params of type list
    List<Object> sourceList_StringDouble(){
        //processing here
        return Arrays.asList(arguments("tomato", 2.5), arguments("carrot", 1.5),
                arguments("potato", 3.5));
    }

    /////////////////////////////////////////////////////////

    //Test function accepts 2 params of type stream.
    //This function is in another class.
    @org.junit.jupiter.params.ParameterizedTest
    @MethodSource(value = "junit5tests.ParamProvider#sourceStream_StringDouble") //packageName.className#methodName
    void methodSource_StreamStringDouble(String param1, double param2){
        System.out.println("param1 =" + param1 + "param2 =" + param2);
    }


}



