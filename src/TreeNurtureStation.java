import java.util.Scanner;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 * nature themed class that reads a file containing element entries
 * for binary search trees, creates the trees, then prints the trees and 
 * evaluates their binary search "tree-ness"
 */
public class TreeNurtureStation {
    private static ArrayList<Integer[]> bstData = new ArrayList<>(); //file is parsed into this array for easy of access. 
    private static ArrayList<BestBinarySearchTree<Integer>> treeList = new ArrayList<>(); //trees are stored here for the same reason

    /**
     * launcher for the "nurturing process" ie growing, showing, and 
     * evaluating the binary search trees.
     * @param fileName
     */
    public static void beginNurturing(String fileName) {
        parseFile(fileName);
        buildTrees();
        printTrees();
    }//beginNurturing method

    /**
     * simply reads a txt file containing bts entries and dumps them
     * in the static ArrayList bstData
     * @param fileName
     */
    public static void parseFile(String fileName) {
        Scanner fileText = null;
        try {
            fileText = new Scanner(new BufferedReader(new FileReader(fileName)));
            while (fileText.hasNextLine()) {
                String[] lineDataString = fileText.nextLine().split(", "); 
                Integer[] lineDataInteger = new Integer[lineDataString.length]; //converts strings to Integer[]
                for (int i = 0; i < lineDataString.length; i++) {
                    lineDataInteger[i] = Integer.parseInt(lineDataString[i]);
                }
                bstData.add(lineDataInteger); //stores Integer[] in ArrayList
            }
        } catch (IOException e) {
            System.out.println(e.toString());
        } finally {
            if (fileText != null) {
                fileText.close();
            }
        }
    }//parseFile method

    /**
     * iterates the Integer[]'s within static ArrayList bstData and uses
     * data to create trees (one good tree one bad tree per data line). 
     * stores in static ArrayList treeList
     */
    public static void buildTrees() {
        for (Integer[] singleTreeData : bstData) {
            treeList.add(new BestBinarySearchTree<Integer>(singleTreeData)); //make good tree
            treeList.add(new BestBinarySearchTree<Integer>(singleTreeData, false)); //make bad tree
        }
    }//buildTrees method

    /**
     * (1)prints a tree to the console in Labeled Preorder format. 
     * (2)determines if it is or is not a binary search tree. 
     * (3)stores first, last, -first, and -last values in the data line into an array 
     * (4)checks if first and last entry in the data line is present in the tree. 
     * (5)checks if the inverse of the first and last entry in the data line is present in the tree.
     * 
     * to maintain efficiency and limit to iterating over the array as little as possible, it made sense
     * to me to do all of these simple secondary functions in one method. 
     */
    public static void printTrees() {
        for (int i = 0; i < treeList.size(); i++) {
            BestBinarySearchTree<Integer> bst = treeList.get(i);
            bst.printTree(); //(1)
            System.out.printf(bst.isBinarySearchTree() ? "is a BST\n" : "is not a BST\n"); //(2)
            Integer[] currentTreeDataLine = bstData.get(i / 2);
            Integer[] valuesToCheck = { currentTreeDataLine[0], currentTreeDataLine[currentTreeDataLine.length - 1], //(3)
                    currentTreeDataLine[0] * -1, currentTreeDataLine[currentTreeDataLine.length - 1] * -1 };
            for (int j = 0; j < valuesToCheck.length; j++) {
                if (bst.contains(valuesToCheck[j]) == null) { //(4&5)
                    System.out.printf("Does not contain %d; ", valuesToCheck[j]); 
                } else {
                    System.out.printf("Does contain %d; ", valuesToCheck[j]); 
                }
            }
            System.out.printf("\n\n");
        }
    }//printTrees method
}//TreeNurtureStation class