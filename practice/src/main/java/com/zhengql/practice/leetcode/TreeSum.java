package com.zhengql.practice.leetcode;

/**
 * 描述:
 * 示例 1：
 * <p>
 * 输入：root = [10,5,15,3,7,null,18], L = 7, R = 15
 * 输出：32
 * 示例 2：
 * <p>
 * 输入：root = [10,5,15,3,7,13,18,1,null,6], L = 6, R = 10
 * 输出：23
 *  
 * 提示：
 * 树中的结点数量最多为 10000 个。
 * 最终的答案保证小于 2^31。
 *
 * @author zhengql
 * @date 2019/6/13 17:49
 */
public class TreeSum {

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    public static int rangeSumBST(TreeNode root, int L, int R) {
        int res=0;
        if (root.left != null)
            rangeSumBST(root.left, L, R);
        if (root.right != null)
            rangeSumBST(root.right, L, R);
        if (root.val >= L && root.val <= R)
            res += root.val;
        return res;
    }


    public int rangeSumBST1(TreeNode root, int L, int R) {
        if (root == null) {
            return 0;
        }
        if (root.val < L) {
            return rangeSumBST(root.right, L, R);
        }
        if (root.val > R) {
            return rangeSumBST(root.left, L, R);
        }
        return root.val + rangeSumBST(root.left, L, R) + rangeSumBST(root.right, L, R);
    }

    public static void main(String[] args) {

    }
}
