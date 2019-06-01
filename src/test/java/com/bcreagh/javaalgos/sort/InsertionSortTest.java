package com.bcreagh.javaalgos.sort;

import org.junit.Test;

public class InsertionSortTest {

    private static Sorter<Integer> sorter = new InsertionSort<>();

    @Test
    public void sort_shouldSortRandomlyGeneratedArrays() {
        SortHelper.sort_shouldSortRandomlyGeneratedArrays(sorter);
    }

    @Test
    public void sort_shouldSortRandomlyGeneratedArraysWithNegatives() {
        SortHelper.sort_shouldSortRandomlyGeneratedArraysWithNegatives(sorter);
    }

    @Test
    public void sort_withEmptyArray() {
        SortHelper.sort_withEmptyArray(sorter);
    }

    @Test
    public void sort_withAllEqualValues() {
        SortHelper.sort_withAllEqualValues(sorter);
    }

    @Test
    public void sort_withAlreadySortedArray() {
        SortHelper.sort_withAlreadySortedArray(sorter);
    }
}
