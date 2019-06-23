package com.bcreagh.javaalgos.select;

import com.bcreagh.javaalgos.util.InputUtil;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.Random;

import static org.junit.Assert.assertEquals;

public class QuickSelectTest {

    QuickSelect<Integer> quickSelect;
    Random random;

    @Before
    public void setup() {
        quickSelect = new QuickSelect<>();
        random = new Random(System.currentTimeMillis());
    }

    @Test
    public void select_withRandomInput() {
        for (int i = 0; i < 100; i++) {
            Integer[] input = InputUtil.generatedRandomArray(200, 50000);
            int k = random.nextInt(input.length);
            Integer result = quickSelect.select(input, k);
            verifyResult(input, k, result);
        }
    }

    @Test(expected = IllegalArgumentException.class)
    public void select_withNegativeK() {
        Integer[] input = InputUtil.generatedRandomArrayWithFixedLength(50);
        quickSelect.select(input, -4);
    }

    @Test(expected = IllegalArgumentException.class)
    public void select_withKTooLarge() {
        Integer[] input = InputUtil.generatedRandomArrayWithFixedLength(50);
        quickSelect.select(input, 51);
    }

    @Test
    public void select_withAllEqualItems() {
        Integer[] input = InputUtil.getArrayOfEqualValues(40);
        Integer result = quickSelect.select(input, 10);
        assertEquals(40, (int) result);
    }

    @Test
    public void select_withKEquals0() {
        Integer[] input = InputUtil.generatedRandomArrayWithFixedLength(40);
        Integer result = quickSelect.select(input, 0);
        verifyResult(input, 0, result);
    }

    @Test
    public void select_withKEqualsLastElement() {
        Integer[] input = InputUtil.generatedRandomArrayWithFixedLength(40);
        int k = input.length - 1;
        Integer result = quickSelect.select(input, k);
        verifyResult(input, k, result);
    }

    @Test(expected = IllegalArgumentException.class)
    public void select_withEmptyArray() {
        Integer[] input = InputUtil.getEmptyArray();
        int k = 0;
        quickSelect.select(input, k);
    }

    private void verifyResult(Integer[] input, int k, Integer result) {
        Arrays.sort(input);
        assertEquals(input[k], result);
    }

}
