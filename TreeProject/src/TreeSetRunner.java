import java.util.*;

public class TreeSetRunner {

    public TreeSetRunner() {

        TreeSet<Integer> tree = new TreeSet<>();

        for (int i = 0; i < 30; i++) { //Part 1 + 2
            int rand = (int) (Math.random() * 100) + 1;
            System.out.print(rand + " ");
            tree.add(rand);
        }

        // Part 3
        System.out.println("Size: " + tree.size());
        printTree(tree, "Tree"); //original tree

        //Parts 4 and 5
        TreeSet<Integer> copy = treeCopy(tree, "pre");
        printTree(copy, "Pre-order copy");

        //Parts 6 and 7
        copy = treeCopy(tree, "in");
        printTree(copy, "In order copy");
        System.out.println("The pre and in order methods are the same as the tree's in order method. The post order method is the in order method reversed.");

        //Parts 8 and 9
        copy = treeCopy(tree, "post");
        printTree(copy, "Post-order copy");
        System.out.println("The pre order method is the same as the tree's post order method. In Order is the same os original. The post order method goes up and then goes down.");

        //Parts 10 and 11
        TreeSet<String> treeString = new TreeSet<>();

        for (int x = 0; x < 20; x++) {
            int rand = (int) (Math.random() * 26) + 65;
            System.out.println("" + (char)rand);
            treeString.add("" + (char) rand);
        }

        printStringTree(treeString, "String tree");

        //Part 12
        for (int x = 0; x < 3; x++) {
            treeString.rotateRight();
            printStringTree(treeString, "String tree R" + x);
        }

        for (int x = 0; x < 3; x++) {
            treeString.rotateLeft();
            printStringTree(treeString, "String tree L1" + x);
        }

        //Part 14
        TreeSet<Integer> anotherTreeSet = new TreeSet<>();
        ArrayList<Integer> list = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            int rand = (int) (Math.random() * 100) + 1;
            anotherTreeSet.add(rand);
            list.add(rand);
        }

        Collections.sort(list);
        System.out.println("\nArraylist: " + list);
        printTree(anotherTreeSet, "Tree");

        //Part 15 and 16
        for (int i = 0; i < list.size(); i++) {

            int rand = (int) (Math.random() * list.size());
            int randNum = list.get(rand);
            list.remove(rand);
            anotherTreeSet.remove(randNum);

            System.out.println("Arraylist removed val (" + randNum + "): " + list);
            printTree(anotherTreeSet, "Tree removed val");
        }
    }

    //partAyy!
    public void printTree(TreeSet<Integer> tree, String st) {

        System.out.println(st + "'s pre-order: " + tree.preOrder());
        System.out.println(st + "'s in order: " + tree.inOrder());
        System.out.println(st + "'s post-order: " + tree.postOrder() + "\n");
    }

    public TreeSet<Integer> treeCopy(TreeSet<Integer> tree, String st) {

        String treeOrder = tree.inOrder().substring(1, tree.inOrder().length() - 1); //default to inOrder

        if (st.equals("pre"))
            treeOrder = tree.preOrder().substring(1, tree.preOrder().length() - 1); //preOrder

        if (st.equals("post"))
            treeOrder = tree.postOrder().substring(1, tree.postOrder().length() - 1); //postOrder

        String[] splitTree = treeOrder.split(", ");

        TreeSet<Integer> copy = new TreeSet<>();

        for (int i = 0; i < splitTree.length; i++)
            copy.add(Integer.parseInt(splitTree[i]));

        return copy;
    }

    public void printStringTree(TreeSet<String> tree, String st) {

        System.out.println("\n" + st + "'s pre-order: " + tree.preOrder());
        System.out.println(st + "'s in order: " + tree.inOrder());
        System.out.println(st + "'s post-order: " + tree.postOrder());
    }

    public TreeSet<String> treeStringCopy(TreeSet<String> tree, String st) {

        String treeOrder = tree.inOrder().substring(1, tree.inOrder().length() - 1); //default to inOrder

        if (st.equals("pre"))
            treeOrder = tree.preOrder().substring(1, tree.preOrder().length() - 1); //preOrder

        if (st.equals("post"))
            treeOrder = tree.postOrder().substring(1, tree.postOrder().length() - 1); //postOrder

        String[] splitTree = treeOrder.split(", ");

        TreeSet<String> copy = new TreeSet<>();

        for (int i = 0; i < splitTree.length; i++)
            copy.add(splitTree[i]);

        return copy;
    }

    public static void main(String[] args) {
        TreeSetRunner app = new TreeSetRunner();
    }
}