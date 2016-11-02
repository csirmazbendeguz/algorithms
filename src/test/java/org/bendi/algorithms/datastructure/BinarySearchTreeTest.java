package org.bendi.algorithms.datastructure;

import static org.junit.Assert.*;

import java.util.Arrays;

import org.bendi.algorithms.datastructure.BinarySearchTree;
import org.bendi.algorithms.datastructure.BinarySearchTree.Node;
import org.junit.After;
import org.junit.Test;

public class BinarySearchTreeTest {
	private static final int[] ARRAY = {5, 3, 2, 7, 4, 6};
	private BinarySearchTree bst;
	
	public BinarySearchTreeTest() {
		bst = new BinarySearchTree(ARRAY);
	}
	
	@Test
    public void isTree() {
		assertTrue(bst.isBST());
	}
	
	@Test
    public void size() {
		assertEquals(ARRAY.length, bst.size());
		assertEquals(2, bst.height());
	}
	
	@Test
    public void deleteTest() {
	}
	
	@Test
	public void successorTest() {
		assertEquals(null, bst.getSuccessor(bst.search(7)));
		assertEquals(7, (int) bst.getSuccessor(bst.search(6)).value);
		assertEquals(6, (int) bst.getSuccessor(bst.search(5)).value);
		assertEquals(5, (int) bst.getSuccessor(bst.search(4)).value);
		assertEquals(4, (int) bst.getSuccessor(bst.search(3)).value);
		assertEquals(3, (int) bst.getSuccessor(bst.search(2)).value);
	}
}
