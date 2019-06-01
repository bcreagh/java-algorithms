package com.bcreagh.javaalgos.sort;

public class InsertionSort<T extends Comparable<T>> implements Sorter<T> {

    public T[] sort(T[] input) {
      for (int i = 0; i < input.length; i++) {
          sortItem(i, input);
      }
      return input;
    }

    private void sortItem(int index, T[] input) {
        for (int i = index; i > 0; i --) {
            if (input[i].compareTo(input[i - 1]) < 0) {
                swap(i, i - 1, input);
            } else {
                break;
            }
        }
    }

    private void swap(int index1, int index2, T[] array) {
        T temp = array[index1];
        array[index1] = array[index2];
        array[index2] = temp;
    }
}
