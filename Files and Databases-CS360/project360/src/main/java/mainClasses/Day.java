package project360_try1;

import java.time.LocalDate;

public class Day {

    private static LocalDate today;

    static public void setToday() {
        Day.today = LocalDate.now();
//        System.out.println("set " + today);

    }

    static public LocalDate getToday() {
        return today;
    }

    static public LocalDate addDay() {
        today = today.plusDays(1);
        return today;
    }



}
