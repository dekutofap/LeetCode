/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
public class Solution {
    public boolean isSymmetric(TreeNode root) {
        //Key:�����˼·��һ���tree˼·��̫һ������Щ��...
        //��������ж�current node����Ļ�����̫���жϡ���������һ�㣬�ж����ǵ�parent�ǲ�
        //�ݹ��ǰ��ղ㼶�жϵ�
        if(root == null) return true;
        return helper(root.left,root.right);
    }
    public boolean helper(TreeNode left,TreeNode right){
        if(left == null && right == null) return true;
        if(left == null || right == null) return false;
        if(left.val != right.val) return false;
        return helper(left.left,right.right) && helper(left.right,right.left);
    }
}