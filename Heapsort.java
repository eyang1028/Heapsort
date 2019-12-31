import java.awt.Color; //imported stuff I need
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Insets;
import java.awt.Color;

import java.util.*;
import java.util.Random;
import java.util.Scanner;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import java.io.*;

class Heapsort extends JPanel { //heapsort class
	private int size;
	private int[] arr;

	Heapsort() { //constructor
		int index = 0;

		arr = new int[800]; //create array of integers from file

		try {
			File file = new File("num800");
			Scanner inputFile = new Scanner(file);
			while (inputFile.hasNextInt() && index < arr.length) { //start creating array of integers
				arr[index] = inputFile.nextInt();
				index++;
			}
			inputFile.close();
		} catch(FileNotFoundException e) { //catch error
			System.out.println(e);
		}

		size = arr.length - 1;
	}

	private void delay(long u) { //delay for displaying the sort
		long i = 0L;

		while (i < u) {
			i++;
		}
	}

	private void doDrawing(Graphics g) { //drawing the array values
		Graphics2D g2d = (Graphics2D) g;

		for(int i = 0; i < arr.length; i++) {
			g2d.drawLine(2 * i, 1020, 2 * i, 1020 - (arr[i] / 50));
		}
	}

	public void paintComponent(Graphics g) { //utilizing imported Graphics class
		super.paintComponent(g);
		doDrawing(g);
	}

	private void swap(int i, int j) { //separate method for swap
		int temp = arr[i];
		arr[i] = arr[j];
		arr[j] = temp;
		delay(2000000L);
		repaint();
	}

	private void heapify() {
		int n = size / 2; //n is the index of the last parent

		for(int i = n; i >= 0; i--) {
			subHeapify(i);
		}
	}

	private void subHeapify(int p) { //make the heap first
		int left = 2 * p;
		int right = 2 * p + 1; //implementation

		if(left <= size) { //check if left child is valid
			if(right <= size) { //check if right child is valid
				if(arr[right] > arr[left])
					left = right; //left is the index of bigger child
			}
			if(arr[p] < arr[left]) {
				swap(p, left); //swap if child is bigger than parent
				subHeapify(left); //left might be parents of other children, sort the sub-tree again
			}
		}
	}

	public void sort() {
		int n = arr.length - 1;

		heapify(); //create heap
		for(int i = n; i > 0; i--) {
			swap(0, i);
			--size;
			subHeapify(0); //resort the subtree since root has been changed
		}
	}

	public void sortedNumbers() { //method for outputting the text file
		try{
			FileWriter fr = new FileWriter("sortednumbers.txt");
			BufferedWriter br = new BufferedWriter(fr);
			PrintWriter out = new PrintWriter(br);
			for(int i = 0; i < arr.length; i++) { //print out every element in the array separated by a space
				out.write(arr[i] + " ");
			}
			out.close();
		} catch(IOException e) {
			System.out.println(e);
		}
	}
}

class showHeapsort extends JFrame { //JFrame class for the display
	private Heapsort mySort;

	public showHeapsort() {
		mySort = new Heapsort();
		initUI();
	}

	private void initUI() {
		setTitle("demonstrate heapsort");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		add(mySort);

		setSize(1600, 1020);
		setLocationRelativeTo(null);
	}

	public void startSort() {
		mySort.sort();
		mySort.sortedNumbers();
	}
}

class exeHeapsort { //main class for executing the sort
	public static void main(String args[])
	throws java.io.IOException {
		showHeapsort sortNum = new showHeapsort();
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				sortNum.setVisible(true); //show the JFrame
			}
		});
		sortNum.startSort(); //run the sort
	}
}
