package com.bcreagh.javaalgos.util;

import java.util.Random;

public class InputUtil {

    private static Random random = new Random(System.currentTimeMillis());

    public static Integer[] generatedRandomArray(int maxLength, int maxValue) {
        int length = random.nextInt(maxLength);
        return randomArray(length, maxValue);
    }

    public static Integer[] generatedRandomArrayWithFixedLength(int length) {
        return randomArray(length, Integer.MAX_VALUE);
    }

    private static Integer[] randomArray(int length, int maxValue) {
        Integer[] array = new Integer[length];
        for (int i = 0; i < array.length; i++) {
            array[i] = random.nextInt(maxValue);
        }
        return array;
    }

    public static Integer[] getSortedArray() {
        Integer[] array = new Integer[100];
        for (int i = 0; i < array.length; i++) {
            array[i] = i * 3;
        }
        return array;
    }

    public static Integer[] getEmptyArray() {
        return new Integer[0];
    }

    public static Integer[] getArrayOfEqualValues(int value) {
        Integer[] array = new Integer[50];
        for (int i = 0; i < array.length; i++) {
            array[i] = value;
        }
        return array;
    }

    public static Integer[] getRandomArrayWithNegatives(int maxLength, int maxValue) {
        Integer[] array = generatedRandomArray(maxLength, maxValue);
        for (int i = 0; i < array.length; i++) {
            if (random.nextBoolean()) {
                array[i] = array[i] * -1;
            }
        }
        return array;
    }

}
