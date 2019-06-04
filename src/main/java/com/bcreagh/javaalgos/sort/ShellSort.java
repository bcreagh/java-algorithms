package com.bcreagh.javaalgos.sort;

public class ShellSort<T extends Comparable<T>> implements Sorter<T> {
    @Override
    public T[] sort(T[] input) {
        int h = getFirstH(input.length);

        while (h >= 1) {
            for (int i = h; i < input.length; i++) {
                int itemIndex = i;
                int prevItemIndex = itemIndex - h;
                while (prevItemIndex >= 0 && input[itemIndex].compareTo(input[prevItemIndex]) < 0) {
                    swap(itemIndex, prevItemIndex, input);
                    itemIndex = prevItemIndex;
                    prevItemIndex -= h;
                }
            }
            h = h / 3;
        }
        return input;
    }

    private int getFirstH(int length) {
        int h = 1;
        int nextH = 1;
        while (nextH < length) {
            h = nextH;
            nextH = (h  * 3) + 1;
        }
        return h;
    }

    private void swap(int index1, int index2, T[] input) {
        T temp = input[index1];
        input[index1] = input[index2];
        input[index2] = temp;
    }


}
