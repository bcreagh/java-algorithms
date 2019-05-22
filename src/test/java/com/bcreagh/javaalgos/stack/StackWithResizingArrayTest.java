package com.bcreagh.javaalgos.stack;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class StackWithResizingArrayTest {

    private StackWithResizingArray<Integer> stack;

    @Before
    public void setup() {
        stack = new StackWithResizingArray<>();
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void pop_shouldThrowException_whenStackIsEmpty() {
        stack.pop();
    }

    @Test
    public void stack_testWithSampleValues() {
        int first = 231;
        int second = 12235;
        int third = 22;
        int fourth = 2;
        int fifth = 3499;

        stack.push(first);
        assertEquals(first, (int) stack.pop());
        stack.push(second);
        stack.push(third);
        assertEquals(third, (int) stack.pop());
        stack.push(fourth);
        stack.push(fifth);
        assertEquals(fifth, (int) stack.pop());
        assertEquals(fourth, (int) stack.pop());
        assertEquals(second, (int) stack.pop());
    }

    @Test
    public void stack_shouldBeIterable() {
        int[] items = {234, 898, 9237};
        for (int i = 0; i < items.length; i++) {
            stack.push(items[i]);
        }
        int i = items.length - 1;
        for (int item: stack) {
            assertEquals(items[i], (int) stack.pop());
            i--;
        }
    }
}
