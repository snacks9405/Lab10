import java.util.Scanner;

public class CustomTreeArtist {

    public static void terminalInterface() {

    }
    private static BestBinarySearchTree<Integer> buildInitialTree() {
        Scanner scan = new Scanner(System.in);
        System.out.println("Enter initial tree: ");
        String[] input = scan.nextLine().split(", ");
        Integer[] inputInts = new Integer[input.length];
        try {
            for (int i = 0; i < input.length; i++) {
                inputInts[i] = Integer.parseInt(input[i]);
            }
            return new BestBinarySearchTree<>(inputInts);
        } catch (NumberFormatException e) {
            System.out.println("bad format");
            scan.close();
            return null;
        } finally {
            if (scan!= null) {
                scan.close();
            }
        }
    }
}
