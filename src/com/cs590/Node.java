package com.project2;

public class Node{
	private String value;
	private int frequency;
	private Node left;
	private Node right;
	public Node(String value) {
		super();
		this.value = value;
		this.frequency = 1;
	}
	public Node(String value, int frequency) {
		super();
		this.value = value;
		this.frequency = frequency;
	}
	protected boolean isLeaf(){
		return (left == null) && (right == null);
	}
	protected void selfPlus(){
		this.frequency++;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public int getFrequency() {
		return frequency;
	}
	public void setFrequency(int frequency) {
		this.frequency = frequency;
	}
	public Node getLeft() {
		return left;
	}
	public void setLeft(Node left) {
		this.left = left;
	}
	public Node getRight() {
		return right;
	}
	public void setRight(Node right) {
		this.right = right;
	}
}
