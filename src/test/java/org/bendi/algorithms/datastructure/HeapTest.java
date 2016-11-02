package org.bendi.algorithms.datastructure;

import java.util.Arrays;

import org.bendi.algorithms.datastructure.Heap;
import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;

public class HeapTest {
	public static void test(int[] array, int[] expected) {
		Heap heap = new Heap(array);
		heap.sort();
		assertArrayEquals(expected, array);
	}
	
	@Test
    public void simpleSort() {
		int[] array1 = new int[] {1, 16, 5, 30, 27, 17, 20, 2, 57, 3, 90};
		int[] expected1 = new int[] {1, 2, 3, 5, 16, 17, 20, 27, 30, 57, 90};
		test(array1, expected1);
		
		int[] array2 = new int[] {-3, -8, -3, 5, 0, -1};
		int[] expected2 = new int[] {-8, -3, -3, -1, 0, 5};
		test(array2, expected2);
	}
	
	@Test
    public void emptyArray() {
		assertArrayEquals(new int[] {}, new int[] {});
	}
}
