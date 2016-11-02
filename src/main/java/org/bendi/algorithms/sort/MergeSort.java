package org.bendi.algorithms.sort;

public class MergeSort {
	/**
	 * @param array 	array to sort
	 * @return 			a new sorted array
	 */
	public static Integer[] sort(Integer[] array) {
		return mergeSort(array, 0, array.length / 2, array.length - 1);
	}

	/**
	 * Recursive sorting algorithms.
	 * 
	 * @param array 	input array
	 * @param first 	current partition's 1st index
	 * @param middle 	next seperator's index
	 * @param last 		current partition's last index
	 * @return 			a new sorted array;
	 */
	private static Integer[] mergeSort(Integer[] array, int first, int middle, int last) {
		// > 1 element array
		if(first < last) {
			Integer[] left = mergeSort(array, first, (first + middle) / 2, middle);
			Integer[] right = mergeSort(array, middle + 1, (middle + 1 + last) / 2, last);
			return merge(left, right);
		}
		
		// empty / 1 element array
		return (array.length > 0) ? new Integer[] {array[first]} : new Integer[] {};
	}
	
	/**
	 * Merges 2 sorted array into one.
	 * 
	 * @param array1 	first sorted array
	 * @param array2 	second sorted array
	 * @return 			merged array
	 */
	private static Integer[] merge(Integer[] array1, Integer[] array2) {
		int a1 = 0;
		int a2 = 0;
		
		Integer[] merged = new Integer[array1.length + array2.length];
		
		while( a1 < array1.length || a2 < array2.length ) {
			int index = a1 + a2;
			
			if(a1 >= array1.length) {
				merged[index] = array2[a2++];
			} else if(a2 >= array2.length) {
				merged[index] = array1[a1++];
			} else if(array1[a1].compareTo(array2[a2]) < 0) {
				merged[index] = array1[a1++];
			} else {
				merged[index] = array2[a2++];
			}
		}

		return merged;
	}
}
