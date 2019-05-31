package com.bcreagh.javaalgos.sort;

public interface Sorter {
    <T extends Comparable<T>> T[] sort(T[] input);
}
