import java.util.Arrays;
import java.util.Random;

public class TreeTester {


    // Test program
    public static void main(String[] args) {
        final String ENDLINE = "\n";

        Integer[] list1 = {25, 10, 60, 55, 58, 56, 14, 63, 8, 50, 6, 9, 15,27,29,61};
        Tree<Integer> treeOne = new Tree<Integer>(list1, "TreeOne:",true);

        //Problem 1
        System.out.println("\n\n-------------Program One--------------");
        System.out.println(treeOne.toStringReverse());



        //Problem 2
        System.out.println("\n\n-------------Program Two--------------");
        Integer[] list2 = {4,5,6,8,33,16,80,121,22,25, 36};
        Tree<Integer> treeTwo = new Tree<Integer>(list2, "TreeTwo:", false);
        System.out.println(treeTwo.toStringReverse());

        treeTwo.flip(treeTwo.root);
        treeTwo.changeName("Tree Two Now flipped");
        System.out.println( treeTwo.toStringReverse());
        treeTwo.flip(treeTwo.root);   //Flip back
        treeTwo.changeName("TreeTwo");
        System.out.println(treeTwo.toStringReverse());



        //Problem 3
        System.out.println("\n\n-------------Program Three--------------");
        final int SIZE = 10;
        Integer[] list3 = {111, 176, 67, 77, 112, 119, 120, 70, 92, 153};
        Tree<Integer> treeThree = new Tree<Integer>(list3, "TreeThree:", true);
        System.out.println(treeOne.toStringReverse());
        System.out.println(treeThree.toStringReverse());

        System.out.println("Deepest Node of treeOne " + treeOne.deepestNode());
        System.out.println("Deepest Node of treeThree " + treeThree.deepestNode());


        //Problem 4
        System.out.println("\n\n-------------Program Four--------------");
        int mylevel=3;
        System.out.println(treeThree.toStringReverse());
        System.out.println("Number nodes at level " + mylevel + " is " + treeThree.nodesInLevel(mylevel, treeThree.root, 0));
        mylevel=4;
        System.out.println("Number nodes at level " + mylevel + " is " + treeThree.nodesInLevel(mylevel, treeThree.root, 0));

        //Problem 5
        System.out.println("\n\n-------------Program Five--------------");
        System.out.println(treeThree.toStringReverse());
        System.out.println("All paths from treeThree");
        treeThree.printAllPaths(treeThree.root, "");


        Integer[] list4= {21, 8, 25, 6, 7, 19, 10, 40, 43, 52, 64, 80};
        Tree<Integer> treeFour = new Tree<Integer>(list4, "treeFour", false);



        //Problem 6
        System.out.println("\n\n-------------Program Six--------------");
        System.out.println(treeFour.toStringReverse());
        treeFour.pruneK(60);
        treeFour.changeName("treeFour after pruning 60: ");
        System.out.println(treeFour.toStringReverse());

        System.out.println(treeTwo.toStringReverse());
        treeTwo.pruneK(290);
        treeTwo.changeName("treeTwo after pruning 290: ");
        System.out.println(treeTwo.toStringReverse());





        //Problem 7
        System.out.println("\n\n-------------Program Seven--------------");
        System.out.println(treeOne.toStringReverse());
        System.out.println("treeOne Least Common Ancestor of (10,15) " + treeOne.lca(treeOne.root, 10, 15) + ENDLINE);
        System.out.println("treeOne Least Common Ancestor of (55,61) " + treeOne.lca(treeOne.root,55, 61) + ENDLINE);
        System.out.println("treeOne Least Common Ancestor of (9,50) " + treeOne.lca(treeOne.root,9, 50) + ENDLINE);
        System.out.println("treeOne Least Common Ancestor of (29,62) " + treeOne.lca(treeOne.root,29, 62) + ENDLINE);




        //Problem 8
        System.out.println("\n\n-------------Program Eight--------------");
        Integer[] v5 = {15, 1,2,3,5,10, 65, 66,67,68,83, 70, 90,69,6,8};
        Tree<Integer> treeFive = new Tree<Integer>(v5, "TreeFive:",true);
        System.out.println(treeFive.toStringReverse());
        treeFive.balanceTree();
        treeFive.changeName("treeFive after balancing");
        System.out.println(treeFive.toStringReverse());




        //Problem 9
        System.out.println("\n\n-------------Program Nine--------------");
        System.out.println(treeOne.toStringReverse());
        treeOne.keepRange(14, 50);
        treeOne.changeName("treeOne after keeping only nodes between 14 and 50");
        System.out.println(treeOne.toStringReverse());


        treeFive.changeName("treeFive");
        System.out.println(treeFive.toStringReverse());
        treeFive.keepRange(3, 69);
        treeFive.changeName("treeFive after keeping only nodes between 3 and 69");
        System.out.println(treeFive.toStringReverse());



        // Problem 10
        System.out.println("\n\n-------------Program Ten--------------");
        treeTwo = new Tree<Integer>(list2, "TreeTwo:", false);
        System.out.println(treeTwo.toStringReverse());
        System.out.println("treeTwo Contains BST: " + treeTwo.countBST());

        treeFour = new Tree<Integer>(list4, "treeFour", false);
        System.out.println(treeFour.toStringReverse());
        System.out.println("treeFour Contains BST: " + treeFour.countBST());




        //Bonus Program 11 (Done)
        System.out.println("\n\n-------------Program Eleven--------------");
        Integer[] inorder = {4, 2, 1, 7, 5, 8, 3, 6};
        Integer[] preorder = {1, 2, 4, 3, 5, 7, 8, 6};
        Tree<Integer> treeBonus = new Tree<Integer>("TreeBonus");
        treeBonus.buildTreeTraversals(inorder, preorder);
        treeBonus.changeName("TreeBonus built from inorder and preorder traversals");
//        System.out.println(treeBonus.toString());
        System.out.println(treeBonus.toStringReverse());


    }
}
