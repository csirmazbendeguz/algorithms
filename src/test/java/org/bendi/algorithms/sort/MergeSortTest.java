package org.bendi.algorithms.sort;

import static org.junit.Assert.assertArrayEquals;

import java.util.Arrays;

import org.bendi.algorithms.sort.InsertionSort;
import org.bendi.algorithms.sort.MergeSort;
import org.junit.Test;

public class MergeSortTest {
	private static void test(Integer[] array, Integer[] sorted) {
		//InsertionSort.insertionSort(array);
		//assertArrayEquals(sorted, array);
	}
	
	@Test
    public void simpleSort() {
		//test(new Integer[] {11, 6, -5, 1, 12, 3}, new Integer[] {-5, 1, 3, 6, 11, 12});
		//System.out.println(Arrays.toString(MergeSort.merge(new Integer[] {4, 7, 11, 12}, new Integer[] {1, 3, 5, 12, 15})));
		Integer[] arr = new Integer[] {-1, 0, 1, 2, 3, 4, 5};
    }
}
