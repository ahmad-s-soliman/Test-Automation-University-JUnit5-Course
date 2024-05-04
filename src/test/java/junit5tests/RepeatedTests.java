package junit5tests;

import org.junit.jupiter.api.Assumptions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.RepetitionInfo;

public class RepeatedTests {
    @RepeatedTest(5)
    void firstRepeatedTest(){
        System.out.println("This is the first test method.");
    }

    @RepeatedTest(value = 3, name = "Running repetition: {currentRepetition}." + "Total is : {totalRepetitions}")
    @DisplayName("This is a repeated test method")
    void secondRepeatedTest(){
        System.out.println("This is the second test method");
    }

    @RepeatedTest(3)
    void thirdRepeatedTest(RepetitionInfo repetitionInfo){
        System.out.println("This code will run at each repetition");
        Assumptions.assumingThat(repetitionInfo.getCurrentRepetition() == 3, () -> {
            System.out.println("This code will only run at repetition number 3.");
        });
    }
}
