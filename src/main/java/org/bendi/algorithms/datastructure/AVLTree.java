package org.bendi.algorithms.datastructure;

import java.util.ArrayList;
import java.util.List;

import org.bendi.algorithms.datastructure.BinarySearchTree.Node;

public class AVLTree {
	public static class Node {
		public Integer value;
		public int height = -1;
		public Node left;
		public Node right;
		public Node parent;
		
		@Override
		public String toString() {
			return "Node [value=" + ((value == null) ? "null" : value) + 
					", left=" + ((left == null) ? "null" : left.value) + 
					", right=" + ((right == null) ? "null" : right.value) + 
					", parent=" + ((parent == null) ? "null" : parent.value) + 
					", height=" + height + 
					"]";
		}
	}
	
	// null node
	protected static final Node NULL;
	static { 
		NULL = new Node();
		NULL.value = null;
		NULL.height = -1;
		NULL.left = null;
		NULL.right = null;
		NULL.parent = null;
	}
	
	public Node root;
	
	public AVLTree() {
		root = null;
	}
	
	public AVLTree(int[] array) {
		root = null;
		for(int i : array) { insert(i); }
	}
	
	public Node search(int value) {
		Node current = root;
		
		while( current != NULL ) {
			if(value == current.value) {
				return current;
			} else if(value < current.value) {
				current = current.left;
			} else {
				current = current.right;
			}
		}
		
		return NULL;
	}
	
	public int size() {
		return size(root);
	}
	
	protected int size(Node node) {
		if(node == NULL) return 0;
		
		int left = size(node.left);
		int right = size(node.right);
		
		return left + right + 1;
	}
	
	public int height() {
		return root.height;
	}
	
	public void leftRotate(Node node) {
		Node root = node;
		Node pivot = root.right;
		Node b = root.right.left;
		
		pivot.parent = root.parent;
		if(pivot.parent == null)
			this.root = pivot;
		else if(pivot.parent.left == root)
			pivot.parent.left = pivot;
		else
			pivot.parent.right = pivot;
		
		root.right = b;
		b.parent = root;
		
		pivot.left = root;
		root.parent = pivot;
	}
	
	public void rightRotate(Node node) {
		Node root = node;
		Node pivot = root.left;
		Node b = root.left.right;
		
		pivot.parent = root.parent;
		if(pivot.parent == null)
			this.root = pivot;
		else if(pivot.parent.left == root)
			pivot.parent.left = pivot;
		else
			pivot.parent.right = pivot;
		
		root.left = b;
		b.parent = root;
		
		pivot.right = root;
		root.parent = pivot;
	}
	
	public boolean isBST() {
		return isBST(root, Integer.MIN_VALUE, Integer.MAX_VALUE);
	}
	
	protected boolean isBST(Node node, int min, int max) {
		// leafs are bsts
		if(node == NULL) return true;
		
		if(node.value < min || node.value > max)
			return false;
		
		// allowed values
		// left: -infinity - parent value
		// right: parent value - infinity
		return isBST(node.left, min, node.value) && isBST(node.right, node.value, max);
	}
	
	protected int getBalanceFactor(Node node) {
		return node.right.height - node.left.height;
	}
	
	protected void rebalance() {
		Node violation = getViolation();
		while(violation != null) {
			int balanceFactor = getBalanceFactor(violation);
			
			if(balanceFactor > 1) { // 2 or more right heavy
				if(getBalanceFactor(violation.right) < 0) { // if right subtree is left heavy
					rightRotate(violation.right);
					reCalcHeight(violation.right);
				}
				
				leftRotate(violation);
				reCalcHeight(violation);
			}
			else if(balanceFactor < -1) { // 2 or more left heavy
				if(getBalanceFactor(violation.left) > 0) { // if left subtree is right heavy
					leftRotate(violation.left);
					reCalcHeight(violation.left);
				}
				
				rightRotate(violation);
				reCalcHeight(violation);
			}
			
			violation = getViolation();
		}
	}
	
	public Node getViolation() {
		return getViolation(root);
	}
	
