package org.bendi.algorithms.search;

public class PeakFind {
	/**
	 * Returns the index of a peak in a specified array.
	 * 
	 * @param <T> Comparable object
	 * @param array array to search
	 * @param first first index
	 * @param last last index
	 * @return index of a peak in {@code array}; {@code -1} if the array is empty
	 */
	public static <T extends Comparable<T>> int peakFind1D(T[] array, int first, int last) {
		int middle = (first + last) / 2;
		
		// these nested ifs are a bit ugly.. but necessary
		if(array.length > 0) {
			if(middle == 0) {
				// check right element
				if(array[middle].compareTo(array[middle + 1]) >= 0) {
					return middle;
				}
			} else if(middle == array.length - 1) {
				// check left element
				if(array[middle].compareTo(array[middle - 1]) >= 0) {
					return middle;
				}
			} else {
				// check both sides
				if(array[middle].compareTo(array[middle - 1]) < 0) {
					return peakFind1D(array, first, middle - 1);
				} else if(array[middle].compareTo(array[middle + 1]) < 0) {
					return peakFind1D(array, middle + 1, last);
				} else {
					return middle;
				}
			}
		}

		return -1;
	}
}
