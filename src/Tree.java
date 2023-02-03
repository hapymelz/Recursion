// ******************ERRORS********************************
// Throws UnderflowException as appropriate

import java.util.ArrayList;
import java.util.Comparator;

class UnderflowException extends RuntimeException {
    /**
     * Construct this exception object.
     *
     * @param message the error message.
     */
    public UnderflowException(String message) {
        super(message);
    }
}

public class Tree<E extends Comparable<? super E>> {
    public BinaryNode<E> root;  // Root of tree
    private String treeName;     // Name of tree

    /**
     * Create an empty tree
     * @param label Name of tree
     */
    public Tree(String label) {
        treeName = label;
        root = null;
    }

    /**
     * Create tree from list
     * @param arr   List of elements
     * @param label Name of tree
     * @ordered true if want an ordered tree
     */
    public Tree(E[] arr, String label, boolean ordered) {
        treeName = label;
        if (ordered) {
            root = null;
            for (int i = 0; i < arr.length; i++) {
                bstInsert(arr[i]);
            }
        } else root = buildUnordered(arr, 0, arr.length - 1);
    }

    /**
     * Build a NON BST tree by inorder
     * @param arr nodes to be added
     * @return new tree
     */
    private BinaryNode<E> buildUnordered(E[] arr, int low, int high) {
        if (low > high) return null;
        int mid = (low + high) / 2;
        BinaryNode<E> curr = new BinaryNode<>(arr[mid], null, null);
        curr.left = buildUnordered(arr, low, mid - 1);
        curr.right = buildUnordered(arr, mid + 1, high);
        return curr;
    }


    /**
     * Change name of tree
     * @param name new name of tree
     */
    public void changeName(String name) {
        this.treeName = name;
    }




    /**
     * Return a string displaying the tree contents as a single line
     */
    public String toString() {
        if (root == null)
            return treeName + " Empty tree";

        return treeName + "\n" + printTree(root, 0);
    }
    public String printTree(BinaryNode<E> node, int height) {
        if (node == null) {
            return "";
        }

        String spaces = "";
        for (int i = 0; i <= (4 - height); i++) {
            spaces += " ";
        }
        return spaces + node.element + "(" + height + ")\n" + printTree(node.left, height + 1) + printTree(node.right, height + 1);
    }


    /**
     * Return a string displaying the tree contents as a single line
     */
    public String toString2() {
        if (root == null)
            return treeName + " Empty tree";
        else
            return treeName + " " + toString2(root);
    }

    private String toString2(BinaryNode<E> t) {
        if (t == null) return "";
        StringBuilder sb = new StringBuilder();
        sb.append(toString2(t.left));
        sb.append(t.element.toString() + " ");
        sb.append(toString2(t.right));
        return sb.toString();
    }






    /** Program 1
     * Internal method to return a string of items in the tree in order
     * This routine runs in O(n^2), because you go through the tree once to load
     * values into the array, then again when you go through the array to load the values into the string.
     *
     * @param t the node that roots the subtree.
     */
    ArrayList<E> nodeArr = new ArrayList<>();
    public String toStringReverse() {
        if (!nodeArr.isEmpty()) {
            nodeArr.clear();
        }
        loadArr(root);
        nodeArr.sort(Comparator.reverseOrder());
        String order = "";

        for (E item : nodeArr) {
            int numSpaces = getHeight(item, 0, root);
            String spaces = "";
            for (int i = 0; i < numSpaces; i++) {
                spaces = spaces + " ";
            }
            order = order + spaces + item + "\n";
        }
        return order;
    }
    private int getHeight(E node, int height, BinaryNode<E> currNode) {
        if (currNode == null) {
            return 0;
        }
        if (currNode.element.compareTo(node) == 0) {
            return height;
        }
        return getHeight(node, height + 1, currNode.left) + getHeight(node, height + 1, currNode.right);

    }
    private void loadArr(BinaryNode<E> node) {
        if (node == null) { return; }
        nodeArr.add(node.element);
        loadArr(node.left);
        loadArr(node.right);
    }





    /** Program 2
     * The complexity of finding the flip is O(n)
     * reverse left and right children recursively
     */
    public void flip(BinaryNode<E> node) {
        if (node.right == null && node.left == null) {
            return;
        }
        if (node.left == null) {
            node.left = node.right;
            node.right = null;
            return;
        }
        if (node.right == null) {
            node.right = node.left;
            node.left = null;
            return;
        }
        flip(node.left);
        flip(node.right);
        BinaryNode<E> temp = node.left;
        node.left = node.right;
        node.right = temp;
    }





