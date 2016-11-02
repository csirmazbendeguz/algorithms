package org.bendi.algorithms.sort;

public class InsertionSort {
	/**
	 * An in-place sorting algorithm.
	 * 
	 * @param <T> Comparable object
	 * @param array the array to sort
	 */
	public static <T extends Comparable<T>> void insertionSort(T[] array) {
		for(int i = 1; i < array.length; i++) {
			int j = i;
			while( j > 0 && array[j].compareTo(array[j - 1]) < 0 ) {
				T temp = array[j - 1];
				array[j - 1] = array[j];
				array[j] = temp;
				j--;
			}
		}
	}
}
