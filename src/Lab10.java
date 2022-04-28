/*  
Description: Lab 9 
Author: alex rodriguez 
Date: 4.23.22
Bugs: None that I know of
Reflection: I wasn't really sure how to get a junit test in here
or what it would even do so I'll take the loss for that. 
otherwise relatively easy. good practice with generics. came away
learning quite a lot. 
*/ 
public class Lab10 {
    public static void main(String[] args) throws Exception {
        BestBinarySearchTree<Integer> tree = new BestBinarySearchTree<>(new Integer[] {1, 2, 3, 4});
        tree.printTree();
        tree.drawTree();

        //StdDraw.setCanvasSize(500, 500);
        //StdDraw.setScale(0, 100);
        //StdDraw.filledCircle(50, 50, 25);
    }
}
