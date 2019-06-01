package com.bcreagh.javaalgos.sort;

import java.util.Random;
import static org.junit.Assert.*;

public class SortHelper {

    private static Random random = new Random(System.currentTimeMillis());

    public static void sort_shouldSortRandomlyGeneratedArrays(Sorter<Integer> sorter) {
        for (int i = 0; i < 100; i++) {
            Integer[] input = SortHelper.generatedRandomArray(200, 50000);
            input = sorter.sort(input);
            assertTrue(SortHelper.isSorted(input));
        }
    }

    public static void sort_shouldSortRandomlyGeneratedArraysWithNegatives(Sorter<Integer> sorter) {
        for (int i = 0; i < 20; i++) {
            Integer[] input = SortHelper.generatedRandomArray(200, 50000);
            input = sorter.sort(input);
            assertTrue(SortHelper.isSorted(input));
        }
    }

    public static void sort_withEmptyArray(Sorter<Integer> sorter) {
        Integer[] input = SortHelper.getEmptyArray();
        input = sorter.sort(input);
        assertTrue(input.length == 0);
    }

    public static void sort_withAllEqualValues(Sorter<Integer> sorter) {
        int value = 234;
        Integer[] input = SortHelper.getArrayOfEqualValues(value);
        sorter.sort(input);
    }

    public static void sort_withAlreadySortedArray(Sorter<Integer> sorter) {
        Integer[] input = SortHelper.getSortedArray();
        sorter.sort(input);
        assertTrue(SortHelper.isSorted(input));
    }

    private static <T extends Comparable<T>> boolean isSorted(T[] input) {
        for (int i = 0; i < (input.length - 1); i++) {
            if (input[i].compareTo(input[i + 1]) > 0) {
                return false;
            }
        }
        return true;
    }

    private static Integer[] generatedRandomArray(int maxLength, int maxValue) {
        int length = random.nextInt(maxLength);
        Integer[] array = new Integer[length];
        for (int i = 0; i < array.length; i++) {
            array[i] = random.nextInt(maxValue);
        }
        return array;
    }

    private static Integer[] getSortedArray() {
        Integer[] array = new Integer[100];
        for (int i = 0; i < array.length; i++) {
            array[i] = i * 3;
        }
        return array;
    }

    private static Integer[] getEmptyArray() {
        return new Integer[0];
    }

    private static Integer[] getArrayOfEqualValues(int value) {
        Integer[] array = new Integer[50];
        for (int i = 0; i < array.length; i++) {
            array[i] = value;
        }
        return array;
    }

    private static Integer[] getRandomArrayWithNegatives(int maxLength, int maxValue) {
        Integer[] array = generatedRandomArray(maxLength, maxValue);
        for (int i = 0; i < array.length; i++) {
            if (random.nextBoolean()) {
                array[i] = array[i] * -1;
            }
        }
        return array;
    }


}
