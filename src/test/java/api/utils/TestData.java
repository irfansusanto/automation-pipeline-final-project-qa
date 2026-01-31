package api.utils;

import java.util.UUID;

public class TestData {

    public static String divisionName() {
        return "DIV-" + UUID.randomUUID();
    }

    public static String employeeName() {
        return "EMP-" + UUID.randomUUID();
    }

    public static String trainingName() {
        return "TRN-" + UUID.randomUUID();
    }
}
