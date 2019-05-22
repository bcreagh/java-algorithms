package com.bcreagh.javaalgos.linkedlist;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class LinkedListTest {

    LinkedList<Integer> list;

    @Before
    public void setup() {
        this.list = new LinkedList<>();
    }

    @Test
    public void initialLength_shouldBe0() {
        assertEquals(0, list.getLength());
    }

    @Test
    public void add_shouldIncreaseLengthByOne() {
        list.add(424);
        assertEquals(1, list.getLength());
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void getAt_shouldThrowException_whenPositionIsOutOfBounds() {
        list.getAt(34);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void getAt_shouldThrowException_whenPositionIsNegative() {
        list.getAt(-1);
    }

    @Test
    public void getAt_shouldReturnCorrectItem() {
        int[] itemsToAdd = {124, 235, 3, 345};
        for (int item: itemsToAdd){
            list.add(item);
        }
        for (int i = 0; i < itemsToAdd.length; i++) {
            assertTrue(itemsToAdd[i] == list.getAt(i));
        }
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void addAt_shouldThrowExceptionWhen_positionIsOutOfBounds() {
        int num = 32;
        list.addAt(1, num);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void addAt_shouldThrowExceptionWhen_positionIsNegative() {
        int num = 32;
        list.addAt(-5, num);
    }

    @Test
    public void addAt_shouldAddAnItem_whenPositionIs0AndListIsEmpty() {
        int num = 32;
        list.addAt(0, num);
        assertEquals(num, (int) list.getAt(0));
    }

    @Test
    public void addAt_shouldCauseLastToPointToNewItem_whenPositionIs0AndListIsEmpty() {
        int num = 32;
        list.addAt(0, num);
        assertEquals(num, (int) list.getLast());
    }

    @Test
    public void addAt_shouldLeaveItemsInListWithCorrectPositions() {
        int originalFirst = 124;
        int originalSecond = 2354;
        int originalThird = 322;
        int originalFourth = 888;
        int newItem = 44;
        int[] itemsToAdd = {originalFirst, originalSecond, originalThird, originalFourth};
        for (int item: itemsToAdd){
            list.add(item);
        }
        list.addAt(1, newItem);
        assertEquals(originalFirst, (int) list.getAt(0));
        assertEquals(newItem, (int) list.getAt(1));
        assertEquals(originalSecond, (int) list.getAt(2));
        assertEquals(originalThird, (int) list.getAt(3));
        assertEquals(originalFourth, (int) list.getAt(4));
    }

    @Test
    public void addAt_shouldCauseLengthToIncreaseByOne() {
        int originalLen = list.getLength();
        list.addAt(0, 32);
        assertEquals(originalLen + 1, list.getLength());
    }

    @Test
    public void getLast_shouldReturnCorrectItem_afterAddAtIsPerformed() {
        int lastItem = 243;
        list.add(lastItem);
        list.addAt(0, 32);
        list.addAt(1, 989);
        assertEquals(lastItem, (int) list.getLast());
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void removeAt_shouldThrowException_whenPositionIsOutOfBounds() {
        list.add(1);
        list.add(2);
        list.add(3);
        list.removeAt(10);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void removeAt_shouldThrowException_whenPositionIsNegative() {
        list.add(1);
        list.add(2);
        list.add(3);
        list.removeAt(-1);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void removeAt_shouldThrowException_whenPosition0AndListIsEmpty() {
        list.removeAt(0);
    }

    @Test
    public void removeAt_shouldReturnCorrectItem() {
        int itemAtPosition2 = 9236;
        list.add(12);
        list.add(8732);
        list.add(itemAtPosition2);
        list.add(5453);
        assertEquals(itemAtPosition2, (int) list.removeAt(2));
    }

    @Test
    public void removeAt_shouldDecreaseLengthByOne() {
        list.add(12);
        list.add(8732);
        list.add(5453);
        assertEquals(3, list.getLength());
        list.removeAt(2);
        assertEquals(2, list.getLength());
    }

    @Test
    public void removeAt_shouldSetNextNodeToSpecifiedPosition() {
        int itemAtOriginallyPosition2 = 23876;
        int itemAtOriginallyPosition3 = 3223;
        int[] items = {32, 1, itemAtOriginallyPosition2, itemAtOriginallyPosition3, 65};
        for (int item: items) {
            list.add(item);
        }
        assertEquals(itemAtOriginallyPosition2, (int) list.getAt(2));
        list.removeAt(2);
        assertEquals(itemAtOriginallyPosition3, (int) list.getAt(2));
    }

    @Test
    public void removeAt_shouldCorrectlySetFirst_whenPositionIs0() {
        int itemOriginallyAtPosition1 = 123;
        int[] items = {32, itemOriginallyAtPosition1, 234, 33, 65};
        for (int item: items) {
            list.add(item);
        }
        list.removeAt(0);
        assertEquals(itemOriginallyAtPosition1, (int) list.getAt(0));
    }

    @Test
    public void removeAt_shouldCorrectlySetLast_whenRemovingLastItem() {
        int originalSecondLastItem = 123;
        int[] items = {32, 23, 234, originalSecondLastItem, 65};
        for (int item: items) {
            list.add(item);
        }
        list.removeAt(list.getLength() - 1);
        assertEquals(originalSecondLastItem, (int) list.getLast());
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void removeAt_shouldCauseSubsequentGetLastToThrowException_whenPositionIs0AndLengthIs1() {
        System.out.println("Len: " + list.getLength());
        list.add(123);
        System.out.println("Len: " + list.getLength());
        list.removeAt(0);
        System.out.println("Len: " + list.getLength());
        assertNull(list.getLast());
    }

    @Test
    public void linkedList_shouldBeIterable() {
        ArrayList<Integer> arrayList = new ArrayList<>();
        int[] items = {32, 23, 234, 3242, 65};
        for (int item: items) {
            list.add(item);
        }
        for(int item: list) {
            arrayList.add(item);
        }
        assertEquals(items.length, list.getLength());
        for (int i = 0; i < arrayList.size(); i++) {
            assertEquals(items[i], (int) arrayList.get(i));
        }
    }

}
