package org.postgrestest.insertapp;

import java.math.BigDecimal;
import java.util.Random;

public class RandomGenerator {

    private final Random random = new Random();

    String getStatus() {

        int next = random.nextInt(50);

        if (next < 35) {
            return "S";
        } else if (next < 45) {
            return "P";
        } else {
            return "F";
        }

    }

    BigDecimal getAmount() {

        double next = random.nextDouble() * 1000000;

        return new BigDecimal(next);
    }
}
