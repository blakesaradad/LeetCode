/* *****************************************************************************
 *  LeetCode 654 - Create a maximum binary tree
 *  My submission ran faster than 98% of other submissions, but I didn't do
 *  anything to optimize this, so I don't know why it's any better!
 *
 *  A Maximum binary tree is one where the maximum value of every subtree is
 *  stored in the root of the subtree.
 *
 *  The primary function in this program takes an array of integers as input,
 *  and returns the tree representation, as a maximum binary tree.
 *
 *  I rushed my coding, and introduced a few "silly" errors, like copy/pasting
 *  a line of code but forgetting to change the second line!  A nice reminder
 *  to proceed carefully, especially during interviews or on-the-job!
 *
 *  I also made the assumption that I needed to create a root node and pass that
 *  in to my function to work upon. In the end, I did not pass one in. I allowed
 *  the method to create a node, store the "max" value, and assign to the LEFT
 *  subchild whatever a recursive call would deliver, and last, I assigned to the
 *  RIGHT subchild whatever another recursive call would deliver, but with
 *  different parameters than the earlier call.
 *
 *  I also had to amend my "find" function, as it originally did a full array
 *  search 0..length-1, but it really needed to search a limited range. I
 *  parameterized it with 'start' and 'end' values.
 **************************************************************************** */

public class Tree_MaxTree {

    static class TreeNode {
        public int val = 0;
        public TreeNode left = null;
        public TreeNode right = null;
        public TreeNode prev = null;

        TreeNode(int data, TreeNode left, TreeNode right) {
            this.val = data;
            this.prev = null;
            this.left = left;
            this.right = right;
        }

        TreeNode(int data, TreeNode prev, TreeNode left, TreeNode right) {
            this.val = data;
            this.prev = prev;
            this.left = left;
            this.right = right;
        }

        TreeNode(int data) {
            this.val = data;
            this.prev = null;
            this.left = null;
            this.right = null;
        }

        TreeNode() {}

        public String toString() {
            String dataValue = "  data:" + val;
            String previous = prev == null ? "  prev=null" : "  prev=" + prev.val;
            String leftnode = left == null ? "  left=null" : "  left=" + left.val;
            String rightnode = right == null ? "  right=null" : "  right=" + right.val;
            return "\t" + dataValue + previous + leftnode + rightnode;
        }
    }

    // |||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||
    public TreeNode constructMaximumBinaryTree(int[] arr) {
        // Receive just an array, but all the helper function with start/end values that will live on the call stack
        return buildMaxTree(arr, 0, arr.length - 1);
    }

    // |||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||
    private TreeNode buildMaxTree(int[] arr, int start, int end) {
        if (start > end || end > arr.length - 1)
            return null;

        // For the array range we're given, locate the maximum value
        int maxLocation = find(arr, start, end);

        // Create the node we need, and pass in to the constructor the maximum value for our range.
        TreeNode newNode = new TreeNode(arr[maxLocation]);

        // Assign to the LEFT child the maximum of the LEFT side of our array (left side of the current max)
        newNode.left = buildMaxTree(arr, start, maxLocation - 1);

        // Assign to the RIGHT....
        newNode.right = buildMaxTree(arr, maxLocation + 1, end);

        // Deliver our completed node back to the caller. After all of the recursive calls have
        // been resolved, and the call stack is back to the initial call, this node is our tree's root.
        return newNode;
    }

    // |||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||
    private int find(int[] arr, int start, int end) {
        if (arr.length == 0)
            return -1;
        if (start < 0 || end >= arr.length)
            return -1;

        int maxFound = arr[start];
        int saveLoc = start;

        for (int idx = start; idx <= end; idx++) {
            if (arr[idx] > maxFound) {
                saveLoc = idx;
                maxFound = arr[idx];
            }
        }
        return saveLoc;
    }
// |||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||

    public static void main(String[] args) {
        Tree_MaxTree mt = new Tree_MaxTree();
        int[] arr = { 1, 4, 5, 3, 9, 2, 7, 8, 6 };
        TreeNode root = mt.constructMaximumBinaryTree(arr);
        System.out.println(root);
    }
}
