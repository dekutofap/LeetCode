public class Solution {
    public int findTargetSumWays(int[] nums, int S) {
        //Key point:�õݹ��
        if(nums.length == 0) return 0;
        return helper(nums,0,false,0,S) + helper(nums,0,true,0,S);  
    }
    public int helper(int[] nums,int sum,boolean tag,int index,int S){
        if(index >=0 && index <= nums.length-2){
            if(tag) sum+= nums[index];
            else sum-=nums[index];
            index++;
            //System.out.println("index "+index+" "+sum+"");
            return helper(nums,sum,false,index,S) + helper(nums,sum,true,index,S);
        }
        //System.out.println("index"+index+"......"+sum+"");
         //Key point:ע�⣬��Ϊ��Ҫ�����һ��node�������жϣ�����д������TreeNode�ݹ鲻̫һ��.....ע���������д��
        if(!tag && (sum-nums[index])==S || tag && (sum+nums[index] == S)) return 1;
        return 0;
    }
}