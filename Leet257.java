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
    public List<String> binaryTreePaths(TreeNode root) {
        List<String> list = new ArrayList<>();
        helper(list,root,"");
        return list;
    }
    public void helper(List<String> list,TreeNode node,String path){
        if(node != null){
            if(node.left == null && node.right == null){
                list.add(path+node.val);   
            } else {
                helper(list,node.left,path+node.val+"->");
                helper(list,node.right,path+node.val+"->");
            }
        }
    }
    
}


//д����
/***
public List<String> binaryTreePaths(TreeNode root) {
        //Key point:similar to Permutations
        //��Ϊ��һֱ���µģ����û�ͷ����Permutations���������鷳��.....
        List<String> list = new ArrayList();
        List<Integer> item = new ArrayList();
        //Key point:�ǵ����ֳ�right��left��������ظ���һ��path
        helper(list,item,root);
        return list;
    }
    public void helper(List<String> list,List<Integer> item,TreeNode node){
        //Key point:д�������⣬���leftΪnull����right��Ϊnull����ôҲ����ϡ���������
        
        /**
        if(node == null){
            if(right == 0){
                String tmp = "";
                for(int i = 0;i <= item.size()-1;i++){
                    if(i != 0) tmp += "->"+item.get(i);
                    else tmp += item.get(i)+"";
                }
                list.add(tmp);
            }
        //Key point:�����ֵݹ�û��return�ģ����Ǽ���else��
        } else {
            item.add(node.val);
            helper(list,item,node.left,0);
            helper(list,item,node.right,1);
            item.remove(item.size()-1);
        }

        
        if(node != null){
            if(node.right == null && node.left == null){
                String tmp = "";
                for(int i = 0;i <= item.size()-1;i++){
                    if(i != 0) tmp += "->"+item.get(i);
                    else tmp += item.get(i)+"";
                }
                //Key point:��ΪҶ�ڵ�δ�����ݹ飬����Ҫ�ֶ���һ��
                //list.add(tmp);
                if(item.size() == 0) list.add(tmp+node.val);
                else list.add(tmp+"->"+node.val);
            } else {
                item.add(node.val);
                helper(list,item,node.left);
                helper(list,item,node.right);
                item.remove(item.size()-1);
            }
        }
    }

***/