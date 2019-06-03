package com.bcreagh.javaalgos.sort;

public class MergeSort<T extends Comparable<T>> implements Sorter<T> {
    public T[] sort(T[] input) {
        @SuppressWarnings("unchecked")
        T[] aux = (T[]) new Comparable[input.length];
        int lo = 0;
        int hi = input.length - 1;
        sortSubArray(input, aux, lo, hi);
        return input;
    }

    private void sortSubArray(T[] input, T[] aux, int lo, int hi) {
        if (lo >= hi) {
            return;
        }
        int mid = getMid(lo, hi);
        sortSubArray(input, aux, lo, mid);
        sortSubArray(input, aux, mid + 1, hi);
        merge(input, aux, lo, hi, mid);
    }

    private int getMid(int lo, int hi) {
        return (lo + hi) / 2;
    }

    private void merge(T[] input, T[] aux, int lo, int hi, int mid) {
        copyArray(input, aux, lo, hi);
        int loUnmergedIndex = lo;
        int hiUnmergedIndex = mid + 1;

        for (int i = lo; i <= hi; i++) {
            if (loUnmergedIndex > mid) {
                input[i] = aux[hiUnmergedIndex];
                hiUnmergedIndex += 1;
                continue;
            }
            if (hiUnmergedIndex > hi) {
                input[i] = aux[loUnmergedIndex];
                loUnmergedIndex += 1;
                continue;
            }
            if (aux[loUnmergedIndex].compareTo(aux[hiUnmergedIndex]) <= 0) {
                input[i] = aux[loUnmergedIndex];
                loUnmergedIndex += 1;
            } else {
                input[i] = aux[hiUnmergedIndex];
                hiUnmergedIndex += 1;
            }
        }
    }

    private void copyArray(T[] source, T[] dest, int fromIndex, int toIndex) {
        for (int i = fromIndex; i <= toIndex; i++) {
            dest[i] = source[i];
        }
    }
}