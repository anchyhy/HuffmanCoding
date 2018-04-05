package com.project2;

import java.text.DecimalFormat;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;

import com.project2.FileManage;

public class Huffman {
	
	// Store the symbols and their frequencies 
	private Map<Character, Integer> map_count;
	private Map<Character, String> map_code;
	private double length;
	
	public Huffman(String input) {
		this.length = input.length();
		this.map_code = new HashMap<Character, String>();
		this.map_count = new HashMap<Character, Integer>();
		Node node = createTree(input);
		traversal(node, "");
		translate(node);
	}
	// Create tree 
	private Node createTree(String s){
		if (s.length() == 0 || s == null) {
			return null;
		}
		// Store nodes
		for(int i=0; i<s.length(); i++){
			if(!map_count.containsKey(s.charAt(i))){
				map_count.put(s.charAt(i), 1);
			}else{
				int j = map_count.get(s.charAt(i))+1;
				map_count.put(s.charAt(i), j);
			}
		}
		//System.out.println(map_count.size());
		Node[] nodes=new Node[map_count.size()];
		int idx = 0;
		Iterator<Map.Entry<Character , Integer>> iter = map_count.entrySet().iterator();
		while (iter.hasNext()) {
			Map.Entry<Character, Integer> entry = iter.next();
			Character key = entry.getKey();
			Integer val = entry.getValue();
			nodes[idx++] = new Node(key+"", val);
		}
		// Compare the nodes' frequencies 
		Comparator<Node> OrderFrequence =  new Comparator<Node>(){  
            public int compare(Node n1, Node n2) {  
                // TODO Auto-generated method stub  
                int frequence1 = n1.getFrequency();  
                int frequence2 = n2.getFrequency();  
                if(frequence2 > frequence1) {  
                    return -1;  
                    } else if(frequence2 < frequence1) {  
                    return 1;  
                    } else {  
                    return 0; 
                    }   
                }  
		};
		PriorityQueue<Node> queue =  new PriorityQueue<Node>(s.length(), OrderFrequence);
		for (int i = 0; i < nodes.length; i++) {
			queue.add(nodes[i]);
		}
		for (int i = 0; i < nodes.length - 1; i++) {
			Node n1 = queue.poll();
			Node n2 = queue.poll();
			Node newNode = new Node(n1.getValue() + n2.getValue(), n1.getFrequency() + n2.getFrequency());
			newNode.setLeft(n1.getFrequency() >= n2.getFrequency() ? n1 : n2);
			newNode.setRight(n1.getFrequency() >= n2.getFrequency() ? n2 : n1);
			queue.add(newNode);
		}
		// Return the root of the tree
		return queue.poll();
	}
	
	
	// Traversal of the tree to create the huffman code
	private void traversal(Node node, String code){
		if(!node.isLeaf()){
			if(node.getLeft() != null)
				traversal(node.getLeft(), code+"0");
			if(node.getRight() != null)
				traversal(node.getRight(), code+"1");
		}else
			map_code.put(node.getValue().charAt(0), code);
	}
	
	// BFS the tree to print the huffman code
	private void translate(Node node){
		DecimalFormat df = new DecimalFormat("0.00");
		if (node == null) {
			return;
		}
		int total_length = 0;
		String res = "Symbol             Frequency            Huffman Code          Length Of Each Coded Symbol   \n";
		Queue<Node> queue = new LinkedList<Node>();
		queue.add(node);
		while (!queue.isEmpty()) {
			Node tmp = queue.poll();
			if (tmp.isLeaf()) {
				res += " "+tmp.getValue() + "                  " + df.format(tmp.getFrequency() * 100 / length)+ "%                  " +map_code.get(tmp.getValue().charAt(0)) + "                  " + map_code.get(tmp.getValue().charAt(0)).length() + "\n";
				total_length += tmp.getFrequency() * map_code.get(tmp.getValue().charAt(0)).length();
			} else {
				if (tmp.getLeft() != null) {
					queue.add(tmp.getLeft());
				}
				if (tmp.getRight() != null) {
					queue.add(tmp.getRight());
				}
			}
		}
		res += "The total length of the coded message is " +total_length +" bits.";
		// Output as a file
		FileManage fileManage = new FileManage();
		fileManage.outputFile(res);
	}
}
