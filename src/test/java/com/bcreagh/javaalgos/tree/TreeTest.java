package com.bcreagh.javaalgos.tree;

import com.bcreagh.javaalgos.exceptions.DuplicateKeyException;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public abstract class TreeTest {

    private Tree<Integer, Integer> tree;
    List<KeyValuePair> samplePairs;

    @Before
    public void baseSetup() {
        samplePairs = getSamplePairs();
        tree = getTestTree();
        populateTree(tree);
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

    protected abstract Tree<Integer, Integer> getTestTree();

    protected List<KeyValuePair> getSamplePairs() {

        List<KeyValuePair> pairs = new ArrayList<>();
        pairs.add(new KeyValuePair(50, 3243));
        pairs.add(new KeyValuePair(40, 983));
        pairs.add(new KeyValuePair(7, 77));
        pairs.add(new KeyValuePair(30, 298));
        pairs.add(new KeyValuePair(82, 3762));
        pairs.add(new KeyValuePair(43, 32403));
        pairs.add(new KeyValuePair(120, 995));
        pairs.add(new KeyValuePair(14, 0));
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

    protected class KeyValuePair {
        Integer key;
        Integer value;

        public KeyValuePair(Integer key, Integer value) {
            this.key = key;
            this.value = value;
        }
    }

}
