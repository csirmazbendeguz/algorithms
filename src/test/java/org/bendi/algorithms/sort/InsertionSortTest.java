package org.bendi.algorithms.sort;

import static org.junit.Assert.*;

import org.bendi.algorithms.sort.InsertionSort;
import org.junit.Test;

public class InsertionSortTest {
	private static void test(Integer[] array, Integer[] sorted) {
		InsertionSort.insertionSort(array);
		assertArrayEquals(sorted, array);
	}
	
	@Test
    public void simpleSort() {
		test(new Integer[] {11, 6, -5, 1, 12, 3}, new Integer[] {-5, 1, 3, 6, 11, 12});
    }
	
	@Test
    public void emptyArray() {
		test(new Integer[] {}, new Integer[] {});
    }
}
