import java.util.Scanner;

public class CustomTreeArtist {
    static Scanner scan = new Scanner(System.in);

    /**
     * builds an inital tree, then interacts with user for insertion.
     * 
     * initially was two methods but I was having trouble with scan and 
     * lack of time so it's a big one. 
     */
    public static void terminalInterface() {
        System.out.println("Enter initial tree: ");
        String[] input = scan.nextLine().split(", "); //eat the data
        Integer[] inputInts = new Integer[input.length]; 
        try {
            for (int i = 0; i < input.length; i++) {
                inputInts[i] = Integer.parseInt(input[i]); //chews up the data  to prepare to build the tree
            }
            BestBinarySearchTree<Integer> goodestTree = new BestBinarySearchTree<>(inputInts); //build it
            goodestTree.drawTree(); //draw it
            System.out.print("Element to insert: "); //get dessert
            int toInsert = scan.nextInt();
            while(toInsert != -1) { //prompts user to insert or -1 to break.
                goodestTree.insert(toInsert);
                goodestTree.setHeight();
                goodestTree.drawTree();
                System.out.print("Element to insert: ");
                toInsert = scan.nextInt();
            }
        } catch (NumberFormatException e) {
            System.out.println("bad format");
            scan.close();
        } finally {
            if (scan != null) {
                scan.close();
            }
        }
    }//terminalInterface method
}//CustomTreeArtist class
