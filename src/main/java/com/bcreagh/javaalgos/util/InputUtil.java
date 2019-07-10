package com.bcreagh.javaalgos.util;

import com.bcreagh.javaalgos.random.Shuffle;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
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

    public static Integer[] generatedRandomArrayWithoutDuplicates(int maxLength, int maxValue) {
        Integer[] arrayWithDuplicates = generatedRandomArray(maxLength, maxValue);
        if (arrayWithDuplicates.length == 0) {
            return arrayWithDuplicates;
        }
        Arrays.sort(arrayWithDuplicates);
        List<Integer> listWithDuplicatesRemoved = new ArrayList<>();
        listWithDuplicatesRemoved.add(arrayWithDuplicates[0]);
        Integer previousItem = arrayWithDuplicates[0];
        for (int i = 1; i < arrayWithDuplicates.length; i++) {
            if (arrayWithDuplicates[i].compareTo(previousItem) != 0) {
                listWithDuplicatesRemoved.add(arrayWithDuplicates[i]);
                previousItem = arrayWithDuplicates[i];
            }
        }
        Integer[] arrayWithDupsRemoved = new Integer[listWithDuplicatesRemoved.size()];
        arrayWithDupsRemoved = listWithDuplicatesRemoved.toArray(arrayWithDupsRemoved);
        Shuffle.shuffle(arrayWithDupsRemoved);
        return arrayWithDupsRemoved;
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

    public static void main(String[] args) {

    }
}
