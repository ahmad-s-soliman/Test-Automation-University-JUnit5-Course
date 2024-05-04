package junit5tests;

import listeners.listener;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Assumptions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.util.Arrays.asList;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;


@ExtendWith(listener.class)
public class AssertionsTest {
    @Test
    void assertEquals(){
        Assertions.assertEquals("first String", "second String", "The 2 strings didn't match");
    }

    @Test
    void assertEqualsListTest(){
        List<String> expectedValues = asList("firstString", "secondString", "thirdString");
        List<String> actualValues = asList("firstString", "secondString");
        Assertions.assertEquals(expectedValues, actualValues);
    }

    @Test
    void assertEqualsArrayTest(){
        int [] expected = {1, 5, 3};
        int [] actual = {1, 2, 3};
        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    void assertTrueTest(){
        assertTrue(false, "This condition evaluates to false"); // true && false = fail
        assertFalse(false); //false && false == pass
    }

    @Test
    void assertThrowsTest(){
        assertThrows(NullPointerException.class, null); //method passes if executable is indeed a null
    }

    //this method will execute all assertions and show results at the end. As opposed to failing the test when an
    // assertion fails.
    @Test
    void assertAllTest(){
        assertAll(
                () -> Assertions.assertEquals("first String", "second String", "The 2 strings didn't match"),
                () -> assertThrows(NullPointerException.class, null),
                () -> assertTrue(false, "This condition evaluates to false")
        );
    }

    //the benefit of hamcrest is to allow assertions for collections, which means we can do assertions for lists, maps or arrays if needed.

    @Test
    void assertForMapTest() {
        Map<String, Integer> theMap = new HashMap<>();
        theMap.put("firstKey", 1);
        theMap.put("secondKey", 2);
        theMap.put("thirdKey", 3);

        assertThat(theMap, Matchers.hasValue(4));
        assertThat(theMap, hasKey("secondKey1"));
        
    }


    @Test
    void assertForListTest(){
        List<String> theList = asList("firstString", "secondString", "thirdString");
        assertThat(theList, hasItem("secondString"));
    }

    @Test
    void assertForAnyOf(){
        List<String> theList = asList("firstString", "secondString", "thirdString");
        //check for *any* of the items to exist
        assertThat(theList, anyOf(hasItem("firstString"),
                hasItem("noString")));
    }

    @Test
    void assertForAllOf(){
        List<String> theList = asList("firstString", "secondString", "thirdString");
        //check for *all* of the items to exist
        assertThat(theList, allOf(hasItem("firstString"),
                hasItem("noString")));
    }

    @Test
    void assertForContainsAnyOrder() {
        List<String> theList = asList("firstString", "secondString", "thirdString");

        assertThat(theList, containsInAnyOrder("firstString", "thirdString", "secondString"));
    }


}

