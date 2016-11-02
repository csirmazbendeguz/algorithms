package org.bendi.algorithms.datastructure;

public class Heap {
	private int[] array;
	private int size;
	
	public Heap(int[] array) {
		this.array = array;
		init();
	}
	
	public void init() {
		this.size = array.length;
		for(int i = size / 2 - 1; i >= 0; i--) {
			heapify(i);
		}
	}
	
	private void heapify(int root) {
		int left = getLeftChild(root);
		int right = getRightChild(root);
		boolean leftExists = exists(left);
		boolean rightExists = exists(right);
		
		if(leftExists && rightExists) {
			if(array[left] >= array[right] && array[root] < array[left]) {
				exchange(root, left);
				heapify(left);
			} else if(array[root] < array[right]) {
				exchange(root, right);
				heapify(right);
			}
		} else if(leftExists && !rightExists && array[left] > array[root]) {
			exchange(root, left);
			heapify(left);
		} else if(!leftExists && rightExists && array[right] > array[root]) {
			exchange(root, right);
			heapify(right);
		} else {
			// leafs & sorted heaps
		}
	}
	
	private void exchange(int i1, int i2) {
		int temp = array[i1];
		array[i1] = array[i2];
		array[i2] = temp;
	}
	
	private boolean exists(int index) {
		return (index >= 0 && index < size);
	}
	
	/*
	private int getParent(int index) {
		return (index + 1) / 2 - 1;
	}
	*/
	
	private int getLeftChild(int index) {
		return (index + 1) * 2 - 1;
	}
	
	private int getRightChild(int index) {
		return getLeftChild(index) + 1;
	}
	
	public int[] sort() {
		while( size > 1 ) {
			exchange(0, --size);
			heapify(0);
		}
		
		return array;
	}
}