    /** Program 3
     * The complexity of finding the deepest node is O(n)
     * @return
     */
    private BinaryNode<E> deepestNode = root;
    private int maxHeight = 0;
    public E deepestNode() {
        getDeepest(root, 0);
        return deepestNode.element;
    }
    public void getDeepest(BinaryNode<E> node, int height) {
        if (root == null) { return; }
        if (node == null) { return; }
        getDeepest(node.left, height + 1 );

        if (height > maxHeight) {
            deepestNode = node;
            maxHeight = height;
        }
        getDeepest(node.right, height + 1);

    }





    /** Program 4
     * Counts number of nodes in specified level
     * The complexity of nodesInLevel is O(n)
     * @param level Level in tree, root is zero
     * @return count of number of nodes at specified level
     */
    public int nodesInLevel(int level, BinaryNode<E> node, int nodeLevel) {
        if (node == null) {
            return 0;
        }
        if (nodeLevel == level) {
            return 1;
        }

        return nodesInLevel(level, node.left, nodeLevel + 1) + nodesInLevel(level, node.right, nodeLevel + 1);
    }






    /** Program 5
     * Print all paths from root to leaves
     * The complexity of printAllPaths is O(???)
     */
    public void printAllPaths(BinaryNode<Integer> node, String path) {
        if (root == null) { return; }
        if (node == null) { return; }
        if (node.left == null && node.right == null) {
            System.out.print(path + node.element + "\n");
            return;
        }
        printAllPaths(node.left, path + node.element + " ");
        printAllPaths(node.right, path + node.element + " ");
    }






    /** Program 6
     * Remove all paths from tree that sum to less than given value
     * @param sum: minimum path sum allowed in final tree
     */
    public void pruneK(BinaryNode<E> node, Integer sum) {
        if (node == null) { return; }


    }








    /** Program 7
     * Find the least common ancestor of two nodes
     * @param a first node
     * @param b second node
     * Complexity is O(n)
     * @return String representation of ancestor
     */
    public E lca(BinaryNode<E> node, E a, E b) {
        if (node == null) { return null;}
        if (!contains(a) || !contains(b)) {
            return null;
        }
        if (a.compareTo(node.element) == 0 || b.compareTo(node.element) == 0) { return node.element; }
        E left = lca(node.left, a, b);
        E right = lca(node.right, a, b);
        if (left == null) {
            return right;
        }
        else if (right == null) {
            return left;
        }
        else {
            return node.element;
        }
    }







    /** Program 8
     * Balance the tree
     */
    ArrayList<E> nodes = new ArrayList<>();
    public void balanceTree() {
        if (!nodes.isEmpty()) {
            nodes.clear();
        }

        loadNodes(root);

        nodes.sort(Comparator.naturalOrder());
        for (E item : nodes) {
            System.out.println(item);
        }


        root = new BinaryNode<E>(nodes.get(nodes.size() / 2));

        root.left = balance(0, nodes.size() / 2 - 1);
        root.right = balance(nodes.size() / 2 + 1, nodes.size() - 1);
    }

    private void loadNodes(BinaryNode<E> node) {
        if (node == null) { return; }
        nodes.add(node.element);
        loadNodes(node.left);
        loadNodes(node.right);
    }

    private BinaryNode<E> balance(int start, int end) {
        if (start > end) {
            return null;
        }
        int mid = (start + end) / 2;
        BinaryNode<E> node = new BinaryNode<E>(nodes.get(mid));

        node.left = balance(start, mid - 1);
        node.right = balance(mid + 1, end);

        return node;
    }







    /** Program 9
     * In a BST, keep only nodes between range
     *
     * @param a lowest value
     * @param b highest value
     */
    public void keepRange(E a, E b) {
        if (root == null) { return; }
        if (root.left == null) { return; }

        if (root.element.compareTo(a) <= 0) {
            root = root.right;
        }
        while (root.left.element.compareTo(a) <= 0) {
            root.left = root.left.right;
        }
        keepRange(a, b, root.left);


        if (root.right == null) { return; }
        if (root.element.compareTo(b) >= 0) {
            root = root.left;
        }
        while (root.right.element.compareTo(b) >= 0) {
            root.right = root.right.left;
        }
        keepRange(a, b, root.right);

    }

