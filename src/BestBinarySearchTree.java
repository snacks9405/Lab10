import java.util.ArrayList;
import java.util.HashMap;

import net.datastructures.LinkedBinaryTree;
import net.datastructures.Position;
import dsaj.trees.TraversalExamples;

/**
 * probably the greatest example of a binary search tree that's ever existed
 */
public class BestBinarySearchTree<E extends Comparable<E>> extends LinkedBinaryTree<E> {
    private int height;

    /**
     * This convenience constructor will pre-populate the tree using the array of
     * elements, inserting each one in turn, correctly, into the tree
     * 
     * @param elements
     */
    public BestBinarySearchTree(E[] elements) {
        this(elements, true);
    }// BestBinarySearchTree constructor

    /**
     * This convenience constructor will pre-populate the tree using the array of
     * elements, inserting each one in turn -- correctly, with insert() if doItRight
     * is true, or incorrectly, with brokenInsert() if it is false
     * 
     * @param elements
     * @param doItRight
     */
    public BestBinarySearchTree(E[] elements, boolean doItRight) {
        for (E dataToTree : elements) { // iterate the elements and build the tree...
            if (doItRight) {
                insert(dataToTree); // the right way or...
            } else {
                brokenInsert(dataToTree); // the naughty way.
            }
        }
        setHeight();
    }// BestBinarySearchTree constructor

    /**
     * inserts element at its correct Position, and returns that Position. If the
     * element already exists, throw an illegal state exception.
     * 
     * @param element
     * @return
     */
    protected Position<E> insert(E element) throws IllegalStateException {
        if (contains(element) != null) { // if the item exists in the tree, throw exception
            throw new IllegalStateException();
        }
        if (root == null) {
            return addRoot(element); // give it a root if it has none
        }
        Position<E> walk = root; // walker will travel to the right placement
        while (walk != null) {
            if (element.compareTo(walk.getElement()) < 0) { // if element is less than walk...
                if (left(walk) == null) {
                    return addLeft(walk, element); // make it the left child or...
                    
                } else {
                    walk = left(walk); // move on to the left child.
                }
            } else {
                if (right(walk) == null) { // if element is greater than walk...
                    return addRight(walk, element); // make it the right child or...
                } else {
                    walk = right(walk); // move on to the right child.
                }
            }
        }
        return null;
    }// insert method

    /**
     * inserts element to the left of the leftmost node in the tree, ignoring its
     * actual value. This breaks the binary search tree property, hence the name.
     * 
     * @param element
     * @return
     */
    private Position<E> brokenInsert(E element) {
        if (root() == null) { // give it a root
            return addRoot(element);
        } else {
            Position<E> walk = root();
            while (left(walk) != null) { // element is placed as the furthest left child.
                walk = left(walk);
            }
            return addLeft(walk, element);
        }
    }// brokenInsert method

    /**
     * returns true if the tree is a binary search tree, false otherwise.
     * 
     * @return
     */
    public boolean isBinarySearchTree() {
        return isBinarySearchTree(root); // start recursive search at the root
    }// isBinarySearchTree method

    /**
     * returns true if the tree is a binary search tree, false otherwise.
     * recursive implementation
     * 
     * @param curPos
     * @return
     */
    private boolean isBinarySearchTree(Position<E> curPos) {
        Position<E> leftChild = left(curPos); // get left child or null
        Position<E> rightChild = right(curPos); // get right child or null
        if (leftChild != null) {
            if (curPos.getElement().compareTo(leftChild.getElement()) < 0) { // if an element is out of place...
                return false;
            }
            return isBinarySearchTree(leftChild);
        } else if (rightChild != null) {
            if (curPos.getElement().compareTo(rightChild.getElement()) > 0) { // return false
                return false;
            }
            return isBinarySearchTree(rightChild);
        }
        return true; // if everything checks out, it's definitely a binary search tree.
    }// isBinarySearchTree method

    /**
     * returns the position of the element, null if it is not present
     * checks root cases, then sends it to a further search method. the
     * type is depended on whether the tree is a binary search tree or not.
     * 
     * @param element
     * @return
     */
    public Position<E> contains(E element) {
        if (root == null) { // cursory examination will just check if there's even a root...
            return null;
        }
        if (root.getElement().compareTo(element) == 0) { // then if the root matches the element.
            return root;
        }
        return (isBinarySearchTree() ? contains(element, root) : badTreeContains(element));
    }// contains method

    /**
     * recursively searches a verified binary search tree by iterating through each
     * position
     * and its children until all spots are checked or the element is found
     * 
     * @param element
     * @param curPos
     * @return
     */
    private Position<E> contains(E element, Position<E> curPos) {
        if (curPos.getElement().compareTo(element) == 0) { // if curPos is our element, return it
            return curPos;
        } else if (element.compareTo(curPos.getElement()) < 0) {
            if (left(curPos) == null) { // if element is less than curPos element, check it's child or return null
                return null;
            }
            return contains(element, left(curPos));
        } else {
            if (right(curPos) == null) { // if element is greater than curPos element, check it's child or return null
                return null;
            }
            return contains(element, right(curPos));
        }
    }// contains method

    /**
     * searches through a weird left only array by reapeatdly grabbing the
     * left child
     * 
     * @param element
     * @return
     */
    private Position<E> badTreeContains(E element) {
        Position<E> walk = root;
        while (walk != null) {
            if (element.compareTo(walk.getElement()) == 0) { // if it finds a match, return it
                return walk;
            }
            walk = left(walk);
        }
        return null; // or return null
    }// badTreeContains method

