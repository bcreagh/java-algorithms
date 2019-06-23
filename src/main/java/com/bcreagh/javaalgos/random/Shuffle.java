package com.bcreagh.javaalgos.random;

import java.util.Random;

public class Shuffle {

    public static void shuffle(Object[] input) {
        Random random = new Random();
        for (int i = 0; i < input.length; i++) {
            int swapIndex = i + random.nextInt(input.length - i);
            swap(input, i, swapIndex);
        }
    }

    private static void swap(Object[] input, int index1, int index2) {
        Object temp = input[index1];
        input[index1] = input[index2];
        input[index2] = temp;
    }

}
