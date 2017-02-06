/**
 * Definition of TreeNode:
 * public class TreeNode {
 *     public int val;
 *     public TreeNode left, right;
 *     public TreeNode(int val) {
 *         this.val = val;
 *         this.left = this.right = null;
 *     }
 * }
 */
 
 
public class Solution {
    /**
     * @param root: The root of binary tree.
     * @return: Level order a list of lists of integer
     */
    public ArrayList<ArrayList<Integer>> levelOrder(TreeNode root) {
        // write your code here
        //Key point:�ؼ���Ҫ���ݹ�Ĺ��̼�˳��
        //��ȱ���???
        ArrayList<ArrayList<Integer>> list = new ArrayList<>();
        helper(list,root,0);
        return list;
    }
    public void helper(ArrayList<ArrayList<Integer>> list,TreeNode node,int depth){
        //Key point:Recursionִ�����������µģ���ʱ����Ϊ���������ϣ�����Ϊ��Щalgo��Ҫ����returnֵ->����Ȼ��Ҫ���¶��ϵĿ�����
        //Key point:List�����ɾ����Ԫ�أ�index���Զ��ı�ġ��൱�ڶ�̬����
        //�������depth��������ڵĻ������arrһ����OutOfBound����
        if(node != null){
            if(list.size()-1<depth) list.add(new ArrayList<Integer>());
            list.get(depth).add(node.val);
            helper(list,node.left,depth+1);
            helper(list,node.right,depth+1);
        }
    }
}