    /**
     * This will print the tree, leveraging method(s) in
     * dsaj.trees.TraversalExamples.
     */
    public void printTree() {
        TraversalExamples.printPreorderLabeled(this, root(), new ArrayList<Integer>());
        System.out.println(height);
    }// printTree method

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //below this line are new items
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    /**
     * calculates the height of the tree. (I realize it's off by one but wanted
     * to avoid a lot of +1's later.)
     */
    public void setHeight() {
        if (root == null) {
            height = 0;
        }
        height = setHeight(root, 0);
    }//setHeight method

    /**
     * recursively calculates the height of the tree.
     * @param curPos
     * @param height
     * @return
     */
    private int setHeight(Position<E> curPos, int height) {
        if (curPos == null) {
            return height;
        }
        height++;
        return Math.max(setHeight(left(curPos), height), setHeight(right(curPos), height)); //travels down the tree looking for the longest branch
    }//setHeight method

    /**
     * draws the tree using stdDraw
     */
    public void drawTree() {
        int drawHeight = height * 100; //easily scales the tree for the heights we're focusing on. 
        int drawWidth =  height * 100;
        final int RADIUS = 20; //found 20 looks nice, let's lock it in
        StdDraw.setCanvasSize(drawWidth, drawHeight); 
        StdDraw.setScale(0, drawHeight);
        drawTrees(1, drawHeight, drawWidth, RADIUS, new HashMap<>()); //passes along the measurements and begins drawing the tree at the top
    }//drawTree method

    /**
     * recursively draws the tree level by level
     * 
     * @param level             level being drawn
     * @param drawHeight        height of canvas
     * @param drawWidth         width of canvas
     * @param radius            radius of each circle
     * @param parentPositions   the positions and their coordinates on the stdDraw canvas
     */
    public void drawTrees(int level, int drawHeight, int drawWidth, int radius, HashMap<Position<E>, double[]> parentPositions) {
        double nodeMaxOnLevel = Math.pow(2, level - 1); //helps determine the amount...
        double numberSplitsOnAxis = nodeMaxOnLevel + 1;    //of splits on the x axis we need to make enough space.
        double yPosition = drawHeight - (level * (drawHeight / (height + 1))); //splits the y plane into (height+1) spaces and selects the one we should be on
        ArrayList<Position<E>> nodesAtLevel = getNodesAtHeight(level); //uses helper method to get all of the nodes at the current level
        HashMap<Position<E>, double[]> curPositions = new HashMap<>(); //prepares a hashmap to store these nodes and their coordinates to draw lines to in the next level
        for (int i = 0; i < nodeMaxOnLevel; i++) { //iterates through the nodes at this level and plots them...
            Position<E> curNode = nodesAtLevel.get(i);
            if (curNode != null) {
                double xPosition = drawWidth / numberSplitsOnAxis + (i * (drawWidth / numberSplitsOnAxis));
                StdDraw.setPenColor(StdDraw.WHITE);
                StdDraw.filledCircle(xPosition, yPosition, radius);
                StdDraw.setPenColor(StdDraw.BLACK);
                StdDraw.circle(xPosition, yPosition, radius);
                StdDraw.text(xPosition, yPosition, curNode.getElement().toString());
                double[] xyVals = {xPosition, yPosition};
                curPositions.put(curNode, xyVals); //and stores them for the next level down. 
                if (curNode != root) {
                    double[] parentData = parentPositions.get(parent(curNode)); //to draw a line, start at the current node, find its parent in the hashmap...
                    StdDraw.line(xPosition, yPosition, parentData[0], parentData[1]); //and finish at the parents coordinates. 
                }
            }
        }
        if (level != height) { //continue drawing until we've done the whole thing.
            drawTrees(++level, drawHeight, drawWidth, radius, curPositions);
        }
    }//drawTrees method

    /**
     * returns an arrayList containing all of the nodes at the specified height
     * 
     * @param levelToSearch
     * @return
     */
    public ArrayList<Position<E>> getNodesAtHeight(int levelToSearch) {
        ArrayList<Position<E>> nodeSet = new ArrayList<>();
        if (levelToSearch == 1) {
            nodeSet.add(root); //easy level one only has the root
        } else {
            nodeSet.add(root); //pass the root in and search the next
            nodeSet = getNodesAtHeight(levelToSearch, 2, nodeSet);//set height to search as 2 as it's the one after root.
        }
        return nodeSet;
    }//getNodesAtHeight method

    /**
     * recursively grabs the nodes at the specified level by going down 1-1 and getting all of the children. then
     * their children etc etc.
     * 
     * @param levelToSearch  target level
     * @param currentLevel  current level
     * @param nodeSet the parents of the current level
     * @return the nodes at the current level
     */
    public ArrayList<Position<E>> getNodesAtHeight(int levelToSearch, int currentLevel, ArrayList<Position<E>> nodeSet) {
        ArrayList<Position<E>> nodesAtCurrentHeight = new ArrayList<>();
        for (Position<E> curPos : nodeSet) { //iterate through the parents of the current level
            Position<E> leftChild = (curPos == null ? null : left(curPos));
            Position<E> rightChild = (curPos == null ? null : right(curPos));
            if (leftChild == null) {
                nodesAtCurrentHeight.add(null); //preserve empty spaces
            } else {
                nodesAtCurrentHeight.add(leftChild); //or pass in actual nodes
            }
            if (rightChild == null) {
                nodesAtCurrentHeight.add(null);
            } else {
                nodesAtCurrentHeight.add(rightChild);
            }
        }
        if (levelToSearch == currentLevel) { //if this was the level target, send em out.
            return nodesAtCurrentHeight;
        }

        return getNodesAtHeight(levelToSearch, ++currentLevel, nodesAtCurrentHeight); //if we haven't gone far enough down, keep digging.
    }//getNodesAtHeight method
}// BestBinarySearchTree class