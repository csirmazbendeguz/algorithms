package org.bendi.algorithms.search;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

import org.bendi.algorithms.search.BinarySearch;

public class BinarySearchTest {
	private static final Integer[] TEST_ARRAY = {1, 3, 4, 6, 10, 12, 15, 18, 20};
	
	/**
	 * @param key object to search for
	 * @param index expected index of the key
	 */
	private static void test(int key, int index) {
		int result = BinarySearch.binarySearch(TEST_ARRAY, key);
    	assertEquals(result, index);
	}
	
	@Test
    public void keysFound() {
    	test(1, 0);
    	test(3, 1);
    	test(4, 2);
    	test(6, 3);
    	test(10, 4);
    	test(12, 5);
    	test(15, 6);
    	test(18, 7);
    	test(20, 8);
    }
	
    @Test
    public void keysNotFoundInside() {
    	test(2, -1);
    	test(5, -1);
    	test(7, -1);
    	test(8, -1);
    	test(9, -1);
    	test(11, -1);
    	test(13, -1);
    	test(14, -1);
    	test(16, -1);
    	test(17, -1);
    	test(19, -1);
    }
    
    @Test
    public void keysNotFoundOutside() {
    	test(0, -1);
    	test(-1, -1);
    	test(-2, -1);
    	test(-3, -1);
    	test(21, -1);
    	test(22, -1);
    	test(23, -1);
    	test(24, -1);
    }
}