package com.bcreagh.javaalgos.sort;

import com.bcreagh.javaalgos.random.Shuffle;

public class QuickSort<T extends Comparable<T>> implements Sorter<T> {

    @Override
    public T[] sort(T[] input) {
        Shuffle.shuffle(input);
        sort(input, 0, input.length - 1);
        return input;
    }

    private void sort(T[] input, int lo, int hi) {
        if (lo >= hi) {
            return;
        }

        int lt = lo;
        int gt = hi;
        int i = lo + 1;

        while (i <= gt) {
            int compare = input[i].compareTo(input[lt]);
            if (compare < 0) {
                swap(input, i, lt);
                i++;
                lt++;
            }
            else if (compare > 0) {
                swap(input, i, gt);
                gt--;
            } else {
                i++;
            }
        }
        sort(input, lo, lt - 1);
        sort(input, gt + 1, hi);
    }

    private void swap(T[] input, int i, int j) {
        T temp = input[i];
        input[i] = input[j];
        input[j] = temp;
    }

}
