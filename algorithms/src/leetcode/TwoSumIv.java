package leetcode;

import java.util.HashSet;
import java.util.Set;

public class TwoSumIv {

    // https://leetcode.com/problems/two-sum-iv-input-is-a-bst

    public static boolean findTarget(TreeNode root, int k) {

        Set<Integer> s = new HashSet<>();

        help(s,root,k);

        for(int i : s) {
            if(s.contains(k-i) && k != 2*i)
                return true;
        }
        return false;
    }


    private static  void help(Set<Integer> set, TreeNode TreeNode, int k ) {

        if(TreeNode == null)
            return;

        if(TreeNode.val < k ) {
            help(set,TreeNode.right,k);
        }

        help(set,TreeNode.left,k);

        set.add(TreeNode.val);
    }

    public static TreeNode insertLevelOrder(int[] arr, int i)
    {
        TreeNode root = null;
        // Base case for recursion
        if (i < arr.length) {
            root = new TreeNode(arr[i]);

            // insert left child
            root.left = insertLevelOrder(arr, 2 * i + 1);

            // insert right child
            root.right = insertLevelOrder(arr, 2 * i + 2);
        }
        return root;
    }

    public static void main(String[] args) {

        TreeNode r = insertLevelOrder(new int[]{2,0,3,-4,1},0);

        System.out.println(findTarget(r,-1));
    }


}

class TreeNode {
  int val;
  TreeNode left;
  TreeNode right;
  TreeNode() {}
  TreeNode(int val) { this.val = val; }
  TreeNode(int val, TreeNode left, TreeNode right) {
      this.val = val;
      this.left = left;
      this.right = right;
  }
}
