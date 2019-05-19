package com.bcreagh.javaalgos.resizingarray;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class ResizingArrayTest {

    @Test(expected = IndexOutOfBoundsException.class)
    public void get_shouldThrowException_whenOutOfBounds() {
        ResizingArray<Object> array = new ResizingArray<>(10);
        array.get(0);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void get_shouldThrowException_whenNegative() {
        ResizingArray<Object> array = new ResizingArray<>(10);
        array.get(-3);
    }

    @Test()
    public void get_shouldReturnCorrectItem() {
        ResizingArray<Object> array = new ResizingArray<>(10);
        Object testObject = new Object();
        array.add(testObject);
        assertEquals(testObject, array.get(0));
    }


    @Test()
    public void get_shouldReturnCorrectItem_afterResizing() {
        int testNum = 182;
        ResizingArray<Integer> array = new ResizingArray<>(4);
        for (int i = 0; i < 15; i++) {
            array.add(i);
        }
        array.add(testNum);
        assertTrue(testNum == array.get(15));
    }

    @Test()
    public void add_shouldIncreaseLengthByOne() {
        ResizingArray<Object> array = new ResizingArray<>(10);
        int startingLength = array.getLength();
        array.add(new Object());
        assertEquals(startingLength + 1, array.getLength());
    }

    @Test()
    public void resizingArray_shouldResizeBeyondInitialCapacity() {
        ResizingArray<Integer> array = new ResizingArray<>(4);
        assertEquals(4, array.getCapacity());
        for (int i = 0; i < 15; i++) {
            array.add(i);
        }
        assertEquals(16, array.getCapacity());
    }

    @Test
    public void removeAt_shouldRemoveCorrectIndex() {
        int numOrginallyAtIndex2 = 2376;
        int numOrginallyAtIndex3 = 3498;
        ResizingArray<Integer> array = new ResizingArray<>(3);
        array.add(4);
        array.add(24);
        array.add(numOrginallyAtIndex2);
        array.add(numOrginallyAtIndex3);
        array.add(23);
        array.add(99);
        assertTrue(numOrginallyAtIndex2 == array.get(2));
        array.removeAt(2);
        assertTrue(numOrginallyAtIndex3 == array.get(2));
    }

    @Test
    public void removeAt_shouldResizeArray_whenLengthIsSmallEnough() {
        ResizingArray<Integer> array = new ResizingArray<>(4);
        // bring capacity to 16
        for (int i = 0; i < 9; i++) {
            array.add(i);
        }
        assertEquals(16, array.getCapacity());
        //bring length to 5
        for (int i = 8; i > 4; i--) {
            array.removeAt(i);
        }
        assertEquals(16, array.getCapacity());
        array.removeAt(4);
        assertEquals(8, array.getCapacity());
    }

}
