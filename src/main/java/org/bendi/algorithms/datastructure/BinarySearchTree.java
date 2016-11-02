package org.bendi.algorithms.datastructure;

import java.util.ArrayList;
import java.util.List;

public class BinarySearchTree {
	public static class Node {
		public Integer value;
		public Node left;
		public Node right;
		public Node parent;
		
		@Override
		public String toString() {
			return "Node [value=" + ((value == null) ? "null" : value) + 
					", left=" + ((left == null) ? "null" : left.value) + 
					", right=" + ((right == null) ? "null" : right.value) + 
					", parent=" + ((parent == null) ? "null" : parent.value) + 
					"]";
		}
	}
	
	protected Node root;
	
	public BinarySearchTree() {}
	
	public BinarySearchTree(int[] array) {
		for(int i : array) { insert(i); }
	}
	
	public Node search(int value) {
		Node current = root;
		
		while( current != null ) {
			if(value == current.value) {
				return current;
			} else if(value < current.value) {
				current = current.left;
			} else {
				current = current.right;
			}
		}
		
		return null;
	}
	
	public int size() {
		return size(root);
	}
	
	protected int size(Node node) {
		if(node == null) return 0;
		
		int left = size(node.left);
		int right = size(node.right);
		
		return left + right + 1;
	}
	
	public int height() {
		return height(root);
	}
	
	protected int height(Node node) {
		if (node == null)
	        return -1;

	    int left = height(node.left);
	    int right = height(node.right);

	    if(left > right)
	        return left + 1;
	    else
	        return right + 1;
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
		if(node == null) return true;
		
		if(node.value < min || node.value > max)
			return false;
		
		// allowed values
		// left: -infinity - parent value
		// right: parent value - infinity
		return isBST(node.left, min, node.value) && isBST(node.right, node.value, max);
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
		if(current != null) {
			inOrder(sorted, current.left);
			sorted.add(current.value);
			inOrder(sorted, current.right);
		}
	}
	
	protected void descOrder(List<Integer> sorted, Node current) {
		if(current != null) {
			descOrder(sorted, current.right);
			sorted.add(current.value);
			descOrder(sorted, current.left);
		}
	}
	
	public Node getSuccessor(Node node) {
		// if there are bigger children
		if(node.right != null) {
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
        return (parent != null) ? parent : null;
	}
	
	public void delete(int value) {
		delete(search(value));
	}
	
	protected void delete(Node node) {
		if(node == null || node == null) return;
		
		// leaf: simply remove
		if(node.left == null && node.right == null) {
			if(node.parent == null) { // root
				root = null;
			} else {
				Node parent = node.parent;
				if(parent.left == node)
					parent.left = null;
				else
					parent.right = null;
			}
		}
		// 1 child: replace node with child then delete
		else if(node.left != null && node.right == null) {
			Node left = node.left;
			node.value = left.value;
			node.left = left.left;
			node.right = left.right;
		}
		else if(node.left == null && node.right != null) {
			Node right = node.right;
			node.value = right.value;
			node.left = right.left;
			node.right = right.right;
		}
		// 2 children: replace it with successor's value, call delete on successor
		else {
			Node successor = getSuccessor(node);
			node.value = successor.value;
			delete(successor);
		}
	}
	
	public void insert(int value) {
		Node node = new Node();
		node.value = value;
		node.left = null;
		node.right = null;
		
		// root
		if(root == null) {
			node.parent = null;
			root = node;
			return;
		}
		
		Node current = root;
		
		while( true ) {
			if(value < current.value) { // go left
				if(current.left == null) {
					current.left = node;
					node.parent = current;
					return;
				} else {
					current = current.left;
				}
			} else { // go right
				if(current.right == null) {
					current.right = node;
					node.parent = current;
					return;
				} else {
					current = current.right;
				}
			}
		}
	}
	
	protected Node min(Node current) {
		while( true ) {
			if(current.left == null) {
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
			if(current.right == null) {
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
