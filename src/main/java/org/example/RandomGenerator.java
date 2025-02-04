package org.example;

import java.util.Random;

public class RandomGenerator {

    /**
     * Creates a random number ranging between lo and hi.
     * @param lo lower bound (inclusive)
     * @param hi upper bound (inclusive)
     * @return a random integer between lo and hi
     */
    public int discrete(int lo, int hi) { // 🔹 Change to "public"
        if (lo >= hi) {
            System.out.println("Error: lo must be less than hi.");
            System.exit(0);
        }

        Random r = new Random();
        return r.nextInt(hi - lo + 1) + lo;
    }
}
