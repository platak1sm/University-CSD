package Model;

import java.util.HashMap;
import java.util.Map;

public enum Day {
    Start(0),
    Mon(1),
    Tue(2),
    Wed(3),
    Thu(4),
    Fri(5),
    Sat(6),
    Sun(7);


    private int value;
    private static Map map = new HashMap<>();

    private Day(int value) {
        this.value = value;
    }

    static {
        for (Day day : Day.values()) {
            map.put(day.value, day);
        }
    }

    public static Day valueOf(int day) {
        if (day >= 29) day -= 28;
        else if (day >= 22) day -= 21;
        else if (day >= 15) day -= 14;
        else if (day >= 8) day -= 7;
        return (Day) map.get(day);
    }

    public int getValue() {
        return value;
    }
}
