package net.atos.userapi.util;

import java.time.LocalDate;

public class UserUtil {

    public static int getAge(LocalDate birthdate){
        LocalDate today = LocalDate.now();

        int years = today.getYear() - birthdate.getYear();
        int days = today.getDayOfYear() - birthdate.getDayOfYear();

        if(days < 0) years--;
        int age = years;

        return age;
    }
}
