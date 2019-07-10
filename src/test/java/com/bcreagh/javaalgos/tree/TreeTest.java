package com.bcreagh.javaalgos.tree;

import com.bcreagh.javaalgos.exceptions.DuplicateKeyException;
import com.bcreagh.javaalgos.exceptions.TestSetupException;
import com.bcreagh.javaalgos.util.InputUtil;
import org.junit.Before;
import org.junit.Test;

import java.util.*;

import static org.junit.Assert.*;

public abstract class TreeTest {

    protected Tree<Integer, Integer> tree;
    protected List<KeyValuePair> samplePairs;
    protected final KeyValuePair PAIR_WITH_MIN_KEY = new KeyValuePair(7, 77);
    protected final KeyValuePair PAIR_WITH_MAX_KEY = new KeyValuePair(120, 995);
    protected final KeyValuePair PAIR_WITH_THIRD_BIGGEST_KEY = new KeyValuePair(30, 298);
    protected final KeyValuePair PAIR_WITH_FOURTH_BIGGEST_KEY = new KeyValuePair(40, 983);

    @Before
    public void baseSetup() {
        try {
            samplePairs = getSamplePairs();
            tree = getEmptyTestTree();
            populateTree(tree);
        } catch (Exception e) {
            throw new TestSetupException(e);
        }
    }

    @Test(expected = IllegalArgumentException.class)
    public void get_throwsException_keyIsNull() {
        tree.get(null);
    }

    @Test
    public void get_returnsNull_keyNotFound() {
        assertNull(tree.get(getNonExistentKey()));
    }

    @Test
    public void get_returnsValue_keyExistsInTree() {
        KeyValuePair existingPair = samplePairs.get(2);
        Integer valueFromTree = tree.get(existingPair.key);
        assertEquals(existingPair.value, valueFromTree);
    }

    @Test(expected = IllegalArgumentException.class)
    public void insert_throwsException_keyIsNull() {
        tree.insert(null, 3);
    }

    @Test(expected = IllegalArgumentException.class)
    public void insert_throwsException_valueIsNull() {
        tree.insert(4, null);
    }

    @Test(expected = DuplicateKeyException.class)
    public void insert_throwsException_keyAlreadyExists() {
        Integer existingKey = getExistingKey();
        tree.insert(existingKey, 3768);
    }

    @Test
    public void insert_increasesSizeByOne() {
        int initialSize = tree.size();
        tree.insert(98, 2324);
        assertEquals(initialSize + 1, tree.size());
    }

    @Test
    public void update_doesNotChangeSize() {
        int initialSize = tree.size();
        tree.update(getExistingKey(), 98236);
        assertEquals(initialSize, tree.size());
    }

    @Test(expected =  IllegalArgumentException.class)
    public void update_throwsException_keyIsNull() {
        tree.update(null, 23);
    }

    @Test(expected = NoSuchElementException.class)
    public void update_throwsException_keyNotFound() {
        tree.update(getNonExistentKey(), 9639);
    }

    @Test
    public void floor_returnsNull_keyIsLowerThanMin() {
        KeyValuePair minPair = PAIR_WITH_MIN_KEY;
        assertNull(tree.floor(minPair.key - 1));
    }

    @Test
    public void floor_returnsExactMatch_matchIsFound() {
        KeyValuePair existingPair = getExistingPair();
        Integer result = tree.floor(existingPair.key);
        assertEquals(existingPair.value, result);
    }

    @Test
    public void floor_getsFloorValue() {
        KeyValuePair thirdBiggest = PAIR_WITH_THIRD_BIGGEST_KEY;
        KeyValuePair fourthBiggest = PAIR_WITH_FOURTH_BIGGEST_KEY;
        Integer floor = tree.floor(fourthBiggest.key - 1);
        assertEquals(thirdBiggest.value, floor);
    }

    @Test
    public void ceiling_returnsNull_keyIsHigherThanMax() {
        KeyValuePair maxPair = PAIR_WITH_MAX_KEY;
        assertNull(tree.ceiling(maxPair.key + 1));
    }

    @Test
    public void ceiling_returnsExactMatch_matchIsFound() {
        KeyValuePair existingPair = getExistingPair();
        Integer result = tree.ceiling(existingPair.key);
        assertEquals(existingPair.value, result);
    }

    @Test
    public void ceiling_getsFloorValue() {
        KeyValuePair thirdBiggest = PAIR_WITH_THIRD_BIGGEST_KEY;
        KeyValuePair fourthBiggest = PAIR_WITH_FOURTH_BIGGEST_KEY;
        Integer ceiling = tree.ceiling(thirdBiggest.key + 1);
        assertEquals(fourthBiggest.value, ceiling);
    }

