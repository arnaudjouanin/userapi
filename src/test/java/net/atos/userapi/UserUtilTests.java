package net.atos.userapi;

import java.time.LocalDate;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static net.atos.userapi.util.UserUtil.getAge;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class UserUtilTests {

    @Test
    void testGetAge_minorUser(){
        LocalDate minorsBirthdate = LocalDate.of(2017, 5, 12);
        int ageResult = getAge(minorsBirthdate);
        assertEquals(6, ageResult);
    }

    @Test
    void testGetAge_almostMajorUser(){
        LocalDate minorsBirthdate = LocalDate.of(2005, 10, 19);
        int ageResult = getAge(minorsBirthdate);
        assertEquals(17, ageResult);
    }

    @Test
    void testGetAge_majorUser(){
        LocalDate majorsBirthdate = LocalDate.of(1023, 5, 12);
        int ageResult = getAge(majorsBirthdate);
        assertEquals(1000, ageResult);
    }
}
