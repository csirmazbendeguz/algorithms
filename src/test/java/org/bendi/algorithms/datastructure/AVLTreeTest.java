package org.bendi.algorithms.datastructure;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.Arrays;

import org.bendi.algorithms.datastructure.AVLTree.Node;
import org.junit.After;
import org.junit.Test;

public class AVLTreeTest {
	private static final int[] ARRAY = {1, 2, 3, 4, 5, 6, 7};
	private AVLTree avl;
	
	public AVLTreeTest() {
		avl = new AVLTree(ARRAY);
	}
	
	@Test
    public void heightTest() {
		System.out.println(avl.root.right.right);
		assertEquals(3, avl.height());
	}
}