package junit5tests;

import listeners.listener;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.DisabledIf;
import org.junit.jupiter.api.condition.DisabledIfSystemProperty;
import org.junit.jupiter.api.condition.DisabledOnOs;
import org.junit.jupiter.api.condition.OS;
import org.junit.jupiter.api.extension.ExtendWith;

import java.time.DayOfWeek;
import java.time.LocalDateTime;

@ExtendWith(listener.class)
public class DisabledEnabledTest {
    @Test
    @Disabled(value = "This test is didn't run because of disabled annotation")
    void firstTest(){
        System.out.println("First test method");
    }

    @Test
    @DisabledOnOs(value = OS.WINDOWS, disabledReason = "demo of disabled on OS")
    void secondTest(){
        System.out.println("Second test method");
    }

    @Test
    @DisabledIfSystemProperty(named = "env", matches = "staging", disabledReason = "demo of @DisabledIfSystemProperty")
    void thirdTest(){
        System.out.println("Third test method");
    }

    @Test
    @DisabledIf(value = "provider", disabledReason = "demo of @DisabledIf")
    void fourthTest(){
        System.out.println("Fourth test method");
    }

    @Test
    void fifthTest(){
        System.out.println("Fifth test method");
    }

    boolean provider(){
        return LocalDateTime.now().getDayOfWeek().equals(DayOfWeek.FRIDAY);
    }

}
