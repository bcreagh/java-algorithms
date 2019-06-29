package com.bcreagh.javaalgos.queue;

import static org.junit.Assert.*;

import com.bcreagh.javaalgos.util.InputUtil;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.Comparator;
import java.util.NoSuchElementException;

public class PriorityQueueTest {

    private final Integer MAX = 98789;

    PriorityQueue<Integer> queue;

    @Before
    public void setup() {
        queue = getQueue();
    }

    @Test(expected = NoSuchElementException.class)
    public void peek_shouldThrowException_whenEmpty() {
        queue = new PriorityQueue<>(new Integer[0], getComparator());
        queue.peek();
    }

    @Test
    public void peek_shouldReturnLargest() {
        assertEquals(MAX, queue.peek());
    }

    @Test
    public void peek_shouldNotDecreaseSize() {
        int initialSize = queue.size();
        queue.peek();
        assertEquals(initialSize, queue.size());
    }

    @Test(expected = IllegalArgumentException.class)
    public void enqueue_shouldThrowException_whenArgIsNull() {
        queue.enqueue(null);
    }

    @Test(expected = NoSuchElementException.class)
    public void dequeue_shouldThrowException_whenEmpty() {
        queue = new PriorityQueue<>(new Integer[0], getComparator());
        queue.dequeue();
    }

    @Test
    public void dequeue_shouldReturnLargest() {
        assertEquals(MAX, queue.dequeue());
    }

    @Test
    public void dequeue_shouldDecreaseSizeByOne() {
        int initialSize = queue.size();
        queue.dequeue();
        assertEquals(initialSize - 1, queue.size());
    }

    @Test
    public void randomizedTest() {
        for (int i = 0; i < 200; i++) {
            Integer[] input = InputUtil.generatedRandomArray(200, 50000);
            queue = new PriorityQueue<>(input, getComparator());
            Arrays.sort(input);
            for (int j = input.length - 1; j >= 0; j--) {
                assertEquals(input[j], queue.dequeue());
            }
            assertEquals(0, queue.size());
        }
    }

    private PriorityQueue<Integer> getQueue() {
        return new PriorityQueue<>(getInputArray(), getComparator());
    }

    private Integer[] getInputArray() {
        return new Integer[] {44,34, 11, 888, 903, 10003, 5547, MAX, 3, 89};
    }

    private Comparator<Integer> getComparator() {
        return Comparator.naturalOrder();
    }

}
