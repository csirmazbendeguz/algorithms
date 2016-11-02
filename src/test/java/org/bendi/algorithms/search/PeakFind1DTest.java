package org.bendi.algorithms.search;

import static org.junit.Assert.*;

import java.util.Arrays;

import org.bendi.algorithms.search.PeakFind;
import org.junit.Test;

public class PeakFind1DTest {
	private static void test(Integer[] array, Integer[] peakIndices) {
		assertTrue(
			Arrays.asList(peakIndices).contains(
				PeakFind.peakFind1D(array, 0, array.length - 1)
			)
		);
	}
	
	@Test
    public void sortedArrays() {
		test(new Integer[] {-5, 3, 0, 2, 7, 10, 11}, new Integer[] {6});
		test(new Integer[] {5, 6, 100, 101, 202, 3030}, new Integer[] {5});
    }
	
	@Test
    public void randomArrays() {
		test(new Integer[] {6, 0, -32, 4, 11, 22, 77, 32, -25, 70}, new Integer[] {0, 6, 9});
    }
	
	@Test
    public void emptyArray() {
		test(new Integer[] {}, new Integer[] {-1});
    }
}
