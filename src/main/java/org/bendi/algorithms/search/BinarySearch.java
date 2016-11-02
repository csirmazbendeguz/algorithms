package org.bendi.algorithms.search;

public class BinarySearch {
	/**
	 * Returns the index of a specified key in the specified array.
	 * 
	 * @param <T> an object implementing the Comparable interface
	 * @param array the array of objects sorted in ascending order
	 * @param key the search key
	 * @return index of key in {@code array} if present; {@code -1} otherwise
	 */
	public static <T extends Comparable<T>> int binarySearch(T[] array, T key) {
		int first = 0;
		int last = array.length - 1;
		
		while(first <= last) {
			int middle = first + (last - first) / 2;
			int relation = key.compareTo(array[middle]);
			
			if(relation < 0) {
				last = middle - 1;
			} else if(relation > 0) {
				first = middle + 1;
			} else {
				return middle;
			}
		}

		return -1;
	}
}