	protected Node getViolation(Node node) {
		if(node == NULL)
			return null;
		else if(Math.abs(node.left.height - node.right.height) > 1)
			return node;
		
		Node left = getViolation(node.left);
		Node right = getViolation(node.right);
		Node violation = null;
		
		if(left != null)
			violation = left;
		else if(right != null)
			violation = right;
		
		return violation;
	}
	
	public List<Integer> sort(boolean ascending) {
		List<Integer> sorted = new ArrayList<>();
		
		if(ascending) 
			inOrder(sorted, root);
		else 
			descOrder(sorted, root);
		
		return sorted;
	}
	
	protected void inOrder(List<Integer> sorted, Node current) {
		if(current != NULL) {
			inOrder(sorted, current.left);
			sorted.add(current.value);
			inOrder(sorted, current.right);
		}
	}
	
	protected void descOrder(List<Integer> sorted, Node current) {
		if(current != NULL) {
			descOrder(sorted, current.right);
			sorted.add(current.value);
			descOrder(sorted, current.left);
		}
	}
	
	public Node getSuccessor(Node node) {
		// if there are bigger children
		if(node.right != NULL) {
			return min(node.right);
		}
		
		// else successor is ancestor
		// go up and return first parent where we turn right
		Node parent = node.parent;
		Node current = node;
        while ( parent != null && current == parent.right) {
        	current = parent;
        	parent = parent.parent;
        }
        
        // check if it's the last node
        // (if last node then successor "is" root)
        return (parent != null) ? parent : NULL;
	}
	
	public void delete(int value) {
		delete(search(value));
	}
	
	protected void delete(Node node) {
		if(node == NULL || node == null) return;
		
		// leaf: simply remove
		if(node.left == NULL && node.right == NULL) {
			if(node.parent == null) { // root
				root = null;
			} else {
				Node parent = node.parent;
				if(parent.left == node)
					parent.left = NULL;
				else
					parent.right = NULL;
				reCalcHeight(parent);
				rebalance();
			}
		}
		// 1 child: replace node with child then delete
		else if(node.left != NULL && node.right == NULL) {
			Node left = node.left;
			node.value = left.value;
			node.left = left.left;
			node.right = left.right;
			reCalcHeight(node);
			rebalance();
		}
		else if(node.left == NULL && node.right != NULL) {
			Node right = node.right;
			node.value = right.value;
			node.left = right.left;
			node.right = right.right;
			reCalcHeight(node);
			rebalance();
		}
		// 2 children: replace it with successor's value, call delete on successor
		else {
			Node successor = getSuccessor(node);
			node.value = successor.value;
			delete(successor);
		}
	}
	
	protected void reCalcHeight(Node node) {
		int height = Math.max(node.left.height, node.right.height) + 1;
		if(node.height != height) {
			node.height = height;
			if(node.parent != null) // if not root
				reCalcHeight(node.parent);
		}
	}
	
	public void insert(int value) {
		Node node = new Node();
		node.value = value;
		node.left = NULL;
		node.right = NULL;
		
		// root
		if(root == null) {
			node.parent = null;
			node.height = 0;
			root = node;
			return;
		}
		
		Node current = root;
		
		while( true ) {
			if(value < current.value) { // go left
				if(current.left == NULL) {
					current.left = node;
					node.parent = current;
					reCalcHeight(node);
					rebalance();
					return;
				} else {
					current = current.left;
				}
			} else { // go right
				if(current.right == NULL) {
					current.right = node;
					node.parent = current;
					reCalcHeight(node);
					rebalance();
					return;
				} else {
					current = current.right;
				}
			}
		}
	}
	
	protected Node min(Node current) {
		while( true ) {
			if(current.left == NULL) {
				return current;
			} else {
				current = current.left;
			}
		}
	}
	
	public int min() {
		return min(root).value;
	}
	
	protected Node max(Node current) {
		while( true ) {
			if(current.right == NULL) {
				return current;
			} else {
				current = current.right;
			}
		}
	}
	
	public int max() {
		return max(root).value;
	}
}
