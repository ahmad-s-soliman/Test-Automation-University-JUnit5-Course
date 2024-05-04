package junit5tests;

import org.junit.jupiter.api.*;

import java.util.concurrent.TimeUnit;

public class MiscTest {
    @Test
    @Timeout(value = 5, unit = TimeUnit.SECONDS)
    void timeout() throws InterruptedException {
        System.out.println("This is the test with timeout");
        Thread.sleep(6000);
    }

    @Test
    @Timeout(90)
    @DisplayName("This is the nice method")
    @Tag("TheTag")
    void annotatedMethod1(){
        System.out.println("This is the annotated method.");
    }

    @myAnnotation
    void annotatedMethod2() throws InterruptedException {
        System.out.println("This is the custom annotated method.");
        Thread.sleep(3000);
    }


    @TestInstance(TestInstance.Lifecycle.PER_CLASS) //because it has a lifecyle method, i.e beforeAll.
    @Nested
    class NestedTest{
        @BeforeAll
        void beforeAll() {
            System.out.println("This is the before all inside nested test.");
        }

        @Test
        void nestedTest(){
            System.out.println("This is the nested test method.");
        }
    }
}