    @Test
    public void minKey_returnsNull_treeIsEmpty() {
        tree = getEmptyTestTree();
        assertEquals(0, tree.size());
        assertNull(tree.minKey());
    }

    @Test
    public void minKey_returnsMinValue() {
        KeyValuePair minPair = PAIR_WITH_MIN_KEY;
        assertEquals(minPair.value, tree.minKey());
    }

    @Test
    public void maxKey_returnsNull_treeIsEmpty() {
        tree = getEmptyTestTree();
        assertEquals(0, tree.size());
        assertNull(tree.maxKey());
    }

    @Test
    public void maxKey_returnsMaxValue() {
        KeyValuePair maxPair = PAIR_WITH_MAX_KEY;
        assertEquals(maxPair.value, tree.maxKey());
    }

    @Test
    public void getKeys_iteratesOverTreeInOrder() {
        List<KeyValuePair> pairsInOrder = getSamplePairsInOrder();
        int i = 0;
        for (Integer key: tree.getKeys()) {
            assertEquals(pairsInOrder.get(i).key, key);
            i++;
        }
    }

    @Test
    public void test_withRandomInput() {
        for (int i = 0; i < 200; i++) {
            tree = getEmptyTestTree();
            KeyValuePair[] pairs = getRandomKeyValuePairs();
            for (int j = 0; j < pairs.length; j++) {
                tree.insert(pairs[j].key, pairs[j].value);
            }
            assertEquals(pairs.length, tree.size());
            if (pairs.length > 0) {
                KeyValuePair pair = getRandomPair(pairs);
                assertEquals(pair.value, tree.get(pair.key));
                Integer newValue = 99723;
                tree.update(pair.key, newValue);
                assertEquals(newValue, tree.get(pair.key));
                pair.value = newValue;

                Arrays.sort(pairs);

                assertEquals(pairs[0].value, tree.minKey());
                assertEquals(pairs[pairs.length - 1].value, tree.maxKey());
            }
        }

    }

    protected abstract Tree<Integer, Integer> getEmptyTestTree();

    protected List<KeyValuePair> getSamplePairs() {

        List<KeyValuePair> pairs = new ArrayList<>();
        pairs.add(new KeyValuePair(50, 3243));
        pairs.add(PAIR_WITH_FOURTH_BIGGEST_KEY);
        pairs.add(PAIR_WITH_MIN_KEY);
        pairs.add(PAIR_WITH_THIRD_BIGGEST_KEY);
        pairs.add(new KeyValuePair(82, 3762));
        pairs.add(new KeyValuePair(43, 32403));
        pairs.add(PAIR_WITH_MAX_KEY);
        pairs.add(new KeyValuePair(14, 0));
        return pairs;
    }

    protected List<KeyValuePair> getSamplePairsInOrder() {
        List<KeyValuePair> pairs = getSamplePairs();
        Collections.sort(pairs);
        return pairs;
    }

    protected void populateTree(Tree<Integer, Integer> tree) {
        for (KeyValuePair pair: samplePairs) {
            tree.insert(pair.key, pair.value);
        }
    }

    protected KeyValuePair getExistingPair() {
        return samplePairs.get(2);
    }

    protected Integer getExistingKey() {
        return getExistingPair().key;
    }

    protected Integer getNonExistentKey() {
        return 78;
    }

    protected KeyValuePair[] getRandomKeyValuePairs() {
        Integer[] keys = InputUtil.generatedRandomArrayWithoutDuplicates(1000, 50000);
        Integer[] values = InputUtil.generatedRandomArrayWithFixedLength(keys.length);
        KeyValuePair[] pairs = new KeyValuePair[keys.length];
        for (int i = 0; i < keys.length; i++) {
            pairs[i] = new KeyValuePair(keys[i], values[i]);
        }
        return pairs;
    }

    protected KeyValuePair getRandomPair(KeyValuePair[] pairs) {
        Random random = new Random(System.currentTimeMillis());
        int index = random.nextInt(pairs.length);
        return pairs[index];
    }

    protected class KeyValuePair implements Comparable<KeyValuePair> {
        Integer key;
        Integer value;

        public KeyValuePair(Integer key, Integer value) {
            this.key = key;
            this.value = value;
        }

        @Override
        public int compareTo(KeyValuePair o) {
            return key.compareTo(o.key);
        }
    }

}
