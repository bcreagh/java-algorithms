package com.bcreagh.javaalgos.queue;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class QueueWithLinkedListTest {

    QueueWithLinkedList<Integer> queue;

    @Before
    public void setup() {
        queue = new QueueWithLinkedList<>();
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void dequeue_shouldThrowException_whenQueueIsEmpty() {
        queue.dequeue();
    }


    @Test
    public void queue_testWithSampleValues() {
        int first = 231;
        int second = 12235;
        int third = 22;
        int fourth = 2;
        int fifth = 3499;
        queue.enqueue(first);
        assertEquals(first, (int) queue.dequeue());
        queue.enqueue(second);
        queue.enqueue(third);
        queue.enqueue(fourth);
        assertEquals(second, (int) queue.dequeue());
        queue.enqueue(fifth);
        assertEquals(third, (int) queue.dequeue());
        assertEquals(fourth, (int) queue.dequeue());
        assertEquals(fifth, (int) queue.dequeue());
    }

    @Test
    public void queue_shouldBeIterable() {
        int[] items = {234, 166, 74, 3, 2398};
        for (int i = 0; i < items.length; i++) {
            queue.enqueue(items[i]);
        }
        int i = 0;
        for (int item: queue) {
            assertEquals(items[i], item);
            i++;
        }
    }
}
