package com.bcreagh.javaalgos.sort;

import com.bcreagh.javaalgos.util.InputUtil;

import java.util.Random;
import static org.junit.Assert.*;

public class SortHelper {

    private static Random random = new Random(System.currentTimeMillis());

    public static void sort_shouldSortRandomlyGeneratedArrays(Sorter<Integer> sorter) {
        for (int i = 0; i < 100; i++) {
            Integer[] input = InputUtil.generatedRandomArray(200, 50000);
            input = sorter.sort(input);
            assertTrue(SortHelper.isSorted(input));
        }
    }

    public static void sort_shouldSortRandomlyGeneratedArraysWithNegatives(Sorter<Integer> sorter) {
        for (int i = 0; i < 20; i++) {
            Integer[] input = InputUtil.getRandomArrayWithNegatives(200, 50000);
            input = sorter.sort(input);
            assertTrue(SortHelper.isSorted(input));
        }
    }

    public static void sort_withEmptyArray(Sorter<Integer> sorter) {
        Integer[] input = InputUtil.getEmptyArray();
        input = sorter.sort(input);
        assertTrue(input.length == 0);
    }

    public static void sort_withAllEqualValues(Sorter<Integer> sorter) {
        int value = 234;
        Integer[] input = InputUtil.getArrayOfEqualValues(value);
        sorter.sort(input);
    }

    public static void sort_withAlreadySortedArray(Sorter<Integer> sorter) {
        Integer[] input = InputUtil.getSortedArray();
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

}