    public void keepRange(E a, E b, BinaryNode<E> node) {
        if (node == null) { return; }
        if (node.left != null) {
            while (node.left != null && node.left.element.compareTo(a) <= 0) {
                if (node.left.right != null) {
                    node.left = root.left.right;
                }
                else if (node.right.left != null) {
                    node.left = node.right.left;
                }
                else {
                    node.right = null;
                    node.left = null;
                }
            }
            keepRange(a, b, node.left);
        }


        if (node.right != null) {
            while (node.right != null && node.right.element.compareTo(b) >= 0) {
                if (node.right.left != null) {
                    node.right = node.right.left;
                }
                else if (node.left != null && node.left.right != null) {
                    node.right = node.left.right;
                }
                else {
                    node.right = null;
                    node.left = null;
                }
            }
            keepRange(a, b, node.right);
        }
    }








    /** Program 10
     * Counts all non-null binary search trees embedded in tree
     *  The complexity of countBST is O(n)
     * @return Count of embedded binary search trees
     */
    public int countBST(BinaryNode<E> node) {
//        BSTrees bst = countBSTrees();
//        return bst.bstNum;
        if (root == null) { return 0;}
        if (node.left == null && node.right == null) { return 1;}

        if (node.left == null && node.right != null) {
            if (node.right.element.compareTo(node.element) > 0) {
                return 1 + countBST(node.right);
            }
        }

        else if (node.right == null && node.left != null) {
            if (node.left.element.compareTo(node.element) < 0) {
                return 1 + countBST(node.left);
            }
        }
        else if (node.left != null && node.right != null) {
            if (node.left.element.compareTo(node.element) < 0 && node.right.element.compareTo(node.element) > 0) {
                return 1 + countBST(node.left) + countBST(node.right);
            }
        }

        return countBST(node.left) + countBST(node.right);
    }

    static class BSTrees {
        int bstNum;
        int bstMax;
        int bstMin;
        boolean isBST;

        BSTrees(int num, int max, int min, boolean bst) {
            bstNum = num;
            bstMax = max;
            bstMin = min;
            isBST = bst;
        }
    }

//    public BSTrees countBSTrees() {
//        if (root == null)
//
//
//
//        return bst;
//    }






    /** Program 11
     * Build tree given inOrder and preOrder traversals.  Each value is unique
     * @param inOrder  List of tree nodes in inorder
     * @param preOrder List of tree nodes in preorder
     */
    public void buildTreeTraversals(BinaryNode<E> node, E[] inOrder, E[] preOrder) {
        if (node == null) { return; }

    }







    // Basic node stored in unbalanced binary  trees
    private static class BinaryNode<E> {
        E element;            // The data in the node
        BinaryNode<E> left;   // Left child
        BinaryNode<E> right;  // Right child

        // Constructors
        BinaryNode(E theElement) {
            this(theElement, null, null);
        }

        BinaryNode(E theElement, BinaryNode<E> lt, BinaryNode<E> rt) {
            element = theElement;
            left = lt;
            right = rt;
        }

        // toString for BinaryNode
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append("Node:");
            sb.append(element);
            return sb.toString();
        }

    }

    /**
     * Insert into a bst tree; duplicates are allowed
     * The complexity of bstInsert depends on the tree.  If it is balanced the complexity is O(log n)
     * @param x the item to insert.
     */
    public void bstInsert(E x) {

        root = bstInsert(x, root);
    }

    /**
     * Internal method to insert into a subtree.
     * In tree is balanced, this routine runs in O(log n)
     * @param x the item to insert.
     * @param t the node that roots the subtree.
     * @return the new root of the subtree.
     */
    private BinaryNode<E> bstInsert(E x, BinaryNode<E> t) {
        if (t == null)
            return new BinaryNode<E>(x, null, null);
        int compareResult = x.compareTo(t.element);
        if (compareResult < 0) {
            t.left = bstInsert(x, t.left);
        } else {
            t.right = bstInsert(x, t.right);
        }
        return t;
    }

    /**
     * Determines if item is in tree
     * @param item the item to search for.
     * @return true if found.
     */
    public boolean contains(E item) {
        return contains(item, root);
    }

    /**
     * Internal method to find an item in a subtree.
     * This routine runs in O(log n) as there is only one recursive call that is executed and the work
     * associated with a single call is independent of the size of the tree: a=1, b=2, k=0
     *
     * @param x is item to search for.
     * @param t the node that roots the subtree.
     * @return node containing the matched item.
     */
    private boolean contains(E x, BinaryNode<E> t) {
        if (t == null)
            return false;

        int compareResult = x.compareTo(t.element);

        if (compareResult < 0)
            return contains(x, t.left);
        else if (compareResult > 0)
            return contains(x, t.right);
        else {
            return true;    // Match
        }
    }

}
