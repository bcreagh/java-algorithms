package com.bcreagh.javaalgos.select;

import com.bcreagh.javaalgos.random.Shuffle;

public class QuickSelect<T extends Comparable<T>> {

    public T select(T[] input, int k) {
        validate(input, k);
        Shuffle.shuffle(input);
        return select(input, k, 0, input.length - 1);
    }

    private void validate(T[] input, int k) {
        if (k < 0) {
            throw new IllegalArgumentException("k must be greater than 0. k was: " + k);
        }
        if (input.length <= 0) {
            throw new IllegalArgumentException("Input array was empty!");
        }
        if (k >= input.length) {
            String message = String.format(
                    "k was outside the bounds of the input array. k was: %d, input length was: %d", k, input.length
            );
            throw new IllegalArgumentException(message);
        }
    }

    private T select(T[] input, int k, int lo, int hi) {
        int lt = lo;
        int gt = hi;
        int i = lo + 1;
        while (i <= gt) {
            int compare = input[i].compareTo(input[lt]);
            if (compare < 0) {
                swap(input, lt, i);
                lt++;
                i++;
            } else if (compare > 0) {
                swap(input, i, gt);
                gt--;
            } else {
                i++;
            }
        }
        if (k < lt) {
            return select(input, k, lo, lt - 1);
        } else if (k > gt) {
            return select(input, k, gt + 1, hi);
        }
        return input[k];
    }

    private void swap(T[] input, int index1, int index2) {
        T temp = input[index1];
        input[index1] = input[index2];
        input[index2] = temp;
    }

}